package com.example.htnotpadtestapp.presentation.navigation

import androidx.annotation.DrawableRes
import com.example.htnotpadtestapp.R
import com.example.htnotpadtestapp.presentation.notes.ROUTE_NOTES

enum class NavigationTopLevelDestination(
    @DrawableRes val icon: Int,
    @DrawableRes val iconSelected: Int,
    val route: String
) {
    Note(
        R.drawable.ic_nav_note,
        R.drawable.ic_nav_note_selected,
        ROUTE_NOTES,

    ),
    Audio(
        R.drawable.ic_nav_audio,
        R.drawable.ic_nav_audio_selected,
        ROUTE_NOTES
    ),
    Messages(
        R.drawable.ic_nav_sms_notification,
        R.drawable.ic_nav_sms_notification_selected,
        ROUTE_NOTES
    ),
    Setting(
        R.drawable.ic_nav_setting,
        R.drawable.ic_nav_setting_selected,
        ROUTE_NOTES
    );
}
