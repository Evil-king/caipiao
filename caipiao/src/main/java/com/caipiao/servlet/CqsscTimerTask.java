package com.caipiao.servlet;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.caipiao.pojo.CurrentData;

/**
 * 重庆时时彩读取数据的时间段
 * @author Administrator
 *
 */
public class CqsscTimerTask extends TimerTask {
	private Logger log = Logger.getLogger(CqsscTimerTask.class);
    @Override
    public void run() {
        // 首次执行时间为00:11，直接调用接口获取数据并保存到数据库
    	try {
	    	CurrentData currentData = OpenCaiApiPlus.cqssc(1).get(0);
	        ConnectionFactory.getInstance().insert(currentData);
        
     // 根据当前时间，判定下一次执行时间（即任务间隔执行时间）
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int temp = calendar.get(Calendar.MINUTE) % 10 == 0? 10 : calendar.get(Calendar.MINUTE) % 10;
        long muchMinute = 0;
        int minute  = calendar.get(Calendar.MINUTE)+ (hourOfDay * 60);
        int second  =calendar.get(Calendar.SECOND);
        long period = 0; // 间隔时间
        if ((0 <= hourOfDay && minute < 115) || (22 <= hourOfDay && hourOfDay < 24)) { // 夜间 [22:00, 01:55] 每5分钟一期，共48期
            period = 5 * 60 *1000;
            if (temp >= 2 && temp <= 5) {
            	muchMinute = (temp - 1) * 60 * 1000;
            } else if (temp >= 7 && temp <= 10) {
            	muchMinute = (temp - 6) * 60 * 1000;
            }
        } else if (10 <= hourOfDay && hourOfDay < 22) { // 白天 [10:00, 21:50] 每10分钟一期，共72期
            period = 10 * 60 *1000;
            muchMinute = temp>=2?(temp - 1) * 60 * 1000:0;
        } else { // 其他时间 [02:00, 9:55]，暂停销售
            period = 8 * 60 * 60 *1000 + 5 * 60 * 1000;
        }
        
        		log.info("period:"+period);
            Thread.sleep(period - muchMinute - second);//减去执行方法的睡眠时间
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 递归
        run();
    }

}
