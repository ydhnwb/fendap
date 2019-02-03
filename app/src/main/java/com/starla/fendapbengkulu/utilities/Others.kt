package com.starla.fendapbengkulu.utilities

import android.text.Html
import android.os.Build
import android.text.Spanned
import com.starla.fendapbengkulu.models.User
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Color

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

        fun getDarkColor(i : Int, value : Double) : Int{
            val r = Color.red(i)
            val g = Color.green(i)
            val b = Color.blue(i)
            return Color.rgb((r*value).toInt(), (g*value).toInt(), (b*value).toInt())
        }
    }
}