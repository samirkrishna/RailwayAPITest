
package com.example.chsamirkrishna.railwayapitest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    var et:EditText?=null
    var lview:ListView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        et=findViewById(R.id.et1)
        lview=findViewById(R.id.lview)
    }

    fun getpnrstatus(v: View)
    {

        var r =Retrofit.Builder().
                baseUrl("https://api.railwayapi.com/")
                .addConverterFactory(GsonConverterFactory.create()).build()

        var api =r.create(PnrStatusAPI::class.java)

        var call =api.getPnrInfo(et1.text.toString())

        call.enqueue(object : Callback<PnrStatus> {
            override fun onFailure(call: Call<PnrStatus>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<PnrStatus>?, response: Response<PnrStatus>?) {

                var pnr_status=response?.body()
                var list =ArrayList<String>()
                list.add("Train no"+pnr_status?.train?.number)
                list.add("Train name"+pnr_status?.train?.name)
                list.add("DOJ"+pnr_status?.doj)
                list.add("No Of Passagers : "+
                        pnr_status?.total_passengers.toString())
                list.add("From Station : "+
                        pnr_status?.from_station?.name)
                list.add("To Station : "+
                        pnr_status?.to_station?.name)

                var adapter =ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_dropdown_item_1line,list)
                lview?.adapter=adapter
            }

        })
    }
}
