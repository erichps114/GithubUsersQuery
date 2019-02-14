package com.project.githubusers.uicomponent

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.TransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.project.githubusers.R
import com.project.githubusers.model.UserModel
import kotlinx.android.synthetic.main.search_result_item_layout.view.*

class ResultsAdapter(private val list : List<UserModel>, private val activity : AppCompatActivity) : RecyclerView.Adapter<UsersViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): UsersViewHolder {
       return UsersViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.search_result_item_layout,p0,false))

    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: UsersViewHolder, p1: Int) {
        val user = list[p1]
        p0.userNameET.text = user.login
        Glide
            .with(activity)
            .load(user.avatar_url)
            .apply(RequestOptions().placeholder(R.drawable.placeholder))
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(p0.avatarIV)
    }
}

class UsersViewHolder(v : View) : RecyclerView.ViewHolder(v){
    val userNameET = v.name
    val avatarIV = v.avatar
}