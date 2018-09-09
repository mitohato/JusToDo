package com.ict.mito.justodo.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ict.mito.justodo.R
import com.ict.mito.justodo.databinding.MainFragmentBinding
import com.ict.mito.justodo.model.ToDoInfo
import com.ict.mito.justodo.view.ToDoInfoAdapter

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: MainFragmentBinding? =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.main_fragment,
                        container,
                        false
                )
        
        val todoInfoArrayList = arrayListOf<ToDoInfo>()
    
        todoInfoArrayList.add(ToDoInfo(
                id = "",
                title = "hoge",
                description = "hogehoge",
                dueData = 0.toString(),
                deadline = "0"
        ))
    
        todoInfoArrayList.add(ToDoInfo(
                id = "",
                title = "hoge",
                description = "hogehoge",
                dueData = 0.toString(),
                deadline = "0"
        ))
        binding?.let { it ->
            it.layoutManager = LinearLayoutManager(this.context?.applicationContext)
            it.adapter = ToDoInfoAdapter(todoInfoArrayList)
            it.setAddOnClick {
                it.findNavController().navigate(R.id.action_mainFragment_to_addFragment)
            }
        }
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
}
