import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;


public class DietListServlet extends HttpServlet {
    public DietListServlet(){

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filePath = req.getSession().getServletContext().getRealPath("") + "/database.txt";
        DietInfoDAO dDAO = new DietInfoDAO();
        List<DietInfo> dietInfos = dDAO.getAllDiet(filePath);
        String dString = this.createDietInfoString(dietInfos);
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.write(dString);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
    private String createDietInfoString(List<DietInfo> dietInfos) {
        if(dietInfos == null){
            return "ThatsALL";
        }
        StringBuilder buffer = new StringBuilder();
        Iterator var4 = dietInfos.iterator();
        while(var4.hasNext()) {
            DietInfo dietInfo = (DietInfo) var4.next();
            buffer.append(dietInfo.getId()).append(",");
            buffer.append(dietInfo.getDietName()).append(",");
            buffer.append(dietInfo.getPrice()).append(",");
            buffer.append(dietInfo.getImageName());
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
