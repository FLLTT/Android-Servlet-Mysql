import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class UserListServlet extends HttpServlet {
    public UserListServlet(){}

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = req.getSession().getServletContext().getRealPath("") + "/database.txt";
        UserInfoDAO uDAO = new UserInfoDAO();
        List<UserInfo> userInfos = uDAO.getAllUsers(filePath);
        String uString = this.createProductsString(userInfos);
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

    private String createProductsString(List<UserInfo> userInfos) {
        StringBuilder buffer = new StringBuilder();
        Iterator var4 = userInfos.iterator();

        while(var4.hasNext()) {
            UserInfo userInfo = (UserInfo) var4.next();
            buffer.append(userInfo.getId()).append(",");
            buffer.append(userInfo.getUsername()).append(",");
            buffer.append(userInfo.getPassword()).append(",");
            buffer.append(userInfo.getPhoneNumber()).append(",");
            buffer.append(userInfo.getGender());
            buffer.append("\n");
        }

        return buffer.toString();
    }
}
