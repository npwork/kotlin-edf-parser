package com.npwork.edfparser.dto

data class EdfFile(
        val header: EdfHeader,
        val signal: EdfSignal
) {
    val samples: List<Int> = (0 until header.numberOfChannels).map { signal.digitalValues[it].size }.toList()
    val sampleRate: List<Double> = samples.map { it / (header.numberOfRecords * header.durationOfRecords) }.toList()
}
