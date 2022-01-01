package com.example.l06

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.navigation.fragment.findNavController

class FragmentEdit : Fragment() {

    private var position: Int? = null
    private var name: String? = ""
    private var gender: Boolean? = true
    private var occupation: String? = ""
    private var color: Int? = Color.WHITE
    private var rating: Float? = null

    private var originalBundle: Bundle? = null

    private lateinit var textColor: TextView

    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editName: EditText = view.findViewById(R.id.editTextName)
        val editOccupation: EditText = view.findViewById(R.id.editOccupation)
        val radioButtonWoman: RadioButton = view.findViewById(R.id.radioButtonWoman)
        val radioButtonMan: RadioButton = view.findViewById(R.id.radioButtonMan)
        val imageView: ImageView = view.findViewById(R.id.imageViewEdit)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBarE)

        val seekBarRed: SeekBar = view.findViewById(R.id.seekBarR)
        val seekBarGreen: SeekBar = view.findViewById(R.id.seekBarG)
        val seekBarBlue: SeekBar = view.findViewById(R.id.seekBarB)

        parentFragmentManager.setFragmentResultListener("f3_edit", viewLifecycleOwner) {
                _, bundle ->
            originalBundle = bundle
            position = bundle.getInt("position")
            name = bundle.getString("name")
            gender = bundle.getBoolean("gender")
            occupation = bundle.getString("occupation")
            color = bundle.getInt("color")
            rating = bundle.getFloat("rating")

            textColor = view.findViewById(R.id.textColor2)

            if (position != null) {
                editName.text = name?.toEditable()
                if (gender == true) radioButtonWoman.isChecked = true else radioButtonMan.isChecked = true
                imageView.setImageResource(if(gender == true) R.drawable.woman else R.drawable.man)
                editOccupation.text = occupation?.toEditable()
                textColor.setBackgroundColor(color!!)
                ratingBar.numStars = 5
                ratingBar.rating = rating!!

                val redInt = color!!.red
                val greenInt = color!!.green
                val blueInt = color!!.blue

                seekBarRed.setProgress(redInt, true)
                seekBarGreen.setProgress(greenInt, true)
                seekBarBlue.setProgress(blueInt, true)
            }
        }

        val radioGroup: RadioGroup = view.findViewById(R.id.RadioGroupGenders)
        radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { group, checkedId ->
//            val rb = findViewById<View>(checkedId) as RadioButton
            gender = checkedId == R.id.radioButtonWoman
            imageView.setImageResource(if(checkedId == R.id.radioButtonWoman) R.drawable.woman else R.drawable.man)
        })

        seekBarRed.max = 255
        seekBarGreen.max = 255
        seekBarBlue.max = 255

        seekBarRed.setOnSeekBarChangeListener(seekBarListenerRed)
        seekBarGreen.setOnSeekBarChangeListener(seekBarListenerGreen)
        seekBarBlue.setOnSeekBarChangeListener(seekBarListenerBlue)

        val backButton: Button = view.findViewById(R.id.button4)
        backButton.setOnClickListener {
            if (position == -1) {
                findNavController().navigate(R.id.action_global_fragment3)
            }
            else {
                parentFragmentManager.setFragmentResult("f3", originalBundle!!)
                findNavController().navigate(R.id.action_global_fragmentDetails)
            }
        }

        val saveButton: Button = view.findViewById(R.id.button5)
        saveButton.setOnClickListener {
            val bundle = Bundle()

            bundle.putInt("position", position!!)
            bundle.putString("name", editName.text.toString())
            bundle.putBoolean("gender", gender!!)
            bundle.putString("occupation", editOccupation.text.toString())
            bundle.putInt("color", Color.rgb(seekBarRed.progress, seekBarGreen.progress, seekBarBlue.progress))
            bundle.putFloat("rating", ratingBar.rating)

            parentFragmentManager.setFragmentResult("f3_altered", bundle)
            findNavController().navigate(R.id.action_global_fragment3)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
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