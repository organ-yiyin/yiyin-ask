package com.yiyn.ask.sys.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.yiyn.ask.base.constants.AuthorityCode;
import com.yiyn.ask.base.utils.DwzResponseForm;
import com.yiyn.ask.sys.convert.UserConvert;
import com.yiyn.ask.sys.dao.impl.UserAuthorityDaoImpl;
import com.yiyn.ask.sys.dao.impl.UserDaoImpl;
import com.yiyn.ask.sys.form.UserForm;
import com.yiyn.ask.sys.po.UserAuthorityPo;
import com.yiyn.ask.sys.po.UserPo;

@Controller
@RequestMapping("/sys/user/authority")
public class UserAuthorityController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public static final String FOLDER_PATH = "/yiyn/sys";
	
	public static final String URL_PATH_PREFIX = "/sys/user/authority";
	
	@Resource(name="userDao")
	private UserDaoImpl userDao;
	
	@Autowired
	private UserAuthorityDaoImpl userAuthorityDao;
	
	@RequestMapping(value = "/forwardDetails.do", method = RequestMethod.GET)
	public ModelAndView forwardDetails(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id") Long id) throws Exception {
		
		logger.info("forwardDetails");
		
		UserPo userPo = this.userDao.findById(id);
		UserForm userForm = UserConvert.convertToForm(userPo);
		List<UserAuthorityPo> userAuthorityPos = this.userAuthorityDao.findByUserId(id);

		ModelAndView mv = new ModelAndView(FOLDER_PATH + "/userAuthorityDetails.jsp");
		mv.addObject("info", userForm);
		mv.addObject("userAuthorityPos", userAuthorityPos);
		
		return mv;
	}
	
	@RequestMapping(value = "/save.do", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	@Transactional
	public String save(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("id") Long id) throws Exception {
		
		logger.info("save");
		UserPo userPo = this.userDao.findById(id);
		if(AuthorityCode.isSuperAdmin(userPo.getUser_no())) {
			return new Gson().toJson(DwzResponseForm.createFailResponseForm("不能修改super_admin帐号的权限控制"));
		}
		
		String[] authItems = request.getParameterValues("authItem");
		
		this.userAuthorityDao.deleteByUserId(id);
		for(String item : authItems) {
			UserAuthorityPo po = new UserAuthorityPo();
			po.setUser_id(id);
			po.setAuthority_code(item);
			
			userAuthorityDao.save(po);
		}

		return new Gson().toJson(DwzResponseForm.createCloseCurrentResponseForm());
	}
	
}
