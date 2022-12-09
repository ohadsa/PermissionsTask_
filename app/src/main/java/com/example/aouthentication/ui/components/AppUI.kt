package com.example.aouthentication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AppUI(
) {
    val state = rememberPagerState(pageCount = 2)
    val scope = rememberCoroutineScope()
    val clickEvent: (index: Int) -> Unit = {
        scope.launch {
            state.animateScrollToPage(it)
        }
    }

    Column {
        HorizontalPager(
            state = state,
            dragEnabled = false,
            modifier = Modifier.fillMaxSize(),
        ) { page ->
            when (page) {
                0 -> FirstPage{ clickEvent(1) }
                1 -> SecondPage { clickEvent(0) }
            }
        }
    }
}
