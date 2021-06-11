package com.example.demoproject

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.demoproject.dataclass.SendData
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_name_description.*


class NameDescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name_description)

        saveTheTextBtn.setOnClickListener {
            saveDataBase()
            val intent = Intent(this, SavedListActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    private fun saveDataBase() {
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)
        val name1 = editTextName1.text.toString().trim()
        val name2=editTextDescription1.text.toString().trim()
        if (name1.isEmpty() && name2.isEmpty()) {
            editTextName1.error = "Please Enter Name"
            return
        }
        val ref = FirebaseDatabase.getInstance().getReference("Places with Location")
        val push = ref.push().key
        val push2 = push?.let { SendData(it, name1, name2, latitude, longitude) }
        if (push != null) {
            ref.child(push).setValue(push2).addOnCompleteListener {
                Toast.makeText(applicationContext, "Details saved successfully", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
