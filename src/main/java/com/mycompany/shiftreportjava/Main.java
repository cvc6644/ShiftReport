/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shiftreportjava;

import static java.lang.System.in;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

/**
 *
 * @author snowyowl
 */
public class Main {
    public static void main(String[]args){
        ArrayList<ArrayList<String>> ll = new ArrayList<>();
        ll.add(new ArrayList<>(Arrays.asList("Hello","my","baby")));
        ll.add(new ArrayList<>(Arrays.asList("Hello1","my1","baby1")));
        ll.add(new ArrayList<>(Arrays.asList("Hello2","my2","baby2")));
        for (ArrayList<String> i : ll) {
            for(String s : i){
                System.out.print(s);
            }
            System.out.println();
        }
    }
}
