package com.yiyn.ask.base.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yiyn.ask.base.dao.BaseDao;
import com.yiyn.ask.base.po.AttachmentPo;

@Repository("attachmentDao")
public class AttachmentDaoImpl extends BaseDao<AttachmentPo> {
		
	public List<AttachmentPo> findAllByObject(Integer object_type, Long object_id) throws Exception{
		Map<String,Object> param = new HashMap<>();
		param.put("object_type", object_type);
		param.put("object_id", object_id);
		
		return this.getSqlSession().selectList(this.getNameStatement() + ".findAllByObject", param);
	}
	
	public Long save(AttachmentPo attachmentPo) throws Exception{
		if(attachmentPo.getId() == null) {
			this.insert(attachmentPo);
		}
		else {
			this.updateById(attachmentPo);
		}
		return attachmentPo.getId();
	}
	
	@Override
	public String getNameStatement() {
		return "base.attachment";
	}

}
