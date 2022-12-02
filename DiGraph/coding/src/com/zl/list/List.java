package com.zl.list;

public interface List<T> {

    public void add(int index,T value);

    public T remove(int index);

    public void set(int index,T value);

    public T get(int index);

    public boolean isEmpty();

    public int size();
}
