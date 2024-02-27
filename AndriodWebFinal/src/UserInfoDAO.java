import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDAO {
    public UserInfoDAO(){}

    public List<UserInfo> getAllUsers(String filePath){
        try{
            Connection conn = this.getConn(filePath);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM userinfo";
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList userInfos = new ArrayList();

            while(rs.next()){
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phonenumber");
                String gender = rs.getString("gender");
                UserInfo userInfo = new UserInfo();
                userInfo.setId(id);
                userInfo.setUsername(username);
                userInfo.setPassword(password);
                userInfo.setPhoneNumber(phoneNumber);
                userInfo.setGender(gender);
                userInfos.add(userInfo);
            }

            conn.close();
            return userInfos;
        }catch (Exception var13){
            var13.printStackTrace();
            return null;
        }
    }
    public String passwordExchange(String username, String newPassword , String phoneNumber, String filepath){
        try {
            Connection conn = this.getConn(filepath);
            String sql = "UPDATE userinfo SET password = ? WHERE username = ? AND phonenumber = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            stmt.setString(3, phoneNumber);

            int rowsAffected = stmt.executeUpdate(); // rowAffected = 0 表示受影响的行数为 0  则说明电话号码不匹配

            stmt.close();
            conn.close();

            if (rowsAffected == 0) {
                return "phoneNumber is not correct";
            } else {
                return "password has been changed!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    public String saveAllUser(String username,String password, String phonenumber, String gender, String filePath) {
        try {
            Connection conn = this.getConn(filePath);
            String sqlJudge = "SELECT 1 FROM userinfo WHERE username = ?";
            PreparedStatement stmtJudge = conn.prepareStatement(sqlJudge);
            stmtJudge.setString(1, username);
            ResultSet rs = stmtJudge.executeQuery();
            if (rs.next()) {
                return "user exist！";
            } else {
                String sql = "INSERT INTO userinfo (username, password, phonenumber, gender) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, phonenumber);
                stmt.setString(4, gender);
                stmt.executeUpdate();
                stmt.close();
                conn.close();

            }
            rs.close();
            stmtJudge.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       return "register success!";
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

    public UserInfo getUserInfoByUsername(String username, String filePath) {
        try {
            Connection conn = this.getConn(filePath);
            String sql = "SELECT * FROM userinfo WHERE username = ?";  // 这样可以确保输入的username不会被误解为SQL代码的一部分，从而避免了SQL注入攻击的风险
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username); // 将实际的查询值设置到PreparedStatement对象中
            ResultSet rs = stmt.executeQuery();
            UserInfo userInfo = null;
            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phonenumber");
                String gender = rs.getString("gender");
                userInfo = new UserInfo();
                userInfo.setId(id);
                userInfo.setUsername(username);
                userInfo.setPassword(password);
                userInfo.setPhoneNumber(phoneNumber);
                userInfo.setGender(gender);
            }
            conn.close();
            return userInfo;
        } catch (Exception var12) {
            var12.printStackTrace();
            return null;
        }
    }
}
