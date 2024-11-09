package com.example.userprofileregistration.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.userprofileregistration.Data.UserDatabase
import com.example.userprofileregistration.Model.Profile
import com.example.userprofileregistration.Repository.ProfileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel (application: Application) : AndroidViewModel(application) {
    private val repository: ProfileRepository
    init {
        val argentinaProfileDao = UserDatabase.getDatabase(application).userProfileDao()
        repository = ProfileRepository(argentinaProfileDao)
    }

    fun getUserProfiles(): LiveData<List<Profile>> {
        return repository.getUserProfiles()
    }

    fun insertUserProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(profile)
        }
    }

    fun updateUserProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(profile)
        }
    }

    fun deleteUserProfile(profile: Profile) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(profile)
        }
    }
}