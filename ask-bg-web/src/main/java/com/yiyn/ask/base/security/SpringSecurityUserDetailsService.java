package com.yiyn.ask.base.security;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yiyn.ask.sys.dao.impl.UserAuthorityDaoImpl;
import com.yiyn.ask.sys.dao.impl.UserDaoImpl;
import com.yiyn.ask.sys.po.UserAuthorityPo;
import com.yiyn.ask.sys.po.UserPo;

/**
 * 
 * 
 * @author eleven.li
 *
 */
public class SpringSecurityUserDetailsService implements UserDetailsService {
	
	@Resource(name="userDao")
	private UserDaoImpl uerDao;
	
	@Autowired
	private UserAuthorityDaoImpl userAuthorityDao;
	
	@Override
	public SpingSecurityUserBo loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		SpingSecurityUserBo bo = null;
		try {
			UserPo po = uerDao.findByUserNo(username);
			if(po == null || !"Y".equals(po.getEnabled())){
				return null;
			}
			bo = new SpingSecurityUserBo(po, new ArrayList());
			List<UserAuthorityPo> authoritys = userAuthorityDao.findByUserId(po.getId());
			bo.setUserAuthoritys(authoritys);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}

}
