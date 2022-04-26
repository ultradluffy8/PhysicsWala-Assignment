package com.example.physicswalaassignment

import org.json.JSONArray
import javax.security.auth.Subject

data class Profile (
    val name : String,
    val qualification : JSONArray,
    val subject : JSONArray
        )