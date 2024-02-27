import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

//本方法是用于用户数据注册 并且判断用户是否已经注册
public class UserRegisterServlet extends HttpServlet {
    public UserRegisterServlet() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = req.getSession().getServletContext().getRealPath("") + "/database.txt";
        UserInfoDAO uDAO = new UserInfoDAO();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String phonenumber = req.getParameter("phonenumber");
        String gender = req.getParameter("gender");
        String sReturn = uDAO.saveAllUser(username, password, phonenumber, gender,filePath);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(sReturn);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
