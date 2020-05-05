package com.jjs.algorithm.gui.template.sort;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.concurrent.TimeUnit;

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

    public static void strokeRect(Graphics2D g2d, int x, int y, int width, int height) {
        Rectangle2D.Double rect = new Rectangle2D.Double(x,y ,width,height);
        g2d.draw(rect);
    }

    public static void fillRect(Graphics2D g2d, int x, int y, int width, int height) {
        Rectangle2D.Double rect = new Rectangle2D.Double(x,y ,width,height);
        g2d.fill(rect);
    }

    public static void packTime (long mill) {
        try {
            TimeUnit.MICROSECONDS.sleep(mill);
        } catch (InterruptedException e) {

        }
    }

    public static void putImage(Graphics2D g2d, int x, int y, String imageURL) {
        ImageIcon icon = new ImageIcon(imageURL);
        Image image = icon.getImage();

        g2d.drawImage(image, x, y, null);
    }

    public static void drawText(Graphics2D g2d, String text, int centernx, int centerny) {
        if (text == null) {
            return;
        }
        FontMetrics metrics = g2d.getFontMetrics();
        int w = metrics.stringWidth(text);
        int h = metrics.getDescent();
        g2d.drawString(text, centernx - w/2, centerny + h);
    }
}
