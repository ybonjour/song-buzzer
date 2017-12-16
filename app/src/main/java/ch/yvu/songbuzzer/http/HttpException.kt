package ch.yvu.songbuzzer.http

import okhttp3.Response

class HttpException(val response: Response) : Exception() {
}