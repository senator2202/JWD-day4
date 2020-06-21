package by.kharitonov.day4.task2.entity;

public enum PeakType {
    MAX(1), MIN(-1);

    private int peakValue;

    PeakType(int peakValue) {
        this.peakValue = peakValue;
    }

    int getPeakValue() {
        return peakValue;
    }
}
