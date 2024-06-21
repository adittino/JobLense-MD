package com.project.joblense.ui.auth.register


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.joblense.R
import com.project.joblense.data.factory.ViewModelFactory
import com.project.joblense.databinding.FragmentRegisterBinding
import com.project.joblense.ui.auth.login.LoginFragment

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private val registerViewModel: RegisterViewModel by viewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            btnLogin.setOnClickListener {
                switchToLogin()
            }

            btnSignup.setOnClickListener {
                val name = binding.edUsername.text.toString()
                val username = binding.edEmail.text.toString()
                val password = binding.edPassword.text.toString()

                if (username.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty()) {
                    registerViewModel.register(name, username,password)
                    binding.progressbar.visibility = View.VISIBLE
                } else {
                    Toast.makeText(requireContext(), "Please enter your email and password", Toast.LENGTH_SHORT).show()
                }
            }

            registerViewModel.registrationResult.observe(requireActivity()){ result ->
                binding.progressbar.visibility = View.GONE

                if (result.success) {
                    Toast.makeText(requireContext(), "Registration successful", Toast.LENGTH_SHORT).show()
                    switchToLogin()
                } else {
                    Toast.makeText(requireContext(), "Registration failed: ${result.error}", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    private fun switchToLogin() {
        with(parentFragmentManager.beginTransaction()) {
            replace(
                R.id.auth_container,
                LoginFragment(),
                LoginFragment::class.java.simpleName
            )
            commit()
        }
    }
}