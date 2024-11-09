    package com.example.userprofileregistration.View

    import android.content.Intent
    import android.os.Bundle
    import android.os.Handler
    import android.os.Looper
    import androidx.activity.enableEdgeToEdge
    import androidx.appcompat.app.AppCompatActivity
    import androidx.core.view.ViewCompat
    import androidx.core.view.WindowInsetsCompat
    import com.example.userprofileregistration.Model.Profile
    import com.example.userprofileregistration.R

    class LodingAcivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            enableEdgeToEdge()
            setContentView(R.layout.activity_loding_acivity )
            // Simulate loading or some processing time
            Handler(Looper.getMainLooper()).postDelayed({
                // Retrieve the target activity and user profile
                val targetActivityName = intent.getStringExtra("TARGET_ACTIVITY")
                val userProfile = intent.getSerializableExtra("USER_PROFILE") as Profile?

                // Navigate to the target activity
                val targetIntent = Intent()
                targetIntent.setClassName(this, targetActivityName ?: return@postDelayed)
                userProfile?.let {
                    targetIntent.putExtra("USER_PROFILE", it)
                }

                startActivity(targetIntent)
                finish() // Finish this activity so the user can't return to it
            }, 1000) // 3000 milliseconds = 3 seconds
        }
    }