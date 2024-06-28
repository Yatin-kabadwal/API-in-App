package com.example.apibasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView  = findViewById(R.id.recyclerView)

        val retrofitBuilder = Retrofit.Builder()// Step number 4 to create builder
            .baseUrl("https://dummyjson.com/")  // paste the URL here but delete the end point as here we  have deleted the "product"
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        val retrofitData = retrofitBuilder.getProduct()  //getting data

        retrofitData.enqueue(object : Callback<MyData?> { // Press control + shift + space


            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                // if API call is successful, use the data and show it in your app
                val responseBody = response.body()
                val productList = responseBody?.products ?: emptyList()

                myAdapter = MyAdapter(this@MainActivity,productList)
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                // if API call fails
                Log.d("Main Activity", "onFailure: " + t.message)
            }
        })
    }
}
