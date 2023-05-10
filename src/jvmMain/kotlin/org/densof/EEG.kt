
import org.apache.commons.math3.complex.Complex
import org.apache.commons.math3.transform.DftNormalization
import org.apache.commons.math3.transform.FastFourierTransformer
import org.apache.commons.math3.transform.TransformType
import kotlin.math.sqrt


class EEG {

    fun fftTransform(arr: DoubleArray): DoubleArray{
        for (i in arr.indices) {
            arr[i] = arr[i] / 8
        }
        val fastFourierTransformer = FastFourierTransformer(DftNormalization.STANDARD)
        val complex: Array<Complex> = fastFourierTransformer.transform(arr, TransformType.FORWARD)
        val real = DoubleArray(complex.size)
        val imaginary = DoubleArray(complex.size)
        for (i in real.indices) {
            real[i] = complex[i].real
            imaginary[i] = complex[i].imaginary
        }
        val newArr = DoubleArray(24)
        for (i in newArr.indices) {
            newArr[i] = sqrt((real[i] * real[i]) + (imaginary[i] * imaginary[i]))
        }
        return newArr
    }
}