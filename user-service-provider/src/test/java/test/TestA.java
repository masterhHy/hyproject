package test;

import com.alibaba.fastjson.JSONObject;
import com.hao.user.UserCenterApplication;
import com.hao.user.service.ResourceService;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserCenterApplication.class)
public class TestA {
    @Autowired
    private ResourceService resourceService;


    @Test
    public void test1(){
        List<Map<String, Object>> allMenuByUserId = resourceService.getAllMenuByUserId("1");
        System.out.println(JSONObject.toJSONString(allMenuByUserId));
    }
}