package by.kharitonov.day4.task1.entity;

import java.util.Arrays;
import java.util.Optional;

public class Array {
    private int[] array;

    public Array(int size) {
        array = new int[size];
    }

    public Array(int[] array) {
        this.array = new int[array.length];
        copy(array);
    }

    private void copy(int[] array) {
        for (int i = 0; i < array.length; i++) {
            this.array[i] = array[i];
        }
    }

    public int getLength() {
        return array.length;
    }

    public boolean setElement(int index, int value) {
        if (!inRange(index)) {
            return false;
        }
        array[index] = value;
        return true;
    }

    private boolean inRange(int index) {
        return index < array.length && index >= 0;
    }

    public Optional<Integer> getElement(int index) {
        if (!inRange(index)) {
            return Optional.ofNullable(null);
        }
        return Optional.of(array[index]);
    }

    public Array copy() {
        return new Array(array);
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
        if (array.length != array1.getLength()) {
            return false;
        }
        return Arrays.equals(array, array1.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Array{");
        for (int i = 0; i < array.length - 1; i++) {
            sb.append(array[i]).append(",");
        }
        sb.append(array[array.length - 1]).append('}');
        return sb.toString();
    }
}
