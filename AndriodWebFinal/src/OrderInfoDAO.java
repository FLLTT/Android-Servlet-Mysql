import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderInfoDAO {
    public OrderInfoDAO(){}

    public String SaveAllOrderInfo(String username, String phoneNumber, String dietInfo, String totalPrice, String filePath) {
        try {
            Connection conn = this.getConn(filePath);
            String sql = "INSERT INTO orderinfo (username, phoneNumber, dietInfo, totalPrice) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, phoneNumber);
            stmt.setString(3, dietInfo);
            stmt.setString(4, totalPrice);
            int rowsAffected = stmt.executeUpdate();
            stmt.close();
            conn.close();
            if (rowsAffected > 0) {
                return "save success!";
            } else {
                return "save wrong!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "save wrong!";
        }
    }

    public List<OrderInfo> OrderInfoRead(String username, String phoneNumber, String filepath) {
        try {
            Connection conn = this.getConn(filepath);
            String sql = "SELECT * FROM orderinfo WHERE username = ? AND phoneNumber = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, phoneNumber);
            ResultSet rs = stmt.executeQuery();
            ArrayList orderInfos = new ArrayList();
            while (rs.next()) {
                int id = rs.getInt("id");
                String dietInfo = rs.getString("dietinfo");
                String totalPrice = rs.getString("totalprice");

                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setId(id);
                orderInfo.setUsername(username);
                orderInfo.setPhoneNumber(phoneNumber);
                orderInfo.setDietInfo(dietInfo);
                orderInfo.setTotalPrice(totalPrice);
                orderInfos.add(orderInfo);
            }

            conn.close();
            return orderInfos;
        } catch (Exception e) {
            e.printStackTrace();
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
