package com.example.demo.service;

import com.example.demo.constant.DefaultConstant;

public class Helper {
    public static String seacrhString(String s){
        if (s == null || s.isEmpty()) s = DefaultConstant.EMPTY_STRING;
        return "%"+s+"%";
    }

}
