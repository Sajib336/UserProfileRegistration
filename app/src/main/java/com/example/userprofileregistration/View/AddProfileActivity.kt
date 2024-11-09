package com.example.userprofileregistration.View

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.userprofileregistration.Model.Profile
import com.example.userprofileregistration.R
import com.example.userprofileregistration.ViewModel.ProfileViewModel

class AddProfileActivity : AppCompatActivity() {
    private lateinit var profileViewModel24: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_profile)

        profileViewModel24 = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val nameEditText = findViewById<EditText>(R.id.profileNameEt)
        val emailEditText = findViewById<EditText>(R.id.profileEmailEt)
        val dobEditText = findViewById<EditText>(R.id.profileDOBEt)
        val districtEditText = findViewById<EditText>(R.id.profileDistrictEt)
        val mobileEditText = findViewById<EditText>(R.id.profilemobileEt)

        findViewById<Button>(R.id.addBtn).setOnClickListener {
            val name = nameEditText.text.toString()
            val email = emailEditText.text.toString()
            val dob = dobEditText.text.toString()
            val district = districtEditText.text.toString()
            val mobile = mobileEditText.text.toString()

            val userProfile = Profile(name = name, email = email, dob = dob, district = district, mobile = mobile)
            profileViewModel24.insertUserProfile(userProfile)

            finish()
        }
    }
}