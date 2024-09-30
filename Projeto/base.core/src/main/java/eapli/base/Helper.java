package eapli.base;

import java.util.Iterator;

public class Helper {

    public static <T> void removeElementFromList(Iterable<T> iterable, T elementToRemove) {
        Iterator<T> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (element.equals(elementToRemove)) {
                iterator.remove();
            }
        }
    }
}
