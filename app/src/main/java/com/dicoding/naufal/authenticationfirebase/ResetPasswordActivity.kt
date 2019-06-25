package com.dicoding.naufal.authenticationfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_reset_password.*

class ResetPasswordActivity : AppCompatActivity() {

    lateinit var firebase: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)
        setUp()
    }

    private fun setUp(){

        firebase = FirebaseAuth.getInstance()
        btn_reset.setOnClickListener {
            val email = edit_email.text.toString()
            firebase.sendPasswordResetEmail(email).addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(this, "Link reset password telah dikirim ke ${email}",
                        Toast.LENGTH_SHORT)
                        .show()
                } else {
                    Toast.makeText(this, "Email ${email} tidak terdaftar", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}
