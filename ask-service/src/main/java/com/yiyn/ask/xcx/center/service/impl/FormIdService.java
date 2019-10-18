package com.yiyn.ask.xcx.center.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.xcx.center.dao.impl.FormIdDaoImpl;
import com.yiyn.ask.xcx.center.po.FormIdPo;

@Service
/**
 * formid获取，发送短信用
 * @author tupz
 *
 */
public class FormIdService {
   @Autowired
   private FormIdDaoImpl formIdDao;
   
   public FormIdPo getFormId(String rel_user) throws Exception{
	   return formIdDao.getFormId(rel_user);
   }
   
   public void insert(FormIdPo p)throws Exception{
	   formIdDao.insert(p);
   }
   
   // 变更发送次数
   public void updateById(FormIdPo p)throws Exception{
	   formIdDao.updateById(p);
   }
   
   // 直接删除id
   public void delForm(FormIdPo p)throws Exception{
	   formIdDao.deleteById(p.getId());
   }
}
