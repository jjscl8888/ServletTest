package com.jjs.algorithm.gui;

import java.awt.*;

/**
 * @author jjs
 * @Version 1.0 2020/5/4
 */
public class Test {

    public static void main(String[] args) {
        // 将创建任务放入一个队列中，会使用其他线程创建
        EventQueue.invokeLater(() ->{
            new AlgoGui("welcome", 500, 500);
//            // 设置标题welcome
//            JFrame jf = new JFrame("welcome");
//            //设置大小
//            jf.setSize(500,500);
//            // 窗口大小是否可改变
//            jf.setResizable(false);
//            // 设置关闭窗口按键作用
//            jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            // 设置是否展示窗口
//            jf.setVisible(true);
        });
    }
}
