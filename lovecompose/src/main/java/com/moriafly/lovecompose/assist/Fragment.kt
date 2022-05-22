@file:Suppress("unused", "UNUSED_PARAMETER")

/*
 * Copyright 2022 Moriafly
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moriafly.lovecompose.assist

import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

/**
 * 在 Compose 中添加 Fragment
 */
@Composable
fun Fragment(
    modifier: Modifier = Modifier,
    fragmentManager: FragmentManager,
    layoutId: Int,
    fragment: Fragment
) {
    AndroidView(
        factory = { content ->
            FrameLayout(content).apply {
                id = layoutId
                val fragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.add(id, fragment)
                fragmentTransaction.commit()
            }
        },
        modifier = modifier
    )
}