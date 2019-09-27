package com.calorificapp.features.learning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.calorificapp.R

class LearningFragment : Fragment() {

    companion object {
        fun newInstance() = LearningFragment()
    }

    private lateinit var viewModel: LearningViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.learning_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LearningViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
