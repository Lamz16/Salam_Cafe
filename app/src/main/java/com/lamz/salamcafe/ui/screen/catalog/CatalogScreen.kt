package com.lamz.salamcafe.ui.screen.catalog

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lamz.salamcafe.model.OrderMenu
import com.lamz.salamcafe.ui.common.UiState
import com.lamz.salamcafe.ui.components.CatalogItem
import com.lamz.salamcafe.ui.theme.myColor3
import com.lamz.salamcafe.ui.theme.myColor4
import org.koin.androidx.compose.koinViewModel

@Composable
fun CatalogScreen(
    navigateToDetail: (Long) -> Unit,
    viewModel: CatalogViewModel = koinViewModel(),
    modifier: Modifier = Modifier
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> viewModel.getAllCoffeMenu()
            is UiState.Success -> {
                CatalogContent(
                    listMenu = uiState.data,
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
fun CatalogContent(
    listMenu: List<OrderMenu>,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column {

        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Catalog",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold),
                    color = Color.Black
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(myColor4)
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(120.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = modifier
        ) {

            items(listMenu) { data ->
                Box(
                    modifier = Modifier
                    .clickable { navigateToDetail(data.cafeShop.id) }
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(8.dp))
                    .background(myColor3)) {
                    CatalogItem(
                        image = data.cafeShop.image,
                        title = data.cafeShop.title,
                        price = data.cafeShop.price,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(8.dp)
                            .clickable { navigateToDetail(data.cafeShop.id) }
                    )

                }

            }
        }
    }

}