package com.project.githubusers.utility

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast

fun Context.toast(message : String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}

fun RecyclerView.attachLoadMore(currentList : List<Any>, onLoadMore : () -> Unit){
    this.addOnScrollListener(object : RecyclerView.OnScrollListener(){
        var isLoadMore = false
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (layoutManager !is LinearLayoutManager) throw IllegalArgumentException("Bro, mohon maap nih! Cuma bisa LinearLayoutManager doang")
            val lastVisibleItem = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

            if (lastVisibleItem >= currentList.size - 3  && !isLoadMore){
                isLoadMore = true
                Log.i("ERICH", " Load more")
                onLoadMore()
            } else if (lastVisibleItem < currentList.size - 3){
                isLoadMore = false
            }
        }
    })
}