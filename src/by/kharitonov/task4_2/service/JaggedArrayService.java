package by.kharitonov.task4_2.service;

import by.kharitonov.task4_1.entity.SortDirection;
import by.kharitonov.task4_2.entity.PeakType;
import by.kharitonov.task4_2.entity.SortType;
import by.kharitonov.task4_2.validator.JaggedArrayValidator;

public class JaggedArrayService {
    public boolean generalSorting(int[][] jArray, SortType sortType) {
        if (!new JaggedArrayValidator().
                validateSortingParameters(jArray, sortType)) {
            return false;
        }
        boolean cycleFlag;
        do {
            cycleFlag = false;
            for (int i = 0; i < jArray.length - 1; i++) {
                if (needToSwap(jArray[i + 1], jArray[i], sortType)) {
                    swapRows(jArray, i + 1, i);
                    cycleFlag = true;
                }
            }
        } while (cycleFlag);
        return true;
    }

    private boolean needToSwap(int[] row2, int[] row1, SortType sortType) {
        boolean sortFlag = (sortType.getSortDirection() == SortDirection.UP);
        if (sortType.getPeakType() == PeakType.NONE) {
            int sum2 = sumRowItems(row2);
            int sum1 = sumRowItems(row1);
            return sum2 < sum1 == sortFlag;
        } else {
            int peak2 = peakRowElement(row2, sortType.getPeakType());
            int peak1 = peakRowElement(row1, sortType.getPeakType());
            return (peak2 != peak1) && (peak2 < peak1 == sortFlag);
        }
    }

    private void swapRows(int[][] jArray, int row1, int row2) {
        int[] temp = jArray[row1];
        jArray[row1] = jArray[row2];
        jArray[row2] = temp;
    }

    private int sumRowItems(int[] row) {
        int sum = 0;
        for (int i : row) {
            sum += i;
        }
        return sum;
    }

    private int peakRowElement(int[] row, PeakType peakType) {
        boolean peakFlag = (peakType == PeakType.MIN);
        int peak = 0;
        for (int i = 1; i < row.length; i++) {
            if (row[i] < row[peak] == peakFlag) {
                peak = i;
            }
        }
        return row[peak];
    }
}
