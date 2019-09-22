package service;
import domain.PageBean;
import  domain.user;

import java.util.List;
import java.util.Map;

/*
   用户管理的业务接口
 */
public interface userService {
    /*
    查询所有用户信息
     */
    public List<user> findAll();

    user login(user user);

    /*
    保存user
     */
    void addUser(user user);

    /*
    根据id删除user
     */
    void deleteUser(String id);
    /*
    根据id查询
     */
    user findUserById(String id);
    /*
    修改用户信息
     */
    void updateUser(user user);
    /*
    批量删除用户
     */
    void delSelectedUser(String[] ids);
    /*
    分页条件查询
     */
    PageBean<user> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
