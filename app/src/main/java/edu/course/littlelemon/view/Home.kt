package edu.course.littlelemon.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import edu.course.littlelemon.R
import edu.course.littlelemon.navigation.ProfileDestination
import edu.course.littlelemon.room.MenuItemRoom
import edu.course.littlelemon.ui.theme.*
import java.text.DecimalFormat

@Composable
fun Home(navController: NavController, items: List<MenuItemRoom>) {
    Column(
        Modifier.fillMaxSize()) {
        Column(
            Modifier.fillMaxWidth()
                .fillMaxHeight(0.3f)
                .background(PrimaryColor1)
                .padding(15.dp)
        ) {
            Text(
                "Little Lemon",
                Modifier
                    .fillMaxWidth()
                    .background(PrimaryColor1)
                    .padding(top = 5.dp),
                textAlign = TextAlign.Start,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor2,
            )
            Text(
                "Chicago",
                Modifier
                    .fillMaxWidth()
                    .background(PrimaryColor1)
                    .padding(vertical = 5.dp),
                textAlign = TextAlign.Start,
                fontSize = 20.sp,
                color = Color.White,
            )
            Row( Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    "We are a family-owned Mediterranean restaurant, focused on traditional recipes served with a modern twist",
                    Modifier
                        .fillMaxWidth(0.5f)
                        .background(PrimaryColor1)
                        .padding(vertical = 5.dp),
                    maxLines = 5,
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    color = Color.White,
                )
                Image(
                    painterResource(R.drawable.hero_image),
                    contentDescription = "Little Lemon Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.9f)
                        .background(color = PrimaryColor1, shape = RoundedCornerShape(8.dp))
                        .align(Alignment.Top)
                )
            }
        }
        Column(
            Modifier.fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
                .padding(15.dp)
        ) {
            Column(
                Modifier.fillMaxWidth()
                    .fillMaxHeight(0.3f)
            ) {
                Spacer(modifier = Modifier.padding(vertical = 20.dp))
                Text(
                    "ORDER FOR DELIVERY !",
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CardButton("Starters", {})
                    CardButton("Mains", {})
                    CardButton("Desserts", {})
                    CardButton("Drinks", {})
                }
            }
            MenuItems(items)
//            Spacer(modifier = Modifier.padding(vertical = 20.dp))
            Button(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
                shape = RoundedCornerShape(20),
                colors = ButtonColors(PrimaryColor2, Color.Black, PrimaryColor1, PrimaryColor2),
                onClick = {
                    navController.navigate(ProfileDestination)
                }) {
                Text("Profile")
            }
        }
    }
}

@Composable
fun MenuItems(menuItems: List<MenuItemRoom>) {
    LazyColumn (Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.7f)) {
        items( menuItems ) { menuItem ->
            MenuItem(menuItem)
        }

    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItem(menu: MenuItemRoom) {
    val price = menu.price
    val dec = DecimalFormat("#.00")
    val priceFormated = dec.format(price)
    Column() {
        Text(menu.title, Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
            textAlign = TextAlign.Start,
            fontSize = 16.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Black,)
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)) {
            Column(Modifier.fillMaxWidth(0.5f)) {
                Text(
                    menu.description,
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                    color = Color.Black,
                )
                Text("$$priceFormated", Modifier
                    .fillMaxWidth(),
                    textAlign = TextAlign.Start,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,)
            }
            GlideImage(
                model = menu.image,
                contentDescription = menu.title
            )
        }

    }
}

@Composable
fun CardButton( texto : String, onClick : () -> Unit ) {
    Button( onClick = onClick,
        colors = ButtonColors(containerColor = HighLightColor1, contentColor = PrimaryColor1,
             disabledContainerColor = Color.Gray, disabledContentColor = Color.LightGray),
        contentPadding = PaddingValues(vertical = 0.dp, horizontal = 5.dp),
        modifier = Modifier
            .background(shape = RoundedCornerShape(16.dp), color = HighLightColor1)) {
        Text( texto, modifier = Modifier
            .background(HighLightColor1),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            color = PrimaryColor1, )
    }
}

//@Composable
//@Preview(showBackground = true)
//fun HomePreview() {
//    val navController = rememberNavController()
//    Home(navController, emptyList())
//}

//@Composable
//@Preview(showBackground = true)
//fun CardButtonPreview() {
//    CardButton("Starters", onClick = {})
//}


@Composable
@Preview(showBackground = true)
fun MenuItemPreview() {
    val item = MenuItemRoom(1, "Greek Salad", 10.0, "The famous greek salad of crispy lettuce, peppers, olives, our Chicago.",
        "https://github.com/Meta-Mobile-Developer-PC/Working-With-Data-API/blob/main/images/greekSalad.jpg?raw=true",
        "starters"
    )
    MenuItem(item )
}