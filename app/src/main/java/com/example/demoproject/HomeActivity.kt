package com.example.demoproject


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.home_page.*
import kotlinx.android.synthetic.main.marked_places.*


class HomeActivity: AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)



        //Button for set the marker
        markThePlaceBtn.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }



        //Button for see the Marked list
        showMarkedPlacesBtn.setOnClickListener{
            val intent = Intent(this@HomeActivity, MarkedPlacesActivity::class.java)
            startActivity(intent)
        }

    }}

