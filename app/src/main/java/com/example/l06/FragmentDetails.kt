package com.example.l06

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.navigation.fragment.findNavController

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentDetails.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentDetails : Fragment() {

    private var position: Int? = null
    private var name: String? = null
    private var gender: Boolean? = null
    private var occupation: String? = null
    private var color: Int? = null
    private var rating: Float? = null
    private var innerBundle: Bundle? = null

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
            findNavController().navigate(R.id.action_global_fragmentEdit)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentDetails.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentDetails().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}