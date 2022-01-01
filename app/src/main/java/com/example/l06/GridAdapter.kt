package com.example.l06

import android.widget.GridView

import android.view.ViewGroup

//import android.R
import android.content.Context
import android.view.View
import android.widget.AbsListView

import android.widget.BaseAdapter
import android.widget.ImageView


class GridAdapter(c: Context, initImgIDs: Array<Int>) : BaseAdapter() {
    private val ctx: Context = c
    var imgIDs = initImgIDs

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
            mV.layoutParams = AbsListView.LayoutParams(200, 200)
            mV.scaleType = ImageView.ScaleType.CENTER_CROP
            mV.setPadding(8, 8, 8, 8)
        } else mV = cView as ImageView
        mV.setImageResource(imgIDs[pos])
        return mV
    }
}