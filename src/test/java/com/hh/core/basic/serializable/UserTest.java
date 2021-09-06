package com.hh.core.basic.serializable;

import org.junit.Test;

import java.io.*;

/**
 * Created by hh on 2020/3/17.
 */
public class UserTest {

    @Test
    public void testSerializable() throws IOException, ClassNotFoundException {
        // 序列化对象User
        FileOutputStream fos = new FileOutputStream("g:\\object.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        User user1 = new User("xcbeyond", 20);
        oos.writeObject(user1);
        oos.flush();
        oos.close();

        // 反序列化
        FileInputStream fis = new FileInputStream("g:\\object.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        User user2 = (User) ois.readObject();
        System.out.println(user2.getName()+ "," + user2.getAge());
    }

}
