package com.example.homework9

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.homework9.databinding.ActivityAddUserBinding

class AddUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddUserBinding
    private var isUpdateBool: Boolean? = null
    private var user: UserData? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        isUpdateBool = isUpdate()
        binding.addUserBtn.setOnClickListener {
            onButtonClicked()
        }
    }

    private fun onButtonClicked() {
        if (isUpdateBool!!)
            updateUser()
        else addUser()
    }

    private fun isUpdate(): Boolean {
        user = intent.getSerializableExtra("user") as UserData?
        if (user != null) {
            binding.addUserBtn.text = "update"
            binding.enterName.setText((user as UserData).name)
            binding.enterEmail.setText((user as UserData).email)
            binding.enterLastName.setText((user as UserData).lastName)
            return true
        }
        return false
    }

    private fun addUser() {
        if (!nothingIsEmpty())
            return
        binding.apply {
            userList.add(
                UserData(
                    enterName.text.toString(),
                    enterLastName.text.toString(),
                    enterEmail.text.toString()
                )
            )

        }
        onBackPressed()
    }

    private fun updateUser() {
        if (!nothingIsEmpty())
            return
        val position = intent.getIntExtra("position", 0)
        binding.apply {
            userList[position] = UserData(
                enterName.text.toString(),
                enterLastName.text.toString(),
                enterEmail.text.toString()
            )
        }
        onBackPressed()
    }

    private fun nothingIsEmpty(): Boolean {
        binding.apply {
            when {
                enterName.text.toString().isEmpty() -> {
                    myToast("Please enter name")
                    return false
                }
                enterLastName.text.toString().isEmpty() -> {
                    myToast("Please enter last name")
                    return false
                }
                enterEmail.text.toString().isEmpty() -> {
                    myToast("Please enter email")
                    return false
                }
            }
        }
        return true
    }

    private fun myToast(st: String) {
        Toast.makeText(applicationContext, st, Toast.LENGTH_SHORT).show()
    }
}