package com.jjs.algorithm.gui.template.motefaluo;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author jjs
 * @Version 1.0 2020/5/5
 */
public class MonteKale {
    private Circle circle;
    private List<Point> pointList;
    private static long innerPointCount = 0;

    public MonteKale(Circle circle) {
        this.circle = circle;
        this.pointList = new LinkedList<>();
    }

    public void putPoint(Point point) {
        if (point == null) {
            return;
        }
        pointList.add(point);
        if (circle.contains(point)) {
            innerPointCount ++;
        }
    }

    public int getPointSize() {
        return pointList.size();
    }

    public double calPi() {
        if (pointList.size() > 0) {
            return 4.0 * innerPointCount / pointList.size();
        }
        return 4.0;
    }

    public Circle getCircle() {
        return circle;
    }

    public List<Point> getPointList() {
        return pointList;
    }
}
