package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class AppTab {
    HOME, THEMEN, TRAINING
}

class ApologeticsViewModel(private val repository: ApologeticsRepository) : ViewModel() {

    // Bottom Navigation State
    private val _currentTab = MutableStateFlow(AppTab.HOME)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

    // Search and Filtering State for "Themen"
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow("Alle")
    val selectedCategory: StateFlow<String> = _selectedCategory.asStateFlow()

    // Active Study Thema (Detail View)
    private val _selectedThema = MutableStateFlow<ThemaItem?>(null)
    val selectedThema: StateFlow<ThemaItem?> = _selectedThema.asStateFlow()

    // Room Persistent Notes Database
    val userNotes: StateFlow<List<UserNote>> = repository.allNotes
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _trophies = MutableStateFlow(17)
    val trophies: StateFlow<Int> = _trophies.asStateFlow()

    // Interactive Quiz State
    private val _quizQuestions = MutableStateFlow(TheologyData.QUIZ_QUESTIONS)
    val quizQuestions: StateFlow<List<QuizQuestion>> = _quizQuestions.asStateFlow()

    private val _currentQuestionIndex = MutableStateFlow(0)
    val currentQuestionIndex: StateFlow<Int> = _currentQuestionIndex.asStateFlow()

    private val _selectedAnswerIndex = MutableStateFlow<Int?>(null)
    val selectedAnswerIndex: StateFlow<Int?> = _selectedAnswerIndex.asStateFlow()

    private val _isAnswerChecked = MutableStateFlow(false)
    val isAnswerChecked: StateFlow<Boolean> = _isAnswerChecked.asStateFlow()

    private val _quizScore = MutableStateFlow(0)
    val quizScore: StateFlow<Int> = _quizScore.asStateFlow()

    private val _isQuizStarted = MutableStateFlow(false)
    val isQuizStarted: StateFlow<Boolean> = _isQuizStarted.asStateFlow()

    private val _isQuizFinished = MutableStateFlow(false)
    val isQuizFinished: StateFlow<Boolean> = _isQuizFinished.asStateFlow()

    // Note Editor Modal State
    private val _editingNote = MutableStateFlow<UserNote?>(null)
    val editingNote: StateFlow<UserNote?> = _editingNote.asStateFlow()

    init {
        viewModelScope.launch {
            _trophies.value = repository.getTrophies()
        }
    }

    fun selectTab(tab: AppTab) {
        _currentTab.value = tab
        // Clear selected topic when switching main tabs to return to list
        if (tab != AppTab.THEMEN) {
            _selectedThema.value = null
        }
    }

    fun selectThema(thema: ThemaItem?) {
        _selectedThema.value = thema
        if (thema != null) {
            _currentTab.value = AppTab.THEMEN
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    // Room Database Operations (suspend delegated via Coroutines)
    fun addNote(title: String, content: String) {
        viewModelScope.launch {
            repository.insertNote(UserNote(title = title, content = content))
        }
    }

    fun updateNote(id: Int, title: String, content: String) {
        viewModelScope.launch {
            repository.updateNote(UserNote(id = id, title = title, content = content))
            _editingNote.value = null
        }
    }

    fun deleteNote(note: UserNote) {
        viewModelScope.launch {
            repository.deleteNote(note)
            if (_editingNote.value?.id == note.id) {
                _editingNote.value = null
            }
        }
    }

    fun startEditingNote(note: UserNote?) {
        _editingNote.value = note
    }

    // Interactive Apologetics Quiz Trainer Actions
    fun startQuiz() {
        _currentQuestionIndex.value = 0
        _selectedAnswerIndex.value = null
        _isAnswerChecked.value = false
        _quizScore.value = 0
        _isQuizStarted.value = true
        _isQuizFinished.value = false
        // Shuffle and take 12 random questions to make training highly replayable and dynamic
        _quizQuestions.value = TheologyData.QUIZ_QUESTIONS.shuffled().take(12)
    }

    fun selectAnswer(index: Int) {
        if (!_isAnswerChecked.value) {
            _selectedAnswerIndex.value = index
        }
    }

    fun submitAnswer() {
        val selected = _selectedAnswerIndex.value ?: return
        _isAnswerChecked.value = true
        val currentQuestion = _quizQuestions.value[_currentQuestionIndex.value]
        if (selected == currentQuestion.correctIndex) {
            _quizScore.value += 1
            // Earn a trophy instantly!
            viewModelScope.launch {
                val newTrophies = _trophies.value + 1
                _trophies.value = newTrophies
                repository.saveTrophies(newTrophies)
            }
        }
    }

    fun nextQuestion() {
        _selectedAnswerIndex.value = null
        _isAnswerChecked.value = false
        if (_currentQuestionIndex.value + 1 < _quizQuestions.value.size) {
            _currentQuestionIndex.value += 1
        } else {
            _isQuizFinished.value = true
            _isQuizStarted.value = false
        }
    }

    fun resetQuiz() {
        _isQuizStarted.value = false
        _isQuizFinished.value = false
        _currentQuestionIndex.value = 0
    }
}
