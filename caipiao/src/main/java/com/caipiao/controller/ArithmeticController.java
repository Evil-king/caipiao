package com.caipiao.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(origins = "*", maxAge = 3600)//springmvc解决跨域问题
@Controller
@RequestMapping(value="arithmetic",method=RequestMethod.POST)
public class ArithmeticController {
	@Autowired
	private ArithmeticService arithmeticService;
	
	
	@RequestMapping(value="primacy")
	public @ResponseBody ServerResponse<Map<String, Map<String, List<String>>>> toPrimacy(){
			
			return ServerResponse.createBySuccess(0,"成功",arithmeticService.primacy() );
	}
	/**
	 * 带参数的四种玩法
	 * @param type
	 * @return
	 */
	@RequestMapping(value="primacyParam")
	public @ResponseBody ServerResponse<Map<String, Map<String, List<String>>>> toPrimacyParam(String[] type){
			
			return ServerResponse.createBySuccess(0,"成功",arithmeticService.primacyParam(type) );
	}
	
	/**
	 * 不带方法标示
	 * @param type
	 * @return
	 */
	@RequestMapping(value="primacyParamNo")
	public @ResponseBody ServerResponse<Map<String, List<String>>> toPrimacyParamNo(String[] type){
		
		return ServerResponse.createBySuccess(0,"成功",arithmeticService.primacyParamNo(type) );
	}
	
	/**
	 * 通过参数查询前三 中三 后三的值
	 * @param type
	 * @param openCode
	 * @return
	 */
	@RequestMapping(value="getPrizePrarm")
	public @ResponseBody ServerResponse<List<String>> getPrizePrarm(String type, String openCode){
		
		return ServerResponse.createBySuccess(0,"成功",arithmeticService.getPrizePrarm(type, openCode) );
	}
	
	/**
	 * 判断所有前三 中三 后三是否中奖
	 * @return
	 */
	@RequestMapping(value="getPrize")
	public @ResponseBody ServerResponse<Map<String, Map<String, String>>> getPrize(){
		
		return ServerResponse.createBySuccess(0,"成功",arithmeticService.getPrize());
	}
	
	
}
