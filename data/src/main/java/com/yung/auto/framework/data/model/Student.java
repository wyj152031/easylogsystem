package com.yung.auto.framework.data.model;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
public class Student {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + "|" + name;
    }
}
