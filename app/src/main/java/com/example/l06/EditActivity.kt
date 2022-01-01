package com.example.l06

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.*

class EditActivity : AppCompatActivity() {
    private var position: Int? = null
    private var name: String? = null
    private var gender: Boolean? = null
    private var occupation: String? = null
    private var color: Int? = null
    private var rating: Float? = null

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val iii: Intent = getIntent()
        val bundle = iii.extras

        position = bundle?.getInt("position")!!
        name = bundle.getString("name")
        gender = bundle.getBoolean("gender")
        occupation = bundle.getString("occupation")
        color = bundle.getInt("color")
        rating = bundle.getFloat("rating")

        val editName: EditText = findViewById(R.id.editTextName)
        val editOccupation: EditText = findViewById(R.id.editOccupation)
        val radioButtonWoman: RadioButton = findViewById(R.id.radioButtonWoman)
        val radioButtonMan: RadioButton = findViewById(R.id.radioButtonMan)
        val imageView: ImageView = findViewById(R.id.imageViewEdit)
        val ratingBar: RatingBar = findViewById(R.id.ratingBarE)
        val textColor: TextView = findViewById(R.id.textColor2)

        editName.text = name?.toEditable()

        if (gender == true) radioButtonWoman.isChecked = true else radioButtonMan.isChecked = true
        imageView.setImageResource(if(gender == true) R.drawable.woman else R.drawable.man)
        editOccupation.text = occupation?.toEditable()
        textColor.setBackgroundColor(color!!)
        ratingBar.numStars = 5
        ratingBar.rating = rating!!
    }
}