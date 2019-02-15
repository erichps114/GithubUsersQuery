package com.project.githubusers.uicomponent

import com.project.githubusers.model.UserModel

interface MainContract {
    interface View{
        fun showLoading(isShow : Boolean)
        fun onDataResult (list : List<UserModel>, totalResult : Int)
        fun errorToast(message : String)
    }

    interface Presenter {
        fun searchUserName (query : String, currentPage : Int)
    }
}