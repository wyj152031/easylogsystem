package com.yung.auto.framework.utility.file.txtfille;

import com.yung.auto.framework.utility.entities.LogLevel;
import com.yung.auto.framework.utility.entities.LogType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyujing on 2018/1/8.
 */
public class Main {

    public static void main(String[] args) {
        String dirName = "temp";
        FileReaderWarpper reader = new FileReaderWarpper();
        String content = "how are you";
        Map<String,String> map = new HashMap<String, String>();
        map.put("store","name");
        map.put("common","user");
        map.put("hero","wang");

        System.out.println(new Date().toString());
        for(int i =0;i<10;i++) {
            reader.fileTxtWriter("temp",map,LogLevel.INFO, LogType.APP);
        }

    }
}
