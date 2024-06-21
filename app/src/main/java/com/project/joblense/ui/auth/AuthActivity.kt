package com.project.joblense.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.project.joblense.R
import com.project.joblense.data.factory.ViewModelFactory
import com.project.joblense.databinding.ActivityAuthBinding
import com.project.joblense.ui.auth.login.LoginFragment
import com.project.joblense.ui.main.MainActivity

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    private val viewModel by viewModels<AuthViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getSession()
    }
    private fun getSession() {
        viewModel.getSession().observe(this) { user ->
            if (user.isLogin && user.token.isNotEmpty()) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.auth_container, LoginFragment())
                    .commit()
            }
        }
    }
}