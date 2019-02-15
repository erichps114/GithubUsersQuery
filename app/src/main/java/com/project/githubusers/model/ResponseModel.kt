package com.project.githubusers.model

import com.google.gson.annotations.Expose

class ResponseModel{
    @Expose
    var items : List<UserModel> = mutableListOf()

    @Expose
    var total_count = 0

}