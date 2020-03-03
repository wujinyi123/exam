package com.system.exam.util;

import java.util.Date;

public class ExamCodeUtil {
    public static String getCode() {
        return new Date().getTime()+"";
    }
}
