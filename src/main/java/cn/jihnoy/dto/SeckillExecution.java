package cn.jihnoy.dto;

import cn.jihnoy.entity.SuccessKilled;
import cn.jihnoy.enums.SeckillStatEnum;

/*
* 封装秒杀结果
*/
public class SeckillExecution {
    private long seckillId;
    private int state;
    private String stateInfo;
    private SuccessKilled successKilled;

    //秒杀成功返回所有信息
    public SeckillExecution(long seckillId, SeckillStatEnum seckillStatEnum, SuccessKilled successKilled){
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getInfo();
        this.successKilled = successKilled;
    }

    //秒杀失败？
    public SeckillExecution(long seckillId, SeckillStatEnum seckillStatEnum){
        this.seckillId = seckillId;
        this.state = seckillStatEnum.getState();
        this.stateInfo = seckillStatEnum.getInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
