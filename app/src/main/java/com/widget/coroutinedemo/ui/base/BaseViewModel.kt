package com.widget.coroutinedemo.ui.base

import androidx.lifecycle.ViewModel
import com.widget.coroutinedemo.data.repository.BaseRepository

abstract class BaseViewModel(private val repository: BaseRepository) : ViewModel() {

//suspend fun logout(api: UserApi) = withContext(Dispatchers.IO) { repository.logout(api) }
}