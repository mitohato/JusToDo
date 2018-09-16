package com.ict.mito.justodo.ui.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ict.mito.justodo.model.ToDoInfo

/**
 * Created by mito on 2018/09/04.
 */
class AddViewModel : ViewModel() {
    var todoInfoLiveData: MutableLiveData<ToDoInfo>? = null
        get() {
            if (field == null) {
                field = MutableLiveData()
            }
            return field
        }
}
