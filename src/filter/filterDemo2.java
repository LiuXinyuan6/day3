//package filter;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import java.io.IOException;
//
//@WebFilter(value = "/*",dispatcherTypes=DispatcherType.REQUEST)
//public class filterDemo2 implements Filter {
//    public void destroy() {
//        System.out.println("destroy..");
//    }
//
//    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
//        System.out.println("filter执行了");
//        chain.doFilter(req, resp);
//        System.out.println("filter回来了");
//    }
//
//    public void init(FilterConfig config) throws ServletException {
//    System.out.println("init..");
//    }
//
//}
