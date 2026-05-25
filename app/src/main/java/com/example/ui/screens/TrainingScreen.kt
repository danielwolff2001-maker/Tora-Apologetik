package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.UserNote
import com.example.data.TheologyData
import com.example.ui.theme.*
import com.example.viewmodel.ApologeticsViewModel

@Composable
fun TrainingScreen(viewModel: ApologeticsViewModel) {
    val isQuizStarted by viewModel.isQuizStarted.collectAsState()
    val isQuizFinished by viewModel.isQuizFinished.collectAsState()
    val userNotes by viewModel.userNotes.collectAsState()

    var showNotesEditor by remember { mutableStateOf(false) }
    var showFallaciesList by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepBackground)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Dynamic Title
        if (!isQuizStarted && !isQuizFinished) {
            Column {
                Text(
                    text = "Aktivierungs-Training",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "Trainiere Argumente und entlarve falsche Vorwürfe",
                    fontSize = 13.sp,
                    color = TextMuted
                )
            }
        }

        // Animated Area
        AnimatedContent(
            targetState = QuizSceneState(isQuizStarted, isQuizFinished),
            label = "QuizSceneAnimation"
        ) { state ->
            when {
                state.isFinished -> {
                    QuizFinishedCard(
                        viewModel = viewModel,
                        onReset = { viewModel.resetQuiz() }
                    )
                }
                state.isStarted -> {
                    QuizActiveSession(viewModel = viewModel)
                }
                else -> {
                    // Standard Dashboard View (Exercising Menu)
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        QuizStandardOverviewCard(
                            viewModel = viewModel,
                            onStart = { viewModel.startQuiz() }
                        )

                        // Logical Fallacies Card button
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { showFallaciesList = !showFallaciesList },
                            colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
                            shape = RoundedCornerShape(16.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, MutedSeparator)
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(36.dp)
                                                .background(PrimaryTeal.copy(alpha = 0.12f), CircleShape),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Info,
                                                contentDescription = "Fallacies",
                                                tint = PrimaryTeal,
                                                modifier = Modifier.size(18.dp)
                                            )
                                        }
                                        Column {
                                            Text("Apol-Fehlschlüsse", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                                            Text("Argumentative Denkfehler entlarven", fontSize = 11.sp, color = TextMuted)
                                        }
                                    }

                                    Icon(
                                        imageVector = if (showFallaciesList) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                        contentDescription = "Expand",
                                        tint = TextMuted
                                    )
                                }

                                if (showFallaciesList) {
                                    HorizontalDivider(color = MutedSeparator, modifier = Modifier.padding(vertical = 4.dp))
                                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                        TheologyData.FALLACIES.forEach { fallacy ->
                                            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                                                Text(fallacy.title, fontSize = 13.sp, fontWeight = FontWeight.Bold, color = AccentGold)
                                                Text(fallacy.description, fontSize = 12.sp, color = TextPrimary)
                                                Text(fallacy.example, fontSize = 11.sp, color = ErrorRed, fontStyle = FontStyle.Italic)
                                                Text(fallacy.correction, fontSize = 12.sp, color = SuccessGreen, fontWeight = FontWeight.Medium)
                                                Spacer(modifier = Modifier.height(4.dp))
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        // Bibel-Memory Trainer (Auswendiglernen)
                        BibleMemoryTrainerSection()

                        Spacer(modifier = Modifier.height(12.dp))

                        // Room Saved Notes Workspace Dashboard
                        ApologeticsNotesDashboard(
                            notes = userNotes,
                            onAddNoteClicked = {
                                viewModel.startEditingNote(null)
                                showNotesEditor = true
                            },
                            onNoteSelected = { note ->
                                viewModel.startEditingNote(note)
                                showNotesEditor = true
                            },
                            onDeleteNote = { note -> viewModel.deleteNote(note) }
                        )

                        Spacer(modifier = Modifier.height(80.dp))
                    }
                }
            }
        }
    }

    // Modal Sheet representable Dialog to write or edit Notes
    if (showNotesEditor) {
        val activeNote by viewModel.editingNote.collectAsState()
        NoteEditorDialog(
            note = activeNote,
            onDismiss = { showNotesEditor = false },
            onSave = { title, content ->
                if (activeNote == null) {
                    viewModel.addNote(title, content)
                } else {
                    viewModel.updateNote(activeNote!!.id, title, content)
                }
                showNotesEditor = false
            }
        )
    }
}

data class QuizSceneState(val isStarted: Boolean, val isFinished: Boolean)

@Composable
fun QuizStandardOverviewCard(
    viewModel: ApologeticsViewModel,
    onStart: () -> Unit
) {
    val trophies by viewModel.trophies.collectAsState()

    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, MutedSeparator),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(AccentGold.copy(alpha = 0.15f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Star, contentDescription = "Quiz", tint = AccentGold, modifier = Modifier.size(24.dp))
                    }
                    Column {
                        Text(text = "Übungen", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                        Text(text = "12 apologetische Fragen pro Runde (Pool von 35)", fontSize = 12.sp, color = TextMuted)
                    }
                }

                Row(
                    modifier = Modifier
                        .background(PrimaryTeal.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Menu, contentDescription = "Trophies", tint = PrimaryTeal, modifier = Modifier.size(14.dp))
                    Text("$trophies Pokale", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryTeal)
                }
            }

            Text(
                text = "Trainiere mit echten messianischen Argumenten gegen typische Vorbehalte. Lerne feine Text-Nuancen im griechischen und hebräischen Urtext kennen.",
                fontSize = 13.sp,
                color = TextPrimary,
                lineHeight = 20.sp
            )

            // Difficulty counts
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DifficultyBadge("12 Leicht", "Einsteiger")
                DifficultyBadge("13 Mittel", "Medium")
                DifficultyBadge("10 Schwer", "Experte")
            }

            Button(
                onClick = onStart,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryTeal),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Jetzt üben", fontWeight = FontWeight.Bold, color = DeepBackground, fontSize = 15.sp)
            }
        }
    }
}

@Composable
fun DifficultyBadge(countLabel: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(SurfaceVariantDark, RoundedCornerShape(10.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        Text(text = countLabel, fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
        Text(text = label, fontSize = 10.sp, color = TextMuted)
    }
}

@Composable
fun QuizActiveSession(viewModel: ApologeticsViewModel) {
    val questions by viewModel.quizQuestions.collectAsState()
    val currentIndex by viewModel.currentQuestionIndex.collectAsState()
    val selectedIndex by viewModel.selectedAnswerIndex.collectAsState()
    val isChecked by viewModel.isAnswerChecked.collectAsState()
    val score by viewModel.quizScore.collectAsState()

    val currentQuestion = questions[currentIndex]

    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(2.dp, PrimaryTeal),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Frage ${currentIndex + 1} von ${questions.size}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryTeal
                )
                Box(
                    modifier = Modifier
                        .background(AccentGold.copy(alpha = 0.12f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        currentQuestion.difficulty.uppercase(),
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        color = AccentGold
                    )
                }
            }

            // Progress bar
            LinearProgressIndicator(
                progress = { (currentIndex + 1).toFloat() / questions.size.toFloat() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = PrimaryTeal,
                trackColor = MutedSeparator
            )

            // Current Question
            Text(
                text = currentQuestion.question,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                lineHeight = 22.sp
            )

            // Multiple choice option list
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                currentQuestion.options.forEachIndexed { optIndex, optionText ->
                    val isSelected = selectedIndex == optIndex
                    val isCorrect = currentQuestion.correctIndex == optIndex

                    val borderAccent = when {
                        isChecked && isCorrect -> SuccessGreen
                        isChecked && isSelected && !isCorrect -> ErrorRed
                        isSelected -> PrimaryTeal
                        else -> MutedSeparator
                    }

                    val optionBg = when {
                        isChecked && isCorrect -> SuccessGreen.copy(alpha = 0.08f)
                        isChecked && isSelected && !isCorrect -> ErrorRed.copy(alpha = 0.08f)
                        isSelected -> PrimaryTeal.copy(alpha = 0.05f)
                        else -> Color.Transparent
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(optionBg, RoundedCornerShape(12.dp))
                            .border(1.dp, borderAccent, RoundedCornerShape(12.dp))
                            .clickable(enabled = !isChecked) { viewModel.selectAnswer(optIndex) }
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        // Letter option mapping A, B, C, D
                        val letter = ('A'.code + optIndex).toChar()
                        Box(
                            modifier = Modifier
                                .size(24.dp)
                                .background(
                                    if (isSelected) PrimaryTeal else SurfaceVariantDark,
                                    CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                "$letter",
                                color = if (isSelected) DeepBackground else TextPrimary,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Text(
                            text = optionText,
                            color = TextPrimary,
                            fontSize = 13.sp,
                            modifier = Modifier.weight(1f)
                        )

                        // Checkmark/Cross status indicators
                        if (isChecked) {
                            if (isCorrect) {
                                Icon(Icons.Default.Check, contentDescription = "Richtig", tint = SuccessGreen)
                            } else if (isSelected) {
                                Icon(Icons.Default.Close, contentDescription = "Falsch", tint = ErrorRed)
                            }
                        }
                    }
                }
            }

            // Exegesis Explanation Panel after validation check on submit
            if (isChecked) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = SurfaceVariantDark),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(Icons.Default.Info, contentDescription = "Explanation", tint = AccentGold, modifier = Modifier.size(16.dp))
                            Text("Exegese & Erklärung", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AccentGold)
                        }
                        Text(
                            text = currentQuestion.explanation,
                            fontSize = 12.sp,
                            color = TextPrimary,
                            lineHeight = 18.sp
                        )
                    }
                }
            }

            // Control Trigger Button
            if (!isChecked) {
                Button(
                    onClick = { viewModel.submitAnswer() },
                    enabled = selectedIndex != null,
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryTeal),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Antwort überprüfen", fontWeight = FontWeight.Bold, color = DeepBackground)
                }
            } else {
                Button(
                    onClick = { viewModel.nextQuestion() },
                    colors = ButtonDefaults.buttonColors(containerColor = SuccessGreen),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = if (currentIndex + 1 < questions.size) "Nächste Frage" else "Ergebnisse ansehen",
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }
            }
        }
    }
}

@Composable
fun QuizFinishedCard(viewModel: ApologeticsViewModel, onReset: () -> Unit) {
    val questions by viewModel.quizQuestions.collectAsState()
    val score by viewModel.quizScore.collectAsState()

    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
        shape = RoundedCornerShape(16.dp),
        border = androidx.compose.foundation.BorderStroke(2.dp, SuccessGreen),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Banner/Header icon checkmark
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(SuccessGreen.copy(alpha = 0.15f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Success",
                    tint = SuccessGreen,
                    modifier = Modifier.size(36.dp)
                )
            }

            Text("Trainings-Einheit beendet!", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = TextPrimary)

            Text(
                text = "Du hast das apologetische Sonderszenario erfolgreich abgeschlossen und deine theologische Reaktionsgeschwindigkeit gegen Vorwürfe trainiert.",
                fontSize = 13.sp,
                color = TextMuted,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )

            // Score Dashboard
            Row(
                modifier = Modifier
                    .background(SurfaceVariantDark, RoundedCornerShape(12.dp))
                    .padding(horizontal = 24.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("$score / ${questions.size}", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = PrimaryTeal)
                    Text("Richtige Antworten", fontSize = 10.sp, color = TextMuted)
                }
                Box(
                    modifier = Modifier
                        .height(36.dp)
                        .width(1.dp)
                        .background(MutedSeparator)
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("+ $score", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = AccentGold)
                    Text("Pokale gewonnen", fontSize = 10.sp, color = TextMuted)
                }
            }

            Button(
                onClick = onReset,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryTeal),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Zurück zum Dashboard", fontWeight = FontWeight.Bold, color = DeepBackground)
            }
        }
    }
}

@Composable
fun ApologeticsNotesDashboard(
    notes: List<UserNote>,
    onAddNoteClicked: () -> Unit,
    onNoteSelected: (UserNote) -> Unit,
    onDeleteNote: (UserNote) -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Meine Notizen", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                Text(text = "${notes.size} Argumente gespeichert", fontSize = 11.sp, color = TextMuted)
            }

            Button(
                onClick = onAddNoteClicked,
                colors = ButtonDefaults.buttonColors(containerColor = SurfaceVariantDark),
                contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(Icons.Default.Add, contentDescription = "Add", tint = PrimaryTeal, modifier = Modifier.size(16.dp))
                    Text("Neu", fontSize = 12.sp, color = TextPrimary, fontWeight = FontWeight.Bold)
                }
            }
        }

        if (notes.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, MutedSeparator, RoundedCornerShape(12.dp))
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Icon(Icons.Default.Edit, contentDescription = "Notes Empty", tint = TextMuted)
                    Text("Noch keine Notizen vorhanden.", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                    Text("Erstelle hier eigene apologetische Notizen und Beweisketten.", fontSize = 11.sp, color = TextMuted, textAlign = TextAlign.Center)
                }
            }
        } else {
            notes.forEach { note ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNoteSelected(note) },
                    colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(note.title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = TextPrimary)
                            IconButton(onClick = { onDeleteNote(note) }, modifier = Modifier.size(24.dp)) {
                                Icon(Icons.Default.Delete, contentDescription = "Löschen", tint = ErrorRed.copy(alpha = 0.7f), modifier = Modifier.size(16.dp))
                            }
                        }

                        Text(
                            text = note.content,
                            fontSize = 13.sp,
                            color = TextMuted,
                            maxLines = 2
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditorDialog(
    note: UserNote?,
    onDismiss: () -> Unit,
    onSave: (String, String) -> Unit
) {
    var title by remember { mutableStateOf(note?.title ?: "") }
    var content by remember { mutableStateOf(note?.content ?: "") }
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            Button(
                onClick = {
                    if (title.isBlank() || content.isBlank()) {
                        Toast.makeText(context, "Bitte fülle alle Felder aus!", Toast.LENGTH_SHORT).show()
                    } else {
                        onSave(title, content)
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryTeal)
            ) {
                Text("Speichern", color = DeepBackground, fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Abbrechen", color = TextMuted)
            }
        },
        title = {
            Text(
                text = if (note == null) "Notiz erstellen" else "Notiz bearbeiten",
                color = TextPrimary,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Titel / Bibelstelle", color = TextMuted) },
                    placeholder = { Text("z.B. Markus 7 Reinheit", color = TextMuted) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryTeal,
                        unfocusedBorderColor = MutedSeparator,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = content,
                    onValueChange = { content = it },
                    label = { Text("Inhalt / Argumentationskette", color = TextMuted) },
                    placeholder = { Text("Schreibe hier biblische Belege oder Gedanken dazu auf...", color = TextMuted) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PrimaryTeal,
                        unfocusedBorderColor = MutedSeparator,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                )
            }
        },
        containerColor = SurfaceSlate,
        shape = RoundedCornerShape(16.dp)
    )
}

@Composable
fun BibleMemoryTrainerSection() {
    var memorizedVerses by remember { mutableStateOf(setOf<String>()) }
    var expandedVerseIndex by remember { mutableStateOf<Int?>(null) }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Bibel-Memory Trainer",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
                Text(
                    text = "${memorizedVerses.size} von ${TheologyData.COMFORT_VERSES.size} Versen verinnerlicht",
                    fontSize = 11.sp,
                    color = TextMuted
                )
            }
            if (memorizedVerses.isNotEmpty() && memorizedVerses.size == TheologyData.COMFORT_VERSES.size) {
                Box(
                    modifier = Modifier
                        .background(SuccessGreen.copy(alpha = 0.15f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("Meister! 🏆", color = SuccessGreen, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        // Horizontal list of Scripture cards
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(TheologyData.COMFORT_VERSES) { index, item ->
                val isExpanded = expandedVerseIndex == index
                val isMemorized = memorizedVerses.contains(item.verse)

                Card(
                    colors = CardDefaults.cardColors(containerColor = SurfaceSlate),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .width(280.dp)
                        .animateContentSize(),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (isMemorized) SuccessGreen.copy(alpha = 0.5f) else PrimaryTeal.copy(alpha = 0.2f)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Category and Status
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .background(
                                        when (item.category) {
                                            "Geistlicher Missbrauch" -> ErrorRed.copy(alpha = 0.12f)
                                            "Trauma-Heilung" -> PrimaryTeal.copy(alpha = 0.12f)
                                            "Tora-Freude" -> AccentGold.copy(alpha = 0.12f)
                                            else -> SecondaryIce.copy(alpha = 0.12f)
                                        },
                                        RoundedCornerShape(6.dp)
                                    )
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text(
                                    text = item.category,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = when (item.category) {
                                        "Geistlicher Missbrauch" -> ErrorRed
                                        "Trauma-Heilung" -> PrimaryTeal
                                        "Tora-Freude" -> AccentGold
                                        else -> SecondaryIce
                                    }
                                )
                            }

                            // Memorized Toggle Checkbox Icon
                            Icon(
                                imageVector = if (isMemorized) Icons.Default.CheckCircle else Icons.Default.Add,
                                contentDescription = "Merken",
                                tint = if (isMemorized) SuccessGreen else TextMuted.copy(alpha = 0.6f),
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        memorizedVerses = if (isMemorized) {
                                            memorizedVerses - item.verse
                                        } else {
                                            memorizedVerses + item.verse
                                        }
                                    }
                            )
                        }

                        // Verse reference
                        Text(
                            text = item.verse,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = AccentGold
                        )

                        // Verse Text
                        Text(
                            text = item.text,
                            fontSize = 13.sp,
                            color = TextPrimary,
                            lineHeight = 18.sp,
                            fontStyle = FontStyle.Italic
                        )

                        HorizontalDivider(color = MutedSeparator, modifier = Modifier.padding(vertical = 4.dp))

                        // Devotion action row
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedVerseIndex = if (isExpanded) null else index
                                },
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = if (isExpanded) "Andacht verbergen ▲" else "Geistliche Andacht lesen ▼",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                                color = PrimaryTeal
                            )
                        }

                        if (isExpanded) {
                            Text(
                                text = item.devotion,
                                fontSize = 12.sp,
                                color = TextMuted,
                                lineHeight = 16.sp,
                                modifier = Modifier.padding(top = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
