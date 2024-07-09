package com.example.zoafya.ui.user.navigation

import com.example.zoafya.R


sealed class CustomerHomeDestinations(
    val route: String,
    val label: String,
    val icon: Int,
) {
    object Home : CustomerHomeDestinations(
        route = "home",
        label = "Home",
        icon = R.drawable.baseline_home,
    )

    object Services : CustomerHomeDestinations(
        route = "services",
        label = "Services",
        icon = R.drawable.baseline_miscellaneous_services_24,
    )


    object Chatbot : CustomerHomeDestinations(
        route = "Chatbot",
        label = "Chatbot",
        icon = R.drawable.baseline_chat_24,
    )
    object Contact : CustomerHomeDestinations(
        route = "contact",
        label = "Contact",
        icon = R.drawable.baseline_perm_contact_calendar_24,
    )
}
