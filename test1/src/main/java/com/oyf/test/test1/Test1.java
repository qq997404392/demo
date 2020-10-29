package com.oyf.test.test1;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String args[]) {
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = new ArrayList<>();
        User user = null;
        for (int i = 1; i < 3; i++) {
            user = new User("name" + i, "age" + i, "sex" + i);
            users.add(user);
        }

        // bean转json
        StringWriter str1 = new StringWriter();
        try {
            mapper.writeValue(str1, users);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(str1);

        // json转bean
//        String str2="[{\"name\":\"name1\",\"age\":age1,\"sex\":sex1}]";
        List<User> users2 = new ArrayList<>();
        User user2 = new User("1", "2", "3");

        try {
            String str2 = mapper.writeValueAsString(user2);
            System.out.println(str2);
            user2 = mapper.readValue(str2, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(user2);
    }
}
