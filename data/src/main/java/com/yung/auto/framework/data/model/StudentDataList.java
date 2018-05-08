package com.yung.auto.framework.data.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
public class StudentDataList {

    private static List<Student>  students = new ArrayList<>();
    private static String defaultName = "defaultName";
    private static int size = 10;

    static {
        for(int i= 0;i<size;i++) {
            Student st = new Student();
            st.setId(i);
            st.setName(defaultName);
            students.add(st);
        }
    }

    public static List<Student> getStudents() {
        return students;
    }

    public static void setStudents(List<Student> students) {
        StudentDataList.students = students;
    }

    public static String getDefaultName() {
        return defaultName;
    }

    public static void setDefaultName(String defaultName) {
        StudentDataList.defaultName = defaultName;
    }

    public static int getSize() {
        return size;
    }

    public static void setSize(int size) {
        StudentDataList.size = size;
    }
}
