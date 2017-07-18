package com.caipiao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.caipiao.service.CurrentDataService;
import com.caipiao.vo.ServerResponse;

@Controller
@RequestMapping(value="current_data",method=RequestMethod.GET)
public class CurrentDataController {
	@Autowired
	private CurrentDataService currentDataService;
	
	@RequestMapping(value="/data")
	public @ResponseBody ServerResponse<Object> toData(){
		
		return ServerResponse.createBySuccess(0,"成功", currentDataService.getData());
	}
}
