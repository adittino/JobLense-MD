package com.project.joblense.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.joblense.R
import com.project.joblense.data.factory.ViewModelFactory
import com.project.joblense.data.model.User
import com.project.joblense.databinding.FragmentLoginBinding
import com.project.joblense.ui.auth.register.RegisterFragment
import com.project.joblense.ui.main.MainActivity

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            btnLogin.setOnClickListener {
                val username = binding.edEmailLogin.text.toString()
                val password = binding.edPassword.text.toString()
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    loginViewModel.login(username, password)
                    binding.progressbar.visibility = View.VISIBLE
                } else {
                    Toast.makeText(requireContext(), "Please enter your email and password", Toast.LENGTH_SHORT).show()
                }
            }

            loginViewModel.loginResult.observe(viewLifecycleOwner) { result ->
                binding.progressbar.visibility = View.GONE
                if (result.success) {
                    Toast.makeText(requireContext(), "Login successful", Toast.LENGTH_SHORT).show()
                    result.data?.let { loginResponse ->
                        saveUserSessionAndNavigate(loginResponse.data.user.name, loginResponse.data.token)
                    }
                } else {
                    Toast.makeText(requireContext(), "Login failed: ${result.error}", Toast.LENGTH_SHORT).show()
                }
            }

            btnRegister.setOnClickListener {
                switchToRegister()
            }
        }
    }

    private fun saveUserSessionAndNavigate(name: String, token: String) {
        val email = binding.edEmailLogin.text.toString()
        loginViewModel.saveSession(User(email, name, token))

        loginViewModel.saveSessionResult.observe(viewLifecycleOwner) { isSaved ->
            if (isSaved) {
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "Failed to save session", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun switchToRegister() {
        with(parentFragmentManager.beginTransaction()) {
            replace(
                R.id.auth_container,
                RegisterFragment(),
                RegisterFragment::class.java.simpleName
            )
            commit()
        }
    }
}