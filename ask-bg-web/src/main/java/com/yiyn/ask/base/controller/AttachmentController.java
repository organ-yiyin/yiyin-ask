package com.yiyn.ask.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.yiyn.ask.base.constants.AttachmenetBucketEnum;
import com.yiyn.ask.base.utils.DwzResponseForm;
import com.yiyn.ask.base.utils.OSSClientUtils;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private OSSClientUtils ossclientUtils;
	
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	@ResponseBody
	public String uploadAttachment(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("head_image_file") MultipartFile head_image_file, 
			@RequestParam("attachment_bucket") Integer attachment_bucket) throws Exception {
		
		logger.info("uploadAttachment");
		
		if(head_image_file.getInputStream().available() > 1000 * 2000){
			DwzResponseForm rf = DwzResponseForm.createFailResponseForm("文件大小不能超过2000K");
			return new Gson().toJson(rf);
		}
		
		AttachmenetBucketEnum findAttachmenetBucket = AttachmenetBucketEnum.findAttachmenetBucketByCode(attachment_bucket);
		String fileUrl = ossclientUtils.uploadFile(findAttachmenetBucket.getFolder(), head_image_file.getInputStream(), 
				head_image_file.getOriginalFilename());
		
		DwzResponseForm rf = DwzResponseForm.createSuccessResponseForm();
		rf.setData(fileUrl);
		
		return new Gson().toJson(rf); 
	}
	
	
}
