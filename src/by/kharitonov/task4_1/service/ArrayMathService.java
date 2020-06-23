package by.kharitonov.task4_1.service;

import by.kharitonov.task4_1.entity.IntegerArray;
import org.jetbrains.annotations.NotNull;

public class ArrayMathService {
    public int minValue(@NotNull IntegerArray array) {
        return array.minValue();
    }

    public int maxValue(@NotNull IntegerArray array) {
        return array.maxValue();
    }

    public int[] simpleNumbers(@NotNull IntegerArray array) {
        int[] simpleNumbers = new int[simpleNumbersCount(array)];
        int count = 0;
        for (int i = 0; i < array.getLength(); i++) {
            int element = array.getElement(i);
            if (isSimple(element)) {
                simpleNumbers[count++] = element;
            }
        }
        return simpleNumbers;
    }

    private int simpleNumbersCount(IntegerArray array) {
        int count = 0;
        for (int i = 0; i < array.getLength(); i++) {
            if (isSimple(array.getElement(i))) {
                count++;
            }
        }
        return count;
    }

    private boolean isSimple(int number) {
        if (number < 1) {
            return false;
        }
        int count = 0;
        for (int i = 1; i <= number; i++) {
            if (number % i == 0) {
                count++;
                if (count > 2) {
                    return false;
                }
            }
        }
        return count == 2;
    }

    public int[] fibonacciNumbers(@NotNull IntegerArray array) {
        int[] fiboSeq = fibonacciSequence(array.absValue());
        int[] fiboNumbers = new int[fiboSeqSize(array)];
        int count = 0;
        for (int i = 0; i < array.getLength(); i++) {
            int temp = array.getElement(i);
            if (arrayHasNumber(fiboSeq, temp)) {
                fiboNumbers[count++] = temp;
            }
        }
        return fiboNumbers;
    }

    private int fiboSeqSize(IntegerArray array) {
        int[] fiboSeq = fibonacciSequence(array.absValue());
        int count = 0;
        for (int i = 0; i < array.getLength(); i++) {
            int temp = array.getElement(i);
            if (arrayHasNumber(fiboSeq, temp)) {
                count++;
            }
        }
        return count;
    }

    private boolean arrayHasNumber(int[] array, int number) {
        number = number >= 0 ? number : -number;
        for (int i1 : array) {
            if (i1 == number) {
                return true;
            }
        }
        return false;
    }

    private int[] fibonacciSequence(int number) {
        int positiveNumber = number >= 0 ? number : -number;
        int[] fiboSeq = new int[fiboSeqSize(positiveNumber)];
        int sum = 1;
        fiboSeq[0] = 0;
        fiboSeq[1] = 1;
        for (int i = 2; sum < Math.abs(number); i++) {
            sum = fiboSeq[i - 2] + fiboSeq[i - 1];
            fiboSeq[i] = sum;
        }
        return fiboSeq;
    }

    private int fiboSeqSize(int number) {
        int sum = 1;
        int count;
        int last = 1;
        int beforeLast = 0;
        for (count = 2; sum < number; count++) {
            sum = last + beforeLast;
            beforeLast = last;
            last = sum;
        }
        return count;
    }

    public int[] threeDifferentDigitNumbers(IntegerArray array) {
        int[] numbers = new int[threeDifferentDigitNumberCount(array)];
        int index = 0;
        for (int i = 0; i < array.getLength(); i++) {
            int value = array.getElement(i);
            if (isThreeDifferentDigitNumber(value)) {
                numbers[index++] = value;
            }
        }
        return numbers;
    }

    private int threeDifferentDigitNumberCount(IntegerArray array) {
        int count = 0;
        int length = array.getLength();
        for (int i = 0; i < length; i++) {
            int value = array.getElement(i);
            if (isThreeDifferentDigitNumber(value)) {
                count++;
            }
        }
        return count;
    }

    private boolean isThreeDifferentDigitNumber(int number) {
        if (number < 0) {
            number = -number;
        }
        return isThreeDigitNumber(number) && isDifferentDigitNumber(number);
    }

    private boolean isThreeDigitNumber(int number) {
        return (number / 100 >= 1 && number / 100 < 10);
    }

    private boolean isDifferentDigitNumber(int number) {
        char[] digits = Integer.toString(number).toCharArray();
        return (digits[0] != digits[1] &&
                digits[0] != digits[2] &&
                digits[1] != digits[2]);
    }
}
