package com.hfad.marketsuperapplication


import android.os.Bundle
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.hfad.marketsuperapplication.ui.theme.MyTheme
import com.hfad.marketsuperapplication.ui.theme.lightGreen
//import coil.compose.rememberAsyncImagePainter
//import coil.compose.AsyncImagePainter
import coil.request.ImageRequest


val namesList : ArrayList<String> = arrayListOf("navid", "reza" , "ladan" , "ardalan")
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            MarketSuperApplicationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    val name :String = "nice"
//                    GreetingText(name)
////                    GreetingButton()
//                }
//            }
            //MainScreen(viewModel)
            //GreetingList()
            //TextField(value = "name", onValueChange = {})
            MyTheme {
                UserApplication()
                //userProfileDetailScreen()
            }

        }
    }
}

@Composable
fun MainScreen3(navcontroller : NavHostController){

    Scaffold(
        topBar = { AppBar() }
    ) { innerPadding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {

            LazyColumn {
//                ProfileCard("John Doe",painterResource(id = R.drawable.profilepicture))
//                ProfileCard("Navid Rasouli",painterResource(id = R.drawable.profilepic2))
//                for( i in superUserProfileList){
//                    ProfileCard(name = i.name, painter = i.drawableId , isOnline = i.status )
                items(superUserProfileList){
                    user->
                    ProfileCard(user , 72.dp){
                        navcontroller.navigate("userDetails/${user.id}")

                    }
                }

                }
            }
        }
    }

@Composable
fun userProfileDetailScreen(id:Int){
    val userProfile = superUserProfileList.first{userProfile -> userProfile.id == id}
    Scaffold(
        topBar = { AppBar() }
    ) { innerPadding ->
        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)) {
                    ProfileCard(userProfile, 240.dp, isDtail = true){}

        }
    }

}

@Composable
fun UserApplication(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "userList" ){
        composable("userList"){
            MainScreen3(navController)
        }
        composable("userDetails/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.IntType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt("userId") ?: 0
            userProfileDetailScreen(userId)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AppBar(){
    TopAppBar(title = { Text(text = "Message application users") }
    , navigationIcon = {IconButton(onClick = { /* handle navigation */ })
        {
        Icon(Icons.Filled.Home, contentDescription = "Home")
    }
    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary,titleContentColor = MaterialTheme.colorScheme.onPrimary,navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,actionIconContentColor = MaterialTheme.colorScheme.onPrimary)
    )

}


@Composable
fun ProfileCard(userProfile: UserProfile, imageSize: Dp , isDtail:Boolean=false  , clickAction: ()-> Unit) {
//    CompositionLocalProvider(value = LocalContentAlpha ){}
    Card (modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth()
        .wrapContentHeight(align = Alignment.Top)
        .clickable(onClick = { clickAction.invoke() })
        //, shape = MaterialTheme.shapes.medium
        //, border = BorderStroke(width = 2.dp , color = Color.Green)
        , colors = CardDefaults.cardColors(containerColor = Color.LightGray)
    , elevation = CardDefaults.cardElevation(8.dp)

    ){

        if(isDtail){
            Column (modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){
                ProfilePircture(userProfile.drawableId ,userProfile.status, imageSize)
                ProfileContent(userProfile.name,userProfile.status)
            }
        }
        else {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                ProfilePircture(userProfile.drawableId, userProfile.status, imageSize)
                ProfileContent(userProfile.name, userProfile.status)
            }
        }



    }
}

@Composable
fun ProfileContent(name: String, isOnline: Boolean) {

    var isactive = if(isOnline){
        "Active Now"
    }
    else{
        "Offline"
    }
    Column(modifier = Modifier
        .padding(8.dp)
        //.fillMaxWidth()
    ) {
        Text(text = name, color = Color.Black)

        Text(text = isactive , style = MaterialTheme.typography.bodyMedium , modifier = Modifier.alpha(0.5f)
        , color = Color.Black)
    }
}

@Composable
fun ProfilePircture(PicId: Any? , isOnline :Boolean , imageSize :Dp) {

    var borderOnline = if(isOnline){
        BorderStroke(width = 2.dp , color = MaterialTheme.colorScheme.lightGreen)
    }
    else{
        BorderStroke(width = 2.dp , color = MaterialTheme.colorScheme.error)
    }
    Card(shape = CircleShape ,
        border = borderOnline
        , modifier = Modifier.padding(20.dp)

    ) {


        AsyncImage(
            //model = "https://cdn.tabnak.ir/files/fa/news/1405/2/16/2297965_714.png"
            //model ="https://cdn.farsnews.ir/guest/44676ffa3ec04f909cb562284d24e1fc?centerCrop=true"
            model = PicId
            ,contentDescription = null,
            modifier = Modifier.size(imageSize)
        )
        //val painter = rememberAsyncImagePainter(model = ImageRequest.Builder(LocalContext.current).data(PicId)) // PicId is the resource id here.build())

        //ImageView()
        //ImageView.load()
//        Image(
//            //painter = painterResource(id = R.drawable.profilepicture),
//          //painter = rememberAsyncImagePainter("https://example.com/image.png"),
//            //painter = painter
//            painterResource(id =PicId )
//            //,painter = painter,
//            ,contentDescription = null
//            ,modifier = Modifier.size(72.dp)
//        )
    }

}


//@Composable
//fun MainScreen(){
//    Surface (color = Color.DarkGray, modifier = Modifier.fillMaxSize()){
////        Surface(color = Color.Magenta,
////            modifier = Modifier.wrapContentSize()) {
////            Text(text= "Wrapped content Size",
////                style = MaterialTheme.typography.headlineLarge)
////            Text(text= "Wrapped content Size",
////                style = MaterialTheme.typography.headlineLarge)
////        }
////        Row(modifier = Modifier.fillMaxSize()
////            , horizontalArrangement = Arrangement.SpaceBetween
////           , verticalAlignment = Alignment.CenterVertically) {
//////            Surface(color = Color.Magenta,
//////                modifier = Modifier
//////                    .width(60.dp)
//////                    .height(600.dp)) {
//////
//////            }
//////            Surface(color = Color.Green,
//////                modifier = Modifier
//////                    .width(60.dp)
//////                    .height(600.dp)) {
//////
//////            }
////            HorizentalColorBar(color = Color.Magenta)
////            HorizentalColorBar(color = Color.Green)
////            HorizentalColorBar(color = Color.Blue)
////            HorizentalColorBar(color = Color.Red)
////            HorizentalColorBar(color = Color.Yellow)
////
////
////
////        }
//
////    Column(modifier = Modifier.fillMaxSize()
////            , verticalArrangement = Arrangement.SpaceBetween
////           , horizontalAlignment = Alignment.CenterHorizontally) {
//////            Surface(color = Color.Magenta,
//////                modifier = Modifier
//////                    .width(60.dp)
//////                    .height(600.dp)) {
//////
//////            }
//////            Surface(color = Color.Green,
//////                modifier = Modifier
//////                    .width(60.dp)
//////                    .height(600.dp)) {
//////
//////            }
////            HorizentalColorBar(color = Color.Magenta)
////            HorizentalColorBar(color = Color.Green)
////            HorizentalColorBar(color = Color.Blue)
////            HorizentalColorBar(color = Color.Red)
////            HorizentalColorBar(color = Color.Yellow)
////
////
////
////        }
////        Column(modifier = Modifier.fillMaxSize()
////            , verticalArrangement = Arrangement.SpaceEvenly
////            , horizontalAlignment = Alignment.CenterHorizontally) {
////            Row(modifier = Modifier.fillMaxWidth()){
////                HorizentalColorBar(color = Color.Magenta)
////                HorizentalColorBar(color = Color.Green)
////            }
////
////            HorizentalColorBar(color = Color.Blue)
////            HorizentalColorBar(color = Color.Red)
////            HorizentalColorBar(color = Color.Yellow)
////
////
////
////        }
//        val greetingListState = remember { mutableStateListOf<String> ("John", "Amanda")}
//        val txtFieldValue = remember {
//            mutableStateOf("")
//        }
//
//            GreetingList(greetingListState
//                , {greetingListState.add(txtFieldValue.value)}
//            ,txtFieldValue.value, {newName -> txtFieldValue.value= newName});
//
//
//
//
//    }
//}

//@Composable
//fun GreetingList(nameList : List<String>, buttonClick : ()-> Unit, newName:String
//                 , OnUpdate : (newName:String)->Unit){
//
//    Column(modifier = Modifier.fillMaxSize()
//    , verticalArrangement = Arrangement.SpaceEvenly
//    , horizontalAlignment = Alignment.CenterHorizontally) {
//        for(i in nameList){
//            Greeting(name = "Hello $i !")
//        }
//        TextField(value = newName, onValueChange =  OnUpdate)
//        Button(onClick = buttonClick
//        ) {
//            Text(text = "Addd")
//        }
//    }
//}

//@Composable
//fun MainScreen(viewModel: MainViewModel= MainViewModel()) {
//    val newNameStateContent = viewModel.textFiedState.observeAsState("")
//
//    GreetingMessage(newName = newNameStateContent.value, OnUpdate = {
//        newName -> viewModel.onTextChange(
//            newName
//        )
//    })
//
//}

//@Composable
//fun GreetingMessage(  newName:String
//                 , OnUpdate : (newName:String)->Unit){
//
//
//    Column(modifier = Modifier.fillMaxSize()
//        , verticalArrangement = Arrangement.SpaceEvenly
//        , horizontalAlignment = Alignment.CenterHorizontally) {
//
//        TextField(value = newName, onValueChange = OnUpdate)
//        Button(onClick = { }
//        ) {
//            Text(text = newName)
//            Text(text ="" )
//
//        }
//    }
//}


@Composable
fun Greeting(name:String){
    Text(text = name)
}



@Composable
fun HorizentalColorBar(color:Color=Color.Magenta){
    Surface(color = color,
        modifier = Modifier
            .width(100.dp)
            .height(100.dp)) {

    }
}

@Composable
fun GreetingText(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        //text = "hello this is test testtr navid",
        modifier = modifier
            .width(200.dp)
            .height(240.dp)
//        modifier = modifier.size(80.dp)
//        modifier = modifier.fillMaxWidth(0.90f)

            .clickable { }
            .padding(24.dp)
//            .width(200.dp)
//            .height(240.dp)
//        , style = TextStyle(color =  Color.Blue
//            ,fontWeight =  FontWeight.Bold
//            , fontSize = TextUnit(50f, TextUnitType.Sp)
//        )
        , style = MaterialTheme.typography.labelLarge


    )


}

@Composable
fun GreetingButton(){
    Button(onClick = { /*TODO*/ }){
        GreetingText(name = "navid")
        GreetingText(name = "reza")

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyTheme {
        val name :String = "nice"
//        GreetingText(name)
//        GreetingButton()
        //MainScreen()
        //GreetingList()
        //userProfileDetailScreen()

    }
}