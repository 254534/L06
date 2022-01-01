package com.example.l06

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.*
import android.widget.Toast

import android.widget.RadioButton

import android.widget.RadioGroup
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red


class EditActivity : AppCompatActivity() {
    private var position: Int? = null
    private var name: String? = null
    private var gender: Boolean? = null
    private var occupation: String? = null
    private var color: Int? = null
    private var rating: Float? = null

    private lateinit var textColor: TextView

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
        textColor = findViewById(R.id.textColor2)

        if (position != null) {
            editName.text = name?.toEditable()
            if (gender == true) radioButtonWoman.isChecked = true else radioButtonMan.isChecked = true
            imageView.setImageResource(if(gender == true) R.drawable.woman else R.drawable.man)
            editOccupation.text = occupation?.toEditable()
            textColor.setBackgroundColor(color!!)
            ratingBar.numStars = 5
            ratingBar.rating = rating!!
        }

        val radioGroup: RadioGroup = findViewById(R.id.RadioGroupGenders)
        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
//            val rb = findViewById<View>(checkedId) as RadioButton
            gender = checkedId == R.id.radioButtonWoman
            imageView.setImageResource(if(checkedId == R.id.radioButtonWoman) R.drawable.woman else R.drawable.man)
        })

        val redInt = color!!.red
        val greenInt = color!!.green
        val blueInt = color!!.blue

        val seekBarRed: SeekBar = findViewById(R.id.seekBarR)
        val seekBarGreen: SeekBar = findViewById(R.id.seekBarG)
        val seekBarBlue: SeekBar = findViewById(R.id.seekBarB)

        seekBarRed.max = 255
        seekBarGreen.max = 255
        seekBarBlue.max = 255

        seekBarRed.setProgress(redInt, true)
        seekBarGreen.setProgress(greenInt, true)
        seekBarBlue.setProgress(blueInt, true)

        seekBarRed.setOnSeekBarChangeListener(seekBarListenerRed)
        seekBarGreen.setOnSeekBarChangeListener(seekBarListenerGreen)
        seekBarBlue.setOnSeekBarChangeListener(seekBarListenerBlue)

        val backButton: Button = findViewById(R.id.button4)
        backButton.setOnClickListener {
            onBackPressed()
        }

        val saveButton: Button = findViewById(R.id.button5)
        saveButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
//            val bundle = Bundle()
            bundle.putInt("position", position!!)
            bundle.putString("name", editName.text.toString())
            bundle.putBoolean("gender", gender!!)
            bundle.putString("occupation", editOccupation.text.toString())
            bundle.putInt("color", Color.rgb(seekBarRed.progress, seekBarGreen.progress, seekBarBlue.progress))
            bundle.putFloat("rating", ratingBar.rating)

            intent.putExtras(bundle)
            startActivity(intent)
        }
    }

    val seekBarListenerRed = object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            color = color?.let { Color.rgb(i, it.green, it.blue) }
            textColor.setBackgroundColor(color!!)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }

    val seekBarListenerGreen = object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            color = color?.let { Color.rgb(it.red, i, it.blue) }
            textColor.setBackgroundColor(color!!)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }

    val seekBarListenerBlue = object: SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {
            color = color?.let { Color.rgb(it.red, it.green, i) }
            textColor.setBackgroundColor(color!!)
        }

        override fun onStartTrackingTouch(seekBar: SeekBar) {}
        override fun onStopTrackingTouch(seekBar: SeekBar) {}
    }
}