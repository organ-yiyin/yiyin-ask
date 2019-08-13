package com.yiyn.ask.xcx.account.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.xcx.account.dao.impl.AccountDaoImpl;
import com.yiyn.ask.xcx.account.dao.impl.AccountFlowDaoImpl;
import com.yiyn.ask.xcx.account.po.AccountFlowPo;
import com.yiyn.ask.xcx.account.po.AccountPo;

@Service
/**
 * 快捷回复
 * @author tupz
 *
 */
public class AccountService {
   @Autowired
   private AccountDaoImpl accountDaoImpl;
   
   @Autowired
   private AccountFlowDaoImpl AccountflowDaoImpl;
   
   public AccountPo getAccountInfo(String userno) throws Exception{
	   return accountDaoImpl.getAccountInfo(userno);
   }
   
   public void insetAccount(AccountFlowPo p) throws Exception{
	   AccountflowDaoImpl.insert(p);
   }
}
