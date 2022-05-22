package com.moriafly.lovecompose.assist

import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

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