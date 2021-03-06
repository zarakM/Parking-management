package pk.zarsh.parkingmanagement

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
import kotlinx.android.synthetic.main.slots.*

class SpecificBooking : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_specific_booking)

        val user = FirebaseAuth.getInstance().currentUser?.uid
        var i =intent.extras
        var area = i.getString("area")
        var list = ArrayList<_BookingDetail?>()
        Log.d("areaa",area)
        val ref = FirebaseDatabase.getInstance().getReference("Booking_details/"+area)

        ref.orderByChild("uid").equalTo(user.toString()).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                list.clear()
                p0.children.forEach{
                    val map = it.getValue(_BookingDetail::class.java)
                    val date = map?.date
                    val from = map?.from
                    val to = map?.to
                    val slot = map?.slot
                    val uid = map?.uid
                    val id = map?.id
                    list.add(_BookingDetail(id = id.toString(),slot = slot.toString(), date = date.toString(), from = from.toString(), to = to.toString(), uid = uid.toString()))
                }
                val viewAdapter = adapterBooking(applicationContext,list)
                val viewManager = LinearLayoutManager(applicationContext)
                val recyclerView = findViewById<RecyclerView>(R.id.recycler)
                recyclerView.setHasFixedSize(true)
                recyclerView.layoutManager = viewManager
                recyclerView.adapter = viewAdapter
            }
        })

    }
}
