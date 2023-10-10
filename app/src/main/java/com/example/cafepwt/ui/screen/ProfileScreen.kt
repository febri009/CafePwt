package com.example.cafepwt.ui.screen


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.cafepwt.R
import com.example.cafepwt.ui.theme.CafePwtTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        TopAppBar(
            title =
            {
                Text(
                    text = "About Me",
                    style = MaterialTheme.typography.h6.copy(fontFamily = FontFamily(Font(R.font.poppins))),
                )
            },
            navigationIcon = {
                IconButton(onClick = navigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to Main"
                    )
                }
            },
            backgroundColor = MaterialTheme.colors.surface,
            elevation = AppBarDefaults.TopAppBarElevation
        )
        Image(
            modifier = modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(16.dp)
                .clip(RoundedCornerShape(10.dp)),
            painter = painterResource(R.drawable.pepuy),
            contentScale = ContentScale.FillWidth,
            contentDescription = "profpic"
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "Nama",
            color = Color(android.graphics.Color.parseColor("#6096B4")),
            style = MaterialTheme.typography.subtitle1.copy(fontFamily = FontFamily(Font(R.font.poppins))),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Febriyanti Paramudita",
            style = MaterialTheme.typography.body1.copy(fontFamily = FontFamily(Font(R.font.poppins)))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Email",
            color = Color(android.graphics.Color.parseColor("#6096B4")),
            style = MaterialTheme.typography.subtitle1.copy(fontFamily = FontFamily(Font(R.font.poppins))),
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "muditafebriyanti@gmail.com",
            style = MaterialTheme.typography.body1.copy(fontFamily = FontFamily(Font(R.font.poppins)))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    CafePwtTheme {
        ProfileScreen(navigateBack = {})
    }
}
