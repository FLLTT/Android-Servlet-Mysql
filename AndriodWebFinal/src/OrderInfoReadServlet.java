import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;


public class OrderInfoReadServlet extends HttpServlet {
    public OrderInfoReadServlet(){}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = req.getSession().getServletContext().getRealPath("") + "/database.txt";
        OrderInfoDAO oDAO = new OrderInfoDAO();
        String username = req.getParameter("username");
        String phoneNumber = req.getParameter("phonenumber");
        List<OrderInfo> orderInfos = oDAO.OrderInfoRead(username,phoneNumber,filePath);
        String oString = this.createOrdersString(orderInfos);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(oString);
        writer.flush();
        writer.close();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
    private String createOrdersString(List<OrderInfo> orderInfos){

        StringBuilder buffer = new StringBuilder();
        Iterator var4 = orderInfos.iterator();

        while(var4.hasNext()) {
            OrderInfo orderInfo = (OrderInfo) var4.next();
            //buffer.append(orderInfo.getId()).append(",");
            buffer.append(orderInfo.getUsername()).append(",");
            buffer.append(orderInfo.getPhoneNumber()).append(",");
            buffer.append(orderInfo.getDietInfo()).append(",");
            buffer.append(orderInfo.getTotalPrice());
            buffer.append("\n");
        }

        return buffer.toString();
    }
}
