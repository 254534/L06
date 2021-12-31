package com.example.l06

import android.widget.GridView

import android.view.ViewGroup

//import android.R
import android.content.Context
import android.view.View
import android.widget.AbsListView

import android.widget.BaseAdapter
import android.widget.ImageView


class GridAdapter(c: Context) : BaseAdapter() {
    private val ctx: Context

    var imgIDs = arrayOf<Int>(
        R.drawable.z1, R.drawable.z2, R.drawable.z3, R.drawable.z4
    )

    override fun getCount(): Int {
        return imgIDs.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(pos: Int, cView: View?, parent: ViewGroup?): View {
        val mV: ImageView
        if (cView == null) {
            mV = ImageView(ctx)
            mV.setLayoutParams(AbsListView.LayoutParams(200, 200))
            mV.setScaleType(ImageView.ScaleType.CENTER_CROP)
            mV.setPadding(8, 8, 8, 8)
        } else mV = cView as ImageView
        mV.setImageResource(imgIDs[pos])
        return mV
    }

    init {
        ctx = c
    }
}