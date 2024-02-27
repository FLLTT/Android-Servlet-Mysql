import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.List;

public class UsernameViewServlet extends HttpServlet {
    public UsernameViewServlet(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = req.getSession().getServletContext().getRealPath("") + "/database.txt";
        UserInfoDAO uDAO = new UserInfoDAO();
        String postData = req.getParameter("postData"); // "admin"
        //System.out.println(postData);
        UserInfo userInfo = uDAO.getUserInfoByUsername(postData,filePath);
        String uString = this.createProductsString(userInfo);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(uString);
        writer.flush();
        writer.close();
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
    private String createProductsString(UserInfo userInfo) {

        StringBuilder buffer = new StringBuilder();
        buffer.append(userInfo.getId()).append(",");
        buffer.append(userInfo.getUsername()).append(",");
        buffer.append(userInfo.getPassword()).append(",");
        buffer.append(userInfo.getPhoneNumber()).append(",");
        buffer.append(userInfo.getGender()).append("\n");
        return buffer.toString();

    }
}
