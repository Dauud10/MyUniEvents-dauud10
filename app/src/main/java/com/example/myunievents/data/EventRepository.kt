package com.example.myunievents.data

class EventRepository(private val eventDao: EventDao) {

    suspend fun addEvent(event: Event) = eventDao.insertEvent(event)

    suspend fun getEvents() = eventDao.getAllEvents()
}
