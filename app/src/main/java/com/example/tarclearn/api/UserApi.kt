package com.example.tarclearn.api

import com.example.tarclearn.model.UserDetailDto
import com.example.tarclearn.model.UserCourseDto
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @GET("users/{id}")//get a particular user
    suspend fun getUser(@Path("id") id: String) : Response<UserDetailDto>

    @GET("users/{id}/courses")//Get the courses enrolled by a user
    suspend fun getUserCourses(@Path("id")id: String): Response<List<UserCourseDto>>


//    @POST("users")//add a new user
//    suspend fun createUser(@Body user: User): Response<User>
//
//    @PUT("users/{id}")//update an existing user
//    suspend fun updateUser(@Path("id") id: String, @Body user: User): Response<User>
//
//    @DELETE("users/{id}")//remove an user
//    suspend fun deleteUser(@Path("id") id: String): Response<User>
}