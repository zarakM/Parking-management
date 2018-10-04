package pk.zarsh.parkingmanagement

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main_admin.*

class MainAdminActivity : AppCompatActivity() {
    val refrence_g = FirebaseDatabase.getInstance().getReference("Location/Gulshan")
    val refrence_d = FirebaseDatabase.getInstance().getReference("Location/Defense")
    val refrence_t = FirebaseDatabase.getInstance().getReference("Location/Tariq")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_admin)
        setSupportActionBar(my_toolbar)

        bookings.setOnClickListener { startActivity(Intent(this,BookingDetail::class.java)) }
        user.setOnClickListener { startActivity(Intent(this,UserList::class.java)) }
        feedback.setOnClickListener { startActivity(Intent(this,Reply::class.java)) }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_signin, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_favorite -> {
            FirebaseAuth.getInstance().signOut()
            startActivity(Intent(this,Signin::class.java))
            finish()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}
