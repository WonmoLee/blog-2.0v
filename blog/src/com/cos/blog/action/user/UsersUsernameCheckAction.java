package com.cos.blog.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cos.blog.action.Action;
import com.cos.blog.repository.UsersRepository;

public class UsersUsernameCheckAction implements Action{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
		UsersRepository usersRepository =
				UsersRepository.getInstance();
		int result = usersRepository.findByUsername(username);
		
		PrintWriter out = response.getWriter();
		out.print(result);  //println을 하면 출력시 뒤에 \n이 생략되어있어 비교가안되 오류가뜬다. 이거 조심
	}
}
