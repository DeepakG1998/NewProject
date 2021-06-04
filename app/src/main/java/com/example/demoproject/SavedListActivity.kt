package com.example.demoproject

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject.dataclass.UserLocationList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class SavedListActivity : AppCompatActivity() {

    private var userRecyclerView: RecyclerView? = null
    private var userArrayLocationList: ArrayList<UserLocationList>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        userRecyclerView = findViewById(R.id.userList)
        userRecyclerView?.layoutManager = LinearLayoutManager(this)
        userRecyclerView?.setHasFixedSize(true)
        userArrayLocationList = arrayListOf()
        getUserdata()
    }

    private fun getUserdata() {
        val dbReference = FirebaseDatabase.getInstance().getReference("Places with Location")
        dbReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    userArrayLocationList?.clear()
                    for (userSnapshot in snapshot.children) {
                        val user = userSnapshot.getValue(UserLocationList::class.java)
                        if (user != null) {
                            userArrayLocationList?.add(user)
                        }
                    }
                    userRecyclerView?.adapter = userArrayLocationList?.let { AdapterClass(it) }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext, "Fetched Data's From Database Is Fail", Toast.LENGTH_SHORT).show()
            }
        })
    }
}