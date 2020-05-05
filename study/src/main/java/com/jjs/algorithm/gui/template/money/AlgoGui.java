package com.jjs.algorithm.gui.template.money;

import javax.swing.*;
import java.awt.*;

/**
 * @author jjs
 * @Version 1.0 2020/5/4
 */
public class AlgoGui extends JFrame {

    private Integer guiWidth;
    private Integer guiHeight;

    public AlgoGui(String title, Integer width, Integer height) throws HeadlessException {
        super(title);

        this.guiWidth = width;
        this.guiHeight = height;

        GuiJpanel guiJpanel = new GuiJpanel();
        setContentPane(guiJpanel);
        pack();

        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


//    private Circle[] circles;
//    public void render(Circle[] circles) {
//        this.circles = circles;
//        repaint();
//    }
    private int[] data;
    public void render(int[] data) {
        this.data = data;
        repaint();
    }

    public Integer getGuiWidth() {
        return guiWidth;
    }

    public Integer getGuiHeight() {
        return guiHeight;
    }

    private class GuiJpanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
//            g.setColor(Color.red);
//            g.drawOval(100,100,50,50);
            Graphics2D g2d = (Graphics2D)g;

            //抗锯齿
            RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.addRenderingHints(renderingHints);

            // todo

            int w = guiWidth / data.length;
            for (int i = 0; i < data.length; i++) {
                if (data[i] > 0) {
                    AlgoHelper.setColor(g2d,Color.BLUE);
                    AlgoHelper.fillRect(g2d, i * w + 1, guiHeight/2 - data[i], w - 1, data[i]);
                } else {
                    AlgoHelper.setColor(g2d,Color.RED);
                    AlgoHelper.fillRect(g2d, i * w + 1, guiHeight/2, w - 1, -data[i]);
                }
            }


//            AlgoHelper.setStrokeWidth(g2d, 2);
//            AlgoHelper.setColor(g2d, Color.RED);
//
//            for (Circle circle : circles) {
//                if (circle == null) {
//                    continue;
//                }
//                if (!circle.isFill) {
//                    AlgoHelper.strokeCircle(g2d,circle.x, circle.y, circle.r);
//                } else {
//                    AlgoHelper.fillCircle(g2d,circle.x, circle.y, circle.r);
//                }
//            }


//            AlgoHelper.setStrokeWidth(g2d, 5);
//
//            AlgoHelper.setColor(g2d, Color.BLUE);
//            AlgoHelper.fillCircle(g2d, guiWidth/2, guiHeight/2, 200);
//
//
//            AlgoHelper.setColor(g2d, Color.RED);
//            AlgoHelper.strokeCircle(g2d, guiWidth/2, guiHeight/2, 200);


        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(guiWidth,guiHeight);
        }
    }
}
