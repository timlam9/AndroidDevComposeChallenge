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

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CardButton(animate: Boolean, onButtonClicked: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            PlayButton(
                animate = animate,
                onButtonClicked = onButtonClicked
            )
        }
    }
}

@Composable
fun PlayButton(animate: Boolean, onButtonClicked: () -> Unit) {
    BoxWithConstraints(
        modifier = Modifier.animateContentSize(),
        contentAlignment = Alignment.Center
    ) {
        if (animate) {
            Button(
                modifier = Modifier.fillMaxSize(),
                onClick = onButtonClicked,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text("STOP")
            }
        } else {
            Column(
                modifier = Modifier.offset(x = 0.dp, y = (-20).dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = "AndroidDevChallenge #2",
                    modifier = Modifier.padding(20.dp),
                    style = MaterialTheme.typography.h5,
                    color = if (MaterialTheme.colors.isLight) Color.Gray else Color.White
                )
                Button(
                    modifier = Modifier,
                    shape = RoundedCornerShape(10.dp),
                    onClick = onButtonClicked,
                ) {
                    Text("START")
                }
            }
        }
    }
}
