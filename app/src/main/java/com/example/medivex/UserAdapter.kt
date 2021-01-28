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
import com.example.medivex.Entity.User.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text


class UserAdapter(var lst:MutableList<User>,var context: Context):RecyclerView.Adapter<UserAdapter.UserHolder>() {


    class UserHolder(view: View):RecyclerView.ViewHolder(view){


        var img:ImageView = view.findViewById(R.id.image)
        var fname:TextView = view.findViewById(R.id.fullname)
        var username:TextView = view.findViewById(R.id.username)
        var gender:TextView = view.findViewById(R.id.gender)
        var delete:ImageButton = view.findViewById(R.id.del)
        var update:ImageButton = view.findViewById(R.id.update)


    }
    override fun getItemCount(): Int {
        return lst.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.meroview,parent,false)
        return UserHolder(view)
    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {

        val current = lst[position]

holder.fname.text = current.fullname
        holder.gender.text = current.gender
        holder.username.text = current.username
        Glide.with(context).load(current.img).into(holder.img)


        holder.delete.setOnClickListener(){
CoroutineScope(Dispatchers.IO).launch {

    UserDB.getInstance(context).getUserDAO().delete(current)
    notifyDataSetChanged()

}


        }



        holder.update.setOnClickListener(){


            val dialog = Dialog(context)
            dialog.setCancelable(true)
            dialog.getWindow()!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            dialog.setContentView(R.layout.alert)
var gender = current.gender
            var img= current.img

            var btnSign: Button = dialog.findViewById(R.id.btnSignUp)
            var btnLogin: Button =  dialog.findViewById(R.id.btnLogin)
            val fullname : EditText =  dialog.findViewById(R.id.Fullname)
            val username : EditText =  dialog.findViewById(R.id.Username)
            val password : EditText =  dialog.findViewById(R.id.Password)
            val cpassword : EditText =  dialog.findViewById(R.id.CPassword)
            val male : RadioButton =  dialog.findViewById(R.id.male)
            val female : RadioButton =  dialog.findViewById(R.id.female)
            val other : RadioButton =  dialog.findViewById(R.id.other)
fullname.setText(current.fullname)
            username.setText(current.username)
            password.setText(current.password)
            cpassword.setText(current.password)
            when(gender){

                "Male"->male.isChecked
                "Female"->female.isChecked
                "Others"->other.isChecked
            }





                lst[position].fullname = fullname.text.toString()
                lst[position].username = username.text.toString()
                lst[position].gender = gender
                lst[position].password =password.text.toString()
                lst[position].img = img


                CoroutineScope(Dispatchers.IO).launch {

                    UserDB.getInstance(context).getUserDAO().update(lst[position])

                }
                notifyDataSetChanged()
                dialog.cancel()


            }







            dialog.show()





        }








    }



}