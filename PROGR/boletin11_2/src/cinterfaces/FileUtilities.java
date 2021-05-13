package cinterfaces;

import java.util.List;

public interface FileUtilities<T> {

    void addObject(T o);
    void removeObject(String attribute, String value);

    List<T> getContentAsList();

    T getByAttribute(String attribute, String value);

    int countCoincidences(String attribute, String value);
    int sumIntValue(String attribute);
    double sumDoubleValue(String attribute);

}
