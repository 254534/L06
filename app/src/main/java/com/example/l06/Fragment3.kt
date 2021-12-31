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
        private var names = arrayOf("Yahya O'Brien", "Anne-Marie Sharpe", "Vanessa Raymond", "Kerri Estrada", "Trixie Greenwood", "Ariyan Gaines", "Kinga Stark")
        private var occupation = arrayOf("architekt", "informatyk", "informatyk", "nauczyciel", "taksówkarz", "fryzjer", "architekt")
        private var gender = arrayListOf<Boolean>(false, true, true, true, true, false, true)
        private var age = arrayListOf<Int>(15, 23, 11, 23, 33, 45, 21)
        private var rating = arrayListOf<Float>(1.5F, 2F, 5F, 5F, 3.5F, 4.5F, 2F)
        private var color = arrayListOf<Int>(Color.BLACK, Color.rgb(10, 200, 50), Color.RED, Color.RED, Color.RED, Color.RED, Color.RED)
    }

    fun <T> append(arr: Array<T>, element: T): Array<T?> {
        val array = arr.copyOf(arr.size + 1)
        array[arr.size] = element
        return array
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var listView = view.findViewById<ListView>(R.id.main_listview)
        if (listView != null) run {
            var limg: Array<Int?> = arrayOf()
            for(g in gender) {
                limg = if(g) {
                    append(limg, R.drawable.woman)
                } else {
                    append(limg, R.drawable.man)
                }
            }
            var adapter = CustomListViewAdapter(view.context, names, occupation, limg as Array<Int>)
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
                        bundle.putBoolean("gender", gender[position])
                        bundle.putString("occupation", occupation[position])
                        bundle.putInt("color", color[position])
                        bundle.putFloat("rating", rating[position])
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                }
            }
        }

        var fab: FloatingActionButton = view.findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            val intent = Intent(view.context, DetailsActivity::class.java)
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