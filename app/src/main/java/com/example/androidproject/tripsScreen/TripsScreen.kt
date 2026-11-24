package com.example.androidproject.tripsScreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TripsScreen(
    viewModel: TripsViewModel,
    onSignOut: () -> Unit
) {
    var topMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        containerColor = Color(0xFFFEF7FF),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Android Lab",
                        fontSize = 20.sp,
                        color = Color(0xFF1C1B1F)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF3EDF7)
                ),
                actions = {
                    TextButton(onClick = onSignOut) {
                        Text(
                            text = "SIGN OUT",
                            color = Color(0xFF1C1B1F),
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp
                        )
                    }

                    Box {
                        IconButton(onClick = { topMenuExpanded = true }) {
                            MoreVertIcon()
                        }
                        DropdownMenu(
                            expanded = topMenuExpanded,
                            onDismissRequest = { topMenuExpanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Reset list") },
                                onClick = {
                                    viewModel.resetList()
                                    topMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 12.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item { Spacer(modifier = Modifier.height(4.dp)) }

            items(
                items = viewModel.trips,
                key = { it.instanceId }
            ) { entry ->
                TripCard(
                    trip = entry.trip,
                    onAdd = { viewModel.addTrip(entry) },
                    onRemove = { viewModel.removeTrip(entry) }
                )
            }

            item { Spacer(modifier = Modifier.height(12.dp)) }
        }
    }
}

@Composable
private fun TripCard(
    trip: TripItem,
    onAdd: () -> Unit,
    onRemove: () -> Unit
) {
    var itemMenuExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = trip.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2B2930)
            )

            Box {
                IconButton(onClick = { itemMenuExpanded = true }) {
                    MoreVertIcon()
                }
                DropdownMenu(
                    expanded = itemMenuExpanded,
                    onDismissRequest = { itemMenuExpanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Add item") },
                        onClick = {
                            itemMenuExpanded = false
                            onAdd()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Remove item") },
                        onClick = {
                            itemMenuExpanded = false
                            onRemove()
                        }
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(145.dp)
        ) {
            Image(
                painter = painterResource(id = trip.imageRes),
                contentDescription = trip.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(48.dp)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.65f))
                        )
                    )
            )

            Text(
                text = trip.price,
                color = Color.White,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = trip.description,
            fontSize = 14.sp,
            fontStyle = FontStyle.Italic,
            lineHeight = 18.sp,
            color = Color(0xFF2B2930)
        )
    }
}

@Composable
private fun MoreVertIcon(tint: Color = Color(0xFF2B2930)) {
    Canvas(modifier = Modifier.size(24.dp)) {
        val radius = 2.dp.toPx()
        val centerX = size.width / 2f
        val spacing = 6.dp.toPx()
        val centerY = size.height / 2f

        drawCircle(color = tint, radius = radius, center = Offset(centerX, centerY - spacing))
        drawCircle(color = tint, radius = radius, center = Offset(centerX, centerY))
        drawCircle(color = tint, radius = radius, center = Offset(centerX, centerY + spacing))
    }
}

@Preview(showBackground = true)
@Composable
private fun TripsScreenPreview() {
    val previewViewModel = remember { TripsViewModel() }
    TripsScreen(viewModel = previewViewModel, onSignOut = {})
}