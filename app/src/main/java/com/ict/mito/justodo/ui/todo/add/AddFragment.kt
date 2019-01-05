package com.ict.mito.justodo.ui.todo.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.ict.mito.justodo.R
import com.ict.mito.justodo.databinding.AddFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AddFragment : Fragment() {

    @Inject
    lateinit var todoViewModelProvider: AddViewModelFactory.Provider

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val factory = todoViewModelProvider.provide()
        val viewModel = ViewModelProviders.of(
                this,
                factory
        ).get(AddViewModel::class.java)
        viewModel.navController = findNavController()

        val binding: AddFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.add_fragment,
                container,
                false
        )

        binding.also { it ->
            it.datePicker.setOnDatePickListener { dateSelected ->
                viewModel.onDateChanged(dateSelected)
            }
            it.viewmodel = viewModel
            it.setLifecycleOwner(this)
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}
