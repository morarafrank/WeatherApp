package com.morarafrank.weatherapp.ui.state

sealed class UiEvent {
    data class ShowSnackbar(val message: String) : UiEvent()
}