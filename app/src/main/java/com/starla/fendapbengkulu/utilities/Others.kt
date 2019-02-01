package com.starla.fendapbengkulu.utilities

import android.text.Html
import android.os.Build
import android.text.Spanned
import com.starla.fendapbengkulu.models.User
import android.content.Context
import android.content.Context.MODE_PRIVATE

class Others {
    companion object {
        fun fromHtml(html: String): Spanned {
            var result: Spanned? = null
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            } else {
                result = Html.fromHtml(html)
            }
            return result
        }

        fun setLoggedIn(context : Context, user : User){
            val settings = context.getSharedPreferences("TOKEN", MODE_PRIVATE);
            val editor = settings.edit()
            editor.putString("TOKEN", user.api_token)
            editor.commit()
        }
    }
}