package com.project.githubusers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.project.githubusers.model.UserModel
import com.project.githubusers.uicomponent.MainContract
import com.project.githubusers.uicomponent.MainPresenter
import com.project.githubusers.uicomponent.ResultsAdapter
import com.project.githubusers.utility.attachLoadMore
import com.project.githubusers.utility.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {

    private val mList = mutableListOf<UserModel>()
    private val mAdapter by lazy { ResultsAdapter(mList, this) }
    private val mPresenter by lazy {MainPresenter(this)}
    private var currentPage = 1
    private var isNeedMoreQuery = true
    private var isLoading = false
    private var latestQuery = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        button.setOnClickListener(onButtonClick)

        recylerView.adapter = mAdapter
        recylerView.attachLoadMore(onLoadMore,mList)
    }

    private val onLoadMore : () -> Unit ={
        if (!isLoading){
            Log.d("ERICH","Load more triggered")
            searchUserName()
        }
    }



    override fun showLoading(isShow : Boolean) {
        isLoading = isShow
        toast(if (isShow)"Loading" else "Finish")
    }

    override fun onDataResult(list: List<UserModel>, totalResult : Int) {
        if (list.isEmpty() && mList.isEmpty()) {
            isNeedMoreQuery = false
            toast("No Results Found")
        }
        else {
            if (mList.size >= totalResult-30) isNeedMoreQuery = false
            mList.addAll(list)
            mAdapter.notifyDataSetChanged()
            currentPage++
        }
    }



    private val onButtonClick = View.OnClickListener {
        val currentQuery = userQuery.text.toString()
        if (latestQuery == currentQuery || currentQuery.isEmpty()) return@OnClickListener
        isNeedMoreQuery = true
        latestQuery = currentQuery
        mList.clear()
        currentPage=1
        searchUserName()
    }

    private fun searchUserName(){
        if (isNeedMoreQuery){
            mPresenter.searchUserName(latestQuery,currentPage)
        }
    }


}
