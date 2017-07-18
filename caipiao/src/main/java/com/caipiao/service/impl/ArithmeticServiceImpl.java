package com.caipiao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.caipiao.service.ArithmeticService;
@Service
public class ArithmeticServiceImpl implements ArithmeticService{

	@Override
	public List<String> sum(int num) {
		List<String> list = new ArrayList<String>();
		for(int i=0;i<=9;i++) { //百位
			for(int j=0;j<=9;j++) { //十位
				for(int k=0;k<=9;k++) { //个位
					if((i + k) %10 == num) {
						list.add(i+""+j+""+k);
					}
				}
			}
		}
		return list;
	}

	@Override
	public List<String> equation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> group() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> rubbish() {
		// TODO Auto-generated method stub
		return null;
	}

}
