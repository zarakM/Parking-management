package pk.zarsh.parkingmanagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.feedback.*

class Feedback : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.feedback)

        val uid = FirebaseAuth.getInstance().currentUser?.uid
        val feedRef = FirebaseDatabase.getInstance().getReference("Feedback")
        send.setOnClickListener{
            val c = _Feedback(uid.toString(),feedback.text.toString(),"")
            feedRef.push().setValue(c)
        }
    }
}
