package com.jjs.algorithm.gui.template.sort;

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

    private SelectionSortData sortData;
    public void render(SelectionSortData data) {
        this.sortData = data;
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

            int[] data = AlgoGui.this.sortData.getData();
            if (data == null || data.length == 0) {
                return;
            }

            int length = data.length;
            int w = guiWidth / length;
            for (int i = 0; i < length; i++) {
                if (sortData.getSortIndex() >= i) {
                    AlgoHelper.setColor(g2d, Color.ORANGE);
                } else {
                    AlgoHelper.setColor(g2d, Color.GRAY);
                }
                if (i == sortData.getCurrentIndex()) {
                    AlgoHelper.setColor(g2d, Color.BLUE);
                }
                if (i == sortData.getMinIndex()) {
                    AlgoHelper.setColor(g2d, Color.RED);
                }
                AlgoHelper.strokeRect(g2d, i*w + 1, guiHeight - data[i], w - 1, data[i]);
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(guiWidth,guiHeight);
        }
    }
}
