package com.system.exam;

import com.system.exam.util.ExamCodeUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Demo {
    @Test
    public void demo() {
        for (int i=0; i<100; i++) {
            print();
        }
    }

    private void print() {
        List<String> list = new ArrayList<>();
        for (int i=0; i<10000; i++) {
            list.add(ExamCodeUtil.getCode());
        }
        Collections.sort(list);
        int k=0;
        for (int i=1; i<list.size(); i++) {
            if (list.get(i).equals(list.get(i-1))) {
                k++;
            }
        }
        System.out.println("k="+k);
    }
}
