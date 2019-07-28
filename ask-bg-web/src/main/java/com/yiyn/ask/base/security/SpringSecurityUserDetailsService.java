package com.yiyn.ask.base.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yiyn.ask.sys.dao.impl.AuthorityDaoImpl;
import com.yiyn.ask.sys.dao.impl.UserDaoImpl;
import com.yiyn.ask.sys.po.AuthorityPo;
import com.yiyn.ask.sys.po.UserPo;

/**
 * 
 * 
 * @author eleven.li
 *
 */
public class SpringSecurityUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDaoImpl uerDao;
	
	@Autowired
	private AuthorityDaoImpl authorityDao;
	
	@Override
	public SpingSecurityUserBo loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
		SpingSecurityUserBo bo = null;
		try {
			UserPo po = uerDao.findByUsername(username);
			if(po == null){
				return null;
			}
			
			List<AuthorityPo> bos = authorityDao.findAuthByUserd(po.getUser_id());
			bo = new SpingSecurityUserBo(po, bos, new ArrayList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bo;
	}

}
