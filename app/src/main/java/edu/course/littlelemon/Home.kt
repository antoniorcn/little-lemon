package edu.course.littlelemon

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Home(navController: NavController) {
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
            "Home Screen",
            Modifier
                .fillMaxWidth()
                .background(PrimaryColor1)
                .padding(vertical = 35.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.padding(vertical = 40.dp))
        Button(modifier = Modifier
            .fillMaxWidth(),
            shape = RoundedCornerShape(20),
            colors = ButtonColors(PrimaryColor2, Color.Black, PrimaryColor1, PrimaryColor2),
            onClick = {
                navController.navigate(ProfileDestination)
            }) {
            Text("Profile")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomePreview() {
    val navController = rememberNavController()
    Home(navController)
}