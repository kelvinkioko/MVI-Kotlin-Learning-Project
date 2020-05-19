package com.mvi.transaction.ui.auth.login.state

import com.mvi.transaction.database.entity.UserEntity

/* THIS IS THE SECOND STEP AFTER CREATING THE ENTITIES
 * This is a data class that will contain whatever will be inside the viewstate
 * Contains every data model that will be used inside our signin view.
 */

data class SigninViewState(

    var userEntity: UserEntity? = null
)