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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myappbd.ui.theme.MyAppBDTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppBDTheme {
                Column {
//                    AddDataToDatabase(LocalContext.current)
                    ReadDataFromDatabase(LocalContext.current)
                }
            }
        }
    }
}

@Composable
fun ReadDataFromDatabase(context: Context) {
    val dbHandler = DBHandler(context)
    // initialize array list
    val productList = remember { dbHandler.readProducts().toMutableStateList() }

    Column {
        Spacer(modifier = Modifier.height(50.dp))

        // create a lazy column for displaying a list view.
        LazyColumn {
            // set data for each item of listview
            itemsIndexed(productList) { index, _ ->
                // create card for each item
                Card(
                    modifier = Modifier.padding(8.dp),
                    elevation = CardDefaults.cardElevation(6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(8.dp).fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            text = productList[index].productName,
                            color = Color.Black, textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Text(
                            text = "Product price : " + productList[index].productPrice,
                            modifier = Modifier.padding(4.dp),
                            color = Color.Black, textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                        Button(
                            onClick = {
                                dbHandler.deleteProduct(productList[index].productId)
                                productList.removeAt(index)
                                Toast.makeText(context, "Product Deleted", Toast.LENGTH_SHORT).show()
                            }
                        ) {
                            Text(text = "Delete")
                        }

                        Spacer(modifier = Modifier.width(5.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun AddDataToDatabase(
    context: Context
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
        // initialize database handler
//        val dbHandler: DBHandler = DBHandler(context)
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
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyAppBDTheme {
        val context = LocalContext.current
        Column {
            AddDataToDatabase(context)
            ReadDataFromDatabase(context)
        }
    }
}
