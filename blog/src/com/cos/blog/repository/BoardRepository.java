package com.cos.blog.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cos.blog.db.DBConn;
import com.cos.blog.dto.DetailResponseDto;
import com.cos.blog.model.Board;
import com.cos.blog.model.Users;

public class BoardRepository {
	
	private static final String TAG = "BoardRepository : ";
	private static BoardRepository instance = new BoardRepository();
	private BoardRepository() {}
		public static BoardRepository getInstance() {
		return instance;
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public int count() {
		final String SQL = "SELECT count(*) FROM board";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(TAG+"count : "+e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}

		return -1;
	}
	
	public int save(Board board) {
		final String SQL = "INSERT INTO BOARD(ID, USERID, TITLE, CONTENT, READCOUNT, CREATEDATE) VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE)";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			
			pstmt.setInt(1, board.getUserid());
			pstmt.setString(2, board.getTitle());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getReadCount());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "save : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		
		return -1;
	}
	
	public int update(Board board) {
		final String SQL = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE ID = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getId());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "update : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		
		return -1;
	}
	
	public int deleteById(int id) {
		final String SQL = "DELETE FROM BOARD WHERE ID = ?";
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setInt(1, id);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "delete : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt);
		}
		
		return -1;
	}
	
	public List<Board> findAll(int page) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT /*+ INDEX_DESC(BOARD SYS_C008824)*/ID, ");
		sb.append("USERID, TITLE, CONTENT, READCOUNT, CREATEDATE ");
		sb.append("FROM BOARD ");
		sb.append("OFFSET ? ROWS FETCH NEXT 3 ROWS ONLY");
		final String SQL = sb.toString();
		List<Board> boards = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, page*3);
			//while 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board(
						rs.getInt("id"),
						rs.getInt("userid"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("readCount"),
						rs.getTimestamp("createDate")
				);
				boards.add(board);
			}
			return boards;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	public List<Board> findAll() {
		final String SQL = "SELECT * FROM BOARD ORDER BY ID DESC";
		List<Board> boards = new ArrayList<>();
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			
			//while 돌려서 rs -> java오브젝트에 집어넣기
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board(
						rs.getInt("id"),
						rs.getInt("userid"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getInt("readCount"),
						rs.getTimestamp("createDate")
				);
				boards.add(board);
			}
			return boards;
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findAll : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		
		return null;
	}
	
	public DetailResponseDto findById(int id) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT B.ID, B.USERID, B.TITLE, B.CONTENT, B.READCOUNT, B.CREATEDATE, U.USERNAME ");
		sb.append("FROM BOARD B INNER JOIN USERS U ");
		sb.append("ON B.USERID = U.ID ");
		sb.append("WHERE B.ID = ?");
		final String SQL = sb.toString();
		DetailResponseDto dto = null;
		
		try {
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(SQL);
			//물음표 완성하기
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
			//if 돌려서 rs -> java오브젝트에 집어넣기
			if(rs.next()) {
				dto = new DetailResponseDto();
				Board board = Board.builder()
						.id(rs.getInt(1))
						.userid(rs.getInt(2))
						.title(rs.getString(3))
						.content(rs.getString(4))
						.readCount(rs.getInt(5))
						.createDate(rs.getTimestamp(6))
						.build();
					dto.setBoard(board);
					dto.setUsername(rs.getString(7));
			}
			return dto;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(TAG + "findById : " + e.getMessage());
		} finally {
			DBConn.close(conn, pstmt, rs);
		}
		
		return null;
	}
}
