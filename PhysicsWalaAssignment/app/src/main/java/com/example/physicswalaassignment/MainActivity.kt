package com.example.physicswalaassignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.squareup.picasso.Picasso
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var name: TextView
    lateinit var subjects: ListView
    lateinit var qualification: ListView
    lateinit var img: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val items = fetchData()
        val adapt = Adapter(items)
        recyclerView.adapter = adapt

    }

    private fun fetchData(): MutableList<Profile> {
        val url = " https://my-json-server.typicode.com/easygautam/data/users"
        var dataArray = ArrayList<Profile>()

        val jsonObjectRequest = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                for (x in 0 until it.length()) {
                    val jsonObject: JSONObject = it[x] as JSONObject
                    try {
                        lateinit var img_url: String
                        //jsonObject.getString("profileImage").toString()
                        var name = jsonObject.getString("name").toString()
                        var qualification : JSONArray =  jsonObject.getJSONArray("qualification")
                        var subjects : JSONArray = jsonObject.getJSONArray("subjects")
                        var profile = Profile(
                            name ,
                            qualification,
                            subjects
                        )
                        dataArray.add(profile)

                        img_url = jsonObject.getString("profileImage")
                        Picasso.get().load(img_url).into(img)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            },
            {
                @Override
                fun onErrorResponse(error: VolleyError) {
                    Toast.makeText(this, "Fail to get data...", Toast.LENGTH_LONG).show()
                }
            }
        )
        return dataArray
    }
}