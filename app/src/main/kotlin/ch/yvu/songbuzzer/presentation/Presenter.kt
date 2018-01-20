package ch.yvu.songbuzzer.presentation

interface Presenter<ViewType> {
    var view: ViewType
}