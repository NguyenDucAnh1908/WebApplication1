/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhnd.orderdetails;

import anhnd.product.ProductDTO;
import anhnd.ultil.DBHelper;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.naming.NamingException;

/**
 *
 * @author DucAnh
 */
public class OrderDetailsDAO {
    private List<OrderDetailsDTO> orderDetailsList;

    public List<OrderDetailsDTO> getOrderDetailsList() {
        return orderDetailsList;
    }

    public boolean createOrderDetails(int orderID, Map<ProductDTO, Integer> checkedItems)
            throws SQLException, NamingException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                con.setAutoCommit(false); //*****
                String sql = "Insert Into OrderDetails"
                        + "(OrderID, SKU, Name, Price, Quantity, Total) "
                        + "Values (?, ?, ?, ?, ?, ?)";
                stm = con.prepareStatement(sql);
                BigDecimal price;
                BigDecimal total;
                int quantity;
//                int i = 0; //*****
                int affectedRows =  0;
                for (ProductDTO dto : checkedItems.keySet()) {
                    quantity = checkedItems.get(dto);
                    price = dto.getPrice();
                    total = price.multiply(new BigDecimal(quantity));
                    stm.setInt(1, orderID);
                    stm.setString(2, dto.getSKU());
                    stm.setString(3, dto.getName());
                    stm.setBigDecimal(4, price);
                    stm.setInt(5, quantity);
                    stm.setBigDecimal(6, total);
                    affectedRows += stm.executeUpdate(); //*****
//                    stm.addBatch(); //******
//                    i++;//******
                }
                
                con.commit(); //*****
                
                if (affectedRows == checkedItems.size()) { //*****
                    return true; 
                }
                
//                if (i % 100 == 0 || i == checkedItems.size()) {
//                    stm.executeBatch();
//                    return true;
//                }
            }
        } catch (SQLException ex) {
            if (con != null) {
                con.rollback();
            }
        }
        finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
