package com.jjs.algorithm.gui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/**
 * @author jjs
 * @Version 1.0 2020/5/5
 */
public class AlgoVisualizer {
    private AlgoGui algoGui;
//    private Circle[] circles;
//    private volatile boolean flag = true;
    // 自定义数据
    private Object data;

    private int money[];

    public AlgoVisualizer(int width, int height, int n, int r) {
//        circles = new Circle[n];
//        Random random = new Random();
//        for (int i = 0; i < 10; i++) {
//            int x = random.nextInt(width - 2*r) + r;
//            int y = random.nextInt(height - 2*r) + r;
//            int vx = random.nextInt(11) - 5;
//            int vy = random.nextInt(11) - 5;
//            circles[i] = new Circle(x,y,r,vx,vy);
//        }
        money = new int[n];
        for (int i = 0; i < n; i++) {
            money[i] = 100;
        }

        EventQueue.invokeLater(() -> {
            algoGui = new AlgoGui("welcome", width, height);
            algoGui.addKeyListener(new AlgoKeyListener());
            algoGui.addMouseListener(new AlgoMouseListener());

            new Thread(() -> {
                //run(width, height);
                run();
            }).start();
        });
    }


    private void run() {
        while (true) {
            Arrays.sort(money);

            algoGui.render(money);

            AlgoHelper.packTime(4000);

            for (int j = 0; j < 50; j++) {
                for (int i = 0; i < money.length; i++) {
                    int k = (int)(Math.random()*money.length);
                    money[i] -= 1;
                    money[k] += 1;
                }
            }
        }
    }

//    private void run(int width, int height) {
//        while (true) {
//            algoGui.render(circles);
//
//            AlgoHelper.packTime(10000);
//            if (flag) {
//                for (Circle circle : circles) {
//                    circle.move(0, width, 0, height);
//                }
//            }
//        }
//    }

    private class AlgoKeyListener extends KeyAdapter {
//        @Override
//        public void keyReleased(KeyEvent e) {
//            if (e.getKeyChar() == ' ') {
//                flag = !flag;
//            }
//        }
    }

    private class AlgoMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
//            e.translatePoint(0, -(algoGui.getBounds().height - algoGui.getGuiHeight()));
//
//            for (Circle circle : circles) {
//                if (circle.contains(e.getPoint())) {
//                    circle.isFill = !circle.isFill;
//                }
//            }
        }
    }

    public static void main(String[] args) {
        Integer width = 1000;
        Integer height = 1000;
        new AlgoVisualizer(width,height,100, 100);
    }

}
