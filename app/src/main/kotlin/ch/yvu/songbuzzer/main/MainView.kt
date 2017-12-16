package ch.yvu.songbuzzer.main

import io.reactivex.Observable

interface MainView {
    fun songSearchChanges(): Observable<String>
    fun showSong(song: String)
    fun showErrorMessage(message: String)
}