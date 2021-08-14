package com.diegoribeiro.marvelguide.data.repository

import com.diegoribeiro.marvelguide.data.remote.RemoteClient

class Repository{

    suspend fun getAllCharacters() = RemoteClient.getService().getAllCharacters()
}