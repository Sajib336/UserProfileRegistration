package com.example.userprofileregistration.View

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.userprofileregistration.R

class MainActivity  : AppCompatActivity() {
    private lateinit var listButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        listButton = findViewById(R.id.profileListBtn)

        listButton.setOnClickListener {
            // Start LoadingActivity instead of ProfileListActivity
            val intent = Intent(this,LodingAcivity::class.java)
            intent.putExtra("TARGET_ACTIVITY", "com.example.userprofileregistration.View.ProfileListActivity")
            startActivity(intent)
            finish() // Finish MainActivity24 so the user can't return to it
        }


    }


}