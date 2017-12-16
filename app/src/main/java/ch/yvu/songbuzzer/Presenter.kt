package ch.yvu.songbuzzer

interface Presenter<ViewType> {
    var view: ViewType
}