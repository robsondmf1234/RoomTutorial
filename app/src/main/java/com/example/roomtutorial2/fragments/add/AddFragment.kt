package com.example.roomtutorial2.fragments.add

import android.os.Bundle
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
import com.example.roomtutorial2.model.User
import com.example.roomtutorial2.viewmodel.UserViewModel

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel
    lateinit var edtFirstName: EditText
    lateinit var edtLastName: EditText
    lateinit var edtAge: EditText
    lateinit var button: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        edtFirstName = view.findViewById<EditText>(R.id.edtFistName)
        edtLastName = view.findViewById<EditText>(R.id.edtlastName)
        edtAge = view.findViewById<EditText>(R.id.edtAge)
        button = view.findViewById<Button>(R.id.button)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        button.setOnClickListener {
            insertToDatabase()
        }

        return view
    }

    private fun insertToDatabase() {
        val firstNAme = edtFirstName.text.toString()
        val lastName = edtLastName.text.toString()
        val age = edtAge.text.toString()
        val idadeConvertidade = Integer.parseInt(age)

        if (inputCheck(firstNAme, lastName, age)) {
            //Create user
            val user = User(0, firstNAme, lastName, idadeConvertidade)
            //Add data do database
            mUserViewModel.addUser(user)
            Toast.makeText(context, "Usuario criado com sucesso", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(context, "Favor preencher os campos com valores", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun inputCheck(firstName: String, lasttName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lasttName) && TextUtils.isEmpty(
            age
        ))
    }
/*
    private fun inputCheck(firstName: String, lasttName: String, age: Editable): Boolean {
        return (TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lasttName) && age.isEmpty())
    }
*/

}