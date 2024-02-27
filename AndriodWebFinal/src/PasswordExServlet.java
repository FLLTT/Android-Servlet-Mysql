import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//用于用户忘记密码后的修改密码
public class PasswordExServlet extends HttpServlet {
    public PasswordExServlet(){

    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = req.getSession().getServletContext().getRealPath("") + "/database.txt";
        UserInfoDAO uDAO = new UserInfoDAO();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phonenumber = req.getParameter("phonenumber");
        String sReturn = uDAO.passwordExchange(username,password,phonenumber,filePath);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(sReturn);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
