package com.example.htnotpadtestapp.common

sealed class Resource<T>(val data: T? = null, val noteError: NoteError? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(error: NoteError) : Resource<T>(noteError = error)
}


enum class NoteError(val message: String) {
    EMPTY_TITLE("The title is empty"),
    EMPTY_CONTENT("The content is empty"),
    GENERAL("Operation failed, try again later"),
    NOTE_NOT_FOUND("Note not found, try again later")
}