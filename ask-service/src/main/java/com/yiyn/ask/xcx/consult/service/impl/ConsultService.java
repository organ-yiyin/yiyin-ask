package com.yiyn.ask.xcx.consult.service.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import com.yiyn.ask.base.utils.DateUtils;
import com.yiyn.ask.base.utils.SMSUtils;
import com.yiyn.ask.base.utils.StringUtils;
import com.yiyn.ask.xcx.account.dao.impl.AccountDaoImpl;
import com.yiyn.ask.xcx.account.dao.impl.AccountFlowDaoImpl;
import com.yiyn.ask.xcx.account.po.AccountFlowPo;
import com.yiyn.ask.xcx.account.po.AccountPo;
import com.yiyn.ask.xcx.center.po.FormIdPo;
import com.yiyn.ask.xcx.center.service.impl.FormIdService;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultDaoImpl;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultLogDaoImpl;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultProcessDaoImpl;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultSheetRefDaoImpl;
import com.yiyn.ask.xcx.consult.po.ConsultLogPo;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.po.ConsultProcessPo;
import com.yiyn.ask.xcx.consult.po.ConsultSheetRefPo;
import com.yiyn.ask.xcx.user.dao.impl.UserDaoImpl;
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
   
   @Autowired
   private ConsultSheetRefDaoImpl consultSheetRefDao;
   
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
				   
				   // 待支付、已支付、申请退款、已回答的放到未完成里
				   if(ConsultStatuEnum.PAY_WAIT.getCode().equals(status) || ConsultStatuEnum.PAY.getCode().equals(status) 
						   || ConsultStatuEnum.REFUND_WAIT.getCode().equals(status) || ConsultStatuEnum.ANS.getCode().equals(status)){
					   wList.add(cPo);
				   // 已退款、已结束、超时退款的放到已完成里
				   }else if(ConsultStatuEnum.REFUND.getCode().equals(status) || ConsultStatuEnum.END.getCode().equals(status) 
						   || ConsultStatuEnum.EXPIRED_REFUND.getCode().equals(status)){
					   yList.add(cPo);
				   }
			   }else{
				   // 已结束的订单72小时内都有退单按钮
				   if(ConsultStatuEnum.END.getCode().equals(status) || ConsultStatuEnum.REFUND_WAIT.getCode().equals(status) 
						   || ConsultStatuEnum.REFUND.getCode().equals(status)){
					   Date updated_time = cPo.getUpdated_time();
					   // 日期加三天
					   Date upd_newTime = DateUtils.calculateDate(updated_time, 5, 3);
					   // 跟当前日期比较，小于0 则代表订单结束后3天比当前日期还小，说明订单已超时，sfktd设置为0
					   if(DateUtils.compareDateWithNow(upd_newTime) < 0){
						   cPo.setSfktd(0);
					   }else{
						   cPo.setSfktd(1);
					   }
				   }
				   
				   // 已支付、申请退款、已回答的放到未完成里
				   if(ConsultStatuEnum.PAY.getCode().equals(status) || 
						   ConsultStatuEnum.REFUND_WAIT.getCode().equals(status) || ConsultStatuEnum.ANS.getCode().equals(status)){
					   wList.add(cPo);
				   // 已退款、已结束、超时退款的放到已完成里
				   }else if(ConsultStatuEnum.REFUND.getCode().equals(status) || ConsultStatuEnum.END.getCode().equals(status) 
						   || ConsultStatuEnum.EXPIRED_REFUND.getCode().equals(status)){
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
	   List<ConsultProcessPo> re = consultProcessDao.getConsultProcessList(id);
	   
	   if(re != null && re.size() !=0){
		   boolean show = false;
		   Date compareTime = null;
		   String type = "";
		   for(int i =0;i<re.size();i++){
			   ConsultProcessPo r = re.get(i);
			   Date created_time = r.getCreated_time();
			   if(i == 0 || (!StringUtils.isEmptyString(type) && !type.equals(r.getSend_type()))){
				   show = true;
				   type = r.getSend_type(); // 发送人类型
			   }else{
				   // 如果是5分钟内的都不显示
				   Date date2 = DateUtils.calculateDate(created_time, 12, -5);
				   // 如果发送日期-5大于前面一条消息的时间，则需要显示时间
				   int k = DateUtils.compareDate(compareTime,date2);
				   if(k < 0){
					   show = true;
				   }
			   }
			   
			   compareTime = r.getCreated_time();
			   if(show){
				   r.setShowTime("1");// 显示时间
				   // 如果创建日期为今天，则不显示日期，只显示时分
				   if(DateUtils.format(created_time, "yyyy-MM-dd").equals(DateUtils.format(new Date(), "yyyy-MM-dd"))){
					   r.setCreated_time_format(DateUtils.format(created_time, "HH:mm"));
				   // 如果创建日期为昨日，则显示昨日 xx小时xx分
				   }else if(DateUtils.format(created_time, "yyyy-MM-dd").equals(DateUtils.format(DateUtils.calculateDate(new Date(), 5, -1), "yyyy-MM-dd"))){
					   r.setCreated_time_format("昨日 " + DateUtils.format(created_time, "HH:mm"));
				   // 否则显示
				   }else{
					   r.setCreated_time_format(DateUtils.format(created_time, DateUtils.DATE_CHN));
				   }
			   }
			   show = false;
		   }
	   }
	   
	   return re;
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
   * 咨询单初始化生成--待支付有效期为24小时--失效
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
		  p.setPay_time(DateUtils.getNowTime());
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
				  imgP.setContent_type(ProcessContentTypeEnum.VIDEO.getName());
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
  
  /**
  * 咨询单初始化生成--根据咨询单生成同步生成关联人信息
  * @param p
  * @throws Exception
  */
  public void insConsultSheetRef(ConsultSheetRefPo p) throws Exception{
	   consultSheetRefDao.insert(p);
  }
  
  /**
   * 获取咨询基本信息
   * @param m
   * @return
   * @throws Exception
   */
  public List<ConsultPo> getRefundConsult() throws Exception{
	   return consultDao.getRefundConsult();
  }
}
