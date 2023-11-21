package com.java4.board.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.java4.board.user.domain.User;

@Repository
public class UserDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<User> mapper = new RowMapper<User>() {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new User(rs.getInt("id"), rs.getString("user_id"), rs.getString("password"), rs.getString("name"),
					rs.getString("phone"), rs.getString("address"), rs.getString("email"), rs.getString("git_address"),
					rs.getInt("gender"), rs.getDate("birth"), rs.getTimestamp("created_at"));
		}
	};

	public void add(User user) {
		jdbcTemplate.update(
				"insert into users (\"user_id\", \"password\", \"name\", \"phone\", \"address\", \"email\", \"git_address\",\"gender\", \"birth\") values (?, ?, ?, ?, ?, ?, ?, ?, ?)",
				user.getUserId(), user.getPassword(), user.getName(), user.getPhone(), user.getAddress(),
				user.getEmail(), user.getGitAddress(), user.getGender(), user.getBirth());
	}
	
	public User get(String userId) {
		return jdbcTemplate.queryForObject("select * from users where \"user_id\"=?", mapper, userId);
	}
}



/*
 * package com.java4.board.user.dao;
 * 
 * import java.sql.ResultSet; import java.sql.SQLException; import
 * java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.jdbc.core.JdbcTemplate; import
 * org.springframework.jdbc.core.RowMapper; import
 * org.springframework.stereotype.Repository;
 * 
 * import com.java4.board.user.domain.User;
 * 
 * @Repository public class UserDAO {
 * 
 * @Autowired private JdbcTemplate jdbcTemplate;
 * 
 * private RowMapper<User> mapper = new RowMapper<User>() {
 * 
 * @Override public User mapRow(ResultSet rs, int rowNum) throws SQLException {
 * // TODO Auto-generated method stub return new User( rs.getInt("id"),
 * rs.getString("userId"), rs.getString("password"), rs.getString("name"),
 * rs.getString("phone"), rs.getString("address"), rs.getString("email"),
 * rs.getString("gitAddress"), rs.getInt("gender"), rs.getDate("birth"),
 * rs.getTimestamp("created_at")); } };
 * 
 * public void userAdd(User user) { jdbcTemplate.
 * update("insert into users (\"user_id\", \"password\", \"name\", \"phone\", \"address\", \"email\", \"git_address\", \"gender\", \"birth\", \"create_at\") values (?,? ,?, ?, ?, ?, ?, ?,?,?)"
 * , mapper,user.getUserId(), user.getPassword(), user.getName(),
 * user.getPhone(),user.getAddress(), user.getEmail(),user.getGitAddress(),
 * user.getGender(), user.getBirth(), user.getCreatedAt()); }
 * 
 * 
 * public List<User> getAll() { return
 * jdbcTemplate.query("select * from users order by \"id\"", mapper);
 * 
 * }
 * 
 * }
 */