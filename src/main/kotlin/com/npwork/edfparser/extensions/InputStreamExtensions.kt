package com.npwork.edfparser.extensions

import com.npwork.edfparser.EdfConstants
import java.io.InputStream

fun InputStream.readNBytes(length: Int): ByteArray {
    val data = ByteArray(length)
    this.read(data)
    return data
}

fun InputStream.readASCII(length: Int): String = String(readNBytes(length), EdfConstants.CHARSET).trim()

fun InputStream.readASCIIBulk(length: Int, times: Int): List<String> = (1..times).map { this.readASCII(length) }
fun InputStream.readASCIIBulkDouble(length: Int, times: Int): List<Double> = (1..times).map { this.readASCII(length).trim().toDouble() }
fun InputStream.readASCIIBulkInt(length: Int, times: Int): List<Int> = (1..times).map { this.readASCII(length).trim().toInt() }
