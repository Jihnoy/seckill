package cn.jihnoy.dao;

import cn.jihnoy.entity.Seckill;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SecKillDaoTest extends TestCase {

    @Resource
    private SecKillDao secKillDao;
    @Test
    public void reduceNumber() {
        long seckillId = 1000;
        Date date = new Date();
        int updateCount = secKillDao.reduceNumber(seckillId, date);
        System.out.println(updateCount);
    }
    @Test
    public void queryById(){
        long seckillId = 1000;
        Seckill seckill = secKillDao.queryById(seckillId);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }
    @Test
    public void queryAll() {
        List<Seckill> seckills = secKillDao.queryAll(0, 100);
        for(Seckill seckill : seckills){
            System.out.println(seckill);
        }
    }
}