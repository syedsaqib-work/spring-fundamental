package com.spring.springfundamental.swiftmessage;

import java.util.Arrays;

public class SplitAtSecondOccurance {
    public static void main(String[] args) {
        String str = "camt.029.001.09";

        int first = str.indexOf(".");
        int second = str.indexOf(".", first + 1);

        System.out.println(first);
        System.out.println(second);
        System.out.println(str.substring(0, second));
    }
}
