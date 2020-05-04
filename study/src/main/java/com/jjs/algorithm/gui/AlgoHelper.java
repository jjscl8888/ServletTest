package com.jjs.algorithm.gui;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * @author jjs
 * @Version 1.0 2020/5/4
 */
public class AlgoHelper {
    private AlgoHelper() {}

    public static void setStrokeWidth(Graphics2D g2d, int width) {
        g2d.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
    }

    public static void setColor(Graphics2D g2d, Color color) {
        g2d.setColor(color);
    }

    public static void strokeCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D.Double circle = new Ellipse2D.Double(x - r,y - r,2*r,2*r);
        g2d.draw(circle);
    }

    public static void fillCircle(Graphics2D g2d, int x, int y, int r) {
        Ellipse2D.Double circle = new Ellipse2D.Double(x - r,y - r,2*r,2*r);
        g2d.fill(circle);
    }
}
