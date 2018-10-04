package pk.zarsh.parkingmanagement

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.parking_areas.*

class ParkingAreas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.parking_areas)

        Tariq.setOnClickListener{
            startActivity(Intent(this,Slots::class.java).putExtra("area","Tariq"))
        }
        Gulshan.setOnClickListener{
            startActivity(Intent(this,Slots::class.java).putExtra("area","Gulshan"))
        }
        Defence.setOnClickListener{
            startActivity(Intent(this,Slots::class.java).putExtra("area","Defense"))
        }
    }
}
