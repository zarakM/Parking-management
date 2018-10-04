package pk.zarsh.parkingmanagement

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.booking_detail.*

class BookingDetail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.booking_detail)

        val auth= FirebaseAuth.getInstance().currentUser?.email

        if(auth.toString()=="admin@g.com") {
            Tariq.setOnClickListener {
                startActivity(Intent(this, BookingTariq::class.java))
            }
            Gulshan.setOnClickListener {
                startActivity(Intent(this, BookingGulshan::class.java))
            }
            Defence.setOnClickListener {
                startActivity(Intent(this, BookingDefense::class.java))
            }
            Log.d("taag","admin")
        }
        else{
            Tariq.setOnClickListener {
                startActivity(Intent(this, SpecificBooking::class.java).putExtra("area","Tariq"))
            }
            Gulshan.setOnClickListener {
                startActivity(Intent(this, SpecificBooking::class.java).putExtra("area","Gulshan"))
            }
            Defence.setOnClickListener {
                startActivity(Intent(this, SpecificBooking::class.java).putExtra("area","Defense"))
            }
            Log.d("taag","user")
        }
    }
}
