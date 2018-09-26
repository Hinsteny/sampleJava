package com.hinsteny.process;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.MessageFormat;

/**
 * @author Hinsteny
 * @date 2017-09-26
 * @copyright: 2017 All rights reserved.
 */
public class JobProcess {

    /**
     * 打印信息到日志文件
     */
    private static final String log_file = "C://log.txt";

    public static void main(String[] args) {
        System.out.println("args: " + args);
        ProcessHandle current = ProcessHandle.current();
        int i =10;
        while (i-- < 0) {
            writeContentToFile(log_file, MessageFormat.format("Pid: {0}, Times: {1}", current.pid(), i));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeContentToFile(String filePath, String content) {
        throw new NullPointerException();
//        if (null == content || content.trim().length() == 0) return;
//        writeByteToFile(filePath, content.getBytes());
    }

    private static void writeByteToFile(String filePath, byte[] content) {
        try (RandomAccessFile rf = new RandomAccessFile(filePath, "rw");){
            rf.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
