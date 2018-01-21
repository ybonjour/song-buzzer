package ch.yvu.songbuzzer.http

import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun isGetRequest(baseUrl: String, path: String, queryParameters: Map<String, String>) = object : TypeSafeMatcher<GetRequest>() {
    override fun matchesSafely(item: GetRequest) =
            item == GetRequest(baseUrl, path, queryParameters)

    override fun describeTo(description: Description) {
        description.appendValue(GetRequest(baseUrl, path, queryParameters))
    }
}
