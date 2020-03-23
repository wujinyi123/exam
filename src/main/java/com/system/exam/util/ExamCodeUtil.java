package com.system.exam.util;

import java.util.Arrays;
import java.util.Date;

public class ExamCodeUtil {
    private static String[][] codeArr;

    static {
        codeArr = new String[6][];
        codeArr[0] = new String[]{"q","w","e","r","t","y","u","i","p","a"};
        codeArr[1] = new String[]{"s","d","f","g","h","j","k","l","z","x"};
        codeArr[2] = new String[]{"c","v","b","n","m","1","2","3","4","5"};
        codeArr[3] = new String[]{"6","7","8","9","Q","W","E","R","T","Y"};
        codeArr[4] = new String[]{"U","I","P","A","S","D","F","G","H","J"};
        codeArr[5] = new String[]{"K","L","Z","X","C","V","B","N","M","o"};
    }

    public static String getCode() {
        char[] str = (new Date().getTime()+"").toCharArray();
        StringBuffer codeStr = new StringBuffer();
        for (int i=0; i<str.length; i++) {
            codeStr.append(codeArr[(int) (Math.random() * 6)][str[i]-'0']);
        }
        return codeStr.toString();
    }

}
