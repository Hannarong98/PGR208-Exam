package com.example.foreignlands

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foreignlands.data.NoForeignLandsApiService
import com.example.foreignlands.data.TestData
import com.example.foreignlands.data.response.ApiResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val places = mutableListOf<TestData>()

        for (i in 0..100){
            places.add(TestData("Hello World"))
        }


        fetchApi()


    }



    private fun fetchApi(){
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.noforeignland.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(NoForeignLandsApiService::class.java)

        api.getPlaces().enqueue(object : Callback<ApiResponse> {
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                d("API", "onFailure: $t")
            }

            override fun onResponse(call: Call <ApiResponse>, response: Response<ApiResponse>) {
               // d("API", "onResponse ${response.body()?.features?.get(1)?.properties?.name}")
                showData(response.body()!!)
            }

        })
    }

    private fun showData(place: ApiResponse) {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PlaceAdapter(place)
        }
    }

}
