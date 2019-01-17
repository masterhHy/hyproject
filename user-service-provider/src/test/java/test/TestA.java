package test;

import com.hao.user.service.ResourceService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = UserCenterApplication.class)
public class TestA {
    @Autowired
    private ResourceService resourceService;


    @Test
    public void test1(){
        //resourceService.getAllMenuByUserId("1");
    }
}