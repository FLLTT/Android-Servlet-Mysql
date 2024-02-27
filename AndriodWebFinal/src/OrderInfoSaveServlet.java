import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class OrderInfoSaveServlet extends HttpServlet {
    public OrderInfoSaveServlet(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = req.getSession().getServletContext().getRealPath("") + "/database.txt";
        OrderInfoDAO oDAO = new OrderInfoDAO();
        String username = req.getParameter("username");
        String phoneNumber = req.getParameter("phonenumber");
        String dietInfo = req.getParameter("dietinfo");
        String totalPrice = req.getParameter("totalprice");
        String oReturn = oDAO.SaveAllOrderInfo(username,phoneNumber,dietInfo,totalPrice,filePath);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(oReturn);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
