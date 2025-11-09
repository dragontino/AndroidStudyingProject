package ru.dragontino.androidtestproject.core.retrofit

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class ServerResponseInterceptor : Interceptor {
    private companion object {
        const val TAG = "ServerResponseInterceptor"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        val url = response.request.url
        val code = response.code
        Log.d(TAG, "Response code from $url is: $code")
        return response
    }
}