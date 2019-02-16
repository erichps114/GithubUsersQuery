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
                mView.errorToast("No Connection")
                mView.showLoading(false)
            }

            override fun onResponse(call: Call<ResponseModel>, response: Response<ResponseModel>) {
                if (response.code() == 200){
                    val items = response.body()?.items.orEmpty()
                    val totalCount = response.body()?.total_count ?: 0
                    mView.onDataResult(items, totalCount)
                } else if (response.code() == 403){
                    mView.errorToast("Limit exceed. Please wait a little bit")
                }
                mView.showLoading(false)
            }
        })

    }
}