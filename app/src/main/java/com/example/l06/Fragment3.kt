package com.example.l06

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Fragment3 : Fragment() {
    companion object {
        var names = mutableListOf<String>("Yahya O'Brien", "Anne-Marie Sharpe", "Vanessa Raymond", "Kerri Estrada", "Trixie Greenwood", "Ariyan Gaines", "Kinga Stark")
        var occupations = mutableListOf("architekt", "informatyk", "informatyk", "nauczyciel", "taksówkarz", "fryzjer", "architekt")
        var genders = mutableListOf<Boolean>(false, true, true, true, true, false, true)
        var age = mutableListOf<Int>(15, 23, 11, 23, 33, 45, 21)
        var ratings = mutableListOf<Float>(1.5F, 2F, 5F, 5F, 3.5F, 4.5F, 2F)
        var colors = mutableListOf<Int>(Color.BLACK, Color.rgb(10, 200, 50), Color.RED, Color.RED, Color.RED, Color.RED, Color.RED)

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
            var adapter = CustomListViewAdapter(view.context, names, occupations, limg)
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
                    if (view != null) {
                        val intent = Intent(view.context, DetailsActivity::class.java)
                        val bundle = Bundle()
                        bundle.putInt("position", position)
                        bundle.putString("name", names[position])
                        bundle.putBoolean("gender", genders[position])
                        bundle.putString("occupation", occupations[position])
                        bundle.putInt("color", colors[position])
                        bundle.putFloat("rating", ratings[position])
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                }
            }
        }

        var fab: FloatingActionButton = view.findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(view.context, EditActivity::class.java)
            val bundle = Bundle()
            bundle.putInt("position", -1)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_3, container, false)
    }
}