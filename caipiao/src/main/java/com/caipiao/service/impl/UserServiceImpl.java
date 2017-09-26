package com.caipiao.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caipiao.dao.DisclaimerMapper;
import com.caipiao.dao.GroupManagerMapper;
import com.caipiao.dao.MessageInfoMapper;
import com.caipiao.dao.NoticeMapper;
import com.caipiao.dao.RegistrationCodeMapper;
import com.caipiao.dao.UserMapper;
import com.caipiao.exception.BaseException;
import com.caipiao.pojo.Disclaimer;
import com.caipiao.pojo.GroupManager;
import com.caipiao.pojo.MessageInfo;
import com.caipiao.pojo.Notice;
import com.caipiao.pojo.RegistrationCode;
import com.caipiao.pojo.User;
import com.caipiao.service.UserService;
import com.caipiao.util.MD5;
import com.caipiao.util.StringUtil;
import com.caipiao.vo.ResultJson;
import com.caipiao.vo.UserPage;
import com.caipiao.vo.UserVO;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MessageInfoMapper messageInfoMapper;
	@Autowired
	private NoticeMapper noticeMapper;
	@Autowired
	private GroupManagerMapper gtoupManagerMapper;
	@Autowired
	private DisclaimerMapper disclaimerMapper;
	@Autowired
	private RegistrationCodeMapper registrationCodeMapper;
	
	private Logger logg = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
	
	/**
	 * 登录
	 */
	public User login(String phone, String password) throws BaseException{
		int num = 0;
		 // 检测手机号码
        if (!StringUtil.isHandset(phone)) {
            throw new BaseException(1,"非法手机号码！");
        }
		User us = new User();
		us.setPhone(phone);
		us.setPassword(MD5.encryptMD5(password));
		User user = userMapper.checkParameter(us);
		if(user == null) {
			throw new BaseException(1,"手机号码或密码错误");
		}
		//Token的插入
		user.setToken(MD5.encryptMD5(new Date().getTime()+""));
		if(userMapper.updateByParam(user) < 1) {
			throw new BaseException(1, "Token插入失败");
		}
		
		if(user.getLimitData().equals("1")) {
			throw new BaseException(1, "账号已经禁用");
		}
		Disclaimer dis = new Disclaimer();
		dis.setTitle("圆梦计划");
		dis.setDetail("圆梦计划");
		dis.setMessage("圆梦计划");
		dis.setUserId(user.getId());
		Disclaimer dd = disclaimerMapper.queryByParameter(dis);
		if(dd == null) {
			num = disclaimerMapper.insert(dis);
			if(num < 0) {
				throw new BaseException(1, "标题插入失败!");
			}
		}
		num = disclaimerMapper.updateByParameter(dis);
		if(num < 0) {
			throw new BaseException(1, "标题更新失败!");
		}
		
		GroupManager gm = new GroupManager();
		gm.setTitle("圆梦计划");
		gm.setDetail("圆梦计划");
		gm.setMessage("圆梦计划");
		gm.setUserId(user.getId());
		GroupManager gg = gtoupManagerMapper.queryByParameter(gm);
		if(gg == null) {
			num = gtoupManagerMapper.insert(gm);
			if(num < 0) {
				throw new BaseException(1, "标题插入失败!");
			}
		}
		num = gtoupManagerMapper.updateByParameter(gm);
		if(num < 0) {
			throw new BaseException(1, "标题更新失败!");
		}
		
		
		Notice notice = new Notice();
		notice.setTitle("圆梦计划了");
		notice.setDetail("圆梦计划");
		notice.setMessage("圆梦计划");
		notice.setUserId(user.getId());
		Notice not = noticeMapper.queryByParameter(notice);
		if(not == null) {
			num = noticeMapper.insert(notice);
			if(num < 0) {
				throw new BaseException(1, "标题插入失败!");
			}
		}
		num = noticeMapper.updateByParameter(notice);
		if(num < 0) {
			throw new BaseException(1, "标题更新失败!");
		}
		
		MessageInfo mss = new MessageInfo();
		mss.setTitle("圆梦计划");
		mss.setDetail("圆梦计划");
		mss.setUserId(user.getId());
		MessageInfo mm = messageInfoMapper.queryByParameter(mss);
		if(mm == null) {
			num = messageInfoMapper.insert(mss);
			if(num < 0) {
				throw new BaseException(1, "标题插入失败!");
			}
		}
		num = messageInfoMapper.updateByParameter(mss);
		if(num < 0) {
			throw new BaseException(1, "标题更新失败!");
		}
		return user;
	}

	/**
	 * 注册
	 */
	@Transactional
	public UserVO register(User user) throws BaseException{
		//检验手机号
		if (!StringUtil.isHandset(user.getPhone())) {
			throw new BaseException(1,"非法手机号码！");
        }
		if(user.getRegisterCode().isEmpty()) {
			throw new BaseException(1,"注册码不能为空！");
		}
		
		User uu = new User();
		uu.setPassword(MD5.encryptMD5(user.getPassword()));
		uu.setPhone(user.getPhone());
		uu.setRegisterCode(user.getRegisterCode());
		uu.setType(1);//普通用户
		uu.setLimitData("3");//启用和禁用的初始化状态
		User us = userMapper.checkByPhone(uu);
		if(us != null){
			throw new BaseException(1,"账号已注册！");
		}
		
		List<RegistrationCode> rc = registrationCodeMapper.queryParameter(user.getRegisterCode());
		if(rc.size() == 0) {
			throw new BaseException(1,"输入的注册码不存在,请检查");
		}
		for(RegistrationCode code : rc) {
			if(user.getRegisterCode().contains(code.getCode())) {
				RegistrationCode rcv = new RegistrationCode();
				rcv.setPhone(user.getPhone());
				rcv.setCode(user.getRegisterCode());
				rcv.setType(1);
				rcv.setId(code.getId());
				int num = registrationCodeMapper.updateParameter(rcv);
				if(num < 0) {
					throw new BaseException(1, "注册码更新错误!");
				}
			}else {
				throw new BaseException(1, "注册码有误！");
			}
		}
		
		//新增用户信息
		int num = userMapper.create(uu);
		if(num < 0){
			throw new BaseException(1,"新增用户失败");
		}
		UserVO uv = new UserVO();
		uv.setPassword(user.getPassword());
		uv.setPhone(user.getPhone());
		uv.setType(uu.getType());
        return uv;
		
	}

	/**
	 * 更新密码
	 */
	public int updatePassword(String phone, String newPassword) {
		//检验手机号
		if (!StringUtil.isHandset(phone)) {
			throw new BaseException(1,"非法手机号码！");
        }
		User uu = new User();
		uu.setPhone(phone);
		uu.setPassword(MD5.encryptMD5(newPassword));
		if(userMapper.checkByPhone(uu)==null) {
			throw new BaseException(1,"手机号不存在");
		}
		if(userMapper.updateByParam(uu) < 0) {
			throw new BaseException(1,"更新用户密码失败");
		}
		return userMapper.updateByPasswd(uu);
	}
	
	/**
	 * page分页查询
	 */
	public ResultJson<User> toPage(UserPage userPage) {
		ResultJson<User> json = new ResultJson<>();
		json.setRows(userMapper.list(userPage));
		json.setTotal(userMapper.total(userPage));
		return json;
	}
	
	/**
	 * 启用(0)和禁用(1)
	 */
	public Integer limitData(String phone, String type) {
		if(type.equals("0")) {
			User user = new User();
			user.setPhone(phone);
			user.setLimitData("0");
			int num = userMapper.updateByParam(user);
			if(num < 1) {
				throw new BaseException(1, "更新限制失败");
			}
			return num;
		}else if(type.equals("1")){
			User user = new User();
			user.setPhone(phone);
			user.setLimitData("1");
			int num = userMapper.updateByParam(user);
			if(num < 1) {
				throw new BaseException(1, "更新限制失败");
			}
			return num;
		}
		return 0;
	}


	@Override
	public User queryByParam(User user) {
		
		return userMapper.queryByParam(user);
	}

	@Override
	public int updateByParam(User user) {
		
		return userMapper.updateByParam(user);
	}

}
