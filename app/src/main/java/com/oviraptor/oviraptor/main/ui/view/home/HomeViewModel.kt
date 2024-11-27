package com.oviraptor.oviraptor.main.ui.view.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iszero.dgsw_chatting.network.auth.LoginRequest
import com.oviraptor.oviraptor.main.network.data.Room
import com.oviraptor.oviraptor.remote.Client
import com.oviraptor.oviraptor.remote.parseFailedResponse
import com.oviraptor.oviraptor.remote.refresh
import com.oviraptor.oviraptor.user.ui.view.login.LoginSideEffect
import com.oviraptor.oviraptor.user.userinfo.getAccToken
import com.oviraptor.oviraptor.user.userinfo.getRefToken
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

data class HomeState(
    val rooms : List<Room> = emptyList(),
    val result : String = ""
)

sealed interface HomeSideEffect {
    data object Failed : HomeSideEffect
    data object Expiration : HomeSideEffect
}

class HomeViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(HomeState())
    val uiState = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<HomeSideEffect>()
    val uiEffect: SharedFlow<HomeSideEffect> = _uiEffect.asSharedFlow()

    fun updateRooms(rooms: List<Room>) {
        _uiState.update { it.copy(rooms = rooms) }
    }

    fun updateResult(result: String) {
        _uiState.update { it.copy(result = result) }
    }

    fun getRooms(context: Context) {
        viewModelScope.launch {
            try {
                val chatService = Client.chatService
                val accToken = getAccToken(context)
                if (accToken != null) {
                    val response = chatService.getRooms(accToken)
                    updateRooms(response.data)
                }
                else {
                    updateResult("다시 로그인 해주세요")
                }
            } catch (e: HttpException) {
                _uiEffect.emit(HomeSideEffect.Failed)
                val errorBody = e.response()?.errorBody()?.string()
                when (e.code()) {
                    401 -> {
                        val response = refresh(context)
                        if (response != null){
                            getRooms(context)
                        }
                        else {
                            _uiEffect.emit(HomeSideEffect.Expiration)
                        }
                    }
                    else -> {
                        val errorResponse = errorBody?.let { parseFailedResponse(it) }
                        updateResult(errorResponse?.message ?: "알 수 없는 오류가 발생했습니다.")
                    }
                }
            }
        }
    }
}