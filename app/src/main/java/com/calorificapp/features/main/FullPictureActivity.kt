package com.calorificapp.features.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.calorificapp.R
import java.util.*

class FullPictureActivity : AppCompatActivity() {

//    private var itemRealmList: RealmList<Item>? = null
//    private var realm: Realm? = null
    private var actionBarToolbar: Toolbar? = null
    private var viewPager: ViewPager? = null

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_picture)
//        realm = Realm.getDefaultInstance()
//        itemRealmList = RealmList()

        getPicturesFromDb()

        val position = intent.getIntExtra("pic_position", 0)
        initViewPager(position)
        initActionBar()

    }

    private fun initViewPager(position: Int) {
        viewPager = findViewById(R.id.viewpager)
//        viewPager!!.adapter = ViewPagerAdapter(supportFragmentManager)
        viewPager!!.currentItem = position
        viewPager!!.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                val f = Formatter()
//                f.format("%d из %d", position + 1, itemRealmList!!.size())
                supportActionBar!!.title = f.toString()
            }

            override fun onPageSelected(position: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    @SuppressLint("RestrictedApi")
    private fun initActionBar() {

        actionBarToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(actionBarToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

//        actionBarToolbar!!.navigationIcon!!.setColorFilter(resources.getColor(R.color.white), PorterDuff.Mode.SRC_ATOP)

    }

    private fun getPicturesFromDb() {
//        val RealmResults = realm!!.where(Item::class.java).findAllAsync()
//        itemRealmList!!.addAll(RealmResults)

    }

    override fun onDestroy() {
        super.onDestroy()
//        realm!!.close()
    }


//    internal inner class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
//
//        override fun getItem(position: Int): Fragment {
//            return null
//            return FullPictureFragment.newInstance(position, itemRealmList)
//        }
//
//        override fun getCount(): Int {
//            return 0
////            return itemRealmList!!.size()
//
//        }
//    }
}

