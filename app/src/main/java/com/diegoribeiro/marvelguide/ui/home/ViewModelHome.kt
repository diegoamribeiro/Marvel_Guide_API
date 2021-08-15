package com.diegoribeiro.marvelguide.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.diegoribeiro.marvelguide.data.repository.Repository
import com.diegoribeiro.marvelguide.model.ResponseCharacter
import com.diegoribeiro.marvelguide.utils.ResourceProject
import kotlinx.coroutines.launch
import retrofit2.Response

class ViewModelHome: ViewModel() {
    private val repository = Repository
    val allCharacters: MutableLiveData<ResourceProject<ResponseCharacter>> = MutableLiveData()
    var characterResponse: ResponseCharacter? = null

    init {
        getAllCharacters()
    }

    fun getAllCharacters() = viewModelScope.launch {
        allCharacters.postValue(ResourceProject.Loading())
        val response = repository.getAllCharacters()
        allCharacters.postValue(handleCharacterResponse(response))
    }

    private fun handleCharacterResponse(response: Response<ResponseCharacter>): ResourceProject<ResponseCharacter>{
        if (response.isSuccessful){
            response.body()?.let {resultResponse->
            if (characterResponse == null){
                characterResponse = resultResponse
            }else{
                val oldState = characterResponse?.data?.result
                val newState = resultResponse.data.result
                oldState?.addAll(newState)
            }
                return ResourceProject.Success(characterResponse ?: resultResponse)

            }
        }
        return ResourceProject.Error(response.message())
    }


}