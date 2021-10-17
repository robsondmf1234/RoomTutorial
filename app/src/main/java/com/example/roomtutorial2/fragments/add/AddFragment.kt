package com.example.roomtutorial2.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomtutorial2.R
import com.example.roomtutorial2.databinding.FragmentAddBinding
import com.example.roomtutorial2.model.User
import com.example.roomtutorial2.viewmodel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    lateinit var edtFirstName: EditText
    lateinit var edtLastName: EditText
    lateinit var edtAge: EditText
    lateinit var button: Button
    lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(layoutInflater)

        edtFirstName = binding.edtFistName
        edtLastName = binding.edtlastName
        edtAge = binding.edtAge
        button = binding.button

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        binding.button.setOnClickListener {
            insertToDatabase()
        }

        return binding.root
    }

    private fun insertToDatabase() {
        val firstNAme = edtFirstName.text.toString()
        val lastName = edtLastName.text.toString()
        val age = edtAge.text

        if (inputCheckFirstName(firstNAme) && inputCheckLastName(lastName) && inputCheckAge(age)) {
            //Create user
            val user = User(0, firstNAme, lastName, Integer.parseInt(age.toString()))
            //Add data do database
            mUserViewModel.addUser(user)
            Toast.makeText(context, "Usuario criado com sucesso", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(context, "Favor preencher os campos com valores", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheckFirstName(firstName: String): Boolean {
        return !(TextUtils.isEmpty(firstName))
    }

    private fun inputCheckLastName(lastName: String): Boolean {
        return !(TextUtils.isEmpty(lastName))
    }

    private fun inputCheckAge(age: Editable): Boolean {
        return !(age.isEmpty())
    }
}