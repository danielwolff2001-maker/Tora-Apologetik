package com.example.ui.screens

import android.widget.Toast
import android.speech.tts.TextToSpeech
import java.util.Locale
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.items
import com.example.data.ThemaItem
import com.example.data.TheologyData
import com.example.ui.components.LawGraceBridgeIllustration
import com.example.ui.components.OliveTreeIllustration
import com.example.ui.components.TorahSchattenIllustration
import com.example.ui.theme.*
import com.example.viewmodel.ApologeticsViewModel

@Composable
fun ThemenScreen(viewModel: ApologeticsViewModel) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedCategory.collectAsState()
    val selectedThema by viewModel.selectedThema.collectAsState()

    Crossfade(targetState = selectedThema, label = "ScreenTransition") { thema ->
        if (thema == null) {
            // Main List View of topics
            ThemenList(
                searchQuery = searchQuery,
                selectedCategory = selectedCategory,
                onSearchChanged = { viewModel.setSearchQuery(it) },
                onCategorySelected = { viewModel.selectCategory(it) },
                onTopicSelected = { viewModel.selectThema(it) }
            )
        } else {
            // Detailed exegesis article view
            ThemaDetailView(
                thema = thema,
                onBack = { viewModel.selectThema(null) }
            )
        }
    }
}

@Composable
fun ThemenList(
    searchQuery: String,
    selectedCategory: String,
    onSearchChanged: (String) -> Unit,
    onCategorySelected: (String) -> Unit,
    onTopicSelected: (ThemaItem) -> Unit
) {
    val categories = listOf("Alle", "Grundlagen", "Speisegebote", "Paulus & die Tora", "Hebräerbrief", "Israel & die Heiden")

    // Filter theological items based on search word and category
    val filteredThemen = remember(searchQuery, selectedCategory) {
        TheologyData.THEMEN.filter { thema ->
            val matchCategory = selectedCategory == "Alle" || thema.category == selectedCategory
            val matchText = thema.title.contains(searchQuery, ignoreCase = true) ||
                    thema.scriptureRefs.contains(searchQuery, ignoreCase = true) ||
                    thema.shortDescription.contains(searchQuery, ignoreCase = true)
            matchCategory && matchText
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Top Header
        Column {
            Text(
                text = "Bibelstellen & Einwände",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                text = "Exegese zu scheinbaren Toraskepsis-Versen im NT",
                fontSize = 13.sp,
                color = TextMuted
            )
        }

        // Beautiful Search bar (Sophisticated Dark Style)
        OutlinedTextField(
            value = searchQuery,
            onValueChange = onSearchChanged,
            placeholder = { Text("Themen oder Bibelverse durchsuchen...", color = TextMuted, fontSize = 14.sp) },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Suchen", tint = PrimaryTeal) },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onSearchChanged("") }) {
                        Icon(Icons.Default.Close, contentDescription = "Löschen", tint = TextMuted)
                    }
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = SurfaceSlate,
                unfocusedContainerColor = SurfaceSlate,
                focusedBorderColor = PrimaryTeal,
                unfocusedBorderColor = MutedSeparator,
                focusedTextColor = TextPrimary,
                unfocusedTextColor = TextPrimary
            ),
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        )

        // Dynamic categories Horizontal Slider
        androidx.compose.foundation.lazy.LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 2.dp)
        ) {
            items(categories) { category ->
                val isSelected = category == selectedCategory
                Box(
                    modifier = Modifier
                        .background(
                            if (isSelected) PrimaryTeal else SurfaceVariantDark,
                            RoundedCornerShape(20.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = if (isSelected) Color.Transparent else MutedSeparator,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .clickable { onCategorySelected(category) }
                        .padding(horizontal = 14.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = category,
                        color = if (isSelected) DeepBackground else TextPrimary,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Main scrollable list of theological topics
        if (filteredThemen.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "No topic found",
                        tint = AccentGold,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = "Keine Tora-Einwände gefunden.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Text(
                        text = "Ändere deine Suchanfrage oder Kategorie.",
                        fontSize = 13.sp,
                        color = TextMuted,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            androidx.compose.foundation.lazy.LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 60.dp)
            ) {
                items(filteredThemen) { thema ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onTopicSelected(thema) },
                        colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Circular icon representation for each category
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(PrimaryTeal.copy(alpha = 0.12f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                val itemIcon = when (thema.iconType) {
                                    "law" -> Icons.AutoMirrored.Filled.List
                                    "branch" -> Icons.Default.Check
                                    "food" -> Icons.Default.ShoppingCart
                                    "shadow" -> Icons.Default.Star
                                    else -> Icons.AutoMirrored.Filled.List
                                }
                                Icon(itemIcon, contentDescription = "Thema", tint = PrimaryTeal, modifier = Modifier.size(20.dp))
                            }

                            // Texts
                            Column(modifier = Modifier.weight(1f)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(SurfaceVariantDark, RoundedCornerShape(4.dp))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(thema.category, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = PrimaryTeal)
                                    }
                                    Text(
                                        text = thema.scriptureRefs,
                                        fontSize = 11.sp,
                                        color = AccentGold,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = thema.title,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Text(
                                    text = thema.shortDescription,
                                    fontSize = 12.sp,
                                    color = TextMuted,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis
                                )

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = "${thema.durationMinutes} Min. • ${thema.sections.size} Teilschritte",
                                    color = TextMuted,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }

                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Öffnen",
                                tint = TextMuted
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ThemaDetailView(thema: ThemaItem, onBack: () -> Unit) {
    val context = LocalContext.current
    val clipboardManager = LocalClipboardManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBackground)
    ) {
        // Back toolbar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Zurück", tint = TextPrimary)
            }

            Text(
                text = "Ausführliche Exegese",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            IconButton(onClick = {
                val fullSharedText = "${thema.title}\nBibelstellen: ${thema.scriptureRefs}\n\nEinwand: ${thema.objection}\n\nLies mehr im Exegese-Assistenten der Tora Apologetik App!"
                clipboardManager.setText(AnnotatedString(fullSharedText))
                Toast.makeText(context, "Thema-Gliederung kopiert!", Toast.LENGTH_SHORT).show()
            }) {
                Icon(Icons.Default.Share, contentDescription = "Teilen", tint = PrimaryTeal)
            }
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Info Header
            Column {
                Box(
                    modifier = Modifier
                        .background(PrimaryTeal.copy(alpha = 0.12f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(thema.category.uppercase(), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = PrimaryTeal)
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = thema.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    lineHeight = 32.sp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = "Bibelstelle: ${thema.scriptureRefs}",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentGold
                    )
                    Text(
                        text = "•  ${thema.durationMinutes} Min. Lesezeit",
                        fontSize = 13.sp,
                        color = TextMuted
                    )
                }
            }

            // --- NanoSpeech™ Premium Audio Reader Control Panel ---
            var isTtsInitialized by remember { mutableStateOf(false) }
            var isSpeaking by remember { mutableStateOf(false) }
            var speechRate by remember { mutableStateOf(1.0f) }
            var speechPitch by remember { mutableStateOf(1.0f) }
            var tts by remember { mutableStateOf<TextToSpeech?>(null) }
            var currentTtsSection by remember { mutableStateOf<String?>(null) }

            DisposableEffect(context) {
                var ttsObj: TextToSpeech? = null
                ttsObj = TextToSpeech(context) { status ->
                    if (status == TextToSpeech.SUCCESS) {
                        try {
                            val result = ttsObj?.setLanguage(Locale.GERMAN)
                            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                                ttsObj?.setLanguage(Locale.ENGLISH)
                            }
                            ttsObj?.setOnUtteranceProgressListener(object : android.speech.tts.UtteranceProgressListener() {
                                override fun onStart(utteranceId: String?) {
                                    isSpeaking = true
                                    currentTtsSection = utteranceId
                                }
                                override fun onDone(utteranceId: String?) {
                                    isSpeaking = false
                                    currentTtsSection = null
                                }
                                override fun onError(utteranceId: String?) {
                                    isSpeaking = false
                                    currentTtsSection = null
                                }
                            })
                            isTtsInitialized = true
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                tts = ttsObj
                onDispose {
                    try {
                        ttsObj?.stop()
                        ttsObj?.shutdown()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            // Pulsing Equalizer Visualizer Animations
            val infiniteTransition = rememberInfiniteTransition(label = "equalizer_pulse")
            val bar1Height by infiniteTransition.animateFloat(
                initialValue = 4f,
                targetValue = 20f,
                animationSpec = infiniteRepeatable(
                    animation = tween(450, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "bar1"
            )
            val bar2Height by infiniteTransition.animateFloat(
                initialValue = 6f,
                targetValue = 24f,
                animationSpec = infiniteRepeatable(
                    animation = tween(350, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "bar2"
            )
            val bar3Height by infiniteTransition.animateFloat(
                initialValue = 5f,
                targetValue = 18f,
                animationSpec = infiniteRepeatable(
                    animation = tween(550, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "bar3"
            )
            val bar4Height by infiniteTransition.animateFloat(
                initialValue = 3f,
                targetValue = 22f,
                animationSpec = infiniteRepeatable(
                    animation = tween(400, easing = LinearEasing),
                    repeatMode = RepeatMode.Reverse
                ),
                label = "bar4"
            )

            Card(
                colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                border = androidx.compose.foundation.BorderStroke(
                    1.dp,
                    if (isSpeaking) PrimaryTeal.copy(alpha = 0.45f) else MutedSeparator
                )
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .background(
                                        if (isSpeaking) PrimaryTeal else SurfaceVariantDark,
                                        CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "Audio Reader",
                                    tint = if (isSpeaking) DeepBackground else PrimaryTeal,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = "NanoSpeech™ Audio-Guide",
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                                Text(
                                    text = "Deep-Apologetics High Quality Reader",
                                    fontSize = 9.sp,
                                    color = TextMuted
                                )
                            }
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            if (isSpeaking) {
                                Text(
                                    text = "Spielt ab...",
                                    fontSize = 10.sp,
                                    color = AccentGold,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Row(
                                modifier = Modifier.height(24.dp),
                                verticalAlignment = Alignment.Bottom,
                                horizontalArrangement = Arrangement.spacedBy(3.dp)
                            ) {
                                listOf(bar1Height, bar2Height, bar3Height, bar4Height).forEach { hVal ->
                                    val actualHeight = if (isSpeaking) hVal.dp else 4.dp
                                    Box(
                                        modifier = Modifier
                                            .width(3.dp)
                                            .height(actualHeight)
                                            .background(if (isSpeaking) AccentGold else TextMuted.copy(alpha = 0.4f), RoundedCornerShape(2.dp))
                                    )
                                }
                            }
                        }
                    }

                    Text(
                        text = "Lasse dir die komplette exegetische Auslegung, den Skeptiker-Einwand und alle Argumentationsteilschritte in flüssiger, exzellenter deutscher Sprache vorlesen.",
                        fontSize = 11.sp,
                        color = TextMuted,
                        lineHeight = 15.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Vorlese-Geschwindigkeit", fontSize = 10.sp, color = TextMuted)
                                Text("${String.format("%.1f", speechRate)}x", fontSize = 10.sp, color = PrimaryTeal, fontWeight = FontWeight.Bold)
                            }
                            Slider(
                                value = speechRate,
                                onValueChange = { speechRate = it },
                                valueRange = 0.5f..2.0f,
                                colors = SliderDefaults.colors(
                                    thumbColor = PrimaryTeal,
                                    activeTrackColor = PrimaryTeal,
                                    inactiveTrackColor = MutedSeparator
                                ),
                                modifier = Modifier.height(18.dp)
                            )
                        }

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text("Stimmhöhe (Pitch)", fontSize = 10.sp, color = TextMuted)
                                Text("${String.format("%.1f", speechPitch)}x", fontSize = 10.sp, color = AccentGold, fontWeight = FontWeight.Bold)
                            }
                            Slider(
                                value = speechPitch,
                                onValueChange = { speechPitch = it },
                                valueRange = 0.5f..1.5f,
                                colors = SliderDefaults.colors(
                                    thumbColor = AccentGold,
                                    activeTrackColor = AccentGold,
                                    inactiveTrackColor = MutedSeparator
                                ),
                                modifier = Modifier.height(18.dp)
                            )
                        }
                    }

                    Button(
                        onClick = {
                            if (isSpeaking) {
                                try {
                                    tts?.stop()
                                    isSpeaking = false
                                    currentTtsSection = null
                                } catch (e: Exception) {
                                    e.printStackTrace()
                                }
                            } else {
                                tts?.let { player ->
                                    try {
                                        player.stop()
                                        player.setSpeechRate(speechRate)
                                        player.setPitch(speechPitch)
                                        
                                        player.speak("Theologische Exegese zu: ${thema.title}", TextToSpeech.QUEUE_FLUSH, null, "header")
                                        player.playSilentUtterance(400, TextToSpeech.QUEUE_ADD, "space1")
                                        player.speak("Bibelstelle: ${thema.scriptureRefs}", TextToSpeech.QUEUE_ADD, null, "refs")
                                        player.playSilentUtterance(500, TextToSpeech.QUEUE_ADD, "space2")
                                        player.speak("Einwand der Kritiker: ${thema.objection}", TextToSpeech.QUEUE_ADD, null, "objection")
                                        player.playSilentUtterance(600, TextToSpeech.QUEUE_ADD, "space3")
                                        
                                        thema.sections.forEachIndexed { idx, section ->
                                            player.speak("Schritt ${idx + 1}: ${section.title}", TextToSpeech.QUEUE_ADD, null, "section_title_$idx")
                                            player.playSilentUtterance(300, TextToSpeech.QUEUE_ADD, "space_title_$idx")
                                            player.speak(section.content, TextToSpeech.QUEUE_ADD, null, "section_content_$idx")
                                            player.playSilentUtterance(600, TextToSpeech.QUEUE_ADD, "space_content_$idx")
                                        }
                                        isSpeaking = true
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSpeaking) ErrorRed else PrimaryTeal
                        ),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = if (isSpeaking) Icons.Default.Close else Icons.Default.PlayArrow,
                                contentDescription = if (isSpeaking) "Stoppen" else "Anhören",
                                tint = DeepBackground,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = if (isSpeaking) "Vortrag stoppen" else "Ganze Exegese anhören",
                                fontSize = 12.sp,
                                color = DeepBackground,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            // Objection warning card (The Opposing Claim)
            Card(
                colors = CardDefaults.cardColors(containerColor = ErrorRed.copy(alpha = 0.08f)),
                border = androidx.compose.foundation.BorderStroke(1.dp, ErrorRed.copy(alpha = 0.3f)),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(Icons.Default.Warning, contentDescription = "Einwand", tint = ErrorRed, modifier = Modifier.size(20.dp))
                        Text("Skeptischer Einwand / Argument", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ErrorRed)
                    }
                    Text(
                        text = thema.objection,
                        fontSize = 13.sp,
                        color = TextPrimary,
                        fontStyle = FontStyle.Italic,
                        lineHeight = 20.sp
                    )
                }
            }

            // Custom dynamic Canvas-drawn theological scheme/diagram depending on article contents
            Text("Theologische Schema-Grafik", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .border(2.dp, MutedSeparator, RoundedCornerShape(16.dp))
                    .background(SurfaceVariantDark),
                contentAlignment = Alignment.Center
            ) {
                when (thema.id) {
                    "olbaum_israel", "apg_15_21_joch", "paulinisches_paradoxon", "neuer_bund" -> OliveTreeIllustration()
                    "kolosser_2_16", "matthaeus_5_17", "hebraeer_7_12", "feste_des_herrn" -> TorahSchattenIllustration()
                    else -> LawGraceBridgeIllustration()
                }
            }

            // Refutation Article Sections / Exegesis steps
            Text("Ausführliche Widerlegung & Analyse", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)

            thema.sections.forEachIndexed { i, section ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(SurfaceSlate, RoundedCornerShape(16.dp))
                        .border(1.dp, MutedSeparator, RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(24.dp)
                                    .background(PrimaryTeal, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text("${i + 1}", color = DeepBackground, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                            }
                            Text(text = section.title, fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        }

                        // Copy and play section text controls
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            val isThisSectionPlaying = isSpeaking && currentTtsSection == "section_single_${i}"
                            IconButton(onClick = {
                                if (isThisSectionPlaying) {
                                    try {
                                        tts?.stop()
                                        isSpeaking = false
                                        currentTtsSection = null
                                    } catch (e: Exception) {
                                        e.printStackTrace()
                                    }
                                } else {
                                    tts?.let { player ->
                                        try {
                                            player.stop()
                                            player.setSpeechRate(speechRate)
                                            player.setPitch(speechPitch)
                                            player.speak(
                                                "Abschnitt ${i + 1}: ${section.title}. ${section.content}",
                                                TextToSpeech.QUEUE_FLUSH,
                                                null,
                                                "section_single_${i}"
                                            )
                                            isSpeaking = true
                                            currentTtsSection = "section_single_${i}"
                                        } catch (e: Exception) {
                                            e.printStackTrace()
                                        }
                                    }
                                }
                            }) {
                                Icon(
                                    imageVector = if (isThisSectionPlaying) Icons.Default.Close else Icons.Default.PlayArrow,
                                    contentDescription = "Diesen Abschnitt anhören",
                                    tint = if (isThisSectionPlaying) ErrorRed else AccentGold,
                                    modifier = Modifier.size(16.dp)
                                )
                            }

                            IconButton(onClick = {
                                val copyText = "${section.title}\n\n${section.content}"
                                clipboardManager.setText(AnnotatedString(copyText))
                                Toast.makeText(context, "Abschnitt in Zwischenablage kopiert!", Toast.LENGTH_SHORT).show()
                            }) {
                                Icon(Icons.Default.Edit, contentDescription = "Kopieren", tint = PrimaryTeal, modifier = Modifier.size(16.dp))
                            }
                        }
                    }

                    // Key highlights (if any, like greek vocab differences)
                    if (section.highlights.isNotEmpty()) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .background(SurfaceVariantDark, RoundedCornerShape(8.dp))
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            section.highlights.forEach { keyword ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(Icons.Default.Check, contentDescription = "Highlight", tint = AccentGold, modifier = Modifier.size(14.dp))
                                    Text(keyword, fontSize = 11.sp, fontWeight = FontWeight.Bold, color = AccentGold)
                                }
                            }
                        }
                    }

                    // Section body paragraph
                    Text(
                        text = section.content,
                        fontSize = 14.sp,
                        color = TextPrimary,
                        lineHeight = 22.sp
                    )
                }
            }

            // Bottom Spacer
            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}
