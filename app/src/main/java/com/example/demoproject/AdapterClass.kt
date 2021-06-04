package com.example.demoproject

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.demoproject.dataclass.UserLocationList
import com.google.firebase.database.FirebaseDatabase


class AdapterClass(private val userList: ArrayList<UserLocationList>) :
    RecyclerView.Adapter<AdapterClass.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]

        holder.firstName.text = currentItem.name
        holder.description.text = currentItem.description
        holder.delete.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference("Places with Location")
            ref.child(currentItem.id).removeValue()
        }
        holder.view.setOnClickListener {
            val intent = Intent(it.context, SecondMapsActivity::class.java)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val firstName: TextView = itemView.findViewById(R.id.name_tv)
        val description: TextView = itemView.findViewById(R.id.disc_tv)
        val delete: Button = itemView.findViewById(R.id.delete_btn)
        val view: Button = itemView.findViewById(R.id.view_btn)
    }

}