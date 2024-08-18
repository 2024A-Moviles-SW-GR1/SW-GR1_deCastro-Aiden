package com.example.subscriptionserviceexam

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn_users).setOnClickListener {
            startActivity(Intent(this, UserListActivity::class.java))
        }

        findViewById<Button>(R.id.btn_subscriptions).setOnClickListener {
            startActivity(Intent(this, SubscriptionListActivity::class.java))
        }

        findViewById<Button>(R.id.btn_plans).setOnClickListener {
            startActivity(Intent(this, PlanListActivity::class.java))
        }
    }
}