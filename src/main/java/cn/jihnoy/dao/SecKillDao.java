package cn.jihnoy.dao;

import cn.jihnoy.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SecKillDao {
    /*
    * 减少库存
    * @param seckillId
    * @param killTime
    * @return >1 表明成功修改一行*/
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /*
    *根据Id查询秒杀信息
    * @param seckillId
    * @ return seckill对象 */
    Seckill queryById(long seckillId);

    List<Seckill> queryAll(@Param("offset") int off, @Param("limit") int limit);
}
