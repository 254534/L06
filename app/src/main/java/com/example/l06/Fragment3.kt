package com.example.l06

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Fragment3 : Fragment() {
    companion object {
        var names = mutableListOf<String>("Yahya O'Brien", "Anne-Marie Sharpe", "Vanessa Raymond", "Kerri Estrada", "Trixie Greenwood", "Ariyan Gaines", "Kinga Stark")
        var occupations = mutableListOf("architekt", "informatyk", "informatyk", "nauczyciel", "taksówkarz", "fryzjer", "architekt")
        var genders = mutableListOf<Boolean>(false, true, true, true, true, false, true)
        var age = mutableListOf<Int>(15, 23, 11, 23, 33, 45, 21)
        var ratings = mutableListOf<Float>(1.5F, 2F, 5F, 5F, 3.5F, 4.5F, 2F)
        var colors = mutableListOf<Int>(Color.WHITE, Color.rgb(10, 200, 50), Color.RED, Color.BLUE, Color.RED, Color.GREEN, Color.RED)
        var currentChosen: Int = -1

        fun add(position: Int?, name: String?, occupation: String?, gender: Boolean?, rating: Float?, color: Int?) {
            if(position == null) return
            if (position == -1) {
                names += name!!
                occupations += occupation!!
                genders += gender!!
                ratings += rating!!
                colors += color!!
            }
            else {
                names[position] = name!!
                occupations[position] = occupation!!
                genders[position] = gender!!
                ratings[position] = rating!!
                colors[position] = color!!
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("f3_altered", viewLifecycleOwner) {
                _, bundle ->
            val position = bundle.getInt("position")
            val name = bundle.getString("name")
            val gender = bundle.getBoolean("gender")
            val occupation = bundle.getString("occupation")
            val temp_color = bundle.getInt("color")
            val rating = bundle.getFloat("rating")
            add(position, name, occupation, gender, rating, temp_color)

            var listView = view.findViewById<ListView>(R.id.main_listview)
            val limg: MutableList<Int> = mutableListOf()
            for(g in genders) {
                limg += if(g) {
                    R.drawable.woman
                } else {
                    R.drawable.man
                }
            }
            var adapter = CustomListViewAdapter(view.context, names, occupations, limg, colors)
            listView.adapter = adapter
        }

        var listView = view.findViewById<ListView>(R.id.main_listview)
        if (listView != null) run {
            val limg: MutableList<Int> = mutableListOf()
            for(g in genders) {
                limg += if(g) {
                    R.drawable.woman
                } else {
                    R.drawable.man
                }
            }
            var adapter = CustomListViewAdapter(view.context, names, occupations, limg, colors)
            listView.adapter = adapter
            listView.onItemClickListener = object : AdapterView.OnItemLongClickListener,
                AdapterView.OnItemClickListener {

                override fun onItemLongClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if(resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                        if (view != null) {
                            val bundle = Bundle()
                            bundle.putInt("position", position)
                            bundle.putString("name", names[position])
                            bundle.putBoolean("gender", genders[position])
                            bundle.putString("occupation", occupations[position])
                            bundle.putInt("color", colors[position])
                            bundle.putFloat("rating", ratings[position])

                            parentFragmentManager.setFragmentResult("f3", bundle)
                            findNavController().navigate(R.id.action_global_fragmentDetails)
                        }
                    }
                    else {
                        currentChosen = position
                        update_displayed()
                    }
                }
            }
        }

        var fab: FloatingActionButton = view.findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("position", -1)
            parentFragmentManager.setFragmentResult("f3_edit", bundle)
            findNavController().navigate(R.id.action_global_fragmentEdit)
        }

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if(currentChosen == -1) {
                currentChosen = 0
            }
            update_displayed()
        }
    }

    fun update_displayed() {
        val name: String = names[currentChosen]
        val gender: Boolean = genders[currentChosen]
        val occupation: String = occupations[currentChosen]
        val color: Int = colors[currentChosen]
        val rating: Float = ratings[currentChosen]

        val bundle = Bundle()

        bundle.putInt("position", currentChosen)
        bundle.putString("name", name)
        bundle.putBoolean("gender", gender)
        bundle.putString("occupation", occupation)
        bundle.putInt("color", color)
        bundle.putFloat("rating", rating)

        childFragmentManager.setFragmentResult("f3", bundle)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_3, container, false)
    }
}