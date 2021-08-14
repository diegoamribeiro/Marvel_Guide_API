package com.diegoribeiro.marvelguide.data.repository

import com.diegoribeiro.marvelguide.data.remote.RemoteClient

object Repository{

    suspend fun getAllCharacters() = RemoteClient.getService().getAllCharacters()
}