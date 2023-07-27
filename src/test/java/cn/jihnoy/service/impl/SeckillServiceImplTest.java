package cn.jihnoy.service.impl;

import cn.jihnoy.dto.Exposer;
import cn.jihnoy.dto.SeckillExecution;
import cn.jihnoy.entity.Seckill;
import cn.jihnoy.exception.RepeatKillException;
import cn.jihnoy.exception.SeckillCloseException;
import cn.jihnoy.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() {
        List<Seckill> seckills = seckillService.getSeckillList();
        System.out.println(seckills);
    }

    @Test
    public void getById() {
        long seckillId = 1000;
        Seckill seckill = seckillService.getById(seckillId);
        System.out.println(seckill);
    }

    @Test
    public void exportSeckillUrl() {
        long seckillId = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        System.out.println("=========================================");
        System.out.println(exposer);
        System.out.println("=========================================");
    }

    @Test
    public void executeSeckill() {
        long seckillId=1000;
        long userPhone=13476191876L;
        String md5="f988881ca53e8c41139c7aeecf4ac75b";

        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);

            System.out.println(seckillExecution);
        }catch (RepeatKillException e)
        {
            e.printStackTrace();
        }catch (SeckillCloseException e1)
        {
            e1.printStackTrace();
        }
    }
    @Test
    public void testSeckillLogic() throws Exception{
        long seckillId = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if(exposer.isExposed()){
            System.out.println(exposer);

            long userPhone = 12345678902L;
            String md5 = exposer.getMd5();

            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(seckillId, userPhone, md5);
                System.out.println(seckillExecution);
            }catch (RepeatKillException e){
                e.printStackTrace();
            }catch (SeckillCloseException d){
                d.printStackTrace();
            }
        }else{
            System.out.println(exposer);
        }
    }

}