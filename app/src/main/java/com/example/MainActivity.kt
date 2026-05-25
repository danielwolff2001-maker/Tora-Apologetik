package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.AppDatabase
import com.example.data.ApologeticsRepository
import com.example.ui.screens.*
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.PrimaryTeal
import com.example.viewmodel.ApologeticsViewModel
import com.example.viewmodel.AppTab

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)
        val repository = ApologeticsRepository(database)

        // Simple constructor factory for the ViewModel
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(ApologeticsViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return ApologeticsViewModel(repository) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
        val viewModel = ViewModelProvider(this, factory)[ApologeticsViewModel::class.java]

        setContent {
            MyApplicationTheme {
                MainAppHost(viewModel)
            }
        }
    }
}

@Composable
fun MainAppHost(viewModel: ApologeticsViewModel) {
    val currentTab by viewModel.currentTab.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar(
                containerColor = com.example.ui.theme.SurfaceSlate,
                tonalElevation = 8.dp
            ) {
                NavigationBarItem(
                    selected = currentTab == AppTab.HOME,
                    onClick = { viewModel.selectTab(AppTab.HOME) },
                    icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
                    label = { Text("Home", fontSize = 11.sp, fontWeight = FontWeightSemiBold(currentTab == AppTab.HOME)) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = com.example.ui.theme.SecondaryIce,
                        selectedTextColor = com.example.ui.theme.SecondaryIce,
                        indicatorColor = com.example.ui.theme.SophisticatedGradientEnd,
                        unselectedIconColor = com.example.ui.theme.TextMuted,
                        unselectedTextColor = com.example.ui.theme.TextMuted
                    )
                )

                NavigationBarItem(
                    selected = currentTab == AppTab.THEMEN,
                    onClick = { viewModel.selectTab(AppTab.THEMEN) },
                    icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "Themen") },
                    label = { Text("Themen", fontSize = 11.sp, fontWeight = FontWeightSemiBold(currentTab == AppTab.THEMEN)) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = com.example.ui.theme.SecondaryIce,
                        selectedTextColor = com.example.ui.theme.SecondaryIce,
                        indicatorColor = com.example.ui.theme.SophisticatedGradientEnd,
                        unselectedIconColor = com.example.ui.theme.TextMuted,
                        unselectedTextColor = com.example.ui.theme.TextMuted
                    )
                )

                NavigationBarItem(
                    selected = currentTab == AppTab.TRAINING,
                    onClick = { viewModel.selectTab(AppTab.TRAINING) },
                    icon = { Icon(Icons.Default.Star, contentDescription = "Training") },
                    label = { Text("Training", fontSize = 11.sp, fontWeight = FontWeightSemiBold(currentTab == AppTab.TRAINING)) },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = com.example.ui.theme.SecondaryIce,
                        selectedTextColor = com.example.ui.theme.SecondaryIce,
                        indicatorColor = com.example.ui.theme.SophisticatedGradientEnd,
                        unselectedIconColor = com.example.ui.theme.TextMuted,
                        unselectedTextColor = com.example.ui.theme.TextMuted
                    )
                )
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = com.example.ui.theme.DeepBackground
        ) {
            Crossfade(targetState = currentTab, label = "TabTransition") { tab ->
                when (tab) {
                    AppTab.HOME -> HomeScreen(
                        viewModel = viewModel,
                        onNavigateToTopic = { topic -> viewModel.selectThema(topic) }
                    )
                    AppTab.THEMEN -> ThemenScreen(viewModel = viewModel)
                    AppTab.TRAINING -> TrainingScreen(viewModel = viewModel)
                }
            }
        }
    }
}

private fun FontWeightSemiBold(selected: Boolean): androidx.compose.ui.text.font.FontWeight {
    return if (selected) androidx.compose.ui.text.font.FontWeight.Bold else androidx.compose.ui.text.font.FontWeight.Normal
}

