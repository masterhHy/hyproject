package test;

import com.alibaba.fastjson.JSONObject;
import com.hao.common.entity.user.SysAuthority;
import com.hao.user.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserCenterApplication.class)
public class TestA {
    @Autowired
    private ResourceService resourceService;


    //@Test
    public void test1(){
    	List<SysAuthority> allAuthorit = resourceService.getAllAuthority();
    	String jsonString = JSONObject.toJSONString(allAuthorit);
    	System.out.println(jsonString);
    }
}