package com.example.demirbasinstagram.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.demirbasinstagram.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class KullanıcıActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        val guncelKullanici=auth.currentUser
        if (guncelKullanici!= null){
            val intent =Intent(this, HaberlerActivity::class.java)
            startActivity(intent)
            finish()


        }

    }
    fun girisYap(view:View){
        auth.signInWithEmailAndPassword(emailText.text.toString(),passwordText.text.toString()).addOnCompleteListener { task->
            if (task.isSuccessful){
                val guncelKullanici=auth.currentUser?.email.toString()
                Toast.makeText(this,"Hoşgeldin: ${guncelKullanici}",Toast.LENGTH_SHORT).show()
                val intent =Intent(this, HaberlerActivity::class.java)
                startActivity(intent)
                finish()

            }
        }.addOnFailureListener { exception->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }



    }

    fun kayıtOl(view: View){
        val email=emailText.text.toString()
        val password=passwordText.text.toString()
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful){
                val intent =Intent(this, HaberlerActivity::class.java)
                startActivity(intent)
                finish()

            }
        }.addOnFailureListener { exception->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }

}