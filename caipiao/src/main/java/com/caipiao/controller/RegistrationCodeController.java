package com.caipiao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caipiao.exception.BaseException;
import com.caipiao.pojo.RegistrationCode;
import com.caipiao.service.RegistrationCodeService;
import com.caipiao.vo.ServerResponse;

@CrossOrigin(origins = "*", maxAge = 3600)//springmvc解决跨域问题
@Controller
@RequestMapping(value="regisCode",method=RequestMethod.POST)
public class RegistrationCodeController {
	@Autowired
	private RegistrationCodeService registrationCodeService;
	
	@RequestMapping(value="/data")
	public @ResponseBody ServerResponse<List<String>> toData(Integer userId,Integer count){
		RegistrationCode rc = new RegistrationCode();
		for(int i=1;i<=count;i++) {
		rc.setCode( registrationCodeService.getData(userId));
		rc.setType(0);
			int num = registrationCodeService.insert(rc);
			if(num < 0 ) {
				throw new BaseException(1, "插入有误!");
			}
		}
		return ServerResponse.createBySuccess("成功", registrationCodeService.getListData(count));
	}
}
