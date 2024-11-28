package com.oviraptor.oviraptor.main.ui.view.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oviraptor.oviraptor.main.network.data.Friend
import com.oviraptor.oviraptor.main.network.data.Room
import com.oviraptor.oviraptor.remote.Client
import com.oviraptor.oviraptor.remote.parseFailedResponse
import com.oviraptor.oviraptor.remote.refresh
import com.oviraptor.oviraptor.user.userinfo.getAccToken
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
    val result : String = "",
    val isAddRoom : Boolean = false,
    val addRoomName : String = "",
    val isAddFriend : Boolean = false,
    val friendEmail : String = "",
    val friends : List<Friend> = emptyList()
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

    fun updateIsAddRoom(isAddRoom: Boolean) {
        _uiState.update { it.copy(isAddRoom = isAddRoom) }
    }
    fun updateAddRoomName(addRoomName: String) {
        _uiState.update { it.copy(addRoomName = addRoomName) }
    }
    fun updateIsAddFriend(isAddFriend: Boolean) {
        _uiState.update { it.copy(isAddFriend = isAddFriend) }
    }
    fun updateFriends(friends: List<Friend>) {
        _uiState.update { it.copy(friends = friends) }
    }
    fun updateFriendEmail(friendEmail: String) {
        _uiState.update { it.copy(friendEmail = friendEmail) }
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
                    _uiEffect.emit(HomeSideEffect.Expiration)
                }
            } catch (e: HttpException) {
                _uiEffect.emit(HomeSideEffect.Failed)
                val errorBody = e.response()?.errorBody()?.string()
                when (e.code()) {
                    403 -> {
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

    fun addRoom(context: Context, name : String) {
        viewModelScope.launch {
            try {
                val chatService = Client.chatService
                val accToken = getAccToken(context)
                if (accToken != null) {
                    chatService.addRooms(accToken,name)
                    getRooms(context)
                }
                else {
                    _uiEffect.emit(HomeSideEffect.Expiration)
                }
            }
            catch (e: HttpException) {
                _uiEffect.emit(HomeSideEffect.Failed)
                val errorBody = e.response()?.errorBody()?.string()
                when (e.code()) {
                    403 -> {
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
    fun getFriends(context: Context) {
        viewModelScope.launch {
            try {
                val friendService = Client.friendService
                val accToken = getAccToken(context)
                if (accToken != null) {
                    val response = friendService.getFriends(accToken)
                    updateFriends(response.data)
                }
                else {
                    _uiEffect.emit(HomeSideEffect.Expiration)
                }
            }
            catch (e: HttpException) {
                _uiEffect.emit(HomeSideEffect.Failed)
                val errorBody = e.response()?.errorBody()?.string()
                when (e.code()) {
                    403 -> {
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
    fun addFriend(context: Context, email: String) {
        viewModelScope.launch {
            try {
                val friendService = Client.friendService
                val accToken = getAccToken(context)
                if (accToken != null) {
                    friendService.addFriend(accToken,email)
                    getFriends(context)
                }
                else {
                    _uiEffect.emit(HomeSideEffect.Expiration)
                }
            }
            catch (e: HttpException) {
                _uiEffect.emit(HomeSideEffect.Failed)
                val errorBody = e.response()?.errorBody()?.string()
                when (e.code()) {
                    403 -> {
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
    fun deleteFriend(context: Context, email: String) {
        viewModelScope.launch {
            try {
                val friendService = Client.friendService
                val accToken = getAccToken(context)
                if (accToken != null) {
                    friendService.deleteFriend(accToken,email)
                    getFriends(context)
                }
                else {
                    _uiEffect.emit(HomeSideEffect.Expiration)
                }
            }
            catch (e: HttpException) {
                _uiEffect.emit(HomeSideEffect.Failed)
                val errorBody = e.response()?.errorBody()?.string()
                when (e.code()) {
                    403 -> {
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