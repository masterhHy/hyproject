package test;

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

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserCenterApplication.class)
public class TestA {
    @Autowired
    private ResourceService resourceService;


    @Test
    public void test1(){
        for (int i =1;i<10;i++){
            SysAuthorityQuery query = new SysAuthorityQuery();
            query.setPageNumber(i);
            query.setPageSize(2);
            query.setParentId("5");
            TableData<SysAuthority> res = resourceService.getSubAuthByParentId(query);
            List<SysAuthority>rows = res.getRows();
            for (SysAuthority row:rows){
                System.out.println(row.getId()+"--"+row.getName());
            }
        }
    }
}