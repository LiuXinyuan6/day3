package web.servlet;

import domain.user;
import service.*;
import org.apache.commons.beanutils.BeanUtils;
import service.impl.userServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //1.设置编码
        request.setCharacterEncoding("utf-8");
     //2.获取数据
        //2.1获取用户填写的验证码
        String verifycode = request.getParameter("verifycode");
     //3.校验验证码
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        session.removeAttribute("checkCode_session");//确保验证码一次性
        if (!checkCode_session.equalsIgnoreCase(verifycode))
        {
            //验证码不正确，提示信息
            request.setAttribute("login_msg","验证码错误");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
     //4.封装验证码对象
        user user =new user();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
     //5.调用service查询
        userService service=new userServiceImpl();
        user loginUser=service.login(user);
        if(loginUser!=null)
        {
            //登陆成功
            //将用户存入session
            session.setAttribute("user",loginUser);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }
        else
        {
            //登录失败
            //验证码不正确，提示信息
            request.setAttribute("login_msg","用户名或密码错误");
            //，跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
