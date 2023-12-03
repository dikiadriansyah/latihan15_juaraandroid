package com.example.latihan15_juaraandroid

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.latihan15_juaraandroid.data.Programming
import com.example.latihan15_juaraandroid.data.ProgrammingRepository
import com.example.latihan15_juaraandroid.ui.theme.Latihan15_juaraandroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Latihan15_juaraandroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
                ) {
                    ProgramApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgramTopAppBar(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
    Text(
        text = stringResource(R.string.app_name),
        style = MaterialTheme.typography.displayLarge)
    })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgramApp() {
    Scaffold(topBar={
            ProgramTopAppBar()
        }){it ->
        LazyColumn(contentPadding = it, modifier = Modifier.fillMaxSize()){
            items(ProgrammingRepository.programming){
                ProgrammingItem(program = it, modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small))
                )
            }
        }
    }
}

@Composable
fun ProgrammingItem(
    program: Programming,
    modifier: Modifier = Modifier
){
    var expanded by remember{ mutableStateOf(false) }

    Card(
modifier = Modifier.background(color = MaterialTheme.colorScheme.tertiaryContainer)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .animateContentSize(animationSpec = spring(
                    dampingRatio = Spring.DampingRatioNoBouncy,
                    stiffness = Spring.StiffnessMedium
                ))
        ){

                Text(
                    text = stringResource(program.dateProg),
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small)),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = stringResource(program.name),
                    style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small), bottom = dimensionResource(R.dimen.padding_small))
                )

            Image(
                modifier = modifier
                    .fillMaxSize()
                    .clickable {
//                onClick
                        expanded = !expanded

                    },
                painter = painterResource(program.imgResourceId),
                contentDescription = null
            )



            if(expanded){
                ProgramDes(program.descripText, modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    top = dimensionResource(R.dimen.padding_small),
                    end = dimensionResource(R.dimen.padding_medium),
                    bottom = dimensionResource(R.dimen.padding_medium)
                ))
            }
        }
    }
}


@Composable
fun ProgramDes(
    @StringRes progDes: Int,
    modifier: Modifier = Modifier
){
    Column(modifier = modifier){
        Text(text = stringResource(progDes), style = MaterialTheme.typography.bodyLarge)
    }

}


@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ProgramPreview(){
    val pro = Programming(
        R.string.dat1,
        R.string.title1,
        R.drawable.php,
        R.string.desc1
    )
    Latihan15_juaraandroidTheme {
        ProgrammingItem(program = pro)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Latihan15_juaraandroidTheme {
        ProgramApp()
    }
}