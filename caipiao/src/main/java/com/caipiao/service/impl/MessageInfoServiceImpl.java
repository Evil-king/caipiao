package com.caipiao.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caipiao.dao.DisclaimerMapper;
import com.caipiao.dao.GroupManagerMapper;
import com.caipiao.dao.MessageInfoMapper;
import com.caipiao.dao.NoticeMapper;
import com.caipiao.dao.UserMapper;
import com.caipiao.exception.BaseException;
import com.caipiao.pojo.Disclaimer;
import com.caipiao.pojo.GroupManager;
import com.caipiao.pojo.MessageInfo;
import com.caipiao.pojo.Notice;
import com.caipiao.pojo.User;
import com.caipiao.service.MessageInfoService;

@Service
public class MessageInfoServiceImpl implements MessageInfoService{
	@Autowired
	private MessageInfoMapper messageInfoMapper;
	@Autowired
	private DisclaimerMapper disclaimerMapper;
	@Autowired
	private GroupManagerMapper groupManagerMapper;
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private UserMapper userMapper;
	@Override
	public Integer writerData(String title, String detail,String message, Integer userId,String type) {
		if(type.equals("topMsg")) {
			int num = 0;
			MessageInfo messageInfo = new MessageInfo();
			messageInfo.setDetail(detail);
			messageInfo.setTitle(title);
			messageInfo.setUserId(userId);
			User user =  new User();
			user.setId(userId);
			User  uu = userMapper.queryByUserId(user);
			if(uu.getType()==1) {
				throw new BaseException(1, "该用户没有权限");
			}
			MessageInfo mss = messageInfoMapper.queryByParameter(messageInfo);
			if(mss == null) {
				num = messageInfoMapper.insert(messageInfo);
				if(num < 0) {
					throw new BaseException(1, "插入失败");
				}
				return num;
			}
			num = messageInfoMapper.updateByParameter(messageInfo);
			if(num < 0) {
				throw new BaseException(1, "更新失败");
			}
			return num;
		}
		if(type.equals("orgMsg")) {
			int num = 0;
			Notice notice = new Notice();
			notice.setDetail(detail);
			notice.setMessage(message);
			notice.setTitle(title);
			notice.setUserId(userId);
			User user =  new User();
			user.setId(userId);
			User  uu = userMapper.queryByUserId(user);
			if(uu.getType().equals(1)) {
				throw new BaseException(1, "该用户没有这个权限");
			}
			Notice not = noticeMapper.queryByParameter(notice);
			if(not == null) {
				num = noticeMapper.insert(notice);
				if(num < 0) {
					throw new BaseException(1, "插入失败");
				}
				return num;
			}
			num = noticeMapper.updateByParameter(notice);
			if(num < 0) {
				throw new BaseException(1, "更新失败");
			}
			return num;
		}
		if(type.equals("disclaimerMsg")) {
			int num = 0;
			User user =  new User();
			Disclaimer dis = new Disclaimer();
			dis.setDetail(detail);
			dis.setMessage(message);
			dis.setTitle(title);
			dis.setUserId(userId);
			user.setId(userId);
			User  uu = userMapper.queryByUserId(user);
			if(uu.getType().equals(1)) {
				throw new BaseException(1, "该用户没有权限");
			}
			Disclaimer disc = disclaimerMapper.queryByParameter(dis);
			if(disc == null) {
				num = disclaimerMapper.insert(dis);
				if(num < 0) {
					throw new BaseException(1, "插入失败");
				}
				return num;
			}
			num = disclaimerMapper.updateByParameter(dis);
			if(num < 0) {
				throw new BaseException(1, "更新失败");
			}
			return num;
		}
		if(type.equals("bossMsg")) {
			int num = 0;
			User user =  new User();
			GroupManager groupManage = new GroupManager();
			groupManage.setDetail(detail);
			groupManage.setMessage(message);
			groupManage.setTitle(title);
			groupManage.setUserId(userId);
			user.setId(userId);
			User  uu = userMapper.queryByUserId(user);
			if(uu.getType().equals(1)) {
				throw new BaseException(1, "该用户没有这个权限");
			}
			GroupManager gm = groupManagerMapper.queryByParameter(groupManage);
			if(gm == null) {
				num = groupManagerMapper.insert(groupManage);
				if(num < 0) {
					throw new BaseException(1, "插入失败");
				}
				return num;
			}
			num = groupManagerMapper.updateByParameter(groupManage);
			if(num < 0) {
				throw new BaseException(1, "更新失败");
			}
			return num;
		}
		return null;
	}


	@Override
	public List<Map<String,Object>> getData(Integer userId) {
		List<Map<String, Object>> list =  new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
//		User user =  new User();
//		user.setId(userId);
//		User  u_messageInfo = userMapper.queryByUserId(user);
//		User  u_notice = userMapper.queryByUserId(user);
//		User  u_dis = userMapper.queryByUserId(user);
//		User  u_groupManage = userMapper.queryByUserId(user);
//		if(u_messageInfo.getType()==1 || u_notice.getType()==1 ||u_dis.getType()==1 || u_groupManage.getType()==1) {
//			throw new BaseException(1, "该用户没有权限");
//		}
		MessageInfo mss = messageInfoMapper.queryByUserId(userId);
		Notice not = noticeMapper.queryByUserId(userId);
		Disclaimer dis = disclaimerMapper.queryByUserId(userId);
		GroupManager gm = groupManagerMapper.queryByUserId(userId);
		if(mss ==null || not == null || dis == null || gm == null) {
			throw new BaseException(1, "对象信息不存在");
		}
		map.put("topMsg", mss);
		map.put("orgMsg", not);
		map.put("disclaimerMsg", dis);
		map.put("bossMsg", gm);
		list.add(map);
		return list;
		
	}

}
