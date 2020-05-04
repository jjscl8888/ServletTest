package com.jjs.algorithm.gui;

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

            AlgoHelper.setStrokeWidth(g2d, 5);

            AlgoHelper.setColor(g2d, Color.BLUE);
            AlgoHelper.fillCircle(g2d, guiWidth/2, guiHeight/2, 200);


            AlgoHelper.setColor(g2d, Color.RED);
            AlgoHelper.strokeCircle(g2d, guiWidth/2, guiHeight/2, 200);


        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(guiWidth,guiHeight);
        }
    }
}
