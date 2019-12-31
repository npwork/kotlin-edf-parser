package com.npwork.edfparser.dto

data class EdfHeader(
        val idCode: String,
        val subjectID: String,
        val recordingID: String,
        val startDate: String,
        val startTime: String,
        val bytesInHeader: Int,
        val formatVersion: String,
        val numberOfRecords: Int,
        val durationOfRecords: Double,
        val numberOfChannels: Int,

        // Channel info
        val channelLabels: List<String>,
        val transducerTypes: List<String>,
        val dimensions: List<String>,
        val minInUnits: List<Double>,
        val maxInUnits: List<Double>,
        val digitalMin: List<Int>,
        val digitalMax: List<Int>,
        val prefilterings: List<String>,
        val numberOfSamples: List<Int>,
        val reserveds: List<ByteArray>
)
