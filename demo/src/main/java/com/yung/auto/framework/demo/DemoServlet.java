package com.yung.auto.framework.demo;

import com.yung.auto.framework.cache.local.LavCacheManager;
import com.yung.auto.framework.cache.local.controller.CacheResponse;
import com.yung.auto.framework.cache.local.controller.CacheResponseCode;
import com.yung.auto.framework.data.model.Student;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyj on 2018/5/12.
 */
@RestController
@RequestMapping
public class DemoServlet {
    private final String FAILURE_NOT_CONTAINS_KEY = "There is no such key in cache.";
    private final String SUCCESS = "Success";
    private final String FAILURE = "Failure";

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public CacheResponse listCacheKeys() {

        Student st = new Student();
        st.setName("yung");
        st.setId(1);
        return new CacheResponse(CacheResponseCode.SUCCESS, SUCCESS, st);
    }
}
