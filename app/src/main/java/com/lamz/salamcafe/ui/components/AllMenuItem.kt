package com.lamz.salamcafe.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lamz.salamcafe.R
import com.lamz.salamcafe.ui.theme.FEJetpackComposeTaskTheme
import com.lamz.salamcafe.ui.theme.Shapes
import java.text.NumberFormat
import java.util.Locale

@Composable
fun AllMenuItem(
    image: Int,
    title: String,
    price: Int,
    modifier: Modifier = Modifier,
) {
    val formattedPrice = NumberFormat.getNumberInstance(Locale("id", "ID")).format(price)

    Row(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(Shapes.medium)
        )
        Column(
            Modifier
                .align(Alignment.CenterVertically)
                .padding(start = 16.dp)
        ) {
            Text(
                text = title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = "Rp. $formattedPrice",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.secondary
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
private fun MenuItemPreview() {
    FEJetpackComposeTaskTheme {
        AllMenuItem(R.drawable.menu1, "Falcon", 1000)
    }
}