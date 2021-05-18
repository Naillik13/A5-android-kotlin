package iimdemo.killiangalea.com.myapplication.domain

import android.util.Patterns

object StringUtils {
    fun isValidEmail(email: String): Boolean
            = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}