package com.oviraptor.oviraptor.user.ui.view.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iszero.dgsw_chatting.network.auth.LoginRequest
import com.oviraptor.oviraptor.remote.Client
import com.oviraptor.oviraptor.remote.parseFailedResponse
import com.oviraptor.oviraptor.user.userinfo.saveAccToken
import com.oviraptor.oviraptor.user.userinfo.saveRefToken
import com.oviraptor.oviraptor.user.userinfo.saveUserName
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException

data class LoginState(
    val email: String = "",
    val password: String = "",
    val access: String = "",
    val refresh: String = "",
    val result: String = "",
    val username: String = ""
)

sealed interface LoginSideEffect {
    data object Success : LoginSideEffect
    data object Failed : LoginSideEffect
}

class LoginViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(LoginState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<LoginSideEffect>()
    val uiEffect: SharedFlow<LoginSideEffect> = _uiEffect.asSharedFlow()

    fun updateEmail(email: String) {
        _uiState.update { it.copy(email = email) }
    }

    fun updatePassword(password: String) {
        _uiState.update { it.copy(password = password) }
    }

    fun updateResult(result: String) {
        _uiState.update { it.copy(result = result) }
    }
    fun updateToken(access: String, refresh: String) {
        _uiState.update { it.copy(access = access, refresh = refresh) }
    }
    fun updateUsername(username: String) {
        _uiState.update { it.copy(username = username) }
    }

    fun login( email: String, password: String) {
        viewModelScope.launch {
                try {
                    val authService = Client.userService
                    val request = LoginRequest(email, password)
                    val response = authService.login(request)
                    updateToken(response.data.accessToken, response.data.refreshToken)
                    updateUsername(response.data.username)
                    _uiEffect.emit(LoginSideEffect.Success)
                } catch (e: HttpException) {
                    _uiEffect.emit(LoginSideEffect.Failed)
                    val errorBody = e.response()?.errorBody()?.string()
                    val errorResponse = errorBody?.let { parseFailedResponse(it) }
                    updateResult(errorResponse?.message ?: "알 수 없는 오류가 발생했습니다.")
                }
            }
        }

    fun saveTokens(context: Context){
        saveAccToken(context,uiState.value.access)
        saveRefToken(context,uiState.value.refresh)
        saveUserName(context,uiState.value.username)
    }
}