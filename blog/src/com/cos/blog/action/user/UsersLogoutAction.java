

package com.cos.blog.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cos.blog.action.Action;
import com.cos.blog.util.Script;

public class UsersLogoutAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 가져오기
		HttpSession session = request.getSession();
		//로그아웃 버튼 클릭시 로그인된 정보를 세션에서 날리기, 여기서 세션은 통의 세션이아닌 한 행에 대한 세션정보
		session.invalidate();
		
		//session.removeAttribute("principal"); //프린시펄만 날리기 근데 보통 세션 통째로 날린다.
		Script.href("로그아웃 성공", "index.jsp", response);
	}
}
