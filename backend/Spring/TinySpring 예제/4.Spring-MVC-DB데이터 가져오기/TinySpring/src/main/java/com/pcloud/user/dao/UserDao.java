package com.pcloud.user.dao;

import com.pcloud.user.vo.UserVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserDao {

    @Inject
    private JdbcTemplate jdbcTemplate;

    public List<UserVO> userSelectAll() {
        String query = "select * from User";
        List<UserVO> userList = jdbcTemplate.query(query,
                new RowMapper<UserVO>() {
                    @Override
                    public UserVO mapRow(ResultSet resultSet, int i) throws SQLException {
                        String id = resultSet.getString("name");
                        String passwd = resultSet.getString("passwd");
                        String name = resultSet.getString("name");
                        int genderId = resultSet.getInt("genderId");

                        UserVO user = new UserVO();
                        user.setId(id);
                        user.setPasswd(passwd);
                        user.setName(name);
                        user.setGenderId(genderId);
                        return user;
                    }
                });

        return userList.isEmpty() ? null : userList;
    }

    public UserVO userSelect(String id) {
        String query = "select * from User where id = ? ";

        UserVO user = jdbcTemplate.queryForObject(query, new Object[]{id},
                new RowMapper<UserVO>() {
                    @Override
                    public UserVO mapRow(ResultSet resultSet, int i) throws SQLException {
                        String id = resultSet.getString("name");
                        String passwd = resultSet.getString("passwd");
                        String name = resultSet.getString("name");
                        int genderId = resultSet.getInt("genderId");

                        UserVO user = new UserVO();
                        user.setId(id);
                        user.setPasswd(passwd);
                        user.setName(name);
                        user.setGenderId(genderId);
                        return user;
                    }
                });
        return user;
    }
}
