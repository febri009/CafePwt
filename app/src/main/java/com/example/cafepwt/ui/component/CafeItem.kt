package com.example.cafepwt.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.cafepwt.R
import com.example.cafepwt.ui.theme.CafePwtTheme

@Composable
fun CafeListItem(
    name: String,
    alamat: String,
    cover: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick.invoke() },
        elevation = 0.dp,
        backgroundColor = Color(android.graphics.Color.parseColor("#EEE9DA"))
    ){
        Row(
            modifier = modifier
                .padding(16.dp)
        ){
            AsyncImage(
                model = cover,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp, 100.dp)
                    .clip(RoundedCornerShape(14.dp))
            )

            Spacer(modifier = modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)){
                Text(
                    text = name,
                    style = typography.subtitle1.copy(fontFamily = FontFamily(Font(R.font.poppins))),
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .padding(start = 12.dp)
                )

                Spacer(modifier = modifier.height(8.dp))

                Text(
                    text = alamat,
                    style = typography.caption.copy(fontFamily = FontFamily(Font(R.font.poppins))),
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .padding(start = 12.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CafeListItemPreview() {
    CafePwtTheme {
        Row {
            CafeListItem(
                name = "Nana Coffee Cakes Eatery",
                alamat = "Jl. Jend. Gatot Subroto No.61, Karangjengkol, Sokanegara, Kec. Purwokerto Tim., Kabupaten Banyumas, Jawa Tengah 53115",
                cover = "",
                onClick = {}
            )
        }
    }
}