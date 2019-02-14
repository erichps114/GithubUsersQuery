package com.project.githubusers.uicomponent

import com.project.githubusers.model.UserModel

interface MainContract {
    interface View{
        fun showLoading(isShow : Boolean)
        fun onDataResult (list : List<UserModel>)
    }

    interface Presenter {
        fun searchUserName (query : String, currentPage : Int)
    }
}