package com.example.androidproject.tripsScreen

import androidx.annotation.DrawableRes
import com.example.androidproject.R

data class TripItem(
    val id: Int,
    val title: String,
    val price: String,
    val description: String,
    @DrawableRes val imageRes: Int
)

val defaultTrips = listOf(
    TripItem(
        id = 1,
        title = "Barcelona, 3 nights",
        price = "300 EUR",
        description = "Spain's largest port and second largest city, it is the country's principal industrial and commercial centre, as well as a major cultural and educational centre. It is said to have been founded in the 3rd century bc by the Carthaginians or the Phoenicians, and it was later ruled by the Romans and Visigoths.",
        imageRes = R.drawable.img_barcelona
    ),
    TripItem(
        id = 2,
        title = "Maldive, 7 nights",
        price = "1050 EUR",
        description = "The Maldives is an independent island country in the north-central Indian Ocean. The islands that make up the country extend more than 510 miles (820 km) from north to south and 80 miles (130 km) from east to west. The northernmost atoll is about 370 miles (600 km) south-southwest of the Indian mainland.",
        imageRes = R.drawable.img_maldives
    ),
    TripItem(
        id = 3,
        title = "Thailand, 10 nights",
        price = "890 EUR",
        description = "Thailand is a Southeast Asian country known for tropical beaches, opulent royal palaces, ancient ruins, and ornate temples displaying figures of Buddha.",
        imageRes = R.drawable.img_thailand
    )
)