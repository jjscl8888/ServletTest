package com.jjs.algorithm.gui.template.motefaluo;

import java.awt.*;

/**
 * @author jjs
 * @Version 1.0 2020/5/5
 */
public class Circle {
    private Integer x,y,r;

    public Circle(Integer x, Integer y, Integer r) {
        this.x = x;
        this.y = y;
        this.r = r;

    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getR() {
        return r;
    }

    public boolean contains(Point point) {
        return Math.pow((x - point.x),2) + Math.pow((y - point.y), 2) <= r*r;
    }
}
