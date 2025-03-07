package edu.course.littlelemon

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import edu.course.littlelemon.model.MenuItemNetwork
import edu.course.littlelemon.model.MenuNetworkData
import edu.course.littlelemon.navigation.NavigationComposable
import edu.course.littlelemon.room.AppDatabase
import edu.course.littlelemon.room.MenuItemDao
import edu.course.littlelemon.room.MenuItemRoom
import edu.course.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HeaderValueParam
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CustomHttpLogger(): Logger {
    override fun log(message: String) {
        Log.d("LITTLE-LEMON", message) // Or whatever logging system you want here
    }
}

private val URL = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"

private val client = HttpClient(Android) {
    install(ContentNegotiation) {
        json(contentType = ContentType("text", "plain",
            listOf(HeaderValueParam("charset", "utf-8")))
        )
    }
    install(Logging) {
        logger = CustomHttpLogger()
        level = LogLevel.ALL
    }
}

suspend fun getItemsFromAPIRest() : List<MenuItemNetwork> {
    val resposta : MenuNetworkData = client.get(URL).body()
    return resposta.menu
}

class MenuItemRepository( private val dao : MenuItemDao ) {

    suspend fun dataSynchronize() {
        val items = getItemsFromAPIRest()
        Log.i("LITTLE-LEMON","Items carregados da API Rest: $items")
        dao.insertAll( items.map({ it -> it.toMenuItemRoom() }) )
    }

    suspend fun getItems() : List<MenuItemRoom> {
        return dao.getAll()
    }
}


class MainActivity : ComponentActivity() {



    private val _menuItems =
        MutableStateFlow<List<MenuItemRoom>>(emptyList())
    val menuItems: StateFlow<List<MenuItemRoom>> = _menuItems

//    private fun retrieveItems(dao: MenuItemDao) {
//        Log.i("LITTLE-LEMON", "retrieveItems() acionado")
//        lifecycleScope.launch {
//            Log.i("LITTLE-LEMON", "Carregando dados da rede")
//            val resposta : MenuNetworkData = client.get(URL).body()
//            Log.i("LITTLE-LEMON","Resposta convertida: $resposta")
//            menuItems.value = resposta.menu
//
//            Log.i("LITTLE-LEMON", "Menu:")
//            Log.i("LITTLE-LEMON", menuItems.value.toString())
//        }
//    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java,
            "menu-items").fallbackToDestructiveMigration().build()
        val dao = db.menuItemDao()
        val repository = MenuItemRepository( dao )
        Log.i("LITTLE-LEMON", "Sincronizando dados")
        lifecycleScope.launch {
            _menuItems.value = repository.getItems()
            repository.dataSynchronize()
            Log.i("LITTLE-LEMON","Dados sincronizados e salvos no database")
            _menuItems.value = repository.getItems()
            Log.i("LITTLE-LEMON","Resposta convertida: ${menuItems.value}")
        }
        setContent {
            val allMenuItems by menuItems.collectAsState()
            LittleLemonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppMain(
                        items = allMenuItems,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppMain(items: List<MenuItemRoom>, modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavigationComposable( navController, items )
}

@Preview(showBackground = true)
@Composable
fun AppMainPreview() {
    LittleLemonTheme {
        AppMain(emptyList())
    }
}