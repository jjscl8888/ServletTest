package com.jjs.file;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * @author jjs
 * @Version 1.0 2020/5/4
 */
public class RandomRWTest {

    private static final String NewLine = System.getProperty("line.separator");

    public static void main(String[] args) throws Exception {
        File file = new File("C:\\Users\\蒋景松\\Desktop\\小项目\\ServletTest\\study\\src\\main\\java\\com\\jjs\\file\\text.txt");
        if (file.exists()) {
            file.delete();
        }
        RandomAccessFile accessFile = new RandomAccessFile(file, "rw");

        accessFile.seek(0);

        for (int i = 0; i < 10; i++) {
            String res = "jjs:" + i + NewLine;
            accessFile.seek(accessFile.length());
            accessFile.write(res.getBytes());
        }
        accessFile.close();
    }

}
