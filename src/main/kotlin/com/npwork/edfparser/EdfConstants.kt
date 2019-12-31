package com.npwork.edfparser

import java.nio.charset.Charset

object EdfConstants {
    val CHARSET = Charset.forName("ASCII")

    const val IDENTIFICATION_CODE_SIZE = 8
    const val LOCAL_SUBJECT_IDENTIFICATION_SIZE = 80
    const val LOCAL_REOCRDING_IDENTIFICATION_SIZE = 80
    const val START_DATE_SIZE = 8
    const val START_TIME_SIZE = 8
    const val HEADER_SIZE = 8
    const val DATA_FORMAT_VERSION_SIZE = 44
    const val DURATION_DATA_RECORDS_SIZE = 8
    const val NUMBER_OF_DATA_RECORDS_SIZE = 8
    const val NUMBER_OF_CHANELS_SIZE = 4

    const val LABEL_OF_CHANNEL_SIZE = 16
    const val TRANSDUCER_TYPE_SIZE = 80
    const val PHYSICAL_DIMENSION_OF_CHANNEL_SIZE = 8
    const val PHYSICAL_MIN_IN_UNITS_SIZE = 8
    const val PHYSICAL_MAX_IN_UNITS_SIZE = 8
    const val DIGITAL_MIN_SIZE = 8
    const val DIGITAL_MAX_SIZE = 8
    const val PREFILTERING_SIZE = 80
    const val NUMBER_OF_SAMPLES_SIZE = 8
    const val RESERVED_SIZE = 32

    /** The size of the EDF-Header-Record containing information about the recording  */
    const val HEADER_SIZE_RECORDING_INFO = (IDENTIFICATION_CODE_SIZE + LOCAL_SUBJECT_IDENTIFICATION_SIZE + LOCAL_REOCRDING_IDENTIFICATION_SIZE
            + START_DATE_SIZE + START_TIME_SIZE + HEADER_SIZE + DATA_FORMAT_VERSION_SIZE + DURATION_DATA_RECORDS_SIZE
            + NUMBER_OF_DATA_RECORDS_SIZE + NUMBER_OF_CHANELS_SIZE)

    /** The size per channel of the EDF-Header-Record containing information a channel of the recording  */
    const val HEADER_SIZE_PER_CHANNEL = (LABEL_OF_CHANNEL_SIZE + TRANSDUCER_TYPE_SIZE + PHYSICAL_DIMENSION_OF_CHANNEL_SIZE
            + PHYSICAL_MIN_IN_UNITS_SIZE + PHYSICAL_MAX_IN_UNITS_SIZE + DIGITAL_MIN_SIZE + DIGITAL_MAX_SIZE
            + PREFILTERING_SIZE + NUMBER_OF_SAMPLES_SIZE + RESERVED_SIZE)
}
