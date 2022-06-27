package com.yaan.example.network.volly

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

const val BASE_URL = "https://www.davadoctor.com/wp-json/wc/v3/products"
const val CONSUMER_QUERY = "?consumer_key=ck_64ad758ad6d63a1e02c48738f2e657ff2128154d" +
        "&consumer_secret=cs_8dd8009dc328e2cd8cef9afad313a3297566352a"


class NetworkUtility(context: Context) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context)


    private suspend fun apiRequest(url: String): String =
        suspendCancellableCoroutine { continuation ->
            val request = StringRequest(url,
                { json ->

                    Log.d("teeeeeeest", "apiRequest: $json")
                    if (continuation.isActive)
                        continuation.resume(json)
                },
                { error ->
                    Log.d("teeeeeeest", "apiRequest: ERROR: $error")
                    if (continuation.isActive)
                        continuation.resume(error.message ?: "UNKNOWN ERROR")
                }
            )

            requestQueue.add(request)

        }

    suspend fun getProducts() = apiRequest("$BASE_URL$CONSUMER_QUERY")

    suspend fun getProductById(productId: Int) =
        apiRequest("$BASE_URL/$productId$CONSUMER_QUERY")

    suspend fun getCategories() =
        apiRequest("$BASE_URL/categories$CONSUMER_QUERY")

    suspend fun getCategoryProducts(categoryId: Int) =
        apiRequest("$BASE_URL$CONSUMER_QUERY&category=$categoryId")


}




