class GSR {

    fun getAmpl(array: DoubleArray): Double {
        var max = 0.0
        var min = 1024.0
        for (i in array.indices) {
            if (array[i] < min) min = array[i]
            if (array[i] > max) max = array[i]
        }
        return max - min
    }

}