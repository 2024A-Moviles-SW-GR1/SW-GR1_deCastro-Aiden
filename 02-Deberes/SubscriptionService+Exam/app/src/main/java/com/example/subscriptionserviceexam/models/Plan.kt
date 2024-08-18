package com.example.subscriptionserviceexam.models

data class Plan(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val duration: Int
)