package com.example.zoafya.data.remote

import android.util.Log
import com.example.zoafya.data.remote.dto.PostsRequest
import com.example.zoafya.data.remote.dto.PostsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class BookServiceImpl(
    private val client: HttpClient
) : BookService {
    override suspend fun getPosts(): List<PostsResponse> {
        return try {
            client.get(HttpRoutes.POSTS).body()
        } catch (e: Exception) {
            //
            emptyList()
        }
    }

    override suspend fun createPost(postsRequest: PostsRequest): PostsResponse? {
        return try {
            val response = client.post(HttpRoutes.POSTS) {
                contentType(ContentType.Application.Json)
                setBody(postsRequest)
            }
            Log.d("Ktor", "createPost: ${response.status}")
            null
        } catch (e: Exception) {
            null
        }

    }
}