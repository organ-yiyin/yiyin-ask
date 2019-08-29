package com.yiyn.ask.base.security;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yiyn.ask.sys.dao.impl.UserDaoImpl;
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}

}
