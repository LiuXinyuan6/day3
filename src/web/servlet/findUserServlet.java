package web.servlet;

import  domain.user;
import  service.userService;
import  service.impl.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class findUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取id
        String id=req.getParameter("id");
        //2.调用service查询
        userService service =new userServiceImpl();
        user user=service.findUserById(id);
        //3.将user存入request
        req.setAttribute("user",user);
        //4.转发到update.jsp即可
        req.getRequestDispatcher("/update.jsp").forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
