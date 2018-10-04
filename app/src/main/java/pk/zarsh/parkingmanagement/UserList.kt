package pk.zarsh.parkingmanagement

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_list)

        var list = ArrayList<_Users?>()
        val ref = FirebaseDatabase.getInstance().getReference("User")

        ref.addValueEventListener(object :ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach{
                    val map = it.getValue(_Users::class.java)
                    val name = map?.name
                    val email= map?.email
                    list.add(_Users(name.toString(),email.toString()))
                }
                val viewAdapter = adapterUser(applicationContext,list)
                val viewManager = LinearLayoutManager(applicationContext)
                val recyclerView = findViewById<RecyclerView>(R.id.recycler)
                recyclerView.setHasFixedSize(true)

                recyclerView.layoutManager = viewManager
                recyclerView.adapter = viewAdapter
            }
        })
    }
}
