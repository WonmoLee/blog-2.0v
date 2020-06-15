package com.cos.blog.action.reply;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.model.Reply;
import com.cos.blog.repository.ReplyRepository;
import com.cos.blog.util.Script;
import com.google.gson.Gson;

public class ReplyDeleteProcAction implements Action{
	
	//request.getParameter 못받음.
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(request.getParameter("replyId") == null ||
				request.getParameter("replyId").equals("")) {
			return;
		}
			
		int replyId = Integer.parseInt(request.getParameter("replyId"));
		System.out.println("ReplyDeleteProcAction : replyId"+replyId);
		
		ReplyRepository replyRepository =
				ReplyRepository.getInstance();
		int result = replyRepository.deleteById(replyId);
		System.out.println("result : " + result);
		Script.outText(result+"", response);
	}
}
