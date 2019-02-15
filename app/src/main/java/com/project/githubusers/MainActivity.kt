package com.project.githubusers

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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

        searchView.setOnQueryTextListener(onSearchClickListener)
        with (recylerView){
            adapter = mAdapter
            attachLoadMore(mList){
                Log.d("ERICH","Load more triggered")
                searchUserName()
            }
        }
    }

    private val onSearchClickListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            val currentQuery = p0.orEmpty()
            if (currentQuery.isEmpty()){
                return true
            }

            isNeedMoreQuery = true; latestQuery = currentQuery; mList.clear()
            currentPage = 1
            searchUserName()
            searchView.clearFocus()
            return true
        }

        override fun onQueryTextChange(p0: String?): Boolean {return false}
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) = searchView.clearFocus()

    override fun onBackPressed() {
        if (mList.isNotEmpty()){
            mList.clear()
            mAdapter.notifyDataSetChanged()
            searchView.clearFocus()
            searchView.setQuery("",false)
            showErrorMessage("GitHub")
        } else {
            super.onBackPressed()
        }
    }


    override fun showLoading(isShow : Boolean) {
        isLoading = isShow
        if (isShow) loadingView.visibility = View.VISIBLE
        else loadingView.visibility = View.GONE
    }

    override fun onDataResult(list: List<UserModel>, totalResult : Int) {
        if (totalResult == 0) {
            isNeedMoreQuery = false
            showErrorMessage(getString(R.string.no_user_found))
        }
        else {
            if (mList.size >= totalResult-30) {
                isNeedMoreQuery = false
            } else {
                currentPage++
            }
            mList.addAll(list)
            mAdapter.notifyDataSetChanged()
        }
    }

    override fun errorToast(message: String) = toast(message)

    private fun showErrorMessage(message : String){
        recylerView.visibility = View.GONE
        error_view.visibility = View.VISIBLE
        error_message.text = message
    }

    private fun hideErrorMessage(){
        recylerView.visibility = View.VISIBLE
        error_view.visibility = View.GONE
    }


    private fun searchUserName(){
        if (isNeedMoreQuery){
            hideErrorMessage()
            mPresenter.searchUserName(latestQuery,currentPage)
        }
    }


}
