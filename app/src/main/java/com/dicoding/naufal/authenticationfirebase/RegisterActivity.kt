package com.dicoding.naufal.authenticationfirebase

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var firebase: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUp()
    }

    fun setUp() {
        firebase = FirebaseAuth.getInstance()

        btn_register.setOnClickListener {
            if (validatepassword()) {
                firebase.createUserWithEmailAndPassword(edit_email.text.toString(),
                    edit_password.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            setResult(Activity.RESULT_OK)
                            finish()
                        } else if (it.exception is FirebaseAuthUserCollisionException) {
                            Toast.makeText(this, "Email sudah dipakai", Toast.LENGTH_SHORT)
                                .show()

                        } else if (it.exception is FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(this, "Email tidak valid", Toast.LENGTH_SHORT)
                                .show()

                        } else if (it.exception is FirebaseAuthWeakPasswordException) {
                            Toast.makeText(this, "Password terlalu lemah", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }
        }
    }

    fun validatepassword(): Boolean {
        val password = edit_password.text.toString()
        val rePassword = edit_re_password.text.toString()
        if (password != rePassword) {
            Toast.makeText(this, "Password tidak sama", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        return true
    }
}
