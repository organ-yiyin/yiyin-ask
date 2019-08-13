package com.yiyn.ask.xcx.user.service.impl;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.xcx.user.dao.impl.UserCDaoImpl;
import com.yiyn.ask.xcx.user.dao.impl.UserDaoImpl;
import com.yiyn.ask.xcx.user.dao.impl.UserEvalDaoImpl;
import com.yiyn.ask.xcx.user.dao.impl.UserTagDaoImpl;
import com.yiyn.ask.xcx.user.po.UserCPo;
import com.yiyn.ask.xcx.user.po.UserEvalPo;
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
   
   public UserPo findByUserno(String userno) throws Exception{
	   return userBDao.findByUserno(userno);
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
	   }
	   return reList;
   }
   
   // 个人首页用，资料较少
   public UserPo findUserInfo(String userno) throws Exception{
	   UserPo inP = userBDao.findUserInfo(userno);
	   //获取标签
	   inP.setTagList(userTagDao.getUserTagList(userno));
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
   
   public List<UserEvalPo> findUserEval(String userno) throws Exception{
	   return userEvalDao.findUserEval(userno);
   }
   
   public void insetUser(UserPo p) throws Exception{
	   userBDao.insert(p);
   }
   
   public void updInfo(UserPo p) throws Exception{
	   userBDao.updInfo(p);
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
}
