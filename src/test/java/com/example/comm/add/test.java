package com.example.comm.add;

import javax.sound.midi.SoundbankResource;
import java.util.ArrayList;
import java.util.List;

public class test {


    /*public static void main(String[] args) {
        String s="$07F";
        String substring = s.substring(s.length() - 1);
        System.out.println("返回值的最后一个字符:"+substring);
        String substring1 = s.substring(0, 3);
        System.out.println("返回值前3个字符!"+substring1);
        System.out.println("截取中间的两个字符!"+s.substring(3, 4));
    }*/
    public static List<String> listZL = new ArrayList();

    public static void main(String[] args) {
        String str = "ST NT +000.519kg";
   /*     //获得第一个点的位置
        int index = str.indexOf("+");
        System.out.println(index);
        //根据第二个点的位置，截取 字符串。得到结果 result
        String result = str.substring(index + 1);
        //输出结果
        System.out.println(result);
        System.out.println(str.substring(0, 7));
        System.out.println(str.substring(7));
        if ("ST NT +".equals(str.substring(0, 7))) {
            System.out.println("真的");
            listZL.add("22");
        }
        if(listZL.size()>0){
            System.out.println("666");
        }
        String strs=null;
        for (int i = 0; i <listZL.size() ; i++) {
            strs=listZL.get(i).trim();
            System.out.println("66"+strs);
        }
        System.out.println(strs);
        listZL.remove(strs);
        for (int i = 0; i <listZL.size() ; i++) {
            String s=listZL.get(i).trim();
            System.out.println("88"+s);
        }*/
        String st = "000.05kg";
        String s1=st.substring(0, st.length()-2);
        System.out.println(st.substring(0, st.length()-2));
        double b_weight = Double.valueOf(s1.toString());
        System.out.println(b_weight);
    }
}
