package ch.yvu.songbuzzer.songs

interface Songs {
    fun withName(name: String): List<Song>
}