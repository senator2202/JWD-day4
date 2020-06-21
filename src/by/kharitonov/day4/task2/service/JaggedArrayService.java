package by.kharitonov.day4.task2.service;

import by.kharitonov.day4.task1.entity.SortDirection;
import by.kharitonov.day4.task2.entity.PeakType;
import by.kharitonov.day4.task2.validator.JaggedArrayValidator;

public class JaggedArrayService {
    public boolean sortRowsBySumItems(int[][] jArray, SortDirection direction) {
        if (!new JaggedArrayValidator().validateJaggedArray(jArray)) {
            return false;
        }
        boolean sortFlag = (direction == SortDirection.UP);
        boolean cycleFlag;
        do {
            cycleFlag = false;
            for (int i = 0; i < jArray.length - 1; i++) {
                if (!(sumRowItems(jArray[i + 1]) <
                        sumRowItems(jArray[i]) ^ sortFlag)) {
                    swapRows(jArray, i, i + 1);
                    cycleFlag = true;
                }
            }
        } while (cycleFlag);

        return true;
    }

    private void swapRows(int[][] jArray, int row1, int row2) {
        int[] temp = jArray[row1];
        jArray[row1] = jArray[row2];
        jArray[row2] = temp;
    }

    private int sumRowItems(int[] row) {
        int sum = 0;
        for (int i = 0; i < row.length; i++) {
            sum += row[i];
        }
        return sum;
    }

    public boolean sortRowsByPeakElement(int[][] jArray, PeakType peakType,
                                         SortDirection direction) {
        if (!new JaggedArrayValidator().validateJaggedArray(jArray)) {
            return false;
        }
        boolean sortFlag = (direction == SortDirection.UP);
        boolean cycleFlag;
        do {
            cycleFlag = false;
            for (int i = 0; i < jArray.length - 1; i++) {
                int peakPlus1 = peakRowElement(jArray[i + 1], peakType);
                int peak = peakRowElement(jArray[i], peakType);
                if (peakPlus1!=peak && !(peakPlus1 < peak ^ sortFlag)) {
                    swapRows(jArray, i, i + 1);
                    cycleFlag = true;
                }
            }
        } while (cycleFlag);
        return true;
    }

    private int peakRowElement(int[] row, PeakType peakType) {
        boolean peakFlag = (peakType == PeakType.MIN);
        int peak = 0;
        for (int i = 1; i < row.length; i++) {
            if (!(row[i] < row[peak] ^ peakFlag)) {
                peak = i;
            }
        }
        return row[peak];
    }
}
