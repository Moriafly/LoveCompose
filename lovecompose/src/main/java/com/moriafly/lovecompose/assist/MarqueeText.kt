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

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.annotation.Px
import androidx.appcompat.widget.AppCompatTextView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.viewinterop.AndroidView

/**
 * Marquee Text
 *
 * @param text 文本
 * @param modifier
 * @param color 文本颜色
 * @param fontSizePx 文本大小（单位：像素 px）
 * @param fontWeight 字重（需要 Android 9 及以上版本）
 * @param isHorizontalFadingEdgeEnabled 两边是否淡出
 * @param marqueeRepeatLimit 滚动次数
 */
@Composable
fun MarqueeText(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    @Px fontSizePx: Float,
    textAlign: TextAlign = TextAlign.Start,
    fontWeight: FontWeight = FontWeight.Normal,
    isHorizontalFadingEdgeEnabled: Boolean = true,
    marqueeRepeatLimit: Int = 1
) {
    AndroidView(
        factory = { context ->
            MarqueeTextView(context)
        },
        modifier = modifier,
        update = { view ->
            view.apply {
                textAlignment = when(textAlign) {
                    TextAlign.Start -> View.TEXT_ALIGNMENT_TEXT_START
                    TextAlign.Center-> View.TEXT_ALIGNMENT_CENTER
                    else -> View.TEXT_ALIGNMENT_TEXT_START
                }
                setTextSize(TypedValue.COMPLEX_UNIT_PX, fontSizePx)
                this.text = text
                setTextColor(color.toArgb())
                this.isHorizontalFadingEdgeEnabled = isHorizontalFadingEdgeEnabled
                this.marqueeRepeatLimit = marqueeRepeatLimit
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    this.typeface = Typeface.create(Typeface.DEFAULT, fontWeight.weight, false)
                }
            }
        }
    )
}

private class MarqueeTextView: AppCompatTextView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun isFocused(): Boolean {
        return true
    }

    init {
        this.ellipsize = TextUtils.TruncateAt.MARQUEE
        this.isSingleLine = true
        this.marqueeRepeatLimit = 1
        this.isFocusableInTouchMode = true
        this.isHorizontalFadingEdgeEnabled = true
    }
}