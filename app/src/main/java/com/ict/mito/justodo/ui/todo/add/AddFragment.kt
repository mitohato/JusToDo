package com.ict.mito.justodo.ui.todo.add

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.ict.mito.justodo.R
import com.ict.mito.justodo.databinding.AddFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class AddFragment : Fragment() {

    @Inject
    lateinit var todoViewModelProvider: AddViewModelFactory.Provider
    private val viewmodel: AddViewModel by lazy {
        ViewModelProvider(
            this,
            todoViewModelProvider.provide()
        ).get(AddViewModel::class.java)
    }

    private var binding: AddFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewmodel.navController = findNavController()

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.add_fragment,
            container,
            false
        )

        binding?.let {
            it.datePicker.setOnDatePickListener { dateSelected ->
                viewmodel.onDateChanged(dateSelected)
            }
            it.viewmodel = viewmodel
            it.lifecycleOwner = this
        }

        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        val appCompatActivity = activity as AppCompatActivity?
        appCompatActivity?.supportActionBar?.let {
            it.title = getString(R.string.app_name)
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(
        menu: Menu,
        inflater: MenuInflater
    ) {
        inflater.inflate(
            R.menu.menu_default,
            menu
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
