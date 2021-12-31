package com.example.l06

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class Fragment3 : Fragment() {
    private var ltxt1 = arrayOf("Dzień dobry", "Bonjour", "Hola", "Zdravstvuyte", "Nǐn hǎo", "Salve", "Konnichiwa")
    private var ltxt2 = arrayOf("polski", "francuski", "hiszpański", "rosyjski", "chiński", "włoski", "japoński")
    private var limg = arrayOf(R.drawable.z1, R.drawable.z2, R.drawable.z3, R.drawable.z4, R.drawable.z1, R.drawable.z4, R.drawable.z3)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var listView = view.findViewById<ListView>(R.id.main_listview)
        if (listView != null) run {
            var adapter = CustomListViewAdapter(view.context, ltxt1, ltxt2, limg)
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
                        Toast.makeText(view.context, ltxt1[position], Toast.LENGTH_LONG).show()
                    }
                }
            }
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