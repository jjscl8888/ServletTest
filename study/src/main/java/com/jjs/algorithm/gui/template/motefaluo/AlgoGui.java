package com.jjs.algorithm.gui.template.motefaluo;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jjs
 * @Version 1.0 2020/5/4
 */
public class AlgoGui extends JFrame {

    private Integer guiWidth;
    private Integer guiHeight;

    public ReentrantLock lock = new ReentrantLock();

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


    private MonteKale data;
    public void render(MonteKale data) {
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


            Circle circle = data.getCircle();
            AlgoHelper.setStrokeWidth(g2d, 3);
            // todo
            g2d.setColor(Color.CYAN);
            AlgoHelper.strokeCircle(g2d,circle.getX(),circle.getY(),circle.getR());

            lock.lock();
            List<Point> pointList = data.getPointList();
            for (int i = 0; i < data.getPointSize(); i++) {
                Point point = pointList.get(i);
                if (point == null) {
                    continue;
                }
                if (circle.contains(point)) {
                    AlgoHelper.setColor(g2d, Color.BLUE);
                } else {
                    AlgoHelper.setColor(g2d, Color.RED);
                }
                AlgoHelper.fillCircle(g2d,(int)point.getX(), (int)point.getY(), 3);
            }
            lock.unlock();
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(guiWidth,guiHeight);
        }
    }
}
