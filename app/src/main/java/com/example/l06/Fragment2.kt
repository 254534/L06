package com.example.l06

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class Fragment2 : Fragment() {
    companion object {
        private var languagesInx: Int = 0
        private var imagesInx: Int = 0
    }
    private var listLanguages = arrayOf("Dzień dobry", "Bonjour", "Hola", "Zdravstvuyte", "Nǐn hǎo", "Salve", "Konnichiwa")
    private var listImageIDs = arrayOf(R.drawable.z1, R.drawable.z2, R.drawable.z3, R.drawable.z4, R.drawable.man, R.drawable.woman, R.drawable.z3, R.drawable.z4, R.drawable.z1, R.drawable.z2, R.drawable.man, R.drawable.woman)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridView = view.findViewById(R.id.gridview) as GridView
        val spin = view.findViewById(R.id.spinner) as Spinner

        // Setting adapters ________________________________________________________________________
        gridView.adapter = GridAdapter(this.requireContext(), listImageIDs)

        val adapterSpin = ArrayAdapter(this.requireContext(), android.R.layout.simple_spinner_item,
            listLanguages)
        adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = adapterSpin

        // Setting listeners _______________________________________________________________________
        gridView.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                imagesInx = position
                val bundle = getCurrentBundle()
                parentFragmentManager.setFragmentResult("f2", bundle)
                if (view != null) {
                    Toast.makeText(view.context, "Image changed to ${position + 1}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        spin.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long
            ) {
                languagesInx = position
                val bundle = getCurrentBundle()
                parentFragmentManager.setFragmentResult("f2", bundle)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun onResume() {
        super.onResume()
        val spin = requireView().findViewById(R.id.spinner) as Spinner
        spin.setSelection(languagesInx)
    }

    fun getCurrentBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString("helloText", listLanguages[languagesInx])
        bundle.putInt("imageId", listImageIDs[imagesInx])
        return bundle
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2, container, false)
    }
}