package com.example.cafepwt

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cafepwt.ui.navigation.NavHostContent
import com.example.cafepwt.ui.navigation.Screen
import com.example.cafepwt.ui.theme.CafePwtTheme


@Composable
fun CafePwtApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Screen.Home.route) {
                TopAppBar(
                    title = {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                "Cafe Purwokerto",
                                maxLines = 1,
                                modifier = Modifier.align(Alignment.Center),
                                textAlign = TextAlign.Center,
                                style = MaterialTheme.typography.h6.copy(
                                    fontFamily = FontFamily(
                                        Font(R.font.poppins)
                                    )
                                )
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                navController.navigate(Screen.Profile.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    restoreState = true
                                    launchSingleTop = true
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Info,
                                contentDescription = "about_page",
                                modifier = modifier.size(32.dp)
                            )
                        }
                    },
                    backgroundColor = MaterialTheme.colors.surface,
                )
                Spacer(modifier = Modifier.height(70.dp))
            }
        },

        content = { innerPadding ->
            Box(modifier = modifier.padding(innerPadding)){
                NavHostContent(navController = navController)
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun CafePwtAppPreview() {
    CafePwtTheme {
        CafePwtApp()
    }
}
