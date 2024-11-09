package com.example.userprofileregistration.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.userprofileregistration.Model.Profile
import com.example.userprofileregistration.R

class ProfileAdapter : ListAdapter<Profile, ProfileAdapter.ProfileViewHolder>(DiffCallback()) {

    private var onItemClickListener: ((Profile) -> Unit)? = null
    private var onDeleteClickListener: ((Profile) -> Unit)? = null
    private var onUpdateClickListener: ((Profile) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.profile_item_layout, parent, false)
        return ProfileViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    fun setOnItemClickListener(listener: (Profile) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnDeleteClickListener(listener: (Profile) -> Unit) {
        onDeleteClickListener = listener
    }

    fun setOnUpdateClickListener(listener: (Profile) -> Unit) {
        onUpdateClickListener = listener
    }

    inner class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val profileName: TextView = itemView.findViewById(R.id.argentinaNameTxt)
        private val profileEmail: TextView = itemView.findViewById(R.id.argentinaEmailTxt)
        private val profileDOB: TextView = itemView.findViewById(R.id.argentinaDOBTxt)
        private val profileDistrict: TextView = itemView.findViewById(R.id.argentinaDistritTxt)
        private val profileMobile: TextView = itemView.findViewById(R.id.argentinaMobileTxt)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.deleteBtn)
        private val updateButton: ImageButton = itemView.findViewById(R.id.editBtn)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val profile = getItem(position)
                    onItemClickListener?.invoke(profile)
                }
            }

            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val profile = getItem(position)
                    onDeleteClickListener?.invoke(profile)
                }
            }

            updateButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val profile = getItem(position)
                    onUpdateClickListener?.invoke(profile)
                }
            }
        }

        fun bind(userProfile: Profile) {
            profileName.text = userProfile.name
            profileEmail.text = userProfile.email
            profileDOB.text = userProfile.dob
            profileDistrict.text = userProfile.district
            profileMobile.text = userProfile.mobile
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Profile>() {
        override fun areItemsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Profile, newItem: Profile): Boolean {
            return oldItem == newItem
        }
    }
}