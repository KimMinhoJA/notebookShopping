package notebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import notebook.domain.BoardReview;
import notebook.util.DbUtil;

public class BoardReviewDaoImpl implements BoardReviewDao {

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

}