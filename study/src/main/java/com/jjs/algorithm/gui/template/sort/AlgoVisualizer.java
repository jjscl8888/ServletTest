package com.jjs.algorithm.gui.template.sort;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author jjs
 * @Version 1.0 2020/5/5
 */
public class AlgoVisualizer {
    private AlgoGui algoGui;
    // 自定义数据
    private SelectionSortData sortData;

    public AlgoVisualizer(int width, int height, int n) {

        sortData = new SelectionSortData(n,height);

        EventQueue.invokeLater(() -> {
            algoGui = new AlgoGui("welcome", width, height);
            algoGui.addKeyListener(new AlgoKeyListener());
            algoGui.addMouseListener(new AlgoMouseListener());

            new Thread(() -> {
                run();
            }).start();
        });
    }


    private void run() {

        int[] data = this.sortData.getData();
        if (data == null || data.length == 0) {
            return;
        }
        for (int i = 0; i < data.length; i++) {
            int minIndex = i;
            sortData.setMinIndex(minIndex);
            sortData.setCurrentIndex(-1);
            algoGui.render(sortData);
            AlgoHelper.packTime(10000);
            for (int j = i + 1; j < data.length; j++) {
                if (data[minIndex] > data [j]) {
                    minIndex = j;
                }
                sortData.setMinIndex(minIndex);
                sortData.setCurrentIndex(j);
                algoGui.render(sortData);
                AlgoHelper.packTime(10000);
            }
            if (minIndex != i) {
                int temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
            sortData.setCurrentIndex(-1);
            sortData.setMinIndex(-1);
            sortData.setSortIndex(i);
            algoGui.render(sortData);
            AlgoHelper.packTime(10000);
        }

    }


    private class AlgoKeyListener extends KeyAdapter {

    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {

        }
    }

    public static void main(String[] args) {
        Integer width = 1000;
        Integer height = 1000;
        new AlgoVisualizer(width,height, 100);
    }

}
