package com.heihe.utils;
import java.text.SimpleDateFormat;
/**
 * 获取20位随机数
 * 4位年份+13位时间戳+3位随机数
 * @author yuyu
 */
public class GetId {
    /**
     * 20位末尾的数字id
     */
    public static int Guid=10;

    public static String getGuid() {

        GetId.Guid+=1;

        long now = System.currentTimeMillis();
        //获取4位年份数字
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy");
        //获取时间戳
        String time=dateFormat.format(now);
        String info=now+"";
        //获取三位随机数
        //int ran=(int) ((Math.random()*9+1)*100);
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
        int ran=0;
        if(GetId.Guid>99){
            GetId.Guid=10;
        }
        ran=GetId.Guid;

        return time+info.substring(10, info.length())+ran;
    }
}

