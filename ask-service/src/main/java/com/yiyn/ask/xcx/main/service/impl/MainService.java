package com.yiyn.ask.xcx.main.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.xcx.main.dao.impl.BannerDaoImpl;
import com.yiyn.ask.xcx.main.dao.impl.DistributorsVisitDaoImpl;
import com.yiyn.ask.xcx.main.po.BannerPo;
import com.yiyn.ask.xcx.main.po.DistributorsVisitPo;

@Service
public class MainService {
   @Autowired
   private BannerDaoImpl bannerDaoImpl;
   
   @Autowired
   private DistributorsVisitDaoImpl distributorsVisitDaoImpl;
   
   public List<BannerPo> getBannerList() throws Exception{
	   return bannerDaoImpl.getBannerList();
   }
   
   public void insDis(DistributorsVisitPo t) throws Exception{
	   distributorsVisitDaoImpl.insert(t);
   }
}
