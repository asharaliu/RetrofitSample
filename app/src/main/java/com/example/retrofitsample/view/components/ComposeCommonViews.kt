package com.example.retrofitsample.view.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * To show Error composable screen
 */
@Composable
fun ErrorScreen(error: String? = null) {
    Text(
        modifier = Modifier.fillMaxSize(),
        text = "Network Error - Reason: $error",
        textAlign = TextAlign.Center,
        color = Color.Red
    )
}

/**
 * Item composable that needs to show with shimmer animated background
 * brush - Background data with animation
 */
@Composable
fun ShimmerItem(
    brush: Brush
) {
    Column(modifier = Modifier) {
        repeat(20) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .padding(8.dp)
                    .background(shape = RoundedCornerShape(8.dp), brush = brush)
            )
        }
    }
}

/**
 * Shimmer animation logic
 */
@Composable
fun ShimmerAnimation(
) {
    //Colors for the shimmer animation
    val shimmerColorShades = listOf(
        Color.LightGray.copy(0.9f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.9f)
    )
    val transition = rememberInfiniteTransition()
    //Transition animation
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        )
    )
    val brush = Brush.linearGradient(
        colors = shimmerColorShades,
        start = Offset(10f, 10f),
        end = Offset(translateAnim, translateAnim)
    )

    ShimmerItem(brush = brush)
}

/**
 * Dummy spacer to keep at the bottom of lazy column
 */
@Composable
fun LazyColumnBottomSpacer(height: Dp = 150.dp){
    Spacer(modifier = Modifier.fillMaxWidth().height(height))
}