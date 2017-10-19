package com.wta.NewCloudApp.jiuwei138940.latte_core.util;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
public class NormalUtils {


    //待重新封装
    public static ArrayList<String> rmRepeadtedElementByOrder(ArrayList<String> list) {
        HashSet<String> loadsSet = new HashSet<String>();
        ArrayList<String> loadsList = new ArrayList<String>();
        for (Iterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            String element = iterator.next();
            if (loadsSet.add(element)) {
                loadsList.add(element);
            }
        }

        list.clear();
        list.addAll(loadsList);
        return list;
    }

    public static String checkVal(String value){
        if (value.isEmpty()||value.equals("")){
            return "";
        }
        return value;
    }

}
