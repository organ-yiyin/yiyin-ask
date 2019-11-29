package com.yiyn.ask.xcx.center.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.xcx.center.dao.impl.DistributorsDaoImpl;
import com.yiyn.ask.xcx.center.po.DistributorsPo;

@Service
/**
 * 生成渠道商公众号和小程序码链接
 * @author tupz
 *
 */
public class DistributorsService {
   @Autowired
   private DistributorsDaoImpl distributorsDao;
   
   public List<DistributorsPo> findDisList() throws Exception{
	   return distributorsDao.findDisList();
   }
   
   public DistributorsPo findDisInfo(String id) throws Exception{
	   return distributorsDao.findDisInfo(id);
   }
   
   public void insert(DistributorsPo p)throws Exception{
	   distributorsDao.insert(p);
   }
   
   public void updateById(DistributorsPo p)throws Exception{
	   distributorsDao.updateById(p);
   }
}
