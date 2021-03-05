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

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.amber600

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedCircles(animate: Boolean, animateColor: Boolean) {
    val color by animateColorAsState(if (animateColor) amber600 else MaterialTheme.colors.secondary)
    val transition = rememberInfiniteTransition()

    val transitionStart = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        )
    )

    val transitionEnd = transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500),
            repeatMode = RepeatMode.Reverse
        )
    )

    AnimatedCircles(transitionStart, transitionEnd, animate, color)
}

@Composable
private fun AnimatedCircles(
    transitionStart: State<Float>,
    transitionEnd: State<Float>,
    animate: Boolean,
    color: Color
) {
    BoxWithConstraints(Modifier.padding(20.dp)) {
        val circleSize = 20
        val animatedOffsetStart =
            getAnimationOffset(transitionStart.value, maxWidth.value, circleSize)
        val animatedOffsetEnd = getAnimationOffset(transitionEnd.value, maxWidth.value, circleSize)

        if (animate) {
            BoxWithConstraints {
                Circle(color, animatedOffsetStart)
                Circle(color, animatedOffsetEnd)
            }
        }
    }
}

fun getAnimationOffset(animatedValue: Float, maxWidth: Float, circleWidth: Int): Dp {
    val animatedScreenWidth = animatedValue * maxWidth
    val offset = animatedScreenWidth - circleWidth
    val posOffset = if (offset < 0) animatedScreenWidth else offset
    return posOffset.dp
}

@Composable
fun Circle(color: Color, xOffset: Dp = 0.dp, size: Dp = 20.dp) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(align = Alignment.CenterStart)
            .offset(x = xOffset, y = 0.dp)
            .clip(CircleShape)
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .background(color)
        ) {
        }
    }
}
