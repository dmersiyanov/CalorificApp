package com.calorificapp.features.main

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.calorificapp.R
import com.calorificapp.data.PaperLocalStorage
import com.calorificapp.data.repo.PicturesLocalRepoImpl
import com.calorificapp.features.main.data.PhotoDataSource
import com.calorificapp.features.main.data.SharedPrefRepo
import com.calorificapp.features.main.model.MonthlyPics
import com.calorificapp.features.main.model.YearlyPics
import com.calorificapp.features.main.model.addWeeklyPic
import com.calorificapp.features.utils.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_main.*
import org.angmarch.views.NiceSpinner
import org.angmarch.views.OnSpinnerItemSelectedListener
import timber.log.Timber
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : Fragment(), ProgressPicsAdapter.OnPhotoClickListener {

    private var listener: OnFragmentInteractionListener? = null
    private val spinnerItems = mutableListOf<String>()
    private var mCurrentPhotoPath: String = ""

    private var currentMonthPics = MonthlyPics()

    private lateinit var sharedPrefRepo: PhotoDataSource
    private lateinit var adapter: ProgressPicsAdapter
    private var weekNumber = -1
    private var picturesLocalRepoImpl: PicturesLocalRepoImpl =
        PicturesLocalRepoImpl(PaperLocalStorage())


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinnerItems.addAll(resources.getStringArray(R.array.items_month))
        setupRecycler()
        setupSpinner()
        setCurrentMonth(month_spinner, rv_progress_pics)


        sharedPrefRepo = SharedPrefRepo(context!!)

    }

    override fun onResume() {
        super.onResume()
        loadPictures()
    }

    private fun loadPictures() {
        picturesLocalRepoImpl.get()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.setItems(it)
            }, {
                Timber.e(it)
            })
    }

    private fun setupRecycler() {
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_progress_pics.run {
            this.layoutManager = layoutManager
            this@MainFragment.adapter = ProgressPicsAdapter(this@MainFragment)
            adapter = this@MainFragment.adapter

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        month_spinner.selectedIndex = layoutManager.findFirstVisibleItemPosition()
                    }
                }
            })
        }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(rv_progress_pics)
    }

    private fun setupSpinner() {
        month_spinner.attachDataSource(spinnerItems)

        month_spinner.onSpinnerItemSelectedListener =
            OnSpinnerItemSelectedListener { parent, view, position, id ->
                rv_progress_pics.scrollToPosition(
                    position
                )
            }
    }

    private fun setCurrentMonth(spinner: NiceSpinner, list: RecyclerView) {
        val currentMonth = Calendar.getInstance().get(Calendar.MONTH)

        spinner.selectedIndex = currentMonth
        list.scrollToPosition(currentMonth)
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(requireActivity().packageManager) != null) {
            // Create the File where the photo should go
            var photoFile: File? = null
            try {
                photoFile = createImageFile()
            } catch (ex: IOException) {
                // Error occurred while creating the File
                Timber.tag(this.tag).e(ex)
            }

            // Continue only if the File was successfully created
            if (photoFile != null) {
                val photoURI = FileProvider.getUriForFile(
                    requireContext(),
                    resources.getString(R.string.file_provider_authority),
                        photoFile)
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }


    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "JPEG_$timeStamp"
        val storageDir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(
                imageFileName, /* prefix */
                ".jpg", /* suffix */
                storageDir      /* directory */
        )

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.absolutePath
        return image
    }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imgFile = File(mCurrentPhotoPath)
            if (imgFile.exists()) {
                context?.toast(imgFile.absolutePath)
//                imgFile.delete()
                loadImage(imgFile.absolutePath)
            }
        }
    }

    private fun loadImage(path: String) {
        currentMonthPics.weeklyPics.addWeeklyPic(weekNumber, path)
        val yearlyPics = YearlyPics()
        yearlyPics.list[month_spinner.selectedIndex] = currentMonthPics
        adapter.setItems(yearlyPics)

        picturesLocalRepoImpl.save(yearlyPics)
        currentMonthPics = MonthlyPics()
    }


    override fun onClick(weekNumber: Int) {
        this.weekNumber = weekNumber
        dispatchTakePictureIntent()
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        private const val REQUEST_IMAGE_CAPTURE = 1

        @JvmStatic
        fun newInstance() = MainFragment()
    }
}
