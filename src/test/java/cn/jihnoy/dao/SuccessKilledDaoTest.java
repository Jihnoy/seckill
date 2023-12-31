package cn.jihnoy.dao;

import cn.jihnoy.entity.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        long seckillId = 1000;
        long userPhone = 12345678901L;
        int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
        System.out.println(insertCount);
    }

    @Test
    public void queryByIdWithSeckill() {
        long seckillId = 1000;
        long userPhone = 12345678901L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
        System.out.println(successKilled);
    }
}