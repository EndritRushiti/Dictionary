package com.syncmob.dictionary

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.syncmob.dictionary.databinding.ActivityMainBinding
import org.json.JSONArray
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val KEY = "WORD_DEFINITION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val queue = Volley.newRequestQueue(this)

        binding.findButton.setOnClickListener {

            val url = getUrl()
            val stringRequest = StringRequest(Request.Method.GET, url,
                { response ->
                    try {
                        extractDefinitionsFromJson(response)
                    } catch (exception: Exception) {
                        exception.printStackTrace()
                    }
                },
                { error ->
                    Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
                }
            )
            queue.add(stringRequest)
        }

    }

    private fun extractDefinitionsFromJson(response: String) {
        val jsonArray = JSONArray(response)
        val firstIndex = jsonArray.getJSONObject(0)
        val getshortdef = firstIndex.getJSONArray("shortdef")
        val firstShortDefinition = getshortdef.get(0)

        val intent = Intent(this, DefinitionActivity::class.java)
        intent.putExtra(KEY, firstShortDefinition.toString())
        startActivity(intent)
    }

    private fun getUrl(): String {
        val word = binding.wordEditText.text
        val key = "3549304b-14aa-41b0-ab68-739c43d7675a"
        val url = "https://www.dictionaryapi.com/api/v3/references/learners/json/$word?key=$key"

        return url
    }
}