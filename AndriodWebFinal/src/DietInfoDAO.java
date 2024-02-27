import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DietInfoDAO {
    public DietInfoDAO(){

    }

    public List<DietInfo> getAllDiet(String filePath){
        try{
            Connection conn = this.getConn(filePath);
            String sql = "SELECT * FROM diet";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList dietInfos = new ArrayList();

            while (rs.next()) {
                int id = rs.getInt("id");
                String dietName = rs.getString("dietname");
                int price = rs.getInt("price");
                String imagename = rs.getString("imagename");
                DietInfo dietInfo = new DietInfo();
                dietInfo.setId(id);
                dietInfo.setDietName(dietName);
                dietInfo.setPrice(price);
                dietInfo.setImageName(imagename);
                dietInfos.add(dietInfo);
            }
            conn.close();
            return dietInfos;
        }catch (Exception var12) {
            var12.printStackTrace();
            return null;
        }
    }
    private Connection getConn(String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            String line = null;
            int count = 1;
            String driver = null;
            String url = null;
            String account = null;

            String password;
            for(password = null; (line = reader.readLine()) != null; ++count) {
                if (count == 1) {
                    driver = line.trim().split("-")[1];
                } else if (count == 2) {
                    url = line.trim().split("-")[1];
                } else if (count == 3) {
                    account = line.trim().split("-")[1];
                } else if (count == 4) {
                    password = line.trim().split("-")[1];
                }
            }

            System.out.println(driver + "\n" + url + "\n" + account + "\n" + password);
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(url, account, password);
            return conn;
        } catch (Exception var10) {
            var10.printStackTrace();
            return null;
        }
    }
}
