/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Project;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author chain
 */
public class test {
    public static void main (String[] args){
        /*LocalTime time = LocalTime.of(23, 30);
        LocalTime time2 = LocalTime.of(23, 00);
        Time a = Time.valueOf(time);
        int b = time.getHour();
        System.out.println(time.compareTo(time2));
        System.out.println(time.toString());*/
        /*Integer a = null;
        System.out.println(a);*/
        HashMap<String, Integer> map1 = new HashMap<String, Integer>();
        ArrayList<String> b = new ArrayList<String>();
        b.add("pound");
        b.add("pound");
        b.remove("pound");
        int a = 1;
        map1.put("a", a++);
        map1.put("a", a++);
        map1.put("a", a++);
        System.out.println(map1);
        /*ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(10);
        list.add(50);
        ArrayList<Integer> listclone = new ArrayList<Integer>();
        listclone.add(10);
        listclone.add(50);
        
        System.out.println(list);
        System.out.println(listclone.equals(list));*/
    }
}
