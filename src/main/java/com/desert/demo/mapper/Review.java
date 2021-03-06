package com.desert.demo.mapper;

import java.util.ArrayList;

import com.desert.demo.dto.DTOReview;

public interface Review {
	public int insertReview();
	public int modifyReview();
	public int deleteReview();
	public ArrayList<DTOReview> listReview();
	public DTOReview contentReview();
	public int uphit();
	
	public DTOReview replyView();
	public int insertReply();
	public int totalAmount();
	public int reviewCheck();
	public int buyCheck();

}
