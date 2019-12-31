package com.npwork.edfparser

import com.npwork.edfparser.dto.EdfFile
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class EdfParserTest {
    @Test
    @DisplayName("EEG 38 channels")
    fun eegFile_38_channels() {
        val edfFile = EdfParser.parse(getResource("teuniz_net_test_file_eeg_38_ch.edf"))

        assertEquals(9984, edfFile.header.bytesInHeader)
        verifyNumberOfChannels(38, edfFile)
    }


    @Test
    @DisplayName("Short ECG")
    fun short_ecg() {
        val edfFile = EdfParser.parse(getResource("short_ecg.edf"))
        assertEquals(512, edfFile.header.bytesInHeader)
        assertEquals(listOf(7684), edfFile.samples)
        assertEquals(listOf(256.0000426444631), edfFile.sampleRate)
        verifyNumberOfChannels(1, edfFile)
    }

    @DisplayName("Wrong cases")
    @Nested
    inner class WrongCases {
        @Test
        @DisplayName("Empty file")
        fun emptyFile() {
            assertThrows(EdfFormatException.EmptyFile::class.java) {
                EdfParser.parse(getResource("empty_file.edf"))
            }
        }

        @Test
        @DisplayName("Header is not complete")
        fun partialHeader() {
            assertThrows(EdfFormatException.WrongHeader::class.java) {
                EdfParser.parse(getResource("partial_header.edf"))
            }
        }

        @Test
        @DisplayName("From file format")
        fun partialSignal() {
            assertThrows(EdfFormatException.WrongHeader::class.java) {
                EdfParser.parse(getResource("wrong_file.edf"))
            }
        }
    }

    private fun verifyNumberOfChannels(expectedNumberOfChannels: Int, edfFile: EdfFile) {
        assertEquals(expectedNumberOfChannels, edfFile.header.channelLabels.size)
        assertEquals(expectedNumberOfChannels, edfFile.header.numberOfSamples.size)
    }

    private fun getResource(fileName: String) = EdfParserTest::class.java.getResourceAsStream("/$fileName")
}
