package com.ict.mito.justodo.reducer

import com.ict.mito.justodo.action.CreateToDoAction
import com.ict.mito.justodo.action.DeleteToDoAction
import com.ict.mito.justodo.state.ToDoState
import info.izumin.android.droidux.annotation.Dispatchable
import info.izumin.android.droidux.annotation.Reducer

/**
 * Created by mito on 2018/07/16.
 */
@Reducer(ToDoState::class)
class ToDoReducer {

    @Dispatchable(CreateToDoAction::class)
    fun create(
        state: ToDoState,
        action: CreateToDoAction
    ): ToDoState = ToDoState(state.toDoInfoList + action.toDoInfo)

    @Dispatchable(DeleteToDoAction::class)
    fun delete(
        state: ToDoState,
        action: DeleteToDoAction
    ): ToDoState = ToDoState(state.toDoInfoList - state.getToDoById(action.taskId))
}