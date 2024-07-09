package com.example.zoafya.data.remote.services

import androidx.annotation.DrawableRes
import com.example.zoafya.R

data class Into(
    @DrawableRes val image : Int,
    val title : String,
    val description : String
)
object IntroProviderObject {
    val intro = listOf<Into>(
        Into(
            image = R.drawable.image,
            title = "At the Heart of our Platform",
            description = "We are committed to revolutionizing Mental Health Care. By seamlessly connecting patients with certified Professionals, we aim to break down barriers, reduce stigma, and provide accessible, personalized medical support. ZoAfya ensures that every patient receives the empathy, care, and expertise they need to navigate their health journey effectively"
        ),
        Into(
            image = R.drawable.image,
            title = "Your All-in-One Health Portal",
            description = "ZoAfya is your comprehensive health management platform, designed for flexibility and accessibility. Our portal allows you to effortlessly schedule appointments, and access medical services from anywhere at any time. With ZoAfya, you are exposed to a wide array of medical specialists, from primary care doctors to highly specialized consultants. This seamless integration ensures that you can easily find and connect with the right healthcare professionals to meet your unique needs, making healthcare more convenient and tailored to your lifestyle"
        ),
        Into(
            image = R.drawable.image,
            title = "Empowering Patients, Transforming Lives",
            description = "At ZoAfya, we empower patients by providing easy access to a network of dedicated healthcare professionals across various specialties. Our platform transforms lives by offering personalized care solutions tailored to individual needs, from chronic disease management to preventive care to secure and private mental health care. ZoAfya's approach guarantees that you receive the right support and guidance, helping you achieve better health outcomes and a higher quality of life."
        )
    )
}