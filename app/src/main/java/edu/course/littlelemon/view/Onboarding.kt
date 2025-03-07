package edu.course.littlelemon.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.course.littlelemon.navigation.HomeDestination
import edu.course.littlelemon.R
import edu.course.littlelemon.ui.theme.*

@Composable
fun Onboarding(navController: NavController) {
    var firstName by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemonPrefs", Context.MODE_PRIVATE)

    Column(Modifier.padding(15.dp)) {
        Image(
            painterResource(R.drawable.logo),
            "Logo",
            Modifier
                .fillMaxWidth()
                .fillMaxSize(0.12f)
                .padding(15.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            "Let's get to know you",
            Modifier
                .fillMaxWidth()
                .background(PrimaryColor1)
                .padding(vertical = 35.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.White,
        )
        Text(
            "Personal information",
            Modifier
                .padding(vertical=25.dp, horizontal = 15.dp)
                .align(Alignment.Start),
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(firstName, onValueChange = {firstName = it},
            label = {Text("First name")},
            modifier = Modifier.fillMaxWidth().padding(vertical=10.dp))
        OutlinedTextField(lastName, onValueChange = {lastName = it},
            label = {Text("Last name")},
            modifier = Modifier.fillMaxWidth().padding(vertical=10.dp))
        OutlinedTextField(email, onValueChange = {email = it},
            label = {Text("Email")},
            modifier = Modifier.fillMaxWidth().padding(vertical=10.dp))
        Spacer(modifier = Modifier.padding(vertical=40.dp))
        Button(modifier = Modifier
            .fillMaxWidth(),
            shape = RoundedCornerShape(20),
            colors = ButtonColors(PrimaryColor2, Color.Black, PrimaryColor1, PrimaryColor2),
        onClick = {
            if (firstName.isNotBlank() && lastName.isNotBlank() && email.isNotBlank()) {
                sharedPreferences
                    .edit()
                    .putString("firstName", firstName)
                    .putString("lastName", lastName)
                    .putString("email", email)
                    .apply()
                Toast.makeText(context, "Registration successful !!!", Toast.LENGTH_LONG).show()
                navController.navigate(HomeDestination)
            } else {
                Toast.makeText(context, "Registration unsuccessful. Please enter all data.", Toast.LENGTH_LONG).show()
            }

        }){
            Text("Register")
        }

    }
}

@Composable
@Preview(showBackground = true)
fun OnboardingPreview() {
    val navController = rememberNavController()
    Onboarding(navController)
}