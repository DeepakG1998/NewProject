package com.example.demoproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_name_description.*




class NameDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_description)

        saveTheTextBtn.setOnClickListener {

            //For save the data into database
            saveHero()
            //For store the data into another activity
            val intent=Intent(this@NameDescriptionActivity,MarkedPlacesActivity::class.java)
            val name=editTextName1.text.toString()
            val description=editTextDescription1.text.toString()
            intent.putExtra("name: ",name)
            intent.putExtra("Description: ",description)
            startActivity(intent)

        }
    }

    private fun saveHero() {
        val name1=editTextName1.text.toString().trim()
        if (name1.isEmpty()){
            editTextName1.error="Please enter the name"
            return
        }
        val name2=editTextDescription1.text.toString().trim()
        if (name2.isEmpty()) {
            editTextName1.error = "Please enter the name"
            return
        }
        val name3=editTextDescription1.text.toString().trim()
        if (name3.isEmpty()) {
            editTextName1.error = "Please enter the name"
            return
        }



        val ref=FirebaseDatabase.getInstance().getReference("Places with Location")


        val heroid1=ref.push().key

        val hero= heroid1?.let { SendData(it,name1,name2,name3) }
        if (heroid1 != null) {
            ref.child(heroid1).setValue(hero).addOnCompleteListener {

                Toast.makeText(applicationContext,"Hero saved successfully",Toast.LENGTH_LONG).show()
            }
        }
    }


}