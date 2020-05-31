package com.lwm.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lwm.blog.db.DBConn;
import com.lwm.blog.model.Users;


public class UsersRepository {

	//syso 테스트할때 쓰는거
	private static final String TAG = "UsersRepository";
	//인스턴스화
	private static UsersRepository instance = new UsersRepository();
	//default 생성자
	private UsersRepository() {}
	//싱글톤
	public static UsersRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public Users findByUsernameAndPassword(String username, String password) {
		final String SQL = "SELECT id, username, email, address, userProfile, userRole, createDate FROM USERS WHERE username = ? AND password = ?";
		Users user = new Users();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			//물음표 완성하기
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			
			//if 돌려서 re -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new Users();
				
				user.setId(rs.getInt("id"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
