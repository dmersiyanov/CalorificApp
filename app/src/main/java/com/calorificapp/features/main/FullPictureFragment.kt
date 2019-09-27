package com.calorificapp.features.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.calorificapp.R

class FullPictureFragment : Fragment() {

//    private var itemRealmList: RealmList<Item>? = null
    private var imageView: ImageView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_full_pic, container, false)
        imageView = view.findViewById(R.id.full_pic)
//        Picasso.get().load(itemRealmList!!.get(arguments!!.getInt("position")).getFile()).into(imageView)
        return view

    }

    override fun onDestroy() {
        super.onDestroy()
//        val refWatcher = YadApplication.getRefWatcher(activity)
//        refWatcher.watch(this)
    }

    companion object {

//        fun newInstance(position: Int, itemList: RealmList<Item>): FullPictureFragment {
//            val fragment = FullPictureFragment()
//            fragment.itemRealmList = itemList
//            val b = Bundle()
//            b.putInt("position", position)
//            fragment.arguments = b
//            return fragment
//        }
    }
}