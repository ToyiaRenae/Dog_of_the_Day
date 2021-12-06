package com.example.dogoftheday

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.dogoftheday.ViewModels.MainViewModel
import com.example.dogoftheday.ViewModels.MainViewModelFactory
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels{
        MainViewModelFactory((application as DogApplication).database.dogImageDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        viewModel.getAllDogs().observe(this){
            val imageView = findViewById<ImageView>(R.id.imageView2)
            Picasso.with(this).load(it.get(0).imageUrl).into(imageView)
            val button: Button = findViewById(R.id.button2)
            button.setOnClickListener {
                finish()
            }
        }


    }



}



}
}