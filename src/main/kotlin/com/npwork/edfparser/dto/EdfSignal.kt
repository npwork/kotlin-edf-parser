package com.npwork.edfparser.dto

data class EdfSignal(
        var unitsInDigit: Array<Double>,
        var digitalValues: Array<ShortArray>,
        var valuesInUnits: Array<DoubleArray>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as EdfSignal

        if (!unitsInDigit.contentEquals(other.unitsInDigit)) return false
        if (!digitalValues.contentDeepEquals(other.digitalValues)) return false
        if (!valuesInUnits.contentDeepEquals(other.valuesInUnits)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = unitsInDigit.contentHashCode()
        result = 31 * result + digitalValues.contentDeepHashCode()
        result = 31 * result + valuesInUnits.contentDeepHashCode()
        return result
    }
}