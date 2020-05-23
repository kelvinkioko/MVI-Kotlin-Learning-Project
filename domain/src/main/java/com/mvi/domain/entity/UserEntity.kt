package com.mvi.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/*
 *  A normal entity class can also be annoted with the entity dao to enable it as a table in a room database
 *  But I'll do that later after I have been able to log in successfully.
 */

data class UserEntity(

    @Expose
    @SerializedName("id")
    val id: Int? = null,

    @Expose
    @SerializedName("user_id")
    val user_id: String? = null,

    @Expose
    @SerializedName("name")
    val name: String? = null,

    @Expose
    @SerializedName("email")
    val email: String? = null,

    @Expose
    @SerializedName("phonenumber")
    val phonenumber: String? = null,

    @Expose
    @SerializedName("updated_at")
    val updated_at: String? = null,

    @Expose
    @SerializedName("created_at")
    val created_at: String? = null
) {

    override fun toString(): String {
        return "UserEntity(id=$id, user_id=$user_id, name=$name, email=$email, phonenumber=$phonenumber, updated_at=$updated_at, created_at=$created_at)"
    }
}