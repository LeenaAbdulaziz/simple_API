package com.example.simple_api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val responseText=findViewById<View>(R.id.tv) as TextView
        val apiInterface=APIClinent().GetClient()?.create(APIInterface::class.java)
        val call: Call<ActivityDetails?>?=apiInterface!!.doGetListResources()
        call?.enqueue (object : Callback<ActivityDetails?>
        {
            override fun onResponse(
                call: Call<ActivityDetails?>,
                response: Response<ActivityDetails?>
            ){
                val resoure:ActivityDetails?=response.body()
                val a=resoure?.activity
                val b=resoure?.type
                val c=resoure?.key

                responseText.text="$a\n $b \n $c"
            }
            override fun onFailure(call: Call<ActivityDetails?>, t:Throwable?){
                call.cancel()}



        })
    }

}
