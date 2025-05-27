package com.eagle.all;

import com.eagle.eye.util.EyeUtil;

public class AppMain {
    public static void main(String[] args) {
        EyeUtil util = EyeUtil.getInstance();
        System.out.println(util.getSystemInfo());
    }
}
