package com.oviraptor.oviraptor.user.ui.view.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iszero.dgsw_chatting.network.auth.RegisterRequest
import com.oviraptor.oviraptor.remote.Client
import com.oviraptor.oviraptor.remote.parseFailedResponse
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException


data class RegisterState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val authCode: String = "",
    val result: String = "",
)

sealed interface RegisterSideEffect {
    data object Success : RegisterSideEffect
    data object Failed : RegisterSideEffect
}

class RegisterViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(RegisterState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<RegisterSideEffect>()
    val uiEffect: SharedFlow<RegisterSideEffect> = _uiEffect.asSharedFlow()

    fun updateName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun updateAuthCode(authCode: String) {
        _uiState.update { it.copy(authCode = authCode) }
    }

    fun updateResult(result: String) {
        _uiState.update { it.copy(result = result) }
    }

    fun register( email: String,name: String, password: String, authCode: String) {
        viewModelScope.launch {
            try {
                val userService = Client.userService
                val request = RegisterRequest(email, name, password, authCode)
                userService.register(request)
                _uiEffect.emit(RegisterSideEffect.Success)
            } catch (e: HttpException) {
                _uiEffect.emit(RegisterSideEffect.Failed)
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = errorBody?.let { parseFailedResponse(it) }
                updateResult(errorResponse?.message ?: "알 수 없는 오류가 발생했습니다.")
            }
        }
    }
}