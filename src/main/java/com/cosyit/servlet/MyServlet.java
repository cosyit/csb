package com.cosyit.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(initParams = {
        @WebInitParam(name = "myload", value = "king"),
        @WebInitParam(name = "namedada", value = "wanglaoji")},
        urlPatterns = {"/helloWorld", "/worldHello"})
public class MyServlet extends HttpServlet{
    //出于线程安全性的考虑。除非这个变量是只读，java.util.concurrent.atomic成员。才会把它声明为类级变量（类的成员属性）。
    private transient ServletConfig servletConfig;

    public void init(ServletConfig servletConfig) throws ServletException {
        this.servletConfig = servletConfig;
        System.out.println("初吻");
    }

    public void service(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {

        //一个用户一个HttpSession
        //servletRequest.getSession(false);//获取session,没有不创建。
        HttpSession session=servletRequest.getSession();//ture 获取不到创建一个。

        //后台设进服务器内存，512M 不要放大对象。如果内存不够用，保存在二级存储【disk性能受影响】。
        //HttpSession我们大多数放String , Serializable java对象都是可以setAttr .
        session.setAttribute("name","cengshangbing");
        session.setAttribute("name","mumu"); //相同的name , 值覆盖的问题。

        //getAttributeNames 返回Enumeration
       Enumeration  names= session.getAttributeNames();
       if(names.hasMoreElements()){
           System.out.println(names.nextElement());
       }


       //HttpSession 接口 对象 是纯服务器数据，不会被发送到客户端。它生产一个唯一的标识。
        //标识会发送过去。1.JSESSIONID cookie     2.jsessionid="唯一标识"
        //下次请求web客户端再把这个唯一标识再提交过来。
        // servlet容器会自动选择方式【1 or 2】传递会话标识。

        //页面区。${name}   <%= session.getAttribute("name") %>
        session.getAttribute("name");


        session.getId();
        session.getMaxInactiveInterval();//得到最大不活跃的时间间隔,查看还有多久时间过去。单位：秒

        int second =3600;
        session.setMaxInactiveInterval(second);
        //session.setMaxInactiveInterval(0);//永不过期。不良好的设计。reset,close servletContainer会释放。


        session.invalidate(); //强制会话过期--清空保存的对象。自己主动手动指定销毁HttpSession接口对象。
        //设置默认的会话过期时间。

       Cookie cookie=new Cookie("name","value");//信息量少。web客户端和服务器之间进行交互。
        //HTTP header --->受http协议控制的。 浏览器：20 cookies
        // 非常大的问题：用户可以通过浏览器设置拒绝cookie

        cookie.getDomain();
        cookie.setDomain("");
        cookie.setPath("uri");
        cookie.getMaxAge();
        cookie.setMaxAge(0);//马上就过期。马上删掉


        //查询出一个cookie
        Cookie mycoo=null;
       // servletResponse.addCookie(new Cookie());
        for (Cookie coo:servletRequest.getCookies()
             ) {
            if(coo != null){
                if(coo.getName().equals("name")){
                    mycoo=coo;
                    break;
                }
            }
        }

        //1.设置一下编码
        servletResponse.setContentType("text/html");
        servletResponse.setCharacterEncoding("utf-8");
        String servletName = getServletConfig().getServletName();
        getServletConfig().getServletContext();
        System.out.println(servletRequest.getHeader("Accept"));//HTTP标题
        servletRequest.getQueryString();
        servletRequest.getSession();//servletRequest.getSession(false);//如果你没有，就不再创建新的会话。
        servletRequest.getCookies();
        servletRequest.getPathInfo();


        servletResponse.addCookie(new Cookie("name","value"));
        servletResponse.sendRedirect("");
        servletResponse.addHeader("name","value");


        //PrintWriter : IOS-8859-1  -->
        PrintWriter writerOrout = servletResponse.getWriter();
        //System.out.println(servletConfig.getInitParameter("myload"));
        // servletResponse.getOutputStream();

        String parameter = servletRequest.getParameter("name");
        servletRequest.getProtocol();

        //servletConfig.getInitParameter("myload");
        Enumeration<String> enumeration = servletConfig.getInitParameterNames();
        if (enumeration.hasMoreElements()) {
            //System.out.println("get 变量name:"+enumeration.nextElement());

            // getServletConfig().getInitParameter(enumeration.nextElement());
            // System.out.println("中文"+enumeration.nextElement());
            System.out.println(servletConfig.getInitParameter(enumeration.nextElement()));

        }


        writerOrout.print("<html>\n" +
                "<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "<title>Insert title here</title>\n" +
                "</head>\n" +
                "<body style='background:#666'>\n" +
                "    <h1>servletRequest.getContentLength : " +

                "协议名 :" + servletRequest.getProtocol() +
                "ServletInfo信息 :" +
                "" + getServletInfo() +
                "</body>\n" +
                "</html>");
    }

    public void destroy() {
        System.out.println("卸载，关闭容器时call");
    }

    public String getServletInfo() {
        return "这是我的第一个Servlet";
    }
    public ServletConfig getServletConfig() {
        return servletConfig;
    }
}
