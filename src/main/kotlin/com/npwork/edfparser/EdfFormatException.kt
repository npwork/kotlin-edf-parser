package com.npwork.edfparser

sealed class EdfFormatException(message: String? = null) : RuntimeException(message) {
    class EmptyFile(message: String? = "File is empty") : EdfFormatException(message)
    class WrongFormat(message: String? = "Wrong format of EDF file. Please check https://www.teuniz.net/edfbrowser/edf%20format%20description.html") : EdfFormatException(message)
    class WrongHeader(message: String? = "Error during header parsing") : EdfFormatException(message)
    class WrongSignal(message: String? = "Error during signal parsing") : EdfFormatException(message)
}