package com.example.l06

//import androidx.test.core.app.ApplicationProvider.getApplicationContext

import android.content.Context
import android.view.ViewGroup

import androidx.core.content.ContextCompat.getSystemService

import android.view.LayoutInflater
import android.view.View
import android.widget.*


class CustomListViewAdapter internal constructor(context: Context) : BaseAdapter() {
    private var ltxt1 = arrayOf("Dzień dobry", "Bonjour", "Hola", "Zdravstvuyte", "Nǐn hǎo", "Salve", "Konnichiwa")
    private var ltxt2 = arrayOf("polski", "francuski", "hiszpański", "rosyjski", "chiński", "włoski", "japoński")

    class LVitem {
        var tv1: TextView? = null
        var tv2: TextView? = null
        var img: ImageView? = null
    }

    private var inflater: LayoutInflater? = null
    var myLVitem: LVitem? = null
    var list_length: Int

    override fun getCount(): Int {
        return list_length
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, listItemView: View?, parent: ViewGroup?): View? {
        var listItemView: View? = listItemView
        if (listItemView == null) {
            listItemView = inflater?.inflate(R.layout.list_row, null)
            myLVitem = LVitem()
            myLVitem!!.tv1 = listItemView!!.findViewById(R.id.row_tv1)
            myLVitem!!.tv2 = listItemView.findViewById(R.id.row_tv2)
            myLVitem!!.img = listItemView.findViewById(R.id.row_image) as ImageView
            listItemView.tag = myLVitem
        } else myLVitem = listItemView.tag as LVitem

        //here: setting of the components of the current list item
        myLVitem!!.tv1?.text = ltxt1[position]
        myLVitem!!.tv2?.text = ltxt2[position]
        return listItemView
    }

    init {
        list_length = ltxt1.size
        inflater = LayoutInflater.from(context)
    }
}