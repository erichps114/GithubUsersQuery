package com.project.githubusers.model

import java.io.Serializable

data class UserModel (
    var login : String? = "",
    var avatar_url : String?  = ""
) : Serializable