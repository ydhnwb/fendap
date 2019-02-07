package com.starla.fendapbengkulu.utilities

import android.text.Html
import android.os.Build
import android.text.Spanned
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Color

class Others {
    companion object {
        fun fromHtml(html: String): Spanned {
            var result: Spanned?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
            } else {
                result = Html.fromHtml(html)
            }
            return result
        }
    }
}