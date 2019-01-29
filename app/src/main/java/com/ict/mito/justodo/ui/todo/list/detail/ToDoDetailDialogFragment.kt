package com.ict.mito.justodo.ui.todo.list.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.ict.mito.justodo.R
import com.ict.mito.justodo.databinding.TodoDialogFragmentBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

/**
 * Created by mito on 2018-12-15.
 */
class ToDoDetailDialogFragment : DialogFragment() {
    @Inject
    lateinit var todoDetailViewModelProvider: ToDoDetailViewModelFactory.Provider

    lateinit var viewmodel: ToDoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val args = arguments ?: return null
        val safeArgs = ToDoDetailDialogFragmentArgs.fromBundle(args)

        val factory = todoDetailViewModelProvider.provide()
        viewmodel = ViewModelProviders.of(
                this,
                factory
        ).get(ToDoDetailViewModel::class.java)

        viewmodel.id = safeArgs.toDoId

        val binding: TodoDialogFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.todo_dialog_fragment,
                container,
                false
        )
        binding.also {
            it.viewmodel = viewmodel
            it.setLifecycleOwner(this)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val appCompatActivity = activity as AppCompatActivity?
        appCompatActivity?.supportActionBar?.let {
            it.title = viewmodel.todo.value?.title
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeButtonEnabled(true)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }
}