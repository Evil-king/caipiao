package com.caipiao.service.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caipiao.dao.RegistrationCodeMapper;
import com.caipiao.dao.UserMapper;
import com.caipiao.exception.BaseException;
import com.caipiao.pojo.RegistrationCode;
import com.caipiao.pojo.User;
import com.caipiao.service.RegistrationCodeService;
@Service
public class RegistrationCodeServiceImpl implements RegistrationCodeService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RegistrationCodeMapper registrationCodeMapper;
	
	@Override
	public String getData(Integer userId) {
		User user = new User();
		user.setId(userId);
		User uu = userMapper.queryByUserId(user);
		if(uu.getType()==1) {
			throw new BaseException(1, "该用户无权操作");
		}
		        NumberFormat nf = new DecimalFormat("000");  
		        Calendar cal = Calendar.getInstance();  
		        cal.add(Calendar.YEAR, 3);  
		        cal.add(Calendar.DAY_OF_YEAR, -1);  
		        String licenseNum = nf.format((int) (Math.random() * 100));
		        String expTime = new StringBuilder("-").append(  
		                new SimpleDateFormat("yyMMdd").format(cal.getTime())).append(  
		                "0").toString();
		        String need = new StringBuilder().append("Y").append(licenseNum).append(expTime)  
		                .toString();  
		        String dx = new StringBuilder(need).toString(); 
		        int suf = this.decode(dx); 
		        String code = new StringBuilder(need).append(String.valueOf(suf)).toString(); 
		        return this.change(code);  
		    }  
		  private int decode(String s) {  
		        int i;  
		        char[] ac;  
		        int j;  
		        int k;  
		        i = 0;  
		        ac = s.toCharArray();  
		        j = 0;  
		        k = ac.length;  
		        while (j < k) {  
		            i = (31 * i) + ac[j];  
		            j++;  
		        }  
		        return Math.abs(i);  
		    }  
		  
		    private String change(String s) {  
		        byte[] abyte0;  
		        char[] ac;  
		        int i;  
		        int k;  
		        int j;  
		        abyte0 = s.getBytes();  
		        ac = new char[s.length()];  
		        i = 0;  
		        k = abyte0.length;  
		        while (i < k) {  
		            j = abyte0[i];  
		            if ((j >= 48) && (j <= 57)) {  
		                j = (((j - 48) + 5) % 10) + 48;  
		            } else if ((j >= 65) && (j <= 90)) {  
		                j = (((j - 65) + 13) % 26) + 65;  
		            } else if ((j >= 97) && (j <= 122)) {  
		                j = (((j - 97) + 13) % 26) + 97;  
		            }  
		            ac[i] = (char) j;  
		            i++;  
		        }  
		        return String.valueOf(ac);  
		    }
			
		/**
		 * 插入数据
		 */
		public int insert(RegistrationCode rc) {
			
			return registrationCodeMapper.insert(rc);
		}
		
		/**
		 * 根据count的值返回多条结果
		 * @param count
		 * @return
		 */
		public List<String> getListData(Integer count) {
			
			return registrationCodeMapper.getListData(count);
		}
	}

