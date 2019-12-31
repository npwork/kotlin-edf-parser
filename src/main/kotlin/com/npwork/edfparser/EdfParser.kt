package com.npwork.edfparser

import com.npwork.edfparser.dto.EdfFile
import com.npwork.edfparser.dto.EdfHeader
import com.npwork.edfparser.dto.EdfSignal
import com.npwork.edfparser.extensions.*
import java.io.File
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.Channels
import java.nio.channels.ReadableByteChannel

object EdfParser {
    fun parse(file: File): EdfFile = parse(file.inputStream())

    fun parse(stream: InputStream): EdfFile {
        if (stream.available() == 0)
            throw EdfFormatException.EmptyFile()

        val header = try {
            parseHeader(stream)
        } catch (e: Exception) {
            throw EdfFormatException.WrongHeader()
        }

        val signal = try {
            parseSignal(stream, header)
        } catch (e: Exception) {
            throw EdfFormatException.WrongSignal()
        }

        return EdfFile(header = header, signal = signal)
    }

    private fun parseHeader(stream: InputStream): EdfHeader {
        var numberOfChannels = 0
        return EdfHeader(
                idCode = fun(): String {
                    val idCode = stream.readASCII(EdfConstants.IDENTIFICATION_CODE_SIZE)
                    ensureValidIdentificationCode(idCode)
                    return idCode
                }(),
                subjectID = stream.readASCII(EdfConstants.LOCAL_SUBJECT_IDENTIFICATION_SIZE),
                recordingID = stream.readASCII(EdfConstants.LOCAL_REOCRDING_IDENTIFICATION_SIZE),
                startDate = stream.readASCII(EdfConstants.START_DATE_SIZE),
                startTime = stream.readASCII(EdfConstants.START_TIME_SIZE),
                bytesInHeader = stream.readASCII(EdfConstants.HEADER_SIZE).trim().toInt(),
                formatVersion = stream.readASCII(EdfConstants.DATA_FORMAT_VERSION_SIZE),
                numberOfRecords = stream.readASCII(EdfConstants.NUMBER_OF_DATA_RECORDS_SIZE).trim().toInt(),
                durationOfRecords = stream.readASCII(EdfConstants.DURATION_DATA_RECORDS_SIZE).trim().toDouble(),
                numberOfChannels = fun(): Int {
                    numberOfChannels = stream.readASCII(EdfConstants.NUMBER_OF_CHANELS_SIZE).trim().toInt()
                    return numberOfChannels
                }(),
                channelLabels = stream.readASCIIBulk(EdfConstants.LABEL_OF_CHANNEL_SIZE, numberOfChannels),
                transducerTypes = stream.readASCIIBulk(EdfConstants.TRANSDUCER_TYPE_SIZE, numberOfChannels),
                dimensions = stream.readASCIIBulk(EdfConstants.PHYSICAL_DIMENSION_OF_CHANNEL_SIZE, numberOfChannels),
                minInUnits = stream.readASCIIBulkDouble(EdfConstants.PHYSICAL_MIN_IN_UNITS_SIZE, numberOfChannels),
                maxInUnits = stream.readASCIIBulkDouble(EdfConstants.PHYSICAL_MAX_IN_UNITS_SIZE, numberOfChannels),
                digitalMin = stream.readASCIIBulkInt(EdfConstants.DIGITAL_MIN_SIZE, numberOfChannels),
                digitalMax = stream.readASCIIBulkInt(EdfConstants.DIGITAL_MAX_SIZE, numberOfChannels),
                prefilterings = stream.readASCIIBulk(EdfConstants.PREFILTERING_SIZE, numberOfChannels),
                numberOfSamples = stream.readASCIIBulkInt(EdfConstants.NUMBER_OF_SAMPLES_SIZE, numberOfChannels),
                reserveds = (1..numberOfChannels).map { stream.readNBytes(EdfConstants.RESERVED_SIZE) }
        )
    }

    private fun ensureValidIdentificationCode(idCode: String) {
        if (idCode.trim() != "0") {
            throw EdfFormatException.WrongFormat()
        }
    }

    private fun parseSignal(stream: InputStream, header: EdfHeader): EdfSignal {
        val signal = EdfSignal(
                unitsInDigit = (0 until header.numberOfChannels)
                        .map {
                            (header.maxInUnits[it] - header.minInUnits[it]) /
                                    (header.digitalMax[it] - header.digitalMin[it])
                        }
                        .toTypedArray(),

                digitalValues = (0 until header.numberOfChannels)
                        .map { ShortArray(header.numberOfRecords * header.numberOfSamples[it]) }
                        .toTypedArray(),

                valuesInUnits = (0 until header.numberOfChannels)
                        .map { DoubleArray(header.numberOfRecords * header.numberOfSamples[it]) }
                        .toTypedArray()
        )

        val samplesPerRecord = header.numberOfSamples.sum()

        val ch: ReadableByteChannel = Channels.newChannel(stream)
        val bytebuf = ByteBuffer.allocate(samplesPerRecord * 2)
        bytebuf.order(ByteOrder.LITTLE_ENDIAN)

        for (i in 0 until header.numberOfRecords) {
            bytebuf.rewind()
            ch.read(bytebuf)
            bytebuf.rewind()

            for (j in 0 until header.numberOfChannels) {
                for (k in 0 until header.numberOfSamples[j]) {
                    val s: Int = header.numberOfSamples[j] * i + k
                    signal.digitalValues[j][s] = bytebuf.short
                    signal.valuesInUnits[j][s] = signal.digitalValues[j][s] * signal.unitsInDigit[j]
                }
            }
        }

        return signal
    }
}