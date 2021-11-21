package io.github.gabbloquet.todolist.utils;

import java.util.ArrayList;
import java.util.List;

public final class ArrayUtils {
    public static <E> void moveToPosition(List<E> list, E element, int position) {
        if(list != null && list.size() > 1) {
            list.remove(element);
            int targetedPosition = position - 1;
            List<E> elementsBefore = new ArrayList<E>(list.subList(0, targetedPosition));
            List<E> elementsAfter = new ArrayList<E>(list.subList(targetedPosition, list.size()));

            list.clear();
            list.addAll(elementsBefore);
            list.add(element);
            list.addAll(elementsAfter);
        }
    }
}
