package com.yiyn.ask.xcx.center.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.xcx.center.dao.impl.CenterResponseDaoImpl;
import com.yiyn.ask.xcx.center.po.CenterResponsePo;

@Service
/**
 * 快捷回复
 * @author tupz
 *
 */
public class CenterResponseService {
   @Autowired
   private CenterResponseDaoImpl centerResponseDao;
   
   public List<CenterResponsePo> findResponseList(String userno) throws Exception{
	   return centerResponseDao.findResponseList(userno);
   }
   
   public void insetRes(CenterResponsePo p) throws Exception{
	   centerResponseDao.insert(p);
   }
   
   public void updRes(CenterResponsePo p) throws Exception{
	   centerResponseDao.updateById(p);
   }
   
   public void delRes(Long id) throws Exception{
	   centerResponseDao.deleteById(id);
   }
}
