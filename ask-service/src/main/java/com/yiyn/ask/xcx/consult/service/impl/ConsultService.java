package com.yiyn.ask.xcx.consult.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.xcx.consult.dao.impl.ConsultDaoImpl;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultLogDaoImpl;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultProcessDaoImpl;
import com.yiyn.ask.xcx.consult.po.ConsultLogPo;
import com.yiyn.ask.xcx.consult.po.ConsultPo;
import com.yiyn.ask.xcx.consult.po.ConsultProcessPo;

@Service
public class ConsultService {
   @Autowired
   private ConsultDaoImpl consultDao;
   
   @Autowired
   private ConsultProcessDaoImpl consultProcessDao;
   
   @Autowired
   private ConsultLogDaoImpl consultLogDao;
   
   public Map<String,Object> getConsultList(Map<String,Object> m) throws Exception{
	   Map<String,Object> result = new HashMap<String,Object>();
	   
	   String type = String.valueOf(m.get("type"));
	   //分为未完成和已完成
	   List<ConsultPo> consultList= consultDao.getConsultList(m);
	   if(consultList !=null && consultList.size()!=0){
		   List<ConsultPo> yList = new ArrayList<ConsultPo>();
		   List<ConsultPo> wList = new ArrayList<ConsultPo>();
		   for(ConsultPo cPo:consultList){
			   String status = cPo.getStatus();
			   
			   // 用户端
			   if("user".equals(type)){
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
   
   /**
    * 订单状态的变换
    * @param p
    * @throws Exception
    */
   public void updConsult(ConsultPo p) throws Exception{
	   consultDao.updateStatus(p);
   }
   
   /**
    * 咨询单操作日志--比如支付，退单，订单结束啊，等
    * @param p
    * @throws Exception
    */
   public void insConsultLog(ConsultLogPo p) throws Exception{
	   consultLogDao.insert(p);
   }
}
