package com.project.joblense.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.project.joblense.R
import com.project.joblense.data.factory.ViewModelFactory
import com.project.joblense.databinding.ActivityProfileBinding
import com.project.joblense.ui.auth.AuthActivity
import com.project.joblense.ui.history.HistoryActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nameUser = intent.getStringExtra("name")

        binding.apply {
            appbarLayout.setOnClickListener {
                finish()
            }

            profileName.text = nameUser

            btnHistory.setOnClickListener {
                startActivity(Intent(this@ProfileActivity, HistoryActivity::class.java))
            }

            btnLogout.setOnClickListener {
                viewModel.logout()
                Intent(it.context, AuthActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(this)
                }
            }
        }

    }
}