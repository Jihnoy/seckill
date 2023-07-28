package cn.jihnoy.dao.cache;

import cn.jihnoy.dao.SecKillDao;
import cn.jihnoy.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private long id = 1001;
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SecKillDao seckillDao;
    @Test
    public void testSeckill(){
        Seckill seckill = redisDao.getSeckill(id);
        if(seckill == null){
            seckill = seckillDao.queryById(id);
            if(seckill != null){
                redisDao.putSeckill(seckill);
                logger.info("result={}", redisDao.putSeckill(seckill));
                seckill = redisDao.getSeckill(id);
                logger.info("seckill={}",seckill);
            }
        }
    }
}