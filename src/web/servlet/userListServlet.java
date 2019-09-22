package web.servlet;

import domain.user;
import service.impl.userServiceImpl;
import service.userService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/userListServlet")
public class userListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //1.调用userService完成查询
        userService service=new userServiceImpl();
        List<user> users = service.findAll();
        //2.将List存入request域
        req.setAttribute("users",users);
        //3.装发到List.jsp
        req.getRequestDispatcher("/list.jsp").forward(req,resp);
    }

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
