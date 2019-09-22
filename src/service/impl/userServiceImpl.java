package service.impl;

import dao.impl.userDaoImpl;
import dao.userDao;
import domain.PageBean;
import domain.user;
import service.userService;

import java.util.List;
import java.util.Map;

public class userServiceImpl implements userService {
    private userDao dao=new userDaoImpl();
    @Override
    public List<user> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }
    @Override
    public user login(user user)
    {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public void addUser(user user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    public user findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void updateUser(user user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        for (String id:ids) {
            dao.delete(Integer.parseInt(id));
        }
    }

    @Override
    public PageBean<user> findUserByPage(String _currentPage, String _rows, Map<String,String[]> condition) {
        int currentPage=Integer.parseInt(_currentPage);
        int rows=Integer.parseInt(_rows);
        if(currentPage<=0)
        {
            currentPage=1;
        }
        //创建空的PageBean对象
        PageBean<user> pb=new PageBean<user>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setCurrentPage(rows);
        //调用dao查询总记录数
        int totalCount=dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //调用dao查询List集合
        //计算开始的计算索引
        int start=(currentPage-1)*rows;
        List<user> list=dao.findByPage(start,rows,condition);
        pb.setList(list);
        //计算总页码数
        int totalPage=(totalCount%rows)==0 ? totalCount/rows :(totalCount/rows)+1;
        pb.setTotalPage(totalPage);
        if(currentPage>=totalPage)
            currentPage=totalPage;
        pb.setCurrentPage(currentPage);
        return pb;
    }
}
