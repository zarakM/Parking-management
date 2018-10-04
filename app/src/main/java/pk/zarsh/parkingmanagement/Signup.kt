package pk.zarsh.parkingmanagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.EditText

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import android.widget.ArrayAdapter
import android.widget.Spinner
import kotlinx.android.synthetic.main.activity_signup.*


class Signup : AppCompatActivity() {

    private var inputEmail: EditText? = null
    private var inputPassword: EditText? = null
    private var type: String? = null

    private var btnSignIn: Button? = null
    private var btnSignUp: Button? = null

    private var btnResetPassword: Button? = null
    private var progressBar: ProgressBar? = null

    private var auth: FirebaseAuth? = null
    val mDatabase = FirebaseDatabase.getInstance()
    val mDatabaseReference = mDatabase.getReference("users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        btnSignIn = findViewById<View>(R.id.sign_in_button) as Button
        btnSignUp = findViewById<View>(R.id.sign_up_button) as Button
        inputEmail = findViewById<EditText>(R.id.email)
        inputPassword = findViewById<EditText>(R.id.password)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)

        btnSignIn!!.setOnClickListener({
            val i = Intent(this,Signin::class.java)
            startActivity(i)
        })

        btnSignUp!!.setOnClickListener({

                val email = inputEmail!!.text.toString().trim { it <= ' ' }
                val password = inputPassword!!.text.toString().trim { it <= ' ' }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT).show()
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()

                }

                if (password.length < 6) {
                    Toast.makeText(applicationContext, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show()
                    }

                progressBar!!.visibility = View.VISIBLE
                //create user
            auth!!
                    .createUserWithEmailAndPassword(email!!, password!!)
                    .addOnCompleteListener(this) { task ->
                        progressBar!!.visibility = View.GONE

                        if (task.isSuccessful) {
                            val userRef = FirebaseDatabase.getInstance().getReference("User")
                            val c = _Users(name.text.toString(),email)
                            userRef.push().setValue(c)

                            val i =Intent(this,Signin::class.java)
                            startActivity(i)
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("success", "createUserWithEmail:failure", task.exception)
                            Toast.makeText(this@Signup, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show()
                        }
                    }
        })
    }

    override fun onResume() {
        super.onResume()
        progressBar!!.visibility = View.GONE
    }
}