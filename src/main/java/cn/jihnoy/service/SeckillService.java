package cn.jihnoy.service;

import cn.jihnoy.dto.Exposer;
import cn.jihnoy.dto.SeckillExecution;
import cn.jihnoy.entity.Seckill;
import cn.jihnoy.exception.RepeatKillException;
import cn.jihnoy.exception.SeckillCloseException;
import cn.jihnoy.exception.SeckillException;

import java.util.List;

public interface SeckillService {
    List<Seckill> getSeckillList();

    Seckill getById(long seckillId);

    Exposer exportSeckillUrl(long sekillId);
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
        throws SeckillException, RepeatKillException, SeckillCloseException;
}
