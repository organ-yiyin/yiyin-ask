package com.yiyn.ask.xcx.center.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yiyn.ask.xcx.center.dao.impl.CodeDaoImpl;
import com.yiyn.ask.xcx.center.po.CodePo;

@Service
/**
 * 系统代码
 * @author tupz
 *
 */
public class CodeService {
   @Autowired
   private CodeDaoImpl codeDao;
   
   public List<CodePo> findCodeList(String user_type) throws Exception{
	   return codeDao.findCodeList(user_type);
   }
}
