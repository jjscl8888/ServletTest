package com.jjs.algorithm.gui;

import java.awt.*; /**
 * @author jjs
 * @Version 1.0 2020/5/5
 */
public class Circle {
    public Integer x,y,r;
    private Integer vx, vy;
    public boolean isFill = false;

    public Circle(Integer x, Integer y, Integer r, Integer vx, Integer vy) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
    }

    public void move(int minx, int maxx, int miny, int maxy) {
        this.x += this.vx;
        this.y += this.vy;
        check(minx,maxx,miny,maxy);
    }

    private void check(int minx, int maxx, int miny, int maxy) {
        if (x - r <= minx) {
            x = r;
            vx = -vx;
        }
        if (x + r >= maxx) {
            x = maxx - r;
            vx = -vx;
        }
        if (y - r < minx) {
            y = r;
            vy = -vy;
        }
        if (y + r > maxx) {
            y = maxx - r;
            vy = -vy;
        }
    }

    public boolean contains(Point point) {
        return Math.pow((x - point.x),2) + Math.pow((y - point.y), 2) <= r*r;
    }
}
