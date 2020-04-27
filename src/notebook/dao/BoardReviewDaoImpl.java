package notebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import notebook.domain.BoardReview;
import notebook.exception.CannotModifyException;
import notebook.exception.NotFoundException;
import notebook.util.DbUtil;

public class BoardReviewDaoImpl implements BoardReviewDao {
	
	@Override
	public List<BoardReview> selectByUserId(String userId) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "select * from board_review where user_id=?";
		List<BoardReview> list = new ArrayList<BoardReview>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int reviewNo = rs.getInt("review_no");
				String imgName = rs.getString("img_name");
				String createDate = rs.getString("create_date");
				String content = rs.getString("content");
				String serialNum = rs.getString("serial_num");
				int grade = rs.getInt("grade");
				
				list.add(new BoardReview(reviewNo, userId, imgName, createDate, content, serialNum, grade));
			}

		}finally {
			DbUtil.dbClose(con, ps, rs);
		}

		return list;
	}


	@Override
	public List<BoardReview> selectBySerialNum(String serialNum) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from board_review where serialnum=?";
		List<BoardReview> list = new ArrayList<BoardReview>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, serialNum);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int reviewNo = rs.getInt("review_no");
				String userId = rs.getString("user_id");
				String imgName = rs.getString("img_name");
				String createDate = rs.getString("create_date");
				String content = rs.getString("content");
				int grade = rs.getInt("grade");
				
				list.add(new BoardReview(reviewNo, userId, imgName, createDate, content, serialNum, grade));
			}
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public int update(BoardReview review) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE board_review SET create_date=sysdate, content=?";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, review.getContent());
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}
	
	public int updateReviewAndProductGrade(BoardReview review) throws SQLException, CannotModifyException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con =DbUtil.getConnection();
			con.setAutoCommit(false);
			
			//���� serialnum�� �����Ѹ����� ������ ã�Ƴ��� reviewcnt�� ��´�.
			//grade�� ��� �հ踦 sum�� ��´�.
			String sql = "select grade from board_review where serialnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, review.getSerialNum());
			rs = ps.executeQuery();
			
			int sum = 0;
			int reviewcnt = 0;
				while(rs.next()) {
					int grade = rs.getInt("grade");
					sum = sum + grade;
					reviewcnt++;
					
				}
				ps.close();
				rs.close();
			
			//insertReview
			sql = "UPDATE board_review SET create_date=sysdate, content=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, review.getContent());
			result = ps.executeUpdate();
			if(result == 0 ) {
				con.rollback();
				throw new CannotModifyException("���並 ������ �� �����ϴ�.");
			}
			
			//update product grade
			sql = "UPDATE product SET grade = ?";
			ps = con.prepareStatement(sql);
			int mygrade = review.getGrade();
			int refreshgrade = (mygrade + sum)/(reviewcnt+1);
			ps.setInt(1, refreshgrade);
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new CannotModifyException("cannot modify product's grade");
			}
			
			con.commit();
		
			
		}finally {
			DbUtil.dbClose(con, ps, rs);
			
		}
		
		return result;
	}

	@Override
	public int delete(int reviewNo) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE board_review WHERE review_no = ?";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewNo);
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}
	
	public int deleteAndUpdateProductGrade(int reviewNo) throws SQLException, NotFoundException, CannotModifyException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			
			con =DbUtil.getConnection();
			con.setAutoCommit(false);
		
			//reviewNo�� serialNum�� ã�´�.
			String sql = "select serialnum from board_review where review_no = ?";
			String serialNum = null ;
			int mygrade = 0;
			
			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewNo);
			rs = ps.executeQuery();
			if(rs.next()) {
				serialNum = rs.getString("serialnum");
				mygrade = rs.getInt("grade");
				
			}
			ps.close();
			rs.close();
			
			//serialnum�� �����Ѹ����� ������ ã�Ƴ��� reviewcnt�� ��´�.
			//grade�� ��� �հ踦 sum�� ��´�.
			sql = "select grade from board_review where serialnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, serialNum);
			rs = ps.executeQuery();
			
			int sum = 0;
			int reviewcnt = 0;
				while(rs.next()) {
					int grade = rs.getInt("grade");
					sum = sum + grade;
					reviewcnt++;
					
				}
				ps.close();
				rs.close();
				
			sql = "DELETE board_review WHERE review_no = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, reviewNo);
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new NotFoundException("���� �Ұ��� ");
			}
			ps.close();
			
			//update product grade
			sql = "UPDATE product SET grade = ?";
			ps = con.prepareStatement(sql);
			int refreshgrade = (sum-mygrade)/(reviewcnt);
			ps.setInt(1, refreshgrade);
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new CannotModifyException("cannot modify grade");
			}
			
			con.commit();
		
			
		}finally {
			DbUtil.dbClose(con, ps, rs);
			
		}
		
		return result;
		
	
	
	}

	@Override
	public int insert(BoardReview review) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		
		String sql = "INSERT INTO board_review(review_no, user_id, serialnum, img_name, content, grade) VALUES(seq_board_review.NEXTVAL, ?, ?, ?, ?, ?)";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, review.getUserId());
			ps.setString(2, review.getSerialNum());
			ps.setString(3, review.getImgName());
			ps.setString(4, review.getContent());
			ps.setInt(5, review.getGrade());
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}
	
	
	
	@Override
	public int insertAndUpdateProductGrade(BoardReview review) throws SQLException, NotFoundException, CannotModifyException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con =DbUtil.getConnection();
			con.setAutoCommit(false);
			
			//���� serialnum�� �����Ѹ����� ������ ã�Ƴ��� reviewcnt�� ��´�.
			//grade�� ��� �հ踦 sum�� ��´�.
			String sql = "select grade from board_review where serialnum=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, review.getSerialNum());
			rs = ps.executeQuery();
			
			int sum = 0;
			int reviewcnt = 0;
				while(rs.next()) {
					int grade = rs.getInt("grade");
					sum = sum + grade;
					reviewcnt++;
					
				}
				ps.close();
				rs.close();
			
			//insertReview
			sql = "INSERT INTO board_review(review_no, user_id, serialnum, img_name, content, grade) VALUES(seq_board_review.NEXTVAL, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, review.getUserId());
			ps.setString(2, review.getSerialNum());
			ps.setString(3, review.getImgName());
			ps.setString(4, review.getContent());
			ps.setInt(5, review.getGrade());
			int newgrade = review.getGrade();
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new SQLException("insert fail");
			}
			ps.close();
			

			
			//update product grade
			sql = "UPDATE product SET grade = ?";
			ps = con.prepareStatement(sql);
			int refreshgrade = (newgrade + sum)/(reviewcnt+1);
			ps.setInt(1, refreshgrade);
			result = ps.executeUpdate();
			if(result == 0) {
				con.rollback();
				throw new CannotModifyException("cannot modify grade");
			}
			
			con.commit();
		
			
		}finally {
			DbUtil.dbClose(con, ps, rs);
			
		}
		
		return result;
	}

}
