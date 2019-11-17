package com.calorificapp.features.account

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.calorificapp.CalorificApp
import com.calorificapp.core.base.BaseViewModel
import com.calorificapp.data.PaperLocalStorage
import com.calorificapp.data.repo.PicturesLocalRepoImpl
import com.calorificapp.features.account.model.MonthlyPics
import com.calorificapp.features.account.model.YearlyPics
import com.calorificapp.features.account.model.addWeeklyPic
import com.calorificapp.features.utils.toast
import com.calorificapp.pure_domain.ResourceManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import java.io.File
import javax.inject.Inject

class AccountViewModel @Inject constructor(
    application: Application,
    private val resourceManager: ResourceManager
) :
    BaseViewModel(application) {

    override val disposables: CompositeDisposable = CompositeDisposable()
    private var picturesLocalRepoImpl: PicturesLocalRepoImpl =
        PicturesLocalRepoImpl(PaperLocalStorage())
    private var currentMonthPics = MonthlyPics()
    var weekNumber = -1
    val picturesLiveData: MutableLiveData<YearlyPics> = MutableLiveData()


    fun onPhotoTakeResult(isError: Boolean, path: String = "", currentMonth: Int) {
        if (!isError) {
            val imgFile = File(path)
            if (imgFile.exists()) {
                getApplication<CalorificApp>().toast(imgFile.absolutePath)
                loadImage(imgFile.absolutePath, currentMonth)
            }
        }
    }

    fun onResume() {
        loadPictures()
    }

    private fun loadPictures() {
        picturesLocalRepoImpl.get()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                picturesLiveData.postValue(it)
            }, {
                Timber.e(it)
            })
            .bind()
    }

    private fun loadImage(path: String, currentMonth: Int) {
        currentMonthPics.weeklyPics.addWeeklyPic(weekNumber, path)
        val yearlyPics = YearlyPics()
        yearlyPics.list[currentMonth] = currentMonthPics


        picturesLiveData.postValue(yearlyPics)

        picturesLocalRepoImpl
            .save(yearlyPics)
            .subscribe()
        currentMonthPics = MonthlyPics()
    }

}