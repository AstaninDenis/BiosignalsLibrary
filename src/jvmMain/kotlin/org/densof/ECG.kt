

class ECG() {

    fun getRPeaksCoordinate(array: DoubleArray, frequency: Double): DoubleArray {
        val timeArray = getTimeArray(frequency, array.size)
        val timePeaks = mutableListOf<Double>()
        val maxEcg = 0.8 * array.maxOrNull()!!
        for (i in 2..array.size - 3) {
            if (array[i] > array[i - 1] && array[i] > array[i - 2] &&
                array[i] > array[i + 1] && array[i] > array[i + 2] && array[i] > maxEcg
            ) {
                timePeaks.add(timeArray[i])
            }
        }
        return timePeaks.toDoubleArray()
    }

    fun getRRInterval(rCoordinate: DoubleArray): MutableList<Double> {
        val rrIntervals = mutableListOf<Double>()
        for (i in 1 until rCoordinate.size) {
            rrIntervals.add(rCoordinate[i] - rCoordinate[i - 1])
        }
        return rrIntervals
    }

    fun getPulse(array: DoubleArray, frequency: Double): Double {
        val rCoordinate = getRPeaksCoordinate(array, frequency)
        val rrIntervals = getRRInterval(rCoordinate)
        return 60.0 / (rrIntervals.sum() / rrIntervals.size)
    }

    fun getTimeArray(frequency: Double, size: Int): DoubleArray {
        val array = DoubleArray(size)
        var start = 0.0
        val step = 1.0 / frequency
        for (i in 0 until size) {
            array[i] = start
            start += step
        }
        return array
    }
}
