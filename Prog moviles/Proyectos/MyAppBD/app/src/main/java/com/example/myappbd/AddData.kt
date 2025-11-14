package com.example.myappbd

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myappbd.ui.theme.MyAppBDTheme


class AddData : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppBDTheme {
                val navController = rememberNavController() // NavController
                NavHost( // NavHost
                    navController = navController,
                    startDestination = "add"
                ) {
                    composable("add") { AddDataToDatabase(LocalContext.current, navController.navigate("read")) }

                    composable("read") { ReadDataFromDatabase(LocalContext.current, navController) }

                    composable("update/{id}/{name}/{price}") { backStackEntry ->
                        val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                        val name = backStackEntry.arguments?.getString("name")
                        val price = backStackEntry.arguments?.getString("price")?.toDoubleOrNull()
                        UpdateDataOnDatabase(
                            context = LocalContext.current,
                            navController = navController,
                            id = id,
                            name = name,
                            price = price
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AddDataToDatabase(
    context: Context,
    leerProductos:() -> Unit = {}
) {
    // variables for text field
    val productName = remember {
        mutableStateOf(TextFieldValue())
    }
    val productPrice = remember {
        mutableStateOf(TextFieldValue())
    }

    // column for displaying text fields
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val dbHandler = DBHandler(context)
        Text(
            text = "SQLite Database in Android",
            color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // text field for product name
        TextField(
            value = productName.value,
            onValueChange = { productName.value = it },
            placeholder = { Text(text = "Enter your product name") },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(20.dp))

        // text field for product price
        TextField(
            value = productPrice.value,
            onValueChange = { productPrice.value = it },
            placeholder = { Text(text = "Enter your product price") },
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(15.dp))

        // button to add a new prodcut
        Button(onClick = {
            // call function to add new proudct
            dbHandler.addNewProduct(
                productName.value.text,
                productPrice.value.text.toDoubleOrNull() ?: 0.0
            )
            Toast.makeText(context, "Product Added to Database", Toast.LENGTH_SHORT).show()
            productName.value = TextFieldValue()
            productPrice.value = TextFieldValue()
        }) {
            Text(text = "Add Product to Database", color = Color.White)
        }

        Button(
            onClick = { leerProductos() },
            modifier = Modifier.padding(top = 24.dp)
        ) {
            Text("Veiw list products")
        }
    }
}
