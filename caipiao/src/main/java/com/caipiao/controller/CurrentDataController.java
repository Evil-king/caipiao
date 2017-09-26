package com.caipiao.controller;

import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caipiao.pojo.CurrentData;
import com.caipiao.service.CurrentDataService;
import com.caipiao.vo.ServerResponse;
/**
 * 实施数据
 * @author Fox
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)//springmvc解决跨域问题
@Controller
@RequestMapping(value="current_data",method=RequestMethod.POST)
public class CurrentDataController {
	@Autowired
	private CurrentDataService currentDataService;
	
	private Logger logg = org.slf4j.LoggerFactory.getLogger(CurrentDataController.class);
	/**
	 * 获取最新的实时数据
	 * @return
	 */
	@RequestMapping(value="/data")
	public @ResponseBody ServerResponse<List<CurrentData>> toData(String phone,String token){
		
		return ServerResponse.createBySuccess(0,"成功",  currentDataService.sendData(phone,token));
	}
	
	/**
	 * 返回出现次数最少的两个数
	 * @return
	 */
	@RequestMapping(value="/iceData")
	public @ResponseBody ServerResponse<Map<String, List<String>>> getData(){
		
		return ServerResponse.createBySuccess("成功", currentDataService.getIcdData(62));
	}
	
	/**
	 * 返回最近200条记录的期号 - 开奖码的最后两位  
	 * @return
	 */
	@RequestMapping(value="/hotData")
	public @ResponseBody ServerResponse<Map<String, List<String>>> getData2(){
		
		return ServerResponse.createBySuccess("成功", currentDataService.getHotData());
	}
	
	/**
	 * 返回最近200条记录的期号 - 开奖码的最后两位  
	 * @return
	 */
	@RequestMapping(value="/position")
	public @ResponseBody ServerResponse<Map<String, Map<String, List<String>>>> getPositioning(){
		
		return ServerResponse.createBySuccess("成功", currentDataService.positioning());
	}
	
	/**
	 * 一星方案一
	 * @return
	 */
	@RequestMapping(value="/star")
	public @ResponseBody ServerResponse<Map<String, Map<String, List<String>>>> toStar(){
		
		return ServerResponse.createBySuccess("成功", currentDataService.getStar());
	}
	
	/**
	 * 一星二星复制
	 * @return
	 */
	@RequestMapping(value="/TwoStarCopy")
	public @ResponseBody ServerResponse<Map<String, Object>> getTwoStarCopy(){
		
		return ServerResponse.createBySuccess("成功", currentDataService.getTwoStarCopy());
	}
	
	/**
	 * 后三方法
	 * @return
	 */
	@RequestMapping(value="/endThree")
	public @ResponseBody ServerResponse<Map<String, Map<String, List<String>>>> getendThree(){
		
		return ServerResponse.createBySuccess("成功", currentDataService.endThree(66));
	}
	
	/**
	 * 后三方法复制
	 * @return
	 */
	@RequestMapping(value="/endThreeCopy")
	public @ResponseBody ServerResponse<Map<String, Map<String, List<String>>>> getendThreeCopy(){
		
		return ServerResponse.createBySuccess("成功", currentDataService.endThree(6));
	}
	
}
