package pk.zarsh.parkingmanagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Reply : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reply)
        var list = ArrayList<_Feedback>()

        val ref = FirebaseDatabase.getInstance().getReference("Feedback")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(applicationContext,"not com",Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach{
                    val info = it.getValue(_Feedback::class.java)
                    var title = info?.userid
                    var message = info?.message
                    var reply = info?.reply
                    list.add(_Feedback(title.toString(),message.toString(),reply.toString()))

                }
                val viewAdapter = adapterReply(applicationContext,list)
                val viewManager = LinearLayoutManager(applicationContext)
                val recyclerView = findViewById<RecyclerView>(R.id.recycler)
                recyclerView.setHasFixedSize(true)

                recyclerView.layoutManager = viewManager
                recyclerView.adapter = viewAdapter
            }
        })

    }
}
