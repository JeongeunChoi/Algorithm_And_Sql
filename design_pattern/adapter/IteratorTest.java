package algorithm.design_pattern.adapter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("하나");
        list.add("둘");
        list.add("셋");

        Iterator<String> iterator = list.iterator();

        // Iterator를 Enumeration으로 변환
        Enumeration<Object> enumeration = new IteratorAdapter(iterator);

        // Enumeration으로 데이터 접근
        while (enumeration.hasMoreElements()) {
            System.out.println(enumeration.nextElement());
        }
    }

}
