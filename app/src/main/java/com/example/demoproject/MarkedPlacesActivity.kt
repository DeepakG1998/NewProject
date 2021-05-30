package com.example.demoproject

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_name_description.*
import kotlinx.android.synthetic.main.marked_places.*


class MarkedPlacesActivity:AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.marked_places)


        val name = intent.getStringExtra("name: ")
        val description=intent.getStringExtra("Description: ")
        listThePlaces.text= "Name: $name \n Description: $description"
        if (listThePlaces.text.isEmpty()){
            Toast.makeText(applicationContext, "Please select the place ", Toast.LENGTH_LONG).show()
        }




        //View In Map
        viewInMap.setOnClickListener {
                 val intent = Intent(this@MarkedPlacesActivity, MapsActivity::class.java)
                startActivity(intent)
        }



        //Return to Home Button
        HomeBtn.setOnClickListener {
            val intent = Intent(this@MarkedPlacesActivity, HomeActivity::class.java)
            startActivity(intent)
        }





        //Delete the data from database and
        val ref = FirebaseDatabase.getInstance().reference
        val applesQuery: Query = ref.child("heroes").limitToLast(1)



        //Delete the data from list
        DeleteList.setOnClickListener {

            listThePlaces.text=" "
            applesQuery.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (appleSnapshot in dataSnapshot.children) {
                        appleSnapshot.ref.removeValue()
                        Toast.makeText(
                            applicationContext,
                            "Deleted successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }



                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(TAG, "onCancelled", databaseError.toException())
                }
            })

            }







    }}