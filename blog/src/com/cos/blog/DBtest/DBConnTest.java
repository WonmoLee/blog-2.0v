package com.cos.blog.DBtest;

import org.junit.Test;

import com.cos.blog.db.DBConn;

public class DBConnTest {

	@Test
	public void 데이터베이스_연결_테이스() {
		DBConn.getConnection();
	}
}