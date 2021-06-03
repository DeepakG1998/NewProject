package com.example.demoproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MapsActivity2 : AppCompatActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps2)
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {

        mMap?.clear()
        mMap?.uiSettings?.isZoomControlsEnabled ?:true

        val ref = FirebaseDatabase.getInstance().reference.child("Places with Location")

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(database: DataSnapshot) {
                for (pullRequest in database.children) {
                    val latitude1 = pullRequest.child("latitude").value
                    val longitude1 = pullRequest.child("longitude").value
                    val location2 = LatLng(latitude1 as Double, longitude1 as Double)
                    mMap?.addMarker(
                        MarkerOptions().position(location2)
                    )
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Can Not Take Data's From Database", Toast.LENGTH_SHORT).show()
            }
        })
    }
}