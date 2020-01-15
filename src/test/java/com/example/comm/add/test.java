package com.example.comm.add;

import javax.sound.midi.SoundbankResource;

public class test {


    public static void main(String[] args) {
        String s="$07F";
        String substring = s.substring(s.length() - 1);
        System.out.println("返回值的最后一个字符:"+substring);
        String substring1 = s.substring(0, 3);
        System.out.println("返回值前3个字符!"+substring1);
        System.out.println("截取中间的两个字符!"+s.substring(3, 4));
    }
}
