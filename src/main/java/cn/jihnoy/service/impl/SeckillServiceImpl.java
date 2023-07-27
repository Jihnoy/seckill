package cn.jihnoy.service.impl;

import cn.jihnoy.dao.SecKillDao;
import cn.jihnoy.dao.SuccessKilledDao;
import cn.jihnoy.dto.Exposer;
import cn.jihnoy.dto.SeckillExecution;
import cn.jihnoy.entity.Seckill;
import cn.jihnoy.entity.SuccessKilled;
import cn.jihnoy.enums.SeckillStatEnum;
import cn.jihnoy.exception.RepeatKillException;
import cn.jihnoy.exception.SeckillCloseException;
import cn.jihnoy.exception.SeckillException;
import cn.jihnoy.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {
    //日志
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //混淆字符串保护秒杀接口
    private final String salt = "zxyxhclcndwfswns12345";
    @Autowired
    private SecKillDao secKillDao;
    @Autowired
    private SuccessKilledDao successKilledDao;

    public List<Seckill> getSeckillList(){
        return secKillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId){
        return secKillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId){
        Seckill seckill = secKillDao.queryById(seckillId);
        if(seckill == null){
            return new Exposer(false, seckillId);
        }
        //秒杀未开启
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        //系统当前时间
        Date nowTime = new Date();
        if( startTime.getTime() > nowTime.getTime() || endTime.getTime() < nowTime.getTime()){
            System.out.println("I am in");
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        //秒杀开启
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId){
        String base = seckillId + "/" +salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
    @Transactional
    //秒杀是否成功，成功减库存+明细，失败抛异常，事务回滚
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
        throws SeckillException, RepeatKillException, SeckillCloseException {
        System.out.println(md5);
        System.out.println(getMD5(seckillId));
        if( md5 == null || !md5.equals(getMD5(seckillId))){
            throw new SeckillException("seckill data rewrite");
        }
        Date nowTime = new Date();

        try{
            //减库存
            int updateCount = secKillDao.reduceNumber(seckillId, nowTime);
            if(updateCount <= 0){
                //没有更新记录
                throw new SeckillException("seckill is closed");
            }
            else{
                //加明细
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if(insertCount <= 0){
                    throw new RepeatKillException("seckill repeat");
                }else{
                    //秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        }catch (SeckillCloseException e){
            throw e;
        }catch (RepeatKillException e1){
            throw e1;
        }catch (Exception e2){
            logger.error(e2.getMessage(), e2);
            throw new SeckillException("seckill inner error:" + e2.getMessage());
        }
    }

}
