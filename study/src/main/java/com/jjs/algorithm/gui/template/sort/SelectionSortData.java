package com.jjs.algorithm.gui.template.sort;

/**
 * @author jjs
 * @Version 1.0 2020/5/5
 */
public class SelectionSortData {
    int[] data;
    int sortIndex = -1;
    int currentIndex = -1;
    int minIndex = -1;

    public SelectionSortData(int n, int maxValue) {
        this.data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = (int)(Math.random()*maxValue);
        }
    }

    public int[] getData() {
        return data;
    }

    public int getSortIndex() {
        return sortIndex;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public int getMinIndex() {
        return minIndex;
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public void setSortIndex(int sortIndex) {
        this.sortIndex = sortIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public void setMinIndex(int minIndex) {
        this.minIndex = minIndex;
    }
}
