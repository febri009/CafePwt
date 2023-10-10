package com.example.cafepwt.ui.screen


import com.example.cafepwt.ui.component.RatingBar
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.cafepwt.Injection
import com.example.cafepwt.ViewModelFactory
import com.example.cafepwt.ui.common.UiState
import com.example.cafepwt.ui.theme.CafePwtTheme
import com.example.cafepwt.R

@Composable
fun ScreenDetail(
    cafeId: Long,
    viewModel: DetailCafeViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(android.graphics.Color.parseColor("#EEE9DA")))
    ) {
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getCafeById(cafeId)
                }
                is UiState.Success -> {
                    val data = uiState.data
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        item {
                            CafeDetailScreen(
                                name = data.cafe.name,
                                option = data.cafe.option,
                                cover = data.cafe.cover,
                                hours = data.cafe.hours,
                                alamat = data.cafe.alamat,
                                phone = data.cafe.phone,
                                rating = data.cafe.rating
                            )
                        }
                    }
                }
                is UiState.Error -> {}
            }
        }
    }
}
@Composable
fun CafeDetailScreen(
    name: String,
    option: String,
    cover: String,
    hours: String,
    alamat: String,
    phone: String,
    rating: String,
) {
    Column(
        modifier = Modifier
            .padding(32.dp)
    ) {
        val painter: Painter = rememberAsyncImagePainter(model = cover)
        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(10.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = name,
            color = Color(android.graphics.Color.parseColor("#6096B4")),
            style = typography.h5.copy(fontFamily = FontFamily(Font(R.font.poppins))),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Option",
            color = Color(android.graphics.Color.parseColor("#6096B4")),
            style = typography.subtitle1.copy(fontFamily = FontFamily(Font(R.font.poppins))),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = option,
            style = typography.body1.copy(fontFamily = FontFamily(Font(R.font.poppins)))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Hours",
            color = Color(android.graphics.Color.parseColor("#6096B4")),
            style = typography.subtitle1.copy(fontFamily = FontFamily(Font(R.font.poppins))),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = hours,
            style = typography.body1.copy(fontFamily = FontFamily(Font(R.font.poppins)))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Alamat",
            color = Color(android.graphics.Color.parseColor("#6096B4")),
            style = typography.subtitle1.copy(fontFamily = FontFamily(Font(R.font.poppins))),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = alamat,
            style = typography.body1.copy(fontFamily = FontFamily(Font(R.font.poppins)))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Phone",
            color = Color(android.graphics.Color.parseColor("#6096B4")),
            style = typography.subtitle1.copy(fontFamily = FontFamily(Font(R.font.poppins))),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = phone,
            style = typography.body1.copy(fontFamily = FontFamily(Font(R.font.poppins)))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Rating",
            color = Color(android.graphics.Color.parseColor("#6096B4")),
            style = typography.subtitle1.copy(fontFamily = FontFamily(Font(R.font.poppins))),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            RatingBar(rating = rating.toFloat())
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "(${rating})",
                style = typography.body1.copy(fontFamily = FontFamily(Font(R.font.poppins)))
            )
        }
    }
}

@Preview
@Composable
fun CafeDetailScreenPreview() {
    CafePwtTheme{
        CafeDetailScreen(
            name = "Cafe Win Purwokerto",
            option = "Dine in | Take away | Delivery",
            cover = "https://www.amesbostonhotel.com/wp-content/uploads/2022/12/Cafe-WN-Purwokerto.jpg?ezimgfmt=ng:webp/ngcb1",
            hours = "Open 24 Hours",
            alamat = "Hotel Wisata Niaga, Jl. Merdeka No.5, Brubahan, Kranji, Kec. Purwokerto Tim., Kabupaten Banyumas, Jawa Tengah 53116",
            phone = "081395075858",
            rating = "3.5"
        )
    }
}

