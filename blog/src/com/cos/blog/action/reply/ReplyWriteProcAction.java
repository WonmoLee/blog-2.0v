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

public class ReplyWriteProcAction implements Action{
	
	//request.getParameter 못받음.
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// application/json
		BufferedReader br = request.getReader();
		StringBuffer sb = new StringBuffer();
		String input = null;
		while((input = br.readLine()) != null) {
			sb.append(input);
		}
		System.out.println("맞니" +sb.toString());
		Gson gson = new Gson();
		Reply reply = gson.fromJson(sb.toString(), Reply.class);
		System.out.println("reply.getUserid() : " + reply.getUserid() + "reply.getBoardid()" + reply.getBoardid());
		
		// ReplyRepository 연결 - save(reply)
		ReplyRepository replyRepository = ReplyRepository.getInstance();
		int result = replyRepository.save(reply);
		// save 성공하면 1, 실패하면0, -1
		if(result == 1) {
			
			Script.outText(result + "", response);
			
		} else {
			
			Script.outText("댓글 등록에 실패하였습니다.", response);
			
		}
		// Script.outText() 응답
	}
}
