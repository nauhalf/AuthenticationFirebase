package com.dicoding.naufal.authenticationfirebase

import android.app.Activity
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

        txt_reset_password.setOnClickListener {
            //pindah ke resetpassword activity
            startActivity(Intent(this, ResetPasswordActivity::class.java))
        }

        txt_register.setOnClickListener {
            startActivityForResult(Intent(this, RegisterActivity::class.java), REGISTER_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REGISTER_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    companion object{
        const val REGISTER_REQUEST_CODE = 1
    }
}
