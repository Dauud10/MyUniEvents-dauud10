package com.example.myunievents.data

import kotlinx.coroutines.flow.Flow

object EventRepository {

    private lateinit var dao: EventDao

    fun initialize(eventDao: EventDao) {
        dao = eventDao
    }

    suspend fun insertEvent(event: Event) {
        dao.insertEvent(event)
    }

    fun getAllEvents(): Flow<List<Event>> {
        return dao.getAllEvents()
    }
}
