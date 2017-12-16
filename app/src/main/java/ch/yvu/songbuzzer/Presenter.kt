package ch.yvu.songbuzzer

interface Presenter<ViewType> {
    val view: ViewType
}