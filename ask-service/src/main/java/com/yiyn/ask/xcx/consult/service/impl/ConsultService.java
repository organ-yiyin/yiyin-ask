package com.yiyn.ask.xcx.consult.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yiyn.ask.base.constants.ConsultStatuEnum;
import com.yiyn.ask.base.constants.ProcessContentTypeEnum;
import com.yiyn.ask.base.constants.ProcessSendTypeEnum;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.xcx.account.dao.impl.AccountDaoImpl;
import com.yiyn.ask.xcx.account.dao.impl.AccountFlowDaoImpl;
import com.yiyn.ask.xcx.account.po.AccountFlowPo;
import com.yiyn.ask.xcx.account.po.AccountPo;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultDaoImpl;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultLogDaoImpl;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultProcessDaoImpl;
import com.yiyn.ask.xcx.consult.po.ConsultLogPo;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.po.ConsultProcessPo;
import com.yiyn.ask.xcx.user.dao.impl.UserEvalDaoImpl;
import com.yiyn.ask.xcx.user.dao.impl.UserTagDaoImpl;
import com.yiyn.ask.xcx.user.po.UserEvalPo;
import com.yiyn.ask.xcx.user.po.UserPo;
import com.yiyn.ask.xcx.user.po.UserTagPo;

@Service
public class ConsultService {
   @Autowired
   private ConsultDaoImpl consultDao;
   
   @Autowired
   private ConsultProcessDaoImpl consultProcessDao;
   
   @Autowired
   private ConsultLogDaoImpl consultLogDao;
   @Autowired
   private UserTagDaoImpl userTagDao;
   
   @Autowired
   private AccountDaoImpl accountDao;
   
   @Autowired
   private AccountFlowDaoImpl accountFlowDao;
   
   @Autowired
   private UserEvalDaoImpl userEvalDao;
   
   public Map<String,Object> getConsultList(Map<String,Object> m) throws Exception{
	   Map<String,Object> result = new HashMap<String,Object>();
	   
	   String type = String.valueOf(m.get("type"));
	   //分为未完成和已完成
	   List<ConsultPo> consultList= new ArrayList<ConsultPo>();
	   if("user".equals(type)){
		   consultList = consultDao.getConsultCList(m);
	   }else{
		   consultList = consultDao.getConsultList(m);
	   }
			   
	   if(consultList !=null && consultList.size()!=0){
		   List<ConsultPo> yList = new ArrayList<ConsultPo>();
		   List<ConsultPo> wList = new ArrayList<ConsultPo>();
		   for(ConsultPo cPo:consultList){
			   //根据用户
			   String status = cPo.getStatus();
			   // 用户端
			   if("user".equals(type)){
				   //用户端根据服务人员编号获取标签
				   String userno = cPo.getUser_b_no();
				   List<UserTagPo> tagList = userTagDao.getUserTagList(userno);
				   UserPo p = cPo.getUserPo();
				   p.setTagList(tagList);
				   cPo.setUserPo(p);
				   
				   // 已支付、申请退款、已回答的放到未完成里
				   if("2".equals(status) || "3".equals(status) || "5".equals(status)){
					   wList.add(cPo);
				   // 已退款、已结束的放到已完成里
				   }else if("4".equals(status) || "6".equals(status)){
					   yList.add(cPo);
				   }
			   }else{
				   // 已支付的和申请退款的放到未完成里
				   if("2".equals(status) || "3".equals(status)){
					   wList.add(cPo);
				   // 已退款、已回答、已结束的放到已完成里
				   }else if("4".equals(status) || "5".equals(status) || "6".equals(status)){
					   yList.add(cPo);
				   }
			   }
		   }
		   
		   if("user".equals(type)){
			   result.put("allList", consultList);
		   }
		   
		   result.put("wcount", wList.size());
		   result.put("ycount", yList.size());
		   result.put("wList", wList);
		   result.put("yList", yList);
	   }else{
		   result.put("wcount", 0);
		   result.put("ycount", 0);
	   }
	   
	   return result;
   }
   
   /**
    * 获取咨询基本信息
    * @param m
    * @return
    * @throws Exception
    */
   public ConsultPo getConsultInfo(String id) throws Exception{
	   return consultDao.getConsultInfo(id);
   }
   
   /**
    * 根据微信返回的订单号获取咨询单的基本信息
    * @param m
    * @return
    * @throws Exception
    */
   public ConsultPo getConsultByOdd_Num(String odd_num) throws Exception{
	   return consultDao.getConsultByOdd_Num(odd_num);
   }
   
   /**
    * C端点击咨询单获取咨询基本信息
    * @param m
    * @return
    * @throws Exception
    */
   public ConsultPo getConsultInfoByC(String id) throws Exception{
	   ConsultPo cPo = consultDao.getConsultInfo(id);
	   
	   String userno = cPo.getUser_b_no();
	   List<UserTagPo> tagList = userTagDao.getUserTagList(userno);
	   UserPo p = cPo.getUserPo();
	   p.setTagList(tagList);
	   cPo.setUserPo(p);
	   return cPo;
   }
   
   /**
    * 获取咨询进程列表
    * @param m
    * @return
    * @throws Exception
    */
   public List<ConsultProcessPo> getConsultProcessList(String id) throws Exception{
	   return consultProcessDao.getConsultProcessList(id);
   }
   
   public void insConsultProcess(ConsultProcessPo p) throws Exception{
	   consultProcessDao.insert(p);
   }
   
   public void updConsultStatus(ConsultPo updP) throws Exception{
	   // 更新状态为已回答
	   consultDao.updateStatus(updP);
	   // 插入操作日志表
	   ConsultLogPo t = new ConsultLogPo();
	   t.setLog_type(updP.getStatus());
	   t.setLog_desc("服务人员回答问题后状态变更为已回答");
	   t.setConsult_id(String.valueOf(updP.getId()));
	   t.setCreated_by(updP.getUpdated_by());
	   consultLogDao.insert(t);
   }
   
   /**
    * 订单状态的变换
    * @param p
    * @throws Exception
    */
   public void updConsult(ConsultPo p) throws Exception{
	   consultDao.updateStatus(p);
   }
   
   /**
    * 用户追问后更新追问次数以及状态为已支付
    * @param p
    * @throws Exception
    */
   @Transactional
   public void updConsultQuesNum(ConsultPo p) throws Exception{
	   consultDao.updConsult(p);
	   ConsultLogPo t = new ConsultLogPo();
	   t.setLog_type(p.getStatus());
	   t.setLog_desc("用户追问更新状态");
	   t.setConsult_id(String.valueOf(p.getId()));
	   t.setCreated_by("sheetAboutUser");
	   consultLogDao.insert(t);
   }
   
   /**
    * 咨询单操作日志--比如支付，退单，订单结束啊，等
    * @param p
    * @throws Exception
    */
   public void insConsultLog(ConsultLogPo p) throws Exception{
	   consultLogDao.insert(p);
   }
   
   /**
   * 咨询单初始化生成--待支付有效期为24小时
   * @param p
   * @throws Exception
   */
  public void insConsult(ConsultPo p) throws Exception{
	   consultDao.insert(p);
  }
  
  /**
   * 微信通知订单成功后更新信息
   * @param status
   * @param transaction_id
   * @param p
   * @return
   * @throws Exception
   */
  @Transactional(propagation=Propagation.REQUIRED) 
  public String handlePayTradeOrder(String status,String transaction_id,String end_time,ConsultPo p) throws Exception{
	  String re = "FAIL";
	  try{
		  // 更新咨询单状态
		  p.setStatus(status);
		  p.setUpdated_by("微信通知更新");
		  p.setPay_odd_num(transaction_id);
		  this.updConsult(p);
		  // 插入咨询单进程（包含文字，图片，视频等）
		  // 文字直接插入
		  ConsultProcessPo textP = new ConsultProcessPo();
		  textP.setConsultation_id(String.valueOf(p.getId()));
		  textP.setContent(p.getProblem_desc());
		  textP.setContent_type(ProcessContentTypeEnum.TEXT.getName());
		  textP.setSend_type(ProcessSendTypeEnum.CUSTOMER.getName());
		  
		  this.insConsultProcess(textP);
		  // 有图片和视频插入
		  if(!StringUtils.isEmptyString(p.getProblem_imgs())){
			  String[] imgs = p.getProblem_imgs().split(",");
			  for(String img:imgs){
				  ConsultProcessPo imgP = new ConsultProcessPo();
				  imgP.setConsultation_id(String.valueOf(p.getId()));
				  imgP.setContent(img);
				  imgP.setContent_type(ProcessContentTypeEnum.IMG.getName());
				  imgP.setSend_type(ProcessSendTypeEnum.CUSTOMER.getName());
				  
				  this.insConsultProcess(imgP);
			  }
		  }
		  
		  if(!StringUtils.isEmptyString(p.getProblem_video())){
			  String[] videos = p.getProblem_video().split(",");
			  for(String video:videos){
				  ConsultProcessPo imgP = new ConsultProcessPo();
				  imgP.setConsultation_id(String.valueOf(p.getId()));
				  imgP.setContent(video);
				  imgP.setContent_type(ProcessContentTypeEnum.video.getName());
				  imgP.setSend_type(ProcessSendTypeEnum.CUSTOMER.getName());
				  
				  this.insConsultProcess(imgP);
			  }
		  }
		  // 插入咨询单操作日志
		  ConsultLogPo logp = new ConsultLogPo();
		  logp.setLog_type(ConsultStatuEnum.PAY.getCode());
		  logp.setConsult_id(String.valueOf(p.getId()));
		  logp.setLog_desc("微信通知插入咨询单操作日志！");
		  this.insConsultLog(logp);
		  
		  // 插入账户流水，根据账户ID
		  // 先根据用户编号和id查找账户id
		  AccountPo account = accountDao.getAccountInfo(p.getUser_b_no());
		  AccountFlowPo flowP = new AccountFlowPo();
		  flowP.setAccount_id(account.getId());
		  flowP.setJournal_money(new Double(p.getPrice()));
		  flowP.setJournal_dir("1");// 1：流入，2：流出
		  flowP.setJournal_type("1");// 1：用户支付，2：提现：3：退款
		  flowP.setOrder_id(p.getOdd_num());
		  flowP.setPay_type("WXPAY");
		  flowP.setPay_time(end_time);
		  flowP.setJournal_remark("微信小程序支付通知插入");
		  flowP.setPay_status("1");//1:成功；2：待处理
		  flowP.setPay_channel_no("WXXCX");
		  
		  accountFlowDao.insert(flowP);
		  // 更新B端用户账户余额
		  account.setBalance(account.getBalance().add(new BigDecimal(p.getPrice())));
		  accountDao.updateById(account);
		  re = "SUCCESS";
	  }catch(Exception e){
		  e.printStackTrace();
	  }

	  return re;
 }
  
  public void insEval(UserEvalPo p) throws Exception {
	  userEvalDao.insert(p);
  }
}
