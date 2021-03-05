/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.mainactivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.timer.Timer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val timer: Timer) : ViewModel() {

    companion object {

        const val MAX_SECONDS = 10
    }

    var time: Int by mutableStateOf(MAX_SECONDS)
        private set

    var animate: Boolean by mutableStateOf(false)
        private set

    fun startTimer() {
        animate = true
        timer.startTimeCounter(MAX_SECONDS)
            .onEach {
                time = it
                if (time == 0) stopTimer()
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    fun stopTimer() {
        timer.stopTimeCounter()
        animate = false
    }
}
