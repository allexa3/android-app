package com.example.androidproject.tripsScreen

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

data class TripListEntry(
    val instanceId: Int,
    val trip: TripItem
)

class TripsViewModel : ViewModel() {

    private var nextInstanceId = 1

    val trips = mutableStateListOf<TripListEntry>().apply {
        addAll(defaultTrips.map { TripListEntry(nextInstanceId++, it) })
    }

    fun addTrip(entry: TripListEntry) {
        val index = trips.indexOf(entry)
        val newEntry = TripListEntry(nextInstanceId++, entry.trip)
        if (index >= 0) {
            trips.add(index + 1, newEntry)
        } else {
            trips.add(newEntry)
        }
    }

    fun removeTrip(entry: TripListEntry) {
        trips.remove(entry)
    }

    fun resetList() {
        trips.clear()
        trips.addAll(defaultTrips.map { TripListEntry(nextInstanceId++, it) })
    }
}