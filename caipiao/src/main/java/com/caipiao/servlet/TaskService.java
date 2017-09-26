package com.caipiao.servlet;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;



public class TaskService {
 
    public void startAllTask() {
        startCqsscTimerTask();//重庆时时彩数据采集器
        startCqsscTimerAppendTask();//重庆时时彩追加器
    }

    private void startCqsscTimerTask() {
        Timer timer = new Timer("重庆时时彩定时器");
        TimerTask task = new CqsscTimerTask();
        
        // 根据项目启动时间设置任务首次执行时间
        Calendar calendar = Calendar.getInstance();//获取当前时间
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);//几点
        int minute = calendar.get(Calendar.MINUTE);//几分
        if (0 <= hourOfDay && hourOfDay < 2) {    //如果是0点-2点之间
            if (minute % 10 <= 5) {
                calendar.set(Calendar.MINUTE, (minute / 10) * 10 + 5 );
            } else {
                calendar.set(Calendar.MINUTE, (minute / 10 + 1) * 10 );
            }
        } else if (10 <= hourOfDay && hourOfDay < 22) {  //如果是10点-22点之间
            if (minute % 10 <= 0) {
                calendar.set(Calendar.MINUTE, (minute / 10 ) * 10);
            } else {
                calendar.set(Calendar.MINUTE, (minute / 10 + 1) * 10 );
            }
        } else if (22 <= hourOfDay && hourOfDay < 24) {  //如果是22点-24点之间
            if (minute % 10 <= 5) {
                calendar.set(Calendar.MINUTE, (minute / 10) * 10 + 5);
            } else {
                calendar.set(Calendar.MINUTE, (minute / 10 + 1) * 10 );
            }
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, 10);
            calendar.set(Calendar.MINUTE, 00);
        }
        calendar.set(Calendar.MINUTE, 1);
        calendar.set(Calendar.SECOND, 30);
        Date firstTime = new Date(calendar.getTimeInMillis());
        timer.schedule(task, firstTime);
    }

    private void startCqsscTimerAppendTask() {
        Timer timer = new Timer("重庆时时彩追加定时器");
        Calendar calendar = Calendar.getInstance();
        
        // 追加轮询定时任务，在抓取数据任务执行后每1.5小时轮询一次，检查数据是否有遗漏
        CqsscTimerAppendTask appendTask = new CqsscTimerAppendTask();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        if (minute <= 30) {
            calendar.set(Calendar.MINUTE, 30);
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay + 1);
            calendar.set(Calendar.MINUTE, 00);
        }
        Date appendFirstTime = new Date(calendar.getTimeInMillis());
        timer.schedule(appendTask, appendFirstTime);
    }
}
