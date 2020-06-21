package by.kharitonov.day4.task1.entity;

import java.util.Arrays;
import java.util.Optional;

public class IntegerArray {
    private int[] dataArray;

    public IntegerArray(int size) {
        dataArray = new int[size];
    }

    public IntegerArray(int[] dataArray) {
        this.dataArray = new int[dataArray.length];
        copy(dataArray);
    }

    private void copy(int[] dataArray) {
        for (int i = 0; i < dataArray.length; i++) {
            this.dataArray[i] = dataArray[i];
        }
    }

    public int getLength() {
        return dataArray.length;
    }

    public boolean setElement(int index, int value) {
        if (!inRange(index)) {
            return false;
        }
        dataArray[index] = value;
        return true;
    }

    private boolean inRange(int index) {
        return index < dataArray.length && index >= 0;
    }

    public Optional<Integer> getElement(int index) {
        if (!inRange(index)) {
            return Optional.ofNullable(null);
        }
        return Optional.of(dataArray[index]);
    }

    public int getFirst() {
        return dataArray[0];
    }

    public int getLast() {
        return dataArray[dataArray.length - 1];
    }

    public int maxValue() {
        int max = 0;
        for (int i = 1; i < dataArray.length; i++) {
            if (dataArray[i] > dataArray[max]) {
                max = i;
            }
        }
        return dataArray[max];
    }

    public int minValue() {
        int min = 0;
        for (int i = 1; i < dataArray.length; i++) {
            if (dataArray[i] < dataArray[min]) {
                min = i;
            }
        }
        return dataArray[min];
    }

    public IntegerArray copy() {
        return new IntegerArray(dataArray);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        IntegerArray array1 = (IntegerArray) o;
        if (dataArray.length != array1.getLength()) {
            return false;
        }
        return Arrays.equals(dataArray, array1.dataArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(dataArray);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IntegerArray{");
        for (int i = 0; i < dataArray.length - 1; i++) {
            sb.append(dataArray[i]).append(",");
        }
        sb.append(dataArray[dataArray.length - 1]).append('}');
        return sb.toString();
    }
}