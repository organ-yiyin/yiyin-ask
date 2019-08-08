package com.yiyn.ask.xcx.account.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.xcx.user.dao.impl.UserDaoImpl;
import com.yiyn.ask.xcx.user.po.UserPo;

@Service
/**
 * 快捷回复
 * @author tupz
 *
 */
public class AccountService {
   @Autowired
   private UserDaoImpl userBDao;
   
   public UserPo findByUserno(String userno) throws Exception{
	   return userBDao.findByUserno(userno);
   }
   
   public void insetUser(UserPo p) throws Exception{
	   userBDao.insert(p);
   }
}
