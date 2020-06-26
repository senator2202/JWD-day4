package by.kharitonov.task4_2.type;

import by.kharitonov.task4_1.type.SortDirection;

public enum SortType {
    INCREASING_SUM(SortDirection.UP, PeakType.NONE),
    DECREASING_SUM(SortDirection.DOWN, PeakType.NONE),
    INCREASING_MAX(SortDirection.UP, PeakType.MAX),
    DECREASING_MAX(SortDirection.DOWN, PeakType.MAX),
    INCREASING_MIN(SortDirection.UP, PeakType.MIN),
    DECREASING_MIN(SortDirection.DOWN, PeakType.MIN);

    private final SortDirection sortDirection;
    private final PeakType peakType;

    SortType(SortDirection sortDirection, PeakType peakType) {
        this.sortDirection = sortDirection;
        this.peakType = peakType;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public PeakType getPeakType() {
        return peakType;
    }
}
