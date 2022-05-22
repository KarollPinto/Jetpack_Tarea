package com.example.jetpackfirst
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackfirst.ui.theme.JetpackfirstTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackfirstTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding){
        OnboardingScreen(ButtonNext = { shouldShowOnboarding = false})
    } else {
        Greetings()
    }
}

@Composable
fun Greetings(names : List<String> = List(5) {"$it"}){
    Surface(color = MaterialTheme.colors.background){
        Column(modifier = Modifier.padding(vertical = 4.dp)){
            LazyColumn{
                item{ Text("\tNavigation Bar")}
                items(names){name->
                    Greeting(name)
                }

            }
        }
    }
}

@Composable
private fun Greeting(name: String) {
    val VarExpand = remember { mutableStateOf(false) }
    val expandDimen by animateDpAsState (
        targetValue = if (VarExpand.value) 48.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 1500
        )
    )
    Surface(color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column (
                modifier = Modifier
                    .weight(weight = 1f)
                    .padding(bottom = expandDimen)) {
                Text(text = "Hello World")
                Text(text = name)
            }
            OutlinedButton(onClick = {VarExpand.value =!VarExpand.value}) {
                Text(if(VarExpand.value) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    ButtonNext: ()-> Unit
) {

    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to my first show")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = ButtonNext
            ) {
                Text("Visit app")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320, uiMode = UI_MODE_NIGHT_YES)
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    JetpackfirstTheme {
        OnboardingScreen(ButtonNext={})
    }
}

@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    JetpackfirstTheme{
        MyApp()
    }
}

