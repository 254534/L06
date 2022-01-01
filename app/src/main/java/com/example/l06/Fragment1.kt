package com.example.l06

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class Fragment1 : Fragment() {
    companion object {
        private var helloText: String = "Dzień dobry"
        private var imageId: Int = R.drawable.z1
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = view.findViewById(R.id.textViewM)
        val imageView: ImageView = view.findViewById(R.id.imageViewM)

        textView.text = helloText
        imageView.setImageResource(imageId)

        parentFragmentManager.setFragmentResultListener("f2", viewLifecycleOwner) {
            _, bundle ->
            helloText = bundle.getString("helloText", helloText)
            imageId = bundle.getInt("imageId", imageId)

            textView.text = helloText
            imageView.setImageResource(imageId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

}