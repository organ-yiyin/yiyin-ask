package com.yiyn.ask.order.form;

import com.yiyn.ask.base.constants.CommentsStarEnum;
import com.yiyn.ask.base.utils.PaginationUtils;

public class CommentsManagementForm extends PaginationUtils {
	
	private CommentsStarEnum[] commentsStars = CommentsStarEnum.values();

	public CommentsStarEnum[] getCommentsStars() {
		return commentsStars;
	}

	public void setCommentsStars(CommentsStarEnum[] commentsStars) {
		this.commentsStars = commentsStars;
	}
	
}
