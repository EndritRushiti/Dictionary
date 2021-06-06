package com.syncmob.dictionary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.syncmob.dictionary.databinding.ActivityDefinitionBinding
import com.syncmob.dictionary.databinding.ActivityMainBinding

class DefinitionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDefinitionBinding
    private val KEY = "WORD_DEFINITION"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDefinitionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.definitionTextView.text = intent.getStringExtra(KEY)

        binding.backImageView.setOnClickListener {
            finish()
        }
    }
}