package com.oyf.thread.singleton;

import java.io.*;

/**
 * @author ouyangfei
 * @date Created in 2020/12/23
 * @description
 */
public class Run3 extends Thread {

    @Override
    public void run() {
        System.out.println(Singleton3.getInstance().hashCode());
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Singleton3 instance = Singleton3.getInstance();

        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("test.txt")));
        outputStream.writeObject(instance);
        outputStream.flush();
        outputStream.close();
        System.out.println(instance.hashCode());

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("test.txt")));
        // 会自动调用readResolve
        Singleton3 readObject = (Singleton3) inputStream.readObject();
        inputStream.close();
        System.out.println(readObject.hashCode());
    }

}
