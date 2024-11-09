package com.example.userprofileregistration.Repository

import androidx.lifecycle.LiveData
import com.example.userprofileregistration.Dao.ProfileDao
import com.example.userprofileregistration.Model.Profile

class ProfileRepository (private val userProfileDao: ProfileDao) {
    fun getUserProfiles(): LiveData<List<Profile>> {
        return userProfileDao.getAllUserProfiles()
    }

    suspend fun insert(userProfile: Profile) {
        userProfileDao.insert(userProfile)
    }

    suspend fun update(userProfile: Profile) {
        userProfileDao.update(userProfile)
    }

    suspend fun delete(userProfile: Profile) {
        userProfileDao.delete(userProfile)
    }
}