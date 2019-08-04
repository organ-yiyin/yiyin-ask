package com.yiyn.ask.base.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yiyn.ask.sys.dao.impl.UserBDaoImpl;
import com.yiyn.ask.sys.po.UserBPo;

/**
 * 
 * 
 * @author eleven.li
 *
 */
public class SpringSecurityUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserBDaoImpl uerBDao;
	
	@Override
	public SpingSecurityUserBo loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		SpingSecurityUserBo bo = null;
		try {
			UserBPo po = uerBDao.findByUserNo(username);
			if(po == null){
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
