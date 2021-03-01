package com.example.medivex

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.medivex.Models.Medicine
import com.example.medivex.api.ServiceBuilder
import com.xrest.finalassignment.Class.Person
//import com.bumptech.glide.Glide
//import com.example.medivex.Entity.User.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text


class UserAdapter(var lst:MutableList<Medicine>, var context: Context):RecyclerView.Adapter<UserAdapter.UserHolder>() {


    class UserHolder(view: View):RecyclerView.ViewHolder(view){


        var img:ImageView
        var name:TextView
        var desc:TextView
        var price:TextView
        var btn: TextView
        var rating:RatingBar


        init{

            img = view.findViewById(R.id.img)
            name = view.findViewById(R.id.name)
            desc = view.findViewById(R.id.description)
            price = view.findViewById(R.id.price)
            btn = view.findViewById(R.id.book)
            rating = view.findViewById(R.id.ratingbar)


        }



    }
    override fun getItemCount(): Int {
        return lst.size
    }



    override fun onBindViewHolder(holder: UserHolder, position: Int) {


        val food = lst[position]
        holder.name.text = food.Name
        holder.desc.text = food.Description
        holder.price.text = "Rs "+food.Price.toString()
        holder.rating.rating = food.Rating!!.toFloat()
        Glide.with(context).load("${ServiceBuilder.BASE_URL}images/${food.Image}").into(holder.img)










    }
}



