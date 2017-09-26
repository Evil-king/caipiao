package com.caipiao.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caipiao.service.MessageInfoService;
import com.caipiao.vo.ServerResponse;
/**
 * 信息栏
 * @author Fox
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)//springmvc解决跨域问题
@Controller
@RequestMapping(value="message",method=RequestMethod.POST)
public class MessageController {
	@Autowired
	private MessageInfoService messageInfoService;
	
	
	@RequestMapping(value="/writer")
	public @ResponseBody  ServerResponse<Integer> toWriter(String title,String detail,String message,Integer userId,String type){
		
		return ServerResponse.createBySuccess("成功", messageInfoService.writerData(title,detail,message,userId,type));
	}
	
	
	@RequestMapping(value="/reader")
	public @ResponseBody  ServerResponse<List<Map<String,Object>>> toReader(Integer userId){
		return ServerResponse.createBySuccess("成功", messageInfoService.getData(userId));
	}
}
