package com.example.refrigerator_management

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class DataAdapter(var list: ArrayList<DatabaseModel>) :RecyclerView.Adapter<DataAdapter.ViewHolder>(){
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var name =itemView.findViewById<TextView>(R.id.tv_name)
        var date =itemView.findViewById<TextView>(R.id.tv_date)
        var delete = itemView.findViewById<Button>(R.id.btn_delete)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text=list[position].name
        holder.date.text=list[position].date
        var foodate = list[position]
        holder.delete.setOnClickListener {
            deleteinfo(foodate)
        }

    }

    private fun deleteinfo(foodate: DatabaseModel) {
        val mydatabased = FirebaseDatabase.getInstance().getReference("Usres")
        mydatabased.child(foodate.id).removeValue()
    }


    override fun getItemCount(): Int {
        return list.size
    }
}