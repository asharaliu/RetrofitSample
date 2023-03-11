package com.example.retrofitsample.network

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
inline fun <T> RequestStateRender(
    state: State<RequestState<T>>,
    onError: @Composable ((Throwable) -> Unit) = {},
    onLoading: @Composable (() -> Unit) = {},
    onSuccess: @Composable (T) -> Unit,
) {
    when (val itemValue = state.value) {
        is RequestState.Success -> {
            onSuccess.invoke(
                itemValue.data
            )
        }
        is RequestState.Idle -> {
        }
        is RequestState.Error -> onError.invoke(
            itemValue.error
        )
        RequestState.Loading -> onLoading.invoke()
    }
}