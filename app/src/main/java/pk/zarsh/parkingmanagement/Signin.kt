package pk.zarsh.parkingmanagement

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
import com.google.firebase.auth.AuthResult
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.io.FileInputStream


class Signin : AppCompatActivity() {
    val firebaseDatabase = FirebaseDatabase.getInstance()
    val refrence = firebaseDatabase.getReference("users")
    var type:String? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if (FirebaseAuth.getInstance().currentUser != null){
            finish()
            startActivity(Intent(this,MainUserActivity::class.java))
        }


        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val signin = findViewById<Button>(R.id.sign_in_button)
        val signup = findViewById<Button>(R.id.sign_up_button)
        val auth = FirebaseAuth.getInstance()

        signup.setOnClickListener{
            val i = Intent(this,Signup::class.java)
            startActivity(i)
        }

        signin.setOnClickListener{
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
                        if (task.isSuccessful) {
                            Log.d("signin", "signInWithEmail:success")

                            val user = auth.getCurrentUser()?.uid
                            updateUI(user)
                        } else {
                            Toast.makeText(this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                        }
                    })
        }

    }

    private fun updateUI(user: String?) {
        Log.d("signin user",user)
        val auth= FirebaseAuth.getInstance().currentUser?.email

        if(auth.toString()=="admin@g.com") {
            val i =Intent(this,MainAdminActivity::class.java)
            startActivity(i)
            finish()
        }

        else{
            startActivity(Intent(applicationContext,MainUserActivity::class.java))
            finish()
        }
    }
}
