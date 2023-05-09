/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.registration;

import anhnd.ultil.DBHelper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author DucAnh
 */
// implements Serializable de thuc thi by sream va bit stream
public class RegistrationDAO implements Serializable {

//    public boolean checkLogin(String username, String password)
//            throws SQLException, ClassNotFoundException, NamingException {
 public RegistrationDTO checkLogin(String username, String password)
            throws SQLException, ClassNotFoundException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        RegistrationDTO result = null;
        try {
            //1. con DB 
            con = DBHelper.makeConnection();
            if (con != null) {

                //2. Write SQl command
                String sql = "Select username , fullname, role "
                        + "From  registration "
                        + "Where username = ? And password = ? ";

                //3. Create Statement obj
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4.Execute Statement obj to get result
                rs = stm.executeQuery();
                //5. Process result
                if (rs.next()) {
                    String fullName = rs.getString("fullname");
                    boolean role = rs.getBoolean("role");
                    result = new RegistrationDTO(username, null, fullName, role);
                }
                // tat ca moi doi tuong moi khi su dung buoc so 1 phai khoi tao null 
                // va sau khi dung xong dong lai
            } // end connection has existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }

        return result;
    }
    private List<RegistrationDTO> accountList;

    public List<RegistrationDTO> getAccountList() {
        return accountList;
    }

    public void searchLastname(String keyword)
            throws SQLException, ClassNotFoundException, NamingException {

        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        boolean result = false;
        try {
            //1. con DB 
            con = DBHelper.makeConnection();
            if (con != null) {

                //2. Write SQl command
                String sql = "Select username, password, fullName, role "
                        + "From  registration "
                        + "Where fullName like ? ";

                //3. Create Statement obj
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + keyword + "%");
                //4.Execute Statement obj to get result
                rs = stm.executeQuery();
                //5. Process result
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    boolean role = rs.getBoolean("role");

                    RegistrationDTO dto = new RegistrationDTO(username,
                            password, fullName, role);
                    if (this.accountList == null) {
                        this.accountList = new ArrayList<>();
                    }// end account list had not exited
                    this.accountList.add(dto);
                }
                // tat ca moi doi tuong moi khi su dung buoc so 1 phai khoi tao null 
                // va sau khi dung xong dong lai
            } // end connection has existed
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean deleteAcc(String username)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        String pk = username;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "delete from registration "
                        + "where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, pk);
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;

    }

    public boolean updateAcc(String username, String password, boolean role)
            throws ClassNotFoundException, SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        String pk = username;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "update registration set "
                        + "password = ?, role = ? "
                        + "where username = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, password);
                stm.setBoolean(2, role);
                stm.setString(3, pk);
                
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;

    }
    
    public boolean createAccount(RegistrationDTO dto) throws ClassNotFoundException, SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "insert into registration ("
                        + "username, passowrd, fullname, role "
                        + ") value ("
                        + "?,?,?,?"
                        + ")";
                  //3 create
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getUsername());
                stm.setString(2, dto.getPassword());
                stm.setString(3, dto.getFullName());
                stm.setBoolean(4, dto.isRole());
                int effectRow = stm.executeUpdate();
                if (effectRow > 0) {
                    result = true;
                }
            }

        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

   

}
