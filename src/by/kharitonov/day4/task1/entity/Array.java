package by.kharitonov.day4.task1.entity;

import java.util.Arrays;
import java.util.Optional;

public class Array {
    private int[] dataArray;

    public Array(int size) {
        dataArray = new int[size];
    }

    public Array(int[] dataArray) {
        this.dataArray = new int[dataArray.length];
        this.dataArray = Arrays.copyOf(dataArray, dataArray.length);
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

    public Array copy() {
        return new Array(dataArray);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Array array1 = (Array) o;
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
        final StringBuilder sb = new StringBuilder("Array{");
        for (int i = 0; i < dataArray.length - 1; i++) {
            sb.append(dataArray[i]).append(",");
        }
        sb.append(dataArray[dataArray.length - 1]).append('}');
        return sb.toString();
    }
}
