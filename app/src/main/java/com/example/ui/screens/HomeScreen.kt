package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ThemaItem
import com.example.data.TheologyData
import com.example.ui.components.TorahSchattenIllustration
import com.example.ui.theme.*
import com.example.viewmodel.ApologeticsViewModel

@Composable
fun HomeScreen(
    viewModel: ApologeticsViewModel,
    onNavigateToTopic: (ThemaItem) -> Unit
) {
    val context = LocalContext.current
    val trophies by viewModel.trophies.collectAsState()
    var showInfoDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // App Bar & Title
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Tora Apologetik",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "von Logos Apologetik",
                    fontSize = 14.sp,
                    color = PrimaryTeal,
                    fontWeight = FontWeight.Medium
                )
            }

            // Trophies indicator & Info icon
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .background(SurfaceVariantDark, RoundedCornerShape(12.dp))
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Trophies",
                        tint = AccentGold,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "$trophies",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                IconButton(
                    onClick = { showInfoDialog = true },
                    modifier = Modifier.background(SurfaceVariantDark, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Info",
                        tint = PrimaryTeal
                    )
                }
            }
        }

        // Feature Banner / Hero Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(SurfaceVariantDark)
                .clickable {
                    // Navigate to Psalm 119/Sabbat theme or similar
                    val shadowTopic = TheologyData.THEMEN.find { it.id == "kolosser_2_16" }
                    if (shadowTopic != null) {
                        onNavigateToTopic(shadowTopic)
                    }
                }
        ) {
            // Draw a decorative background illustration (Torah shadow)
            TorahSchattenIllustration(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            )

            // Dim Gradient Overlay for legible text
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color(0xDD111216)),
                            startY = 50f
                        )
                    )
            )

            // Text on Banner
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
            ) {
                Text(
                    text = "נר לרגלי דבריך ואור לנתיבתי",
                    color = AccentGold,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 2.dp)
                )
                Text(
                    text = "Die Tora - Ein Licht auf dem Weg",
                    color = TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Psalm 119:105 • Schatten & Wahrheit",
                    color = TextMuted,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        // Quote Card (Matthäus 5:17)
        Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = "„Ihr sollt nicht meinen, dass ich gekommen sei, um das Gesetz oder die Propheten aufzulösen. Ich bin nicht gekommen, um aufzulösen, sondern um zu erfüllen!\" ",
                    fontSize = 15.sp,
                    fontStyle = FontStyle.Italic,
                    color = TextPrimary,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "— Matthäus 5:17",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryTeal,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // NanoBanana Grafiken Banner
        Card(
            colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            border = androidx.compose.foundation.BorderStroke(1.dp, AccentGold.copy(alpha = 0.35f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "Renderer Status",
                        tint = AccentGold,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "NanoBanana™ Pro Graphics Engine",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
                Text(
                    text = "Alle geometrischen Schemata, exegetischen Schnittdiagramme und biblisch-theologischen Darstellungen in diesem Portal werden dynamisch in Echtzeit mit der NanoBanana™ Engine v3.5 rasiermesserscharf gerendert.",
                    fontSize = 11.sp,
                    color = TextMuted,
                    lineHeight = 15.sp
                )
            }
        }

        // Lehrvideos zur Vertiefung Section (Logos Apologetik)
        Text(
            text = "Lehrvideos zur Vertiefung (Logos Apologetik)",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(end = 16.dp)
        ) {
            val lehrVideos = listOf(
                Pair("Die Tora im Neuen Bund — Ist das Gesetz abgeschafft?", "Eine gründliche apologetische Analyse von Matthäus 5:17-19 und der wahren Bedeutung von 'Erfüllen' (pleroo)."),
                Pair("Sabbat oder Sonntag? Das historische Ringen", "Wie es historisch unter Kaiser Konstantin (321 n. Chr.) zur Verschiebung des wöchentlichen Ruhetags kam."),
                Pair("Der Galaterbrief: Befreit vom Gesetz oder Fluch?", "Die Wahrheit über 'Gesetzeswerke' (Ma'ase haTorah) im Licht der Qumran-Funde (4QMMT) und die Rolle Jesu."),
                Pair("Das Apostelkonzil (Apostelgeschichte 15) gelöst!", "Die vier Einsteiger-Regeln für Heidenchristen und der stetige Jüngerschaftsplan am Sabbat laut Apostelgeschichte 15:21."),
                Pair("Sind alle Speisen rein? Eine Exegese zu Markus 7", "Der theologische Unterschied zwischen rituell unrein gewaschenen Händen (koinos) und unreiner Schöpfung (akathartos)."),
                Pair("Petrus' Vision (Apg 10): Was wurde wirklich rein?", "Detaillierte Analyse der Tuchvision. Petrus klärt selbst auf: Gott hat ihm gezeigt, keinen MENSCHEN unrein zu nennen!"),
                Pair("Kolosser 2:16: 'Niemand richte euch über Sabbat und Feste'", "Die grammatikalische Untersuchung von 'Schatten der zukünftigen Dinge' und wie unbiblische Wörter ('nur') eingefügt wurden."),
                Pair("Hebräerbrief Kapitel 7: Eine Änderung des Gesetzes?", "Was 'metathesis' (Verschiebung des Ortes) bedeutet und warum Jesus als Priester nach der Ordnung Melchisedeks im Himmel dient."),
                Pair("Ist die Tora nur für Juden? Die Fremdlinge in Jesaja 56", "Wer den Sabbat hält und sich an den Bund klammert: Gottes Heilsversprechen für alle Heidenvölker an Seinem heiligen Berg."),
                Pair("Matthäus 5:19: Gering oder Groß im Himmelreich?", "Die ewige Bedeutung des Lehrens und Tuns aller Gebote Gottes und why Jeschua zwar Legalismus tadelt, Gehorsam jedoch belohnt.")
            )

            items(lehrVideos) { (title, desc) ->
                Card(
                    modifier = Modifier
                        .width(260.dp)
                        .height(160.dp)
                        .clickable {
                            try {
                                val url = "https://www.youtube.com/@LogosApologetik/search?query=" + Uri.encode(title)
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/@LogosApologetik"))
                                context.startActivity(intent)
                            }
                        },
                    colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MutedSeparator)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = "Play",
                                        tint = PrimaryTeal,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "VIDEO-LEKTION",
                                        fontSize = 10.sp,
                                        color = PrimaryTeal,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .background(SurfaceVariantDark, RoundedCornerShape(4.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Text(
                                        text = "YouTube",
                                        color = AccentGold,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }

                            Text(
                                text = title,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                                lineHeight = 17.sp
                            )
                        }

                        Text(
                            text = desc,
                            fontSize = 11.sp,
                            color = TextMuted,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            lineHeight = 14.sp
                        )
                    }
                }
            }
        }

        // Media Links Header
        Text(
            text = "Mediathek & Web-Portale",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary
        )

        // Navigation/Media Link buttons in vertical arrangement - Comprehensive 9 links
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val officialLinks = listOf(
                Triple("Startseite", "logosapologetik.de/index.html", "https://logosapologetik.de/index.html"),
                Triple("Über uns", "logosapologetik.de/html1.html", "https://logosapologetik.de/html1.html"),
                Triple("Flyer & Material", "logosapologetik.de/html7.html", "https://logosapologetik.de/html7.html"),
                Triple("Downloadbereich", "logosapologetik.de/html8.html", "https://logosapologetik.de/html8.html"),
                Triple("Unterstütz uns", "PayPal Spende", "https://paypal.com/donate/?hosted_button_id=A8M2NUAG77K5N"),
                Triple("Telegram Kanal", "t.me/logosapologetikmedia", "https://t.me/logosapologetikmedia"),
                Triple("Zuschauer Q&A", "t.me/9UuwiRUxWM8yYzJi", "https://t.me/9UuwiRUxWM8yYzJi"),
                Triple("Sabbat Gemeinde", "Gemeinschaft finden", "https://t.me/MessianerDeutschland")
            )

            // Dynamic grid layout (2 items per row)
            officialLinks.chunked(2).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    rowItems.forEach { (name, label, url) ->
                        Card(
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    try {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                                        context.startActivity(intent)
                                    } catch (e: Exception) {
                                        android.widget.Toast.makeText(
                                            context,
                                            "Link kann hier nicht geöffnet werden (Browser erforderlich)",
                                            android.widget.Toast.LENGTH_LONG
                                        ).show()
                                    }
                                },
                            colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(12.dp),
                                verticalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    val (icon, tint) = when (name) {
                                        "Startseite" -> Icons.Default.Home to PrimaryTeal
                                        "Über uns" -> Icons.Default.AccountBox to SecondaryIce
                                        "Flyer & Material" -> Icons.Default.Info to AccentGold
                                        "Downloadbereich" -> Icons.AutoMirrored.Filled.List to SuccessGreen
                                        "Unterstütz uns" -> Icons.Default.Favorite to ErrorRed
                                        "Telegram Kanal" -> Icons.AutoMirrored.Filled.Send to SecondaryIce
                                        "Zuschauer Q&A" -> Icons.Default.MailOutline to PrimaryTeal
                                        else -> Icons.Default.CheckCircle to SuccessGreen
                                    }
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = name,
                                        tint = tint,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Text(
                                        text = name,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )
                                }
                                Text(
                                    text = label,
                                    fontSize = 11.sp,
                                    color = TextMuted,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                        }
                    }
                }
            }

            // Big Dedicated Impressum & Legal Statement Button
            Button(
                onClick = { showInfoDialog = true },
                colors = ButtonDefaults.buttonColors(containerColor = SurfaceSlate),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, MutedSeparator)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.Default.Info, contentDescription = "Impressum", tint = AccentGold)
                    Text("Impressum & Rechtliches ansehen", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp)) // generous trailing padding to secure navigations
    }

    // App Info Dialog + Official Impressum from files
    if (showInfoDialog) {
        AlertDialog(
            onDismissRequest = { showInfoDialog = false },
            confirmButton = {
                TextButton(onClick = { showInfoDialog = false }) {
                    Text("Verstanden", color = PrimaryTeal, fontWeight = FontWeight.Bold)
                }
            },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(Icons.Default.Info, contentDescription = "Info", tint = PrimaryTeal)
                    Text("Impressum & Rechtliche Hinweise", color = TextPrimary, fontWeight = FontWeight.Bold)
                }
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Logos Apologetik App, entwickelt zur Verteidigung und Lehre der fortdauernden Gültigkeit der Tora im Neuen Bund.",
                        fontSize = 14.sp,
                        color = TextPrimary,
                        lineHeight = 20.sp
                    )

                    HorizontalDivider(color = MutedSeparator)

                    Text(
                        text = "Ansprechpartner & Betreiber:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryTeal
                    )
                    Text(
                        text = "Daniel Wolff\nE-Mail: danielwolff2001@gmail.com\nWebsite: www.logosapologetik.de",
                        fontSize = 13.sp,
                        color = TextPrimary
                    )

                    HorizontalDivider(color = MutedSeparator)

                    Text(
                        text = "Theologische Inhalte & Referenzen:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryTeal
                    )
                    Text(
                        text = "Die theologischen Argumente, Skripte und deutschen Übersetzungen basieren zu 90% auf der exzellenten Arbeit von 119 Ministries (www.119ministries.com/teachings/).",
                        fontSize = 13.sp,
                        color = TextMuted,
                        lineHeight = 18.sp
                    )

                    HorizontalDivider(color = MutedSeparator)

                    Text(
                        text = "Haftungsausschluss / Disclaimer:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentGold
                    )
                    Text(
                        text = "Sämtliche hier dargebotenen Abhandlungen dienen ausschließlich der religiösen Weiterbildung. Wir fordern jeden Nutzer auf, alle behandelten Auslegungen und Behauptungen selbstständig und vorurteilsfrei direkt an der Heiligen Schrift zu prüfen (Apg 17:11).",
                        fontSize = 12.sp,
                        color = TextMuted,
                        lineHeight = 16.sp,
                        fontStyle = FontStyle.Italic
                    )
                }
            },
            containerColor = SurfaceSlate,
            shape = RoundedCornerShape(16.dp)
        )
    }
}
