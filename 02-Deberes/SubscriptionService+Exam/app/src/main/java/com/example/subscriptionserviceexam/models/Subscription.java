package com.example.subscriptionserviceexam.models;
data class Subscription(
        val id: Int,
        val userId: Int,
        val planId: Int,
        val startDate: Date,
        val endDate: Date,
        val status: String
)
