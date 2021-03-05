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
package com.example.androiddevchallenge.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.androiddevchallenge.ui.mainactivity.MainViewModel

@Composable
fun MyApp(viewModel: MainViewModel? = null) {
    val time = viewModel!!.time
    val animate = viewModel.animate
    val clockText = (if (animate) time else MainViewModel.MAX_SECONDS).toString()

    var animateColor by remember { mutableStateOf(false) }
    animateColor = time.rem(2) == 0

    Surface(color = MaterialTheme.colors.primary) {
        ClockScreen(animate, animateColor, clockText, viewModel)
    }
}

@Composable
private fun ClockScreen(
    animate: Boolean,
    animateColor: Boolean,
    clockText: String,
    viewModel: MainViewModel
) {
    Column {
        AnimatedCircles(animate, animateColor)
        Clock(Modifier.weight(1f), clockText, animateColor.not(), animate)
        AnimatedCircles(animate, animateColor)
        CardButton(animate) { viewModel.run { if (animate) stopTimer() else startTimer() } }
    }
}
