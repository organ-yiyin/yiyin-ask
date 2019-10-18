package com.yiyn.ask.xcx.user.service.impl;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yiyn.ask.base.utils.DateUtils;
import com.yiyn.ask.xcx.consult.dao.impl.ConsultRefDaoImpl;
import com.yiyn.ask.xcx.consult.po.ConsultRefPo;
import com.yiyn.ask.xcx.user.dao.impl.UserCDaoImpl;
import com.yiyn.ask.xcx.user.dao.impl.UserColDaoImpl;
import com.yiyn.ask.xcx.user.dao.impl.UserDaoImpl;
import com.yiyn.ask.xcx.user.dao.impl.UserEvalDaoImpl;
import com.yiyn.ask.xcx.user.dao.impl.UserOrderSetDaoImpl;
import com.yiyn.ask.xcx.user.dao.impl.UserTagDaoImpl;
import com.yiyn.ask.xcx.user.po.UserCPo;
import com.yiyn.ask.xcx.user.po.UserColPo;
import com.yiyn.ask.xcx.user.po.UserEvalPo;
import com.yiyn.ask.xcx.user.po.UserOrderSetPo;
import com.yiyn.ask.xcx.user.po.UserPo;
import com.yiyn.ask.xcx.user.po.UserTagPo;

@Service
public class UserService {
   @Autowired
   private UserDaoImpl userBDao;
   
   @Autowired
   private UserTagDaoImpl userTagDao;
   
   @Autowired
   private UserEvalDaoImpl userEvalDao;
   
   @Autowired
   private UserCDaoImpl userCDao;
   
   @Autowired
   private UserColDaoImpl userColDao;
   
   @Autowired
   private ConsultRefDaoImpl csultRefDao;
   
   @Autowired
   private UserOrderSetDaoImpl userOrderSetDao;
   
   public UserPo findByUserno(String userno) throws Exception{
	   UserPo userP = userBDao.findByUserno(userno);
	   List<UserTagPo> tagList = userTagDao.getUserTagList(userno);
	   if(tagList != null && tagList.size() !=0){
		   userP.setTagList(tagList);
	   }
	   return userP;
   }
   
   /**
    * 大咖推荐
    * @return
    * @throws Exception
    */
   public List<UserPo> findRecommendList() throws Exception{
	   List<UserPo> reList= userBDao.findRecommendList();
	   // 标签插入
	   for(UserPo p:reList){
		   String user_no = p.getUser_no();
		   // 根据用户编号查找标签
		   List<UserTagPo> tagList = userTagDao.getUserTagList(user_no);
		   p.setTagList(tagList);
		   if(p.getSkilled() != null){
			   p.setSkilled(p.getSkilled().replace("；", "  "));
		   }
	   }
	   return reList;
   }
   
   /**
    * C端咨询师列表
    * @return
    * @throws Exception
    */
   public List<UserPo> getConsultList(Map<String,Object> m) throws Exception{
	   List<UserPo> reList= userBDao.getConsultList(m);
	   
	   // 标签插入
	   for(UserPo p:reList){
		   String user_no = p.getUser_no();
		   // 根据用户编号查找标签
		   List<UserTagPo> tagList = userTagDao.getUserTagList(user_no);
		   p.setTagList(tagList);
		   if(p.getSkilled() != null){
			   p.setSkilled(p.getSkilled().replace("；", "  "));
		   }
	   }
	   return reList;
   }
   
   /**
    * C端 关注列表
    * @return
    * @throws Exception
    */
   public List<UserPo> getCollectionConsultList(String userno) throws Exception{
	   List<UserPo> reList= userBDao.getCollectionConsultList(userno);
	   // 标签插入
	   for(UserPo p:reList){
		   String user_no = p.getUser_no();
		   // 根据用户编号查找标签
		   List<UserTagPo> tagList = userTagDao.getUserTagList(user_no);
		   p.setTagList(tagList);
		   if(p.getSkilled() != null){
			   p.setSkilled(p.getSkilled().replace("；", "  "));
		   }
	   }
	   return reList;
   }
   
   // B端个人首页用，资料较少
   public UserPo findUserInfo(String userno) throws Exception{
	   UserPo inP = userBDao.findUserInfo(userno);
	   //获取标签
	   if(inP != null){
		   inP.setTagList(userTagDao.getUserTagList(userno));
	   }
	   return inP;
   }
   
   /**
    * 个人资料用
    * @param userno
    * @return
    * @throws Exception
    */
   public UserPo getUserInfo(String userno) throws Exception{
	   UserPo inP = userBDao.findByUserno(userno);
	   //获取标签
	   inP.setTagList(userTagDao.getUserTagList(userno));
	   return inP;
   }
   
   public List<UserEvalPo> findUserEval(Map<String,String> m) throws Exception{
	   return userEvalDao.findUserEval(m);
   }
   
   public void insetUser(UserPo p) throws Exception{
	   userBDao.insert(p);
   }
   
   @Transactional
   public void updInfo(UserPo p) throws Exception{
	   userBDao.updInfo(p);
	   //插入接单日志
	   UserOrderSetPo insP = new UserOrderSetPo();
	   insP.setUser_no(p.getUser_no());
	   insP.setSet_type(p.getOrder_set());
	   userOrderSetDao.insert(insP);
   }
   
   public List<UserTagPo> getTagCodeList() throws Exception{
	   return userTagDao.getTagCodeList();
   }
   
   /**
    * 用户标签更新
    * @param p
    * @throws Exception
    */
   public void updUserTag(List<UserTagPo> pList,String user_no) throws Exception{
	   userTagDao.delTag(user_no);
	   userTagDao.insTag(pList);
   }
   
   // 根据微信openid获取是否用户信息
   public UserCPo findUserCInfo(String userno) throws Exception{
	   UserCPo inP = userCDao.findByUserno(userno);
	   return inP;
   }
   
   /**
    * 新增C端用户（客户）
    * @param p
    * @throws Exception
    */
   public void insCust(UserCPo p) throws Exception{
	   userCDao.insert(p);
   }
   
   /**
    * 更新C端用户手机号码
    * @param p
    * @throws Exception
    */
   public void updPhone(Map<String,Object> m) throws Exception{
	   userCDao.updByUser_no(m);
   }
   
   /**
    * 以下为C端用户关注信息
    * @param p
    * @throws Exception
    */
   public void insUserCol(UserColPo p) throws Exception{
	   userColDao.insert(p);
   }
   
   public void delUserCol(UserColPo p) throws Exception{
	   userColDao.delCol(p);
   }
   public boolean sfRel(UserColPo p) throws Exception{
	   UserColPo a = userColDao.sfRel(p);
	   
	   return a != null? true:false;
   }
   
   // 用户咨询人信息列表
   public List<ConsultRefPo> getRefList(Map<String,Object> m) throws Exception{
	   return csultRefDao.getRefList(m);
   }
   
   // 用户咨询人信息
   public ConsultRefPo getRefInfo(String id) throws Exception{
	   return csultRefDao.getRefInfo(id);
   }
   
   // 用户咨询人信息--详情
   public ConsultRefPo getRefDetail(String id) throws Exception{
	   ConsultRefPo ref = csultRefDao.getRefInfo(id);
	   //计算妈妈年龄以及宝宝年龄
	   // 先算妈妈年龄
	   Date bith_m = DateUtils.parse(ref.getBirthday_m(),"yyyy-MM-dd");
	   ref.setAge_m(String.valueOf(DateUtils.getAge(bith_m)));
	   // 宝宝年龄按算法：分三种（普通）年龄、纠正年龄、纠正胎龄
	   ref.setAge_b(getBirth_b(ref));
	   return ref;
   }
   
   // 用户咨询人修改
   public void updRef(ConsultRefPo p) throws Exception{
	   csultRefDao.updateById(getRefMsg(p));
   }
   
   // 用户咨询人新增
   public void insRef(ConsultRefPo p) throws Exception{
	   csultRefDao.insert(getRefMsg(p));
   }
   
   // 根据咨询单id查询
//   public UserPo findUserByConsultId(String id) throws Exception{
//	   return userBDao.findById(new Long(id));
//   }
   
   /**
    * 根据妈妈出生日期算出年龄，根据宝宝出生日期，预产期判断宝宝是否早产儿，并得出出生孕期
    * @param initP
    * @return
    */
   private ConsultRefPo getRefMsg(ConsultRefPo initP){
	   // 算出宝宝是否早产儿，以及出生孕周
	   String t1 = initP.getBirthday_b();// 出生日期
	   String t2 = initP.getEdc_b(); // 预产期
	   
	   String t3 = DateUtils.format(new Date(), "yyyy-MM-dd"); // 当前日期
	   try{
		   int t4 = DateUtils.getDaysBetweenDates(t1, t2);
		   
		   // 如果出生日期比预产期早21天，则判断为早产儿
		   if(t4 > 21){
			   initP.setPremie("1");
			   int t5 = 40*7 - t4;
			   
			   if(!DateUtils.compareStrDate(t3,t2)){
				   initP.setBirth_week(t5/7 + "周" + t5%7 + "天");
			   }else{
				   // 若T5 > 28周，且t3-t2 <= 24月    或者   T5 <= 28周，且T3-T2 <= 36月
				   if(((t5 > 28*7) && DateUtils.getMonthDiff(t3,t2) <=24) || ((t5 <= 28*7) && 
						   DateUtils.getMonthDiff(t3,t2) <=36 )){
					   initP.setBirth_week(t5/7 + "周" + t5%7 + "天");
				   }else{
					   int t10 = DateUtils.getDaysBetweenDates(t2, t1);
					   //计算出生孕周
					   String birth_week = (t10 + 280)/7 + "周" + (t10 + 280)%7 + "天";
					   initP.setBirth_week(birth_week);
				   }
			   }
		   }else{
			   int t10 = DateUtils.getDaysBetweenDates(t2, t1);
			   //计算出生孕周
			   String birth_week = (t10 + 280)/7 + "周" + (t10 + 280)%7 + "天";
			   initP.setBirth_week(birth_week);
		   }
   	   }catch(Exception e){
   		   e.printStackTrace();
   	   }
	   return initP;
   }
   
   /**
    * 宝宝年龄算法
    * @param initP
    * @return
    */
   private String getBirth_b(ConsultRefPo initP){
	   String age = "";
	   // 算出宝宝是否早产儿，以及出生孕周
	   String t1 = initP.getBirthday_b();// 出生日期
	   String t2 = initP.getEdc_b(); // 预产期
	   
	   String t3 = DateUtils.format(new Date(), "yyyy-MM-dd"); // 当前日期
	   
	   String t31 = DateUtils.format(DateUtils.calculateDate(new Date(),Calendar.DAY_OF_MONTH,1), "yyyy-MM-dd"); // 当前日期 + 1
	   try{
		   int t4 = DateUtils.getDaysBetweenDates(t1, t2);
		   
		   // 如果出生日期比预产期早21天，则判断为早产儿
		   if(t4 > 21){
			   int t5 = 40*7 - t4;
			   // t3 < t2 
			   if(!DateUtils.compareStrDate(t3,t2)){
				   //输出纠正胎龄(T2-T3+T5)  格式为x周x天
				   int tt = DateUtils.getDaysBetweenDates(t3, t2) + t5;
				   
				   age = "纠正胎龄" + tt/7 + "周" + tt%7 + "天";
			   }else{
				   // 若T5 > 28周，且t3-t2 <= 24月    或者   T5 <= 28周，且T3-T2 <= 36月
				   if(((t5 > 28*7) && DateUtils.getMonthDiff(t3,t2) <=24) || ((t5 <= 28*7) && 
						   DateUtils.getMonthDiff(t3,t2) <=36 )){
					   age = "纠正年龄" + DateUtils.getAgeDetail(t2,t3);
				   }else{
					   age = DateUtils.getAgeDetail(t1,t31);
				   }
			   }
		   }else{
			   age = DateUtils.getAgeDetail(t1,t31);
		   }
   	   }catch(Exception e){
   		   e.printStackTrace();
   	   }
	   
	   return age;
   }
   
   public static void main(String[] args){
	   String t2 = "2019-08-08";
	   String t1 = "2019-07-10";
	   int t10 = DateUtils.getDaysBetweenDates(t2, t1);
	   
	   //计算出生孕周
	   String birth_week = (t10 + 280)/7 + "周" + (t10 + 280)%7 + "天";
	   
	   System.out.println(birth_week);
   }
}
