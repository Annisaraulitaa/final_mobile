package com.D121211090.edition.ui.activities.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.D121211090.edition.data.models.QuranEdition
import com.D121211090.edition.ui.activities.detail.DetailActivity
import com.D121211090.edition.ui.theme.Purple80
import com.D121211090.edition.ui.theme.QuranEditionTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuranEditionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        CenterAlignedTopAppBar(
                            modifier = Modifier
                                .padding(12.dp),
                            title = {
                                Text(
                                    text = "Quran Edition App",
                                    fontWeight = FontWeight.SemiBold,
                                    style = MaterialTheme.typography.displaySmall
                                )
                            }
                        )
                        val mainViewModel: MainViewModel = viewModel(factory = MainViewModel.Factory)
                        ListQuranEditionScreen(mainViewModel.mainUiState)
                    }
                }
            }
        }
    }
}

@Composable
fun ListQuranEditionScreen(mainUiState: MainUiState, modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (mainUiState) {
            is MainUiState.Loading -> Text(text = "Loading...", fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
            is MainUiState.Error -> Text(text = "Error", fontSize = 16.sp,
                modifier = Modifier.align(Alignment.Center)
            )
            is MainUiState.Success -> QuranEditionList(
                editions = mainUiState.data,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun QuranEditionList(editions: List<QuranEdition>, modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier){
        items(editions){data ->
            QuranEditionItems(quranEdition = data)
        }
    }
}

@Composable
fun QuranEditionItems(quranEdition: QuranEdition){
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .padding(16.dp)
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
            .background(color = Purple80)
            .clickable {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("Quran Edition", quranEdition)
                context.startActivity(intent)
            }
    ) {
        Spacer(modifier = Modifier.height(2.dp))
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Text(
                text = quranEdition.identifier.toString(),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal
            )
        }
    }
}