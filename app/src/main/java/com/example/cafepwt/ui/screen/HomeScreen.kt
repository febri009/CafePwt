package com.example.cafepwt.ui.screen


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.cafepwt.Injection
import com.example.cafepwt.R
import com.example.cafepwt.ViewModelFactory
import com.example.cafepwt.data.CafeList
import com.example.cafepwt.ui.common.UiState
import com.example.cafepwt.ui.component.CafeListItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,

) {

    val query by viewModel.query
    val filteredCafes by viewModel.uiState.collectAsState(initial = UiState.Loading)

    LaunchedEffect(query) {
        viewModel.searchCafe(query)
    }

    when(filteredCafes){
        is UiState.Loading -> {
            viewModel.getAllCafe()
        }
        is UiState.Success -> {
            HomeContent(
                cafeList = (filteredCafes as UiState.Success<List<CafeList>>).data,
                modifier = modifier,
                navigateToDetail = navigateToDetail,
                query = query,
                onQueryChange = viewModel::searchCafe
            )
        }
        is UiState.Error -> {

        }
    }
}

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
){
    TextField(
        value = query,
        onValueChange = onQueryChange,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(100),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor =  Color(android.graphics.Color.parseColor("#EEE9DA")),
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        placeholder = {
            Text(
                stringResource(R.string.search_cafe),
                fontFamily = FontFamily(Font(R.font.poppins))
            )
        },
        modifier = modifier
            .padding(16.dp, 8.dp, 16.dp, 8.dp)
            .fillMaxWidth()
            .heightIn(min = 48.dp)
            .clip(RoundedCornerShape(80.dp))
    )
}

@Composable
fun HomeContent(
    cafeList: List<CafeList>,
    query: String,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
    onQueryChange: (String) -> Unit
) {
    Column(modifier = modifier) {
        SearchBar(query = query, onQueryChange = onQueryChange)
        LazyColumn {
            items(cafeList) { data ->
                CafeListItem(
                    name = data.cafe.name,
                    alamat = data.cafe.alamat,
                    cover = data.cafe.cover,
                    onClick = {},
                    modifier = Modifier.clickable {
                        navigateToDetail(data.cafe.id)
                    }
                )
            }
        }
    }
}
