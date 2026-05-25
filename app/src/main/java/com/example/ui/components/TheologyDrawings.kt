package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OliveTreeIllustration(modifier: Modifier = Modifier) {
    // Elegant pulsing animation representing the living root
    val infiniteTransition = rememberInfiniteTransition(label = "OliveTreePulse")
    val scalePulse by infiniteTransition.animateFloat(
        initialValue = 0.85f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(2200, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "PulseRoot"
    )

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Subtle glowing atmospheric background (Radial Gradient around the tree root)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0x35D0BCFF), Color.Transparent),
                    center = Offset(w / 2f, h * 0.45f),
                    radius = w * 0.55f * scalePulse
                )
            )

            // Draw deep soil layers
            drawArc(
                color = Color(0xFF141316),
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(-w * 0.15f, h * 0.82f),
                size = Size(w * 1.3f, h * 0.45f)
            )

            // Golden ground border separating earth and atmosphere
            drawLine(
                color = Color(0xFFFFB703).copy(alpha = 0.6f),
                start = Offset(0f, h * 0.85f),
                end = Offset(w, h * 0.85f),
                strokeWidth = 4f
            )

            // Main Vine/Trunk (Aesthetic Cedar Brown)
            val trunkColor = Color(0xFF4E3629)
            val trunkPath = Path().apply {
                moveTo(w * 0.44f, h * 0.85f)
                quadraticTo(w * 0.45f, h * 0.63f, w * 0.38f, h * 0.48f) // left slope
                quadraticTo(w * 0.22f, h * 0.38f, w * 0.18f, h * 0.33f) // main left branch (natural Israel branches)
                lineTo(w * 0.21f, h * 0.30f)
                quadraticTo(w * 0.29f, h * 0.36f, w * 0.39f, h * 0.43f)
                quadraticTo(w * 0.46f, h * 0.46f, w * 0.49f, h * 0.36f) // split junction
                lineTo(w * 0.52f, h * 0.36f)
                quadraticTo(w * 0.54f, h * 0.49f, w * 0.62f, h * 0.40f) // right junction
                quadraticTo(w * 0.74f, h * 0.30f, w * 0.82f, h * 0.28f) // main right branch (Grafted Gentiles)
                lineTo(w * 0.85f, h * 0.31f)
                quadraticTo(w * 0.72f, h * 0.40f, w * 0.64f, h * 0.51f)
                quadraticTo(w * 0.56f, h * 0.63f, w * 0.58f, h * 0.85f) // trunk right slope
                close()
            }
            drawPath(path = trunkPath, color = trunkColor)

            // Draw glowing deep roots extending down to Jeremia 31/Deuteronomy 30
            val rootPath = Path().apply {
                moveTo(w * 0.46f, h * 0.85f)
                quadraticTo(w * 0.43f, h * 0.92f, w * 0.30f, h * 0.96f)
                moveTo(w * 0.52f, h * 0.85f)
                quadraticTo(w * 0.55f, h * 0.93f, w * 0.68f, h * 0.97f)
                moveTo(w * 0.49f, h * 0.85f)
                quadraticTo(w * 0.49f, h * 0.95f, w * 0.47f, h * 1.0f)
            }
            drawPath(
                path = rootPath,
                color = Color(0xFFFFB703),
                style = Stroke(width = 6f, cap = StrokeCap.Round)
            )

            // Original Israel branches (Rich Emerald/Sage foliage)
            drawCircle(color = Color(0xAA4CAF50), radius = w * 0.16f, center = Offset(w * 0.23f, h * 0.30f))
            drawCircle(color = Color(0x774CAF50), radius = w * 0.12f, center = Offset(w * 0.38f, h * 0.26f))

            // Wild Olive branches representing Grafted-In Gentiles (Glistening Lavender foliage)
            drawCircle(color = Color(0xAAD0BCFF), radius = w * 0.17f, center = Offset(w * 0.76f, h * 0.29f))
            drawCircle(color = Color(0x77D0BCFF), radius = w * 0.13f, center = Offset(w * 0.62f, h * 0.24f))

            // Draw descriptive indicator cards
            // Root Label (Tora & Word of God - Gold)
            drawRoundRect(
                color = Color(0xFF2B2930),
                topLeft = Offset(w * 0.33f, h * 0.05f),
                size = Size(w * 0.34f, h * 0.09f),
                cornerRadius = CornerRadius(16f, 16f)
            )
            drawRoundRect(
                color = Color(0xFFFFB703),
                topLeft = Offset(w * 0.33f, h * 0.05f),
                size = Size(w * 0.34f, h * 0.09f),
                cornerRadius = CornerRadius(16f, 16f),
                style = Stroke(width = 2f)
            )

            // Left Tag: "Naturzweige" (Kulturölbaum - Sage Green)
            drawRoundRect(
                color = Color(0xFF2B2930),
                topLeft = Offset(w * 0.08f, h * 0.50f),
                size = Size(w * 0.25f, h * 0.09f),
                cornerRadius = CornerRadius(16f, 16f)
            )
            drawRoundRect(
                color = Color(0xAA4CAF50),
                topLeft = Offset(w * 0.08f, h * 0.50f),
                size = Size(w * 0.25f, h * 0.09f),
                cornerRadius = CornerRadius(16f, 16f),
                style = Stroke(width = 2f)
            )

            // Right Tag: "Eingepfropft" (Lavender Gentiles)
            drawRoundRect(
                color = Color(0xFF2B2930),
                topLeft = Offset(w * 0.66f, h * 0.50f),
                size = Size(w * 0.26f, h * 0.09f),
                cornerRadius = CornerRadius(16f, 16f)
            )
            drawRoundRect(
                color = Color(0xFFD0BCFF),
                topLeft = Offset(w * 0.66f, h * 0.50f),
                size = Size(w * 0.26f, h * 0.09f),
                cornerRadius = CornerRadius(16f, 16f),
                style = Stroke(width = 2f)
            )
        }
        NanoBananaBadge(title = "Ölbaum Israel • HD Map")
    }
}


@Composable
fun TorahSchattenIllustration(modifier: Modifier = Modifier) {
    // Beautiful heavenly glow animation
    val infiniteTransition = rememberInfiniteTransition(label = "ScrollGlow")
    val glowPulse by infiniteTransition.animateFloat(
        initialValue = 0.9f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "LightGlow"
    )

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Radiant Sun of Righteousness (Messiah - Golden Radiance)
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0xFFFFF6CA), Color(0xFFFFB703), Color.Transparent),
                    center = Offset(w * 0.5f, h * 0.16f),
                    radius = h * 0.35f * glowPulse
                )
            )
            drawCircle(
                color = Color.White,
                radius = w * 0.07f,
                center = Offset(w * 0.5f, h * 0.16f)
            )

            // Glistening light rays pointing downward to represent shadow projection
            val ray1 = Path().apply {
                moveTo(w * 0.5f, h * 0.16f)
                lineTo(w * 0.02f, h * 0.98f)
                lineTo(w * 0.20f, h * 0.98f)
                close()
            }
            drawPath(ray1, brush = Brush.verticalGradient(listOf(Color(0x30FFB703), Color.Transparent)))

            val ray2 = Path().apply {
                moveTo(w * 0.5f, h * 0.16f)
                lineTo(w * 0.32f, h * 0.98f)
                lineTo(w * 0.68f, h * 0.98f)
                close()
            }
            drawPath(ray2, brush = Brush.verticalGradient(listOf(Color(0x40FFB703), Color.Transparent)))

            val ray3 = Path().apply {
                moveTo(w * 0.5f, h * 0.16f)
                lineTo(w * 0.80f, h * 0.98f)
                lineTo(w * 0.98f, h * 0.98f)
                close()
            }
            drawPath(ray3, brush = Brush.verticalGradient(listOf(Color(0x30FFB703), Color.Transparent)))

            // Scroll Handles & Spindles
            val spindleColor = Color(0xFF381E72)
            // Left Handles
            drawRect(color = spindleColor, topLeft = Offset(w * 0.25f, h * 0.44f), size = Size(w * 0.06f, h * 0.12f))
            drawCircle(color = Color(0xFF4F378B), radius = w * 0.03f, center = Offset(w * 0.28f, h * 0.44f))
            drawCircle(color = Color(0xFF4F378B), radius = w * 0.03f, center = Offset(w * 0.28f, h * 0.56f))

            // Right Handles
            drawRect(color = spindleColor, topLeft = Offset(w * 0.69f, h * 0.44f), size = Size(w * 0.06f, h * 0.12f))
            drawCircle(color = Color(0xFF4F378B), radius = w * 0.03f, center = Offset(w * 0.72f, h * 0.44f))
            drawCircle(color = Color(0xFF4F378B), radius = w * 0.03f, center = Offset(w * 0.72f, h * 0.56f))

            // Parchment paper open (Aesthetic old cream book scroll)
            drawRoundRect(
                color = Color(0xFFFCF4E3),
                topLeft = Offset(w * 0.29f, h * 0.40f),
                size = Size(w * 0.42f, h * 0.18f),
                cornerRadius = CornerRadius(12f, 12f)
            )
            // Delicate lavender scroll outline
            drawRoundRect(
                color = Color(0xFFD0BCFF),
                topLeft = Offset(w * 0.29f, h * 0.40f),
                size = Size(w * 0.42f, h * 0.18f),
                cornerRadius = CornerRadius(12f, 12f),
                style = Stroke(width = 3f)
            )

            // Left Rolled Column
            drawRoundRect(
                color = Color(0xFFF0DAAC),
                topLeft = Offset(w * 0.30f, h * 0.38f),
                size = Size(w * 0.07f, h * 0.22f),
                cornerRadius = CornerRadius(16f, 16f)
            )
            // Right Rolled Column
            drawRoundRect(
                color = Color(0xFFF0DAAC),
                topLeft = Offset(w * 0.63f, h * 0.38f),
                size = Size(w * 0.07f, h * 0.22f),
                cornerRadius = CornerRadius(16f, 16f)
            )

            // Shadow projection block showing the Sabbath / Mo'edim shadow casted downwards
            val shadowArea = Path().apply {
                moveTo(w * 0.28f, h * 0.65f)
                lineTo(w * 0.10f, h * 0.94f)
                lineTo(w * 0.90f, h * 0.94f)
                lineTo(w * 0.72f, h * 0.65f)
                close()
            }
            drawPath(
                path = shadowArea,
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xDF1C1B1F), Color(0x301C1B1F)),
                    startY = h * 0.65f,
                    endY = h * 0.95f
                )
            )

            // Left shadow line representing pointer direction
            drawLine(
                color = Color(0xFFD0BCFF).copy(alpha = 0.5f),
                start = Offset(w * 0.28f, h * 0.65f),
                end = Offset(w * 0.10f, h * 0.94f),
                strokeWidth = 3f
            )
            // Right shadow line representing pointer direction
            drawLine(
                color = Color(0xFFD0BCFF).copy(alpha = 0.5f),
                start = Offset(w * 0.72f, h * 0.65f),
                end = Offset(w * 0.90f, h * 0.94f),
                strokeWidth = 3f
            )
        }
        NanoBananaBadge(title = "Tora-Schatten • Pro Design")
    }
}

@Composable
fun LawGraceBridgeIllustration(modifier: Modifier = Modifier) {
    // Left/Right shift animation for dynamic bridge
    val infiniteTransition = rememberInfiniteTransition(label = "BridgeAnim")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(2500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "BridgeGlow"
    )

    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val w = size.width
            val h = size.height

            // Left Platform (Sünde & Verderben - Scarlet warnings)
            val leftPlatform = Path().apply {
                moveTo(0f, h * 0.52f)
                lineTo(w * 0.33f, h * 0.52f)
                quadraticTo(w * 0.37f, h * 0.58f, w * 0.34f, h * 0.70f)
                lineTo(w * 0.16f, h * 0.98f)
                lineTo(0f, h * 0.98f)
                close()
            }
            drawPath(leftPlatform, color = Color(0xFF2C191B))
            drawLine(
                color = Color(0xFFF2B8B5),
                start = Offset(0f, h * 0.52f),
                end = Offset(w * 0.33f, h * 0.52f),
                strokeWidth = 5f
            )

            // Right Platform (Ruhe & Leben - Peaceful Green pasture)
            val rightPlatform = Path().apply {
                moveTo(w * 0.67f, h * 0.52f)
                lineTo(w, h * 0.52f)
                lineTo(w, h * 0.98f)
                lineTo(w * 0.80f, h * 0.98f)
                quadraticTo(w * 0.63f, h * 0.72f, w * 0.67f, h * 0.52f)
                close()
            }
            drawPath(rightPlatform, color = Color(0xFF132F24))
            drawLine(
                color = Color(0xFF81C784),
                start = Offset(w * 0.67f, h * 0.52f),
                end = Offset(w, h * 0.52f),
                strokeWidth = 5f
            )

            // Deep red/amber glowing abyss below
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(Color(0x35F2B8B5), Color.Transparent),
                    center = Offset(w * 0.5f, h * 0.75f),
                    radius = w * 0.28f
                )
            )

            // Messiah's Cross Foundation supporting the bridge
            val cross = Path().apply {
                // vertical wood
                moveTo(w * 0.48f, h * 0.28f)
                lineTo(w * 0.52f, h * 0.28f)
                lineTo(w * 0.52f, h * 0.92f)
                lineTo(w * 0.48f, h * 0.92f)
                close()
                // hotizontal wood
                moveTo(w * 0.43f, h * 0.40f)
                lineTo(w * 0.57f, h * 0.40f)
                lineTo(w * 0.57f, h * 0.45f)
                lineTo(w * 0.43f, h * 0.45f)
                close()
            }
            drawPath(cross, color = Color(0xFFFFB703))
            drawPath(cross, color = Color.White.copy(alpha = pulseAlpha), style = Stroke(width = 3f))

            // Bridge deck linking left to right (Messiah's Grace - Lilac glow)
            val bridgeDeck = Path().apply {
                moveTo(w * 0.32f, h * 0.51f)
                lineTo(w * 0.68f, h * 0.51f)
                lineTo(w * 0.68f, h * 0.53f)
                lineTo(w * 0.32f, h * 0.53f)
                close()
            }
            drawPath(bridgeDeck, color = Color(0xFFEADDFF))
            drawPath(bridgeDeck, color = Color(0xFFD0BCFF), style = Stroke(width = 2f))
        }
        NanoBananaBadge(title = "Gesetz & Gnade • Ultra Rendering")
    }
}

@Composable
fun NanoBananaBadge(title: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Box(
            modifier = Modifier
                .background(Color(0xCD08080C), RoundedCornerShape(6.dp))
                .border(1.dp, Color(0x60FFB703), RoundedCornerShape(6.dp))
                .padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(Color(0xFFFFB703), RoundedCornerShape(50))
                )
                androidx.compose.material3.Text(
                    text = "NanoBanana™ Pro v3.5 • $title",
                    color = Color(0xFFFFE082),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

