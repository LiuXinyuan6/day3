package dao.impl;

import dao.userDao;
import domain.user;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class userDaoImpl implements userDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDatasource());

    @Override
    public List<user> findAll() {
        /*
        使用JDBC操作数据库
        1.定义sql
        */
        String sql = "select * from user";
        List<user> users = template.query(sql, new BeanPropertyRowMapper<user>(user.class));
        return users;
    }
    @Override
    public user findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username=? and password=?";
            user user = template.queryForObject(sql, new BeanPropertyRowMapper<user>(user.class),username,password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(user user) {
        //1.定义sql
        String sql="insert into user values(null,?,?,?,?,?,?,null,null)";
        //2.执行sql
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());

    }

    @Override
    public void delete(int id) {
        //1.定义sql
        String sql="delete from user where id=?";
        //2.执行sql
        template.update(sql,id);
    }

    @Override
    public user findById(int id) {
        String sql="select * from user where id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<user>(user.class),id);
    }

    @Override
    public void update(user user) {
        String sql="update user set name=? , gender=? , age=? , address=? ,qq=? , email=? where id=?";
        template.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
    }

    @Override
    public int findTotalCount() {
        String sql="select count(*) from user";
        return  template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<user> findByPage(int start, int rows) {
        String sql="select * from user limit ? , ? ";
        return template.query(sql,new BeanPropertyRowMapper<user>(user.class),start,rows);
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        //1.定义模板初始化sql
        String sql="select count(*) from user where 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        //2.遍历Map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params=new ArrayList<Object>();
        for (String key:keySet) {
            //排除分页条件参数
            if("currentPage".equals(key)||"rows".equals(key))
            {
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value!=null&&!"".equals(value))
            {
                //有值
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//?条件的值
            }
        }
        return template.queryForObject(sb.toString(), Integer.class, params.toArray());
    }

    @Override
    public List<user> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql="select * from user where 1 =1 ";
        StringBuilder sb=new StringBuilder(sql);
        //2.遍历Map
        Set<String> keySet = condition.keySet();
        //定义参数的集合
        List<Object> params=new ArrayList<Object>();
        for (String key:keySet) {
            //排除分页条件参数
            if("currentPage".equals(key)||"rows".equals(key))
            {
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if(value!=null&&!"".equals(value))
            {
                //有值
                sb.append(" and "+key+" like ? ");
                 params.add("%"+value+"%");//?条件的值
            }
        }
        //添加分页的查询
        sb.append(" limit ?,?");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql=sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<user>(user.class),params.toArray());
    }
}
