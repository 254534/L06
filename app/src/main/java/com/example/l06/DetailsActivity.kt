package com.example.l06

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts

class DetailsActivity : AppCompatActivity() {
    private var position: Int? = null
    private var name: String? = null
    private var gender: Boolean? = null
    private var occupation: String? = null
    private var color: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val iii: Intent = getIntent()
        val bundle = iii.extras

        position = bundle?.getInt("position")!!
        name = bundle.getString("name")
        gender = bundle.getBoolean("gender")
        occupation = bundle.getString("occupation")
        color = bundle.getInt("color")

        val textName: TextView = findViewById(R.id.textViewName)
        val textGender: TextView = findViewById(R.id.textViewGender)
        val textOccupation: TextView = findViewById(R.id.textOccupation)
        val textColor: TextView = findViewById(R.id.textColor)
        val imageView: ImageView = findViewById(R.id.imageViewDetails)
        val ratingBar: RatingBar = findViewById(R.id.ratingBarD)

        if(name != null) {
            textName.text = name
        }
        textGender.text = if (gender == true) "kobieta" else "mężczyzna"
        imageView.setImageResource(if(gender == true) R.drawable.woman else R.drawable.man)
        textOccupation.text = occupation
        textColor.setBackgroundColor(color!!)
        ratingBar.numStars = 5
    }
}