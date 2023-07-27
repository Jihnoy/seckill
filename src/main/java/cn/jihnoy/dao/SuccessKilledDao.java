package cn.jihnoy.dao;

import cn.jihnoy.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {
    /*
    * 插入秒杀成功
    * @param seckillId
    * @param userPhone
    * return 插入的行数*/
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /*
    * 根据秒杀id和电话返回秒杀成功对象
    * @param seckillId 编号
    * @param userPhone 电话
    * return 成功秒杀SuccessKilled:{.....}*/
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
