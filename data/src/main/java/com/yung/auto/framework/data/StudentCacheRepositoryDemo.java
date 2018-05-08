package com.yung.auto.framework.data;

import com.yung.auto.framework.data.model.Student;
import com.yung.auto.framework.data.model.StudentDataList;
import com.yung.auto.framework.utility.collection.CollectionUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author wangyujing
 * @date 2018/5/8.
 */
@Service
public class StudentCacheRepositoryDemo implements BasicDataCacheRepository<Student> {

    private List<Student> getStudents() {
        List<Student> students = StudentDataList.getStudents();
        return students;
    }

    @Override
    @Cacheable(cacheNames = "LOCAL_CONFIG_30M", key ="#key")
    public List<Student> getArrayData(String key) {
        List<Student> students = getStudents();
        return students;
    }

    @Override
    @Cacheable(cacheNames = "LOCAL_CONFIG_30M", key ="#key")
    public Student getData(String key) {
        List<Student> students = getStudents();
        if(CollectionUtils.hasElement(students)) {
            Optional<Student> st = students.stream().filter(v->key.equals(String.valueOf(v.getId()))).findFirst();
            if(st.isPresent()) {
                return st.get();
            }
        }
        return null;
    }

    @Override
    public void remove(String key) {

    }
}
