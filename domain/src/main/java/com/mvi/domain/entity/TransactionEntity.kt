package com.mvi.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransactionEntity(

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
    @SerializedName("transaction_name")
    val transaction_name: String? = null,

    @Expose
    @SerializedName("transaction_amount")
    val transaction_amount: String? = null,

    @Expose
    @SerializedName("transaction_description")
    val transaction_description: String? = null,

    @Expose
    @SerializedName("updated_at")
    val updated_at: String? = null,

    @Expose
    @SerializedName("created_at")
    val created_at: String? = null
) {

    /*
     *  ALT + INSERT to insert 'to string' method
     */

    override fun toString(): String {
        return "TransactionEntity(id=$id, user_id=$user_id, transaction_id=$transaction_id, transaction_name=$transaction_name, transaction_amount=$transaction_amount, transaction_description=$transaction_description, updated_at=$updated_at, created_at=$created_at)"
    }
}