package algorithm.design_pattern.adapter;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Iterator를 Enumertation으로 변환시켜 주는 어댑터
 *
 * Iterator 인터페이스
 * - hasNext()
 * - next()
 * - remove()
 *
 * Enumration 인터페이스
 * - hasMoreElements()
 * - nextElement()
 */
public class IteratorAdapter implements Enumeration<Object> { // 어댑터에서 타깃인터페이스를 구현한다.

    Iterator<?> iterator; // 어댑터는 어댑티로 구성되어 있다.

    public IteratorAdapter(Iterator<?> iterator) {
        this.iterator = iterator;
    }


    @Override
    public boolean hasMoreElements() {
        return iterator.hasNext(); // 모든 요청은 어댑티에 위임된다.
    }

    @Override
    public Object nextElement() {
        return iterator.next();
    }
}
