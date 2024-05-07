package com.lamz.salamcafe.ui.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lamz.salamcafe.ui.common.UiState
import org.koin.androidx.compose.koinViewModel
import com.lamz.salamcafe.R
import com.lamz.salamcafe.ui.components.CartItem
import com.lamz.salamcafe.ui.components.OrderButton
import com.lamz.salamcafe.ui.theme.myColor4
import java.text.NumberFormat
import java.util.Locale

@Composable
fun OrderScreen(
    viewModel: OrderViewModel = koinViewModel(),
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> viewModel.getAddedOrderMenu()
            is UiState.Success -> {
                OrderContent(state = uiState.data, onProductCountChange = { menuId, count ->
                    viewModel.orderMenu(menuId, count)
                }, onOrderButtonClicked = onOrderButtonClicked)
            }

            is UiState.Error -> {}
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderContent(
    state: OrderState,
    onProductCountChange: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val shareMessage = stringResource(
        R.string.shared_message,
        state.orderState.count(),
        NumberFormat.getNumberInstance(Locale("id", "ID")).format(state.totalPrice)
    )

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.menu_cart),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(myColor4)
        )
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(weight = 1f)
        ) {
            items(state.orderState, key = { it.cafeShop.id }) { item ->
                CartItem(
                    menuId = item.cafeShop.id,
                    image = item.cafeShop.image,
                    title = item.cafeShop.title,
                    price = item.cafeShop.price * item.count,
                    count = item.count,
                    onProductCountChanged = onProductCountChange,
                )
                Divider()
            }
        }
        OrderButton(
            text = stringResource(
                R.string.total_order,
                NumberFormat.getNumberInstance(Locale("id", "ID")).format(state.totalPrice)
            ),
            enabled = state.orderState.isNotEmpty(),
            onClick = {
                onOrderButtonClicked(shareMessage)
            },
            modifier = Modifier.padding(16.dp)
        )
    }

}
