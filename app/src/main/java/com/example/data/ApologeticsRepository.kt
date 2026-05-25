package com.example.data

import kotlinx.coroutines.flow.Flow

class ApologeticsRepository(private val database: AppDatabase) {
    val allNotes: Flow<List<UserNote>> = database.userNoteDao().getAllNotes()

    suspend fun insertNote(note: UserNote) {
        database.userNoteDao().insertNote(note)
    }

    suspend fun updateNote(note: UserNote) {
        database.userNoteDao().updateNote(note)
    }

    suspend fun deleteNote(note: UserNote) {
        database.userNoteDao().deleteNote(note)
    }

    suspend fun getTrophies(): Int {
        return database.userStatsDao().getStatsByKey("trophies")?.value ?: 17
    }

    suspend fun saveTrophies(count: Int) {
        database.userStatsDao().insertStats(UserStats("trophies", count))
    }
}
