package com.caipiao.servlet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.caipiao.pojo.CurrentData;

/**
 * 重庆时时彩追加器
 * @author Administrator
 *
 */
public class CqsscTimerAppendTask extends TimerTask {
	
    private Logger log = Logger.getLogger(CqsscTimerAppendTask.class);
    @Override
    public void run() {
        log.info("run get database records:"+new Date());
        List<CurrentData> dbRecords = ConnectionFactory.getInstance().selectLast15();
        List<Long> insertExpects = new ArrayList<>();
        
        // 检查最近半小时获取的接口数据的期号是否连续
        long temp = Long.parseLong(dbRecords.get(0).getExpect());
        for (int i = 1; i < dbRecords.size(); i++) {
            long expect = Long.parseLong(dbRecords.get(i).getExpect());
            temp--; 
            if (expect == temp) {
                continue;
            }
            for (long j = expect + 1; j <= temp; j++) {
                insertExpects.add(j);
            }
            temp = expect;
        }
        
        // 如果存在遗漏数据，需要补上
        if (insertExpects.size() > 0) {
            log.info("run get interface records");
            List<CurrentData> insertRecords = new ArrayList<>();
            List<CurrentData> interfaceRecords = OpenCaiApiPlus.cqssc(20);
            for (CurrentData interfaceRecord : interfaceRecords) {
                long expect = Long.parseLong(interfaceRecord.getExpect());
                if (insertExpects.contains(expect)) {
                    insertRecords.add(interfaceRecord);
                }
            }
            int[] num = ConnectionFactory.getInstance().insertAppend(insertRecords);
            if(num.length < 0) {
            	log.info("插入错误");
            }
            log.info("num:"+num.toString());
        }
        
        // 1个后继续
        try {
            log.info("run sleep 1 hour:"+new Date());
            Thread.sleep(1 * 60 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 递归
        run();
    }

}
