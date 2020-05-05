package com.jjs.algorithm.gui.template.motefaluo;

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
    private MonteKale monteKale;
    private Integer N;

    public AlgoVisualizer(int width, int height, int n) {
        Circle circle = new Circle(width/2,height/2, width/2);
        monteKale = new MonteKale(circle);
        N = n;

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
        for (Integer i = 0; i < N; i++) {
            if (i % 100 == 0) {
                algoGui.render(monteKale);
                AlgoHelper.packTime(4000);
                System.out.println(monteKale.calPi());
            }

            int x = (int) (Math.random()*algoGui.getGuiWidth());
            int y = (int) (Math.random()*algoGui.getGuiHeight());
            algoGui.lock.lock();
            monteKale.putPoint(new Point(x,y));
            algoGui.lock.unlock();
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
        new AlgoVisualizer(width,height,60000);
    }

}
