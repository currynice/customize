package com.cxy.customize.core.util;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ConsoleUtil {


//代码演示如何使用Java从控制台键盘读取字符串。
//

        public static void main(String[] args) {

        // ====
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Please enter user name : ");
        String username = null;
        try {
            username = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("You entered : " + username);

        // ===== In Java 5, Java.util,Scanner is used for this purpose.
        Scanner in = new Scanner(System.in);
        System.out.print("Please enter user name : ");
        username = in.nextLine();
        System.out.println("You entered : " + username);


        // ====== Java 6
            //使用的代码的最后一部分java.io.Console班级，等级。
            // 无法从System.console()当通过Eclipse运行演示代码时。
            // 因为Eclipse将应用程序作为后台进程运行，而不是作为带有系统控制台的顶级进程运行。
        Console console = System.console();
        username = console.readLine("Please enter user name : ");
        System.out.println(ClassUtil.getClass(console));
        System.out.println("You entered : " + username);
        }
}


