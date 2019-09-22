package web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class checkcodeservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width=100;
        int height=50;
        BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.pink);
        graphics.fillRect(0,0,width,height);
        graphics.setColor(Color.blue);
        graphics.drawRect(0,0,width-1,height-1);
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random ran=new Random();
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<4;i++)
        {
            int index = ran.nextInt(str.length());
            char ch=str.charAt(index);
            sb.append(ch);
            graphics.drawString(ch+"",width/5*i,height/2);
        }
        String checkCode_session = sb.toString();
        req.getSession().setAttribute("checkCode_session",checkCode_session);
        graphics.setColor(Color.green);
        for(int i=0;i<10;i++)
        {
            int x1 = ran.nextInt(width);
            int y1 = ran.nextInt(height);

            int x2 = ran.nextInt(width);
            int y2 = ran.nextInt(height);
            graphics.drawLine(x1,y1,x2,y2);

        }

        ImageIO.write(image,"jpg",resp.getOutputStream());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
