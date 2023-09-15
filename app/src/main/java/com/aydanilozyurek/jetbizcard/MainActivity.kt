package com.aydanilozyurek.jetbizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.aydanilozyurek.jetbizcard.ui.theme.JetBizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBizCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(){

    val buttonClickedState = remember {
        mutableStateOf(false)
    }

    Surface(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .width(200.dp)
                .height(390.dp)
                .padding(12.dp),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
                CreateImageProfile()
                Divider( modifier = Modifier.padding(top = 12.dp))
                CreateInfo()

                Button(
                    onClick = {
                            // toggle
                              buttonClickedState.value = !buttonClickedState.value

                }, shape = RoundedCornerShape(3.dp)) {
                    Text(text ="PORTFOLIO",
                        style = MaterialTheme.typography.bodyMedium
                        )
                }
                
                if(buttonClickedState.value){
                    Content()
                }else{
                    Box {
                        
                    }
                }
            }

        }
    }
}


@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, Color.LightGray)
        ) {
                Portfolio(data = listOf("Project1","Project2", "Project2"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{
        items(data){
            item ->
            Card(modifier = Modifier
                .padding(13.dp)
                .fillMaxWidth(),
                shape = RectangleShape,
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    CreateImageProfile(modifier = Modifier
                        .size(100.dp)
                        .background(Color.Transparent).align(alignment = CenterVertically))
                    Column(modifier = Modifier
                        .padding(7.dp)
                        .align(alignment = Alignment.CenterVertically)) {
                        Text(text = item,
                            fontWeight = FontWeight.Bold)
                        Text(text = "A great project", style = MaterialTheme.typography.bodyMedium)
                    }
                }

            }

        }
    }
}


@Composable
private fun CreateInfo() {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(
            text = "Ayda O.",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = "Android Compose Developer",
            modifier = Modifier.padding(3.dp),
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = "@compose",
            modifier = Modifier.padding(3.dp),
            style = MaterialTheme.typography.titleMedium,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
private fun CreateImageProfile(modifier : Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.LightGray),
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
    ) {
        Image(
            painter = painterResource(id = R.drawable.image_placeholder),
            contentDescription = "image holder",
            modifier = modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetBizCardTheme {
        CreateBizCard()
    }
}