package com.example.myappbd

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myappbd.ui.theme.MyAppBDTheme

class ReadData : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppBDTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "read") {
                    composable("add") { AddDataToDatabase(LocalContext.current, navController) }

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
fun ReadDataFromDatabase(
    context: Context,
    navController: NavController
) {
    val dbHandler = DBHandler(context)
    val productList = remember { dbHandler.readProducts().toMutableStateList() }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            itemsIndexed(productList) { index, product ->
                Card(
                    modifier = Modifier.padding(8.dp).fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = product.productName,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "Product price : " + product.productPrice,
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Row {
                            Button(
                                onClick = {
                                    dbHandler.deleteProduct(product.productId)
                                    productList.remove(product)
                                    Toast.makeText(context, "Product Deleted", Toast.LENGTH_SHORT).show()
                                }
                            ) {
                                Text(text = "Delete")
                            }

                            Spacer(modifier = Modifier.width(10.dp))

                            Button(
                                onClick = { navController.navigate("update/${product.productId}/${product.productName}/${product.productPrice}") }
                            ) {
                                Text(text = "Update")
                            }
                        }
                    }
                }
            }
        }

        Button(
            onClick = { navController.navigate("add") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text("Add New Product")
        }
    }
}
