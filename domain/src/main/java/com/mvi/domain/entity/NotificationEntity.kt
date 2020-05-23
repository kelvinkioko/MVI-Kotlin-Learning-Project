package com.mvi.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NotificationEntity(

    @Expose
    @SerializedName("id")
    val id: Int? = null,

    @Expose
    @SerializedName("user_id")
    val user_id: String? = null,

    @Expose
    @SerializedName("transaction_id")
    val transaction_id: String? = null,

    @Expose
    @SerializedName("message")
    val message: String? = null,

    @Expose
    @SerializedName("state")
    val state: String? = null,

    @Expose
    @SerializedName("updated_at")
    val updated_at: String? = null,

    @Expose
    @SerializedName("created_at")
    val created_at: String? = null
) {

    override fun toString(): String {
        return "NotificationEntity(id=$id, user_id=$user_id, transaction_id=$transaction_id, message=$message, state=$state, updated_at=$updated_at, created_at=$created_at)"
    }
}