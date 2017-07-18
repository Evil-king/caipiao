package com.caipiao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caipiao.service.ArithmeticService;
import com.caipiao.vo.ServerResponse;

/**
 * 算法Controller
 * @author Fox
 *
 */
@Controller
@RequestMapping(value="arithmetic",method=RequestMethod.POST)
public class ArithmeticController {
	@Autowired
	private ArithmeticService arithmeticService;
	
	
	@RequestMapping(value="primacy")
	public @ResponseBody ServerResponse<List<String>> toData(String type,int num){
		if(type.equals("1")) {
			return ServerResponse.createBySuccess(0,"成功",arithmeticService.sum(num) );
		}
		if(type.equals("2")) {
			return ServerResponse.createBySuccess(0,"成功", arithmeticService.equation());
		}
		if(type.equals("3")) {
			return ServerResponse.createBySuccess(0,"成功", arithmeticService.group());
		}
		return ServerResponse.createBySuccess(0,"成功", arithmeticService.rubbish());
	}
}
