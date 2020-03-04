package com.yiyn.ask.xcx.account.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiyn.ask.xcx.account.dao.impl.AccountDaoImpl;
import com.yiyn.ask.xcx.account.dao.impl.AccountFlowDaoImpl;
import com.yiyn.ask.xcx.account.dao.impl.AccountStatDaoImpl;
import com.yiyn.ask.xcx.account.dao.impl.AccountWithDrawDaoImpl;
import com.yiyn.ask.xcx.account.po.AccountFlowPo;
import com.yiyn.ask.xcx.account.po.AccountPo;
import com.yiyn.ask.xcx.account.po.AccountStatPo;
import com.yiyn.ask.xcx.account.po.AccountWithDrawPo;

@Service
/**
 * 账户
 * @author tupz
 *
 */
public class AccountService {
   @Autowired
   private AccountDaoImpl accountDao;
   
   @Autowired
   private AccountFlowDaoImpl accountflowDao;
   
   @Autowired
   private AccountStatDaoImpl accountStatDao;
   
   @Autowired
   private AccountWithDrawDaoImpl accountWithDrawDao;
   
   /**
    * 查找账户信息
    * @param userno
    * @return
    * @throws Exception
    */
   public AccountPo getAccountInfo(String userno) throws Exception{
	   return accountDao.getAccountInfo(userno);
   }
   
   /**
    * 查找账户信息
    * @param userno
    * @return
    * @throws Exception
    */
   public AccountPo getAccountInfoByUserNo(String userno) throws Exception{
	   return accountDao.getAccountInfoByUserNo(userno);
   }
   
   /**
    * 查找月统计信息
    * @param userno
    * @return
    * @throws Exception
    */
   public List<AccountStatPo> getAcountStatM(Map<String,Object> m) throws Exception{
	   List<AccountStatPo> p = accountStatDao.getAcountStatM(m);
	   if(p != null && p.size() !=0){
		   for(AccountStatPo statP:p){
			   String stat_m = statP.getStat_month();
			   String stat_m_f = "";
			   
			   if("0".equals(stat_m.substring(5, 6))){
				   stat_m_f = stat_m.substring(6, 7);
			   }else{
				   stat_m_f = stat_m.substring(5, 7);
			   }
			   statP.setStat_month_f(stat_m_f);
		   }
	   }
	   return p;
   }
   
   /**
    * 查找收益流水表信息
    * @param userno
    * @return
    * @throws Exception
    */
   public List<AccountFlowPo> findAccountFlowList(Map<String,Object> m) throws Exception{
	   return accountflowDao.findAccountFlowList(m);
   }
   
   /*
    * 插入提现记录表 并更新可提现金额
    */
   @Transactional
   public void insetAccountWithDraw(AccountWithDrawPo p) throws Exception{
	   accountWithDrawDao.insert(p);
	   // 更新账户表的可提现金额
	   AccountPo updP = new AccountPo();
	   updP.setId(new Long(p.getAccount_id()));
	   accountDao.updWithDraw(updP);
   }
   
   public void updateByIdAfterCancel(AccountPo p) throws Exception{
	   accountDao.updateByIdAfterCancel(p);
   }
   
   public void insAccountFlow(AccountFlowPo flowP) throws Exception{
	   accountflowDao.insert(flowP);
   }
}
