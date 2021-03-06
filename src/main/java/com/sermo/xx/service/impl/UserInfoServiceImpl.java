package com.sermo.xx.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sermo.components.biz.util.MD5Util;
import com.sermo.xx.dao.UserInfoDao;
import com.sermo.xx.model.UserInfo;
import com.sermo.xx.service.UserInfoService;
import com.sermo.xx.vo.UserInfoVo;

/**
 * @author sermo
 * @version 2016年7月13日 
 */

@Service
public class UserInfoServiceImpl implements UserInfoService{

	private @Resource UserInfoDao dao;
	
	@Override
	public UserInfoVo login(String email, String pwd) {
		UserInfo userInfo = dao.getUser(email);
		String password = MD5Util.encrypt(pwd);
		if (password.equals(userInfo.getPassword())) {
			return userInfo.copy();
		} else {
			return null;
		}
	}

	@Override
	public boolean register(UserInfo info) {
		String password = MD5Util.encrypt(info.getPassword());
		info.setPassword(password);
		return dao.register(info);
	}

	@Override
	public List<UserInfo> list() {
		return dao.list();
	}

}
