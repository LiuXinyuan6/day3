package dao;

import domain.user;

import java.util.List;
import java.util.Map;

/*
用户操作的Dao
 */
public interface userDao {
    public List<user>  findAll();

    user findUserByUsernameAndPassword(String username, String password);

    void add(user user);

    void delete(int i);

    user findById(int i);

    void update(user user);
    /*
    查询总记录数
     */
    int findTotalCount();
    /*
        分页查询每页记录
         */
    List<user> findByPage(int start, int rows);

    int findTotalCount(Map<String, String[]> condition);

    List<user> findByPage(int start, int rows, Map<String, String[]> condition);
}
