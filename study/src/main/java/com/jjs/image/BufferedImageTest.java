package com.jjs.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author jjs
 * @Version 1.0 2020/5/4
 */
public class BufferedImageTest {
    public static void main(String[] args) throws IOException {
        BufferedImage image = new BufferedImage(500, 500, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,500,500);
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial",Font.BOLD, 50));
        g.drawString("hello world", 50,200);
        g.dispose();
        ImageIO.write(image, "JPG", new File("H:\\1.jpg"));
        System.out.println("===");
    }
}
