package com.project.githubusers.uicomponent

import com.project.githubusers.model.ResponseModel
import com.project.githubusers.rest.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainPresenter (private val mView : MainContract.View) : MainContract.Presenter {
    override fun searchUserName(query: String, currentPage: Int) {
        mView.showLoading(true)
        val callAPI = ApiInterface.instance.searchUsers(query,currentPage)
        callAPI.enqueue(object : Callback<ResponseModel>{
            override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                //TODO : Error
            }

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.code() == 200){
                    val items = response.body()?.items.orEmpty()
                    mView.onDataResult(items)
                }
                mView.showLoading(false)
            }
        })

    }
}