package com.dicoding.naufal.authenticationfirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var auth: FirebaseAuth

    override fun onStart() {
        super.onStart()

        val currentuser = auth.currentUser
        //Cek apakah user sudah login atau belum
        currentuser?.let {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_login)
        setUp()
    }

    private fun setUp(){
        //login

        btn_login.setOnClickListener {
            val email = edit_email.text.toString()
            val password = edit_password.text.toString()
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Log.d("ERROR = ", "login failed")
                        Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT)
                            .show()
                    }
                }

        }
    }
}
