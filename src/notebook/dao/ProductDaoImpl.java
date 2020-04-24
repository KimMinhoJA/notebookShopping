package notebook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import notebook.domain.Product;
import notebook.util.DbUtil;

public class ProductDaoImpl implements ProductDao {

	@Override
	public Product selectByNum(String serialNum) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM product WHERE serialnum = ?";
		Product product = null;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, serialNum);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				product = new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), rs.getString(9)
						, rs.getInt(10), rs.getDouble(11), rs.getString(12), rs.getString(13));
			}
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return product;
	}

	@Override
	public List<Product> selectByPriceRange(int minPrice, int maxPrice) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM product WHERE price BETWEEN ? AND ?";
		List<Product> list = new ArrayList<Product>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, minPrice);
			ps.setInt(2, maxPrice);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), rs.getString(9)
						, rs.getInt(10), rs.getDouble(11), rs.getString(12), rs.getString(13)));
			}
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<Product> selectNewProducts() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT serialnum, model_name, company, price, ram, cpu, note_size, note_weight, launch_date, stock, grade, description_img_name, img_name"
				+ " FROM (select rownum, p.* FROM (SELECT * FROM product order by launch_date desc) p)pro"
				+ " WHERE rownum < 4";
		List<Product> list = new ArrayList<Product>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), rs.getString(9)
						, rs.getInt(10), rs.getDouble(11), rs.getString(12), rs.getString(13)));
			}
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<Product> selectBestProducts() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT serialnum, model_name, company, price, ram, cpu, note_size, note_weight, launch_date, stock, grade, description_img_name, img_name"
				+ " FROM (select rownum, p.* FROM (SELECT * FROM product order by grade desc) p)pro"
				+ " WHERE rownum < 4";
		List<Product> list = new ArrayList<Product>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), rs.getString(9)
						, rs.getInt(10), rs.getDouble(11), rs.getString(12), rs.getString(13)));
			}
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<Product> selectRendomProducts() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT serialnum, model_name, company, price, ram, cpu, note_size, note_weight, launch_date, stock, grade, description_img_name, img_name"
				+ " FROM (SELECT rownum, p.* FROM (SELECT * FROM product order by dbms_random.value) p) WHERE rownum < 4";
		List<Product> list = new ArrayList<Product>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), rs.getString(9)
						, rs.getInt(10), rs.getDouble(11), rs.getString(12), rs.getString(13)));
			}
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<Product> selectSortProduct(String target) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM product ORDER BY ";
		List<Product> list = new ArrayList<Product>();
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			if(target == null) {
				sql += "serialnum";
			}else if("price_desc".equals(target)) {	//���ݳ�����
				sql += "price desc";	
			}else if("price_asc".equals(target)) {	//���ݳ�����
				sql += "price";
			}else if("new".equals(target)) { //�Ż�ǰ
				sql += "launch_date desc";
			}else if("grade".equals(target)) {	//����
				sql += "grade desc";
			}
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Product(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getString(6), rs.getInt(7), rs.getDouble(8), rs.getString(9)
						, rs.getInt(10), rs.getDouble(11), rs.getString(12), rs.getString(13)));
			}
		}finally {
			DbUtil.dbClose(con, ps, rs);
		}
		return null;
	}

	@Override
	public int update(Product product) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE product SET model_name = ?, company = ?, price = ?, ram = ?, cpu = ?, note_size = ?, note_weight = ?, stock = ?, grade = ?, description_img_name = ?, img_name = ? WHERE serialnum = ?";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getModelName());
			ps.setString(2, product.getCompany());
			ps.setInt(3, product.getPrice());
			ps.setInt(4, product.getRam());
			ps.setString(5, product.getCpu());
			ps.setInt(6, product.getNoteSize());
			ps.setDouble(7, product.getNoteWeight());
			ps.setInt(8, product.getStock());
			ps.setDouble(9, product.getGrade());
			ps.setString(10, product.getDescriptionImgName());
			ps.setString(11, product.getImgName());
			ps.setString(12, product.getSerialNum());
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}

	@Override
	public int insert(Product product) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO product(serialnum, model_name, company, price, ram, cpu, note_size, note_weight, launch_date, description_img_name, img_name) VALUES(seq_serialnum.NEXTVAL, ?,?,?,?,?,?,?,?,?,?)";
		int result = 0;
		try {
			con = DbUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getModelName());
			ps.setString(2, product.getCompany());
			ps.setInt(3, product.getPrice());
			ps.setInt(4, product.getRam());
			ps.setString(5, product.getCpu());
			ps.setInt(6, product.getNoteSize());
			ps.setDouble(7, product.getNoteWeight());
			ps.setString(8, product.getLaunchDate());
			ps.setString(9, product.getDescriptionImgName());
			ps.setString(10, product.getImgName());
			result = ps.executeUpdate();
		}finally {
			DbUtil.dbClose(con, ps);
		}
		return result;
	}

}
