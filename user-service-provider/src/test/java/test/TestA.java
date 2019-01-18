package test;

import com.alibaba.fastjson.JSONObject;
import com.hao.common.entity.user.SysAuthority;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysAuthorityQuery;
import com.hao.user.UserCenterApplication;
import com.hao.user.service.ResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.shouldHaveThrown;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserCenterApplication.class)
public class TestA {
    @Autowired
    private ResourceService resourceService;


    @Test
    public void test1(){
    	List<SysAuthority> allAuthorit = resourceService.getAllAuthorit();
    	String jsonString = JSONObject.toJSONString(allAuthorit);
    	System.out.println(jsonString);
    }
}