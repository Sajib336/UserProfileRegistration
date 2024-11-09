package com.example.userprofileregistration.View

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.userprofileregistration.Adapter.ProfileAdapter
import com.example.userprofileregistration.Model.Profile
import com.example.userprofileregistration.R
import com.example.userprofileregistration.ViewModel.ProfileViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProfileListActivity : AppCompatActivity() {
    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_list)

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.profileRecyclerView)
        profileAdapter = ProfileAdapter()

        recyclerView.adapter = profileAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Observe profiles from ViewModel
        profileViewModel.getUserProfiles().observe(this, Observer { profiles ->
            if (profiles.isEmpty()) {
                // Show a Toast message if there are no profiles
                Toast.makeText(this, "No profiles available", Toast.LENGTH_SHORT).show()
            } else {
                profileAdapter.submitList(profiles)
            }
        })

        // Set item click listener for details
        profileAdapter.setOnItemClickListener { profile ->
            val intent = Intent(this@ProfileListActivity, SingleProfileActivity::class.java)
            intent.putExtra("USER_PROFILE", profile)
            startActivity(intent)
        }

        // Set delete click listener
        profileAdapter.setOnDeleteClickListener { userProfile ->
            showDeleteConfirmationDialog(userProfile)
        }

        // Set update click listener
        profileAdapter.setOnUpdateClickListener { userProfile ->
            val intent = Intent(this@ProfileListActivity, UpdateProfileActivity::class.java)
            intent.putExtra("USER_PROFILE", userProfile)
            startActivity(intent)
        }

        // Add profile button click listener
        findViewById<FloatingActionButton>(R.id.addProfileBtn).setOnClickListener {
            val intent = Intent(this@ProfileListActivity, AddProfileActivity::class.java)
            startActivity(intent)
        }
    }

    // Show delete confirmation dialog
    private fun showDeleteConfirmationDialog(profile: Profile) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Delete Profile")
        builder.setMessage("Are you sure you want to delete this profile?")

        builder.setPositiveButton("Yes") { dialog, which ->
            profileViewModel.deleteUserProfile(profile)  // Delete the profile
            dialog.dismiss()
        }

        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()  // Cancel delete operation
        }

        val dialog = builder.create()
        dialog.show()
    }
}