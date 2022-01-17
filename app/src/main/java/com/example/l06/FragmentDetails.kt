package com.example.l06

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentDetails : Fragment() {
    private var position: Int? = null
    private var name: String? = null
    private var gender: Boolean? = null
    private var occupation: String? = null
    private var color: Int? = null
    private var rating: Float? = null
    private var innerBundle: Bundle? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        parentFragmentManager.setFragmentResultListener("f3", viewLifecycleOwner) {
                _, bundle ->

            innerBundle = bundle
            position = bundle?.getInt("position")!!
            name = bundle.getString("name")
            gender = bundle.getBoolean("gender")
            occupation = bundle.getString("occupation")
            color = bundle.getInt("color")
            rating = bundle.getFloat("rating")

            val textName: TextView = view.findViewById(R.id.textViewName)
            val textGender: TextView = view.findViewById(R.id.textViewGender)
            val textOccupation: TextView = view.findViewById(R.id.textOccupation)
            val textColor: TextView = view.findViewById(R.id.textColor)
            val imageView: ImageView = view.findViewById(R.id.imageViewDetails)
            val ratingBar: RatingBar = view.findViewById(R.id.ratingBarD)

            if(name != null) {
                textName.text = name
            }
            textGender.text = if (gender == true) "kobieta" else "mężczyzna"
            imageView.setImageResource(if(gender == true) R.drawable.woman else R.drawable.man)
            textOccupation.text = occupation
            textColor.setBackgroundColor(color!!)
            ratingBar.numStars = 5
            ratingBar.rating = rating!!
        }

        val backButton: Button = view.findViewById(R.id.button1)
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_global_fragment3)
        }

        val editButton: Button = view.findViewById(R.id.button2)
        editButton.setOnClickListener {
            parentFragmentManager.setFragmentResult("f3_edit", innerBundle!!)
            if(parentFragment != null) {
                parentFragment?.parentFragmentManager?.setFragmentResult("f3_edit", innerBundle!!)
            }
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_nav).visibility = View.GONE
            findNavController().navigate(R.id.action_global_fragmentEdit)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }
}