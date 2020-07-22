package com.ptbc.kotlin_mvvm.data.local.pref

import com.ptbc.kotlin_mvvm.data.DataManager

interface PreferencesHelper {

    var accessToken: String?

    var currentUserEmail: String?

    var currentUserId: Long?

    val currentUserLoggedInMode: Int

    var currentUserName: String?

    var currentUserProfilePicUrl: String?

    var isFirstTimeLogin: Boolean

    var adminToken: String?

    var adminTokenType: String?

    var adminExpiresIn: Long?

    fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode)
}