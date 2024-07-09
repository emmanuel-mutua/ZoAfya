package com.example.zoafya.data.remote.services

import androidx.annotation.DrawableRes
import com.example.zoafya.R

data class Services(
    @DrawableRes val image : Int,
    val title : String,
    val time : String,
    val amount : String
)
object ServicesProviderObject {
    val services = listOf<Services>(
        Services(
            image = R.drawable.wellness,
            title = "Wellness Consultation",
            time = "1hr",
            amount = "3,000 Kenyan shillings"
        ),
        Services(
            image = R.drawable.nutrition,
            title = "Nutrition Session",
            time = "1hr",
            amount = "3,000 Kenyan shillings"
        ),
        Services(
            image = R.drawable.skincare,
            title = "Skincare Analysis",
            time = "1hr",
            amount = "2,000 Kenyan shillings"
        ),
        Services(
            image = R.drawable.psychotherapy,
            title = "Psychotherapy",
            time = "1hr",
            amount = "Price negotiable"
        ),
        Services(
            image = R.drawable.nutrition,
            title = "Psychotherapy",
            time = "1hr",
            amount = "Price negotiable"
        )
    )
}

