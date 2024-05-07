package com.lamz.salamcafe.ui.screen.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.lamz.salamcafe.R
import com.lamz.salamcafe.model.OrderMenu
import com.lamz.salamcafe.ui.common.UiState
import com.lamz.salamcafe.ui.components.AllMenuItem
import com.lamz.salamcafe.ui.components.SearchBar
import com.lamz.salamcafe.ui.components.TopMenuItem
import com.lamz.salamcafe.ui.navigation.Screen
import com.lamz.salamcafe.ui.theme.FEJetpackComposeTaskTheme
import com.lamz.salamcafe.ui.theme.myColor3
import com.lamz.salamcafe.ui.theme.myColor4
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewmodel: HomeViewmodel = koinViewModel(),
    navigateToDetail: (Long) -> Unit,
) {
    viewmodel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> viewmodel.getAllCoffeMenu()
            is UiState.Success -> {
                HomeContent(
                    orderMenuMenus = uiState.data,
                    modifier = modifier,
                    navigateToDetail = navigateToDetail
                )
            }

            is UiState.Error -> Log.d(
                "Error Get Data Coffe",
                "Can't get data because: ${uiState.errorMessage}"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    orderMenuMenus: List<OrderMenu>,
    navigateToDetail: (Long) -> Unit,
    viewmodel: HomeViewmodel = koinViewModel(),
) {
    val query by viewmodel.query

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            // Gambar header
            Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Home",
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                        color = Color.White
                    )
                },
                modifier = Modifier
                    .align(Alignment.TopCenter),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color.Black.copy(alpha = 0.5f))
            )
            SearchBar(
                query = query,
                onQueryChange = viewmodel::searchCoffeMenu,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .align(Alignment.Center)
            )
        }
        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(myColor4),
        ) {
            Text(
                text = "Top Menu Coffe",
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }

        LazyRow(
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 4.dp, vertical = 4.dp),
        ) {
            items(orderMenuMenus) { data ->

                if (data.cafeShop.isTop == true) {
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .clickable { navigateToDetail(data.cafeShop.id) }
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(myColor3)
                    ) {
                        TopMenuItem(
                            image = data.cafeShop.image,
                            title = data.cafeShop.title,
                            price = data.cafeShop.price,
                            modifier = Modifier
                                .padding(8.dp)
                                .width(120.dp)
                                .align(Alignment.Center)
                                .clickable { navigateToDetail(data.cafeShop.id) }
                        )
                    }
                }
            }
        }

        Box(
            modifier = modifier
                .fillMaxWidth()
                .background(myColor4),
        ) {
            Text(
                text = "Best-selling menu",
                style = TextStyle(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

        }
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(end = 4.dp, start = 4.dp, bottom = 4.dp),

            ) {
            items(orderMenuMenus) { data ->
                if (data.cafeShop.isBestSell == true) {
                    Box(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .clickable { navigateToDetail(data.cafeShop.id) }
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(8.dp))
                            .background(myColor3),
                    ) {
                        AllMenuItem(
                            image = data.cafeShop.image,
                            title = data.cafeShop.title,
                            price = data.cafeShop.price,
                            modifier = Modifier
                                .padding(8.dp)
                                .clickable { navigateToDetail(data.cafeShop.id) }
                        )
                    }
                }
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
private fun PreviewScreen(navController: NavHostController = rememberNavController()) {
    FEJetpackComposeTaskTheme {
        HomeScreen(
            navigateToDetail = { coffeId ->
                navController.navigate(Screen.DetailCoffe.createRoute(coffeId))
            }
        )
    }
}