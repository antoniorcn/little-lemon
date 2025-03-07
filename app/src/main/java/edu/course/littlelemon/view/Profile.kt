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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import edu.course.littlelemon.navigation.OnboardingDestination
import edu.course.littlelemon.R
import edu.course.littlelemon.ui.theme.*

@Composable
fun Profile(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("LittleLemonPrefs", Context.MODE_PRIVATE)
    val firstName = sharedPreferences.getString("firstName", "");
    val lastName = sharedPreferences.getString("lastName", "");
    val email = sharedPreferences.getString("email", "");
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
            "Profile Screen",
            Modifier
                .fillMaxWidth()
                .background(PrimaryColor1)
                .padding(vertical = 35.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Column(modifier = Modifier
            .background(PrimaryColor2, shape = RoundedCornerShape(16.dp))
            .fillMaxWidth()
            .padding(horizontal = 15.dp))
        {
            Text(
                "User Information",
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 35.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = PrimaryColor1,
            )
            Text(
                    "First Name: $firstName",
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 35.dp),
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = PrimaryColor1,
            )
            Text(
                "Last Name: $lastName",
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 35.dp),
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = PrimaryColor1,
            )
            Text(
                "Email: $email",
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 35.dp),
                textAlign = TextAlign.Start,
                fontSize = 16.sp,
                color = PrimaryColor1,
            )
        }
        Spacer(modifier = Modifier.padding(vertical = 20.dp))
        Button(modifier = Modifier
            .fillMaxWidth(),
            shape = RoundedCornerShape(20),
            colors = ButtonColors(PrimaryColor2, Color.Black, PrimaryColor1, PrimaryColor2),
            onClick = {
                sharedPreferences
                    .edit()
                    .putString("firstName", null)
                    .putString("lastName", null)
                    .putString("email", null)
                    .apply()
                Toast.makeText(context, "Logged Out", Toast.LENGTH_LONG).show()
                navController.navigate(OnboardingDestination)
            }) {
            Text("Logout")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfilePreview() {
    val navController = rememberNavController()
    Profile(navController)
}