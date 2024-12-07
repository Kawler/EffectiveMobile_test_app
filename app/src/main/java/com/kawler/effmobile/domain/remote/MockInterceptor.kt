package com.kawler.effmobile.domain.remote

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class MockInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val url = chain.request().url().toString()

        val responseString = when {
            url.endsWith("offers.json") -> getJsonFromAssets("offers.json")
            url.endsWith("offers_tickets.json") -> getJsonFromAssets("offers_tickets.json")
            url.endsWith("tickets.json") -> getJsonFromAssets("tickets.json")
            else -> throw IllegalArgumentException("Unknown URL")
        }

        return Response.Builder()
            .request(chain.request())
            .protocol(Protocol.HTTP_1_1)
            .code(200)
            .message("OK")
            .body(ResponseBody.create(MediaType.get("application/json"), responseString))
            .build()
    }

    private fun getJsonFromAssets(fileName: String): String {
        val inputStream = this::class.java.classLoader?.getResourceAsStream("assets/$fileName")
        return inputStream?.bufferedReader()?.use { it.readText() } ?: ""
    }
}
