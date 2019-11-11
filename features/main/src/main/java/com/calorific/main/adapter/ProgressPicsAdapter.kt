package com.calorific.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.calorific.main.model.YearlyPics
import com.calorificapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_progress_pic.view.*
import java.io.File

class ProgressPicsAdapter(private var clickListener: OnPhotoClickListener,
                          private var yearlyPics: YearlyPics = YearlyPics()
)
    : RecyclerView.Adapter<ProgressPicsAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var picasso: Picasso

    fun setItems(yearlyPics: YearlyPics) {
        this.yearlyPics = yearlyPics
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        picasso = Picasso.get()
        context = parent.context
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_progress_pic, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 12
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val weeklyPics = yearlyPics.list[position].weeklyPics

        with(holder) {
            for(i in 0..3) {
                val path = weeklyPics[i]

                if (path.isNotEmpty()) {
                    picasso
                            .load(File(path))
                            .fit().centerCrop()
                            .into(weekPicViews[i])
                }
            }
        }

        holder.setListeners(clickListener)
    }



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var week1: ImageView = itemView.week_1
        private var week2: ImageView = itemView.week_2
        private var week3: ImageView = itemView.week_3
        private var week4: ImageView = itemView.week_4
        val weekPicViews = listOf(week1, week2, week3, week4)

        fun setListeners(listener: OnPhotoClickListener) {
            for (i in 0..3) {
                weekPicViews[i].setOnClickListener{ listener.onClick(i) }
            }
        }
    }

    interface OnPhotoClickListener{
        fun onClick(weekNumber: Int)
    }
}
