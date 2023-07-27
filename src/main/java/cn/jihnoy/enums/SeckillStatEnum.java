package cn.jihnoy.enums;

public enum SeckillStatEnum {
    SUCCESS(1, "秒杀成功"),
    END(0, "秒杀结束"),
    REPEAT_KILL(-1, "重复秒杀"),
    INNER_ERROR(-2,"内部错误"),
    DATE_REWRITE(-3, "数据篡改");
    private int state;
    private String info;

    public int getState() {
        return state;
    }

    public String getInfo() {
        return info;
    }

    SeckillStatEnum(int state, String info){
        this.state = state;
        this.info = info;
    }

    public static SeckillStatEnum stateOf(int index){
        for(SeckillStatEnum state:values()){
            if (state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
