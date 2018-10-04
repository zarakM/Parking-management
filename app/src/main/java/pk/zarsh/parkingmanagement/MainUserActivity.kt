    package pk.zarsh.parkingmanagement

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main_user.*

    class MainUserActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main_user)
            setSupportActionBar(my_toolbar)

            book.setOnClickListener {
                startActivity(Intent(this, ParkingAreas::class.java))
            }
            viewbook.setOnClickListener {
                startActivity(Intent(this, BookingDetail::class.java))
            }
            send.setOnClickListener {
                startActivity(Intent(this, Feedback::class.java))
            }
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