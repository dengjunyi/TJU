package com.example.comm.add;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class barcode {


    public static void show() {
        Connection connection = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;
        PreparedStatement ps4 = null;
        PreparedStatement ps5 = null;
        PreparedStatement ps6 = null;
        PreparedStatement ps7 = null;
        PreparedStatement ps8 = null;
        PreparedStatement ps9 = null;
        PreparedStatement ps10 = null;
        ResultSet rs1 = null;
        ResultSet rs2 = null;
        ResultSet rs3 = null;
        ResultSet rs4 = null;
        ResultSet rs5 = null;
        ResultSet rs6 = null;
        ResultSet rs7 = null;
        ResultSet rs8 = null;
        ResultSet rs9 = null;
        ResultSet rs10 = null;
        try {
            //加载数据驱动
            Class.forName("com.mysql.jdbc.Driver");
            //通过驱动管理类获取数据库连接
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/expresssorting?charaterEncoding=utf-8", "root", "root");
            //获取当前时间
            java.util.Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
            //物品表
            //条形码
            String barcode = "888";
            //订单号
            String o_id = "10010";
            //数量
            int number = 1;
            //描述
            String b_Item_info = "";


            // 1:订单号存在,条形码不存在!添加到物品表,如果显示表有订单号的信息,则修改显示表的总数量,修改订单表的总数量,添加到临时表,
            //2.订单号和条形码同时存在!修改此条码的数量,如果显示表存在此订单号的信息,修改显示表此订单的总数量,修改此条码所对应订单表的总数量和状态,修改临时表此条码和订单号对应的信息的数量


            //订单号是否存在
            String sql8 = "SELECT * FROM barcode WHERE o_id=?";
            ps8 = connection.prepareStatement(sql8);
            ps8.setString(1, o_id);
            rs8 = ps8.executeQuery();

            if (rs8.next()) {
                System.out.println("数据库存在订单号");
                //查询物品表
                String sql1 = "SELECT * FROM barcode WHERE  b_barcode=? AND o_id=?";
                ps1 = connection.prepareStatement(sql1);
                ps1.setString(1, barcode);
                ps1.setString(2, o_id);
                rs1 = ps1.executeQuery();
                //查询出此条形码所对应的订单信息
                String sql4 = " select * from orders where o_orderid=?";
                ps4 = connection.prepareStatement(sql4);
                ps4.setString(1, o_id);
                rs4 = ps4.executeQuery();
                rs4.next();

                //判断数据库是否存在这条数据
                //System.out.println(rs1.next());
                if (rs1.next()) {
                    System.out.println("同时存在订单号和条形码");
                    //修改此条码的数量
                    String sql2 = " UPDATE barcode SET b_number=? WHERE b_barcode=? AND o_id=?";
                    ps2 = connection.prepareStatement(sql2);
                    ps2.setInt(1, rs1.getInt("b_number") + number);
                    ps2.setString(2, barcode);
                    ps2.setString(3, o_id);
                    ps2.executeUpdate();
                    //修改临时表的此条码的数量
                    String sql5 = " UPDATE sorting SET s_count=? WHERE s_orderid=? AND s_barcode=?";
                    ps5 = connection.prepareStatement(sql5);
                    ps5.setInt(1, rs1.getInt("b_number") + number);
                    ps5.setString(2, o_id);
                    ps5.setString(3, barcode);
                    ps5.executeUpdate();
                } else {
                    //数据库不同时存在条形码和订单号这条信息,只存在订单号
                    System.out.println("存在订单号");
                    String sql9 = " INSERT INTO barcode(b_number,b_Item_info,b_barcode,o_id) VALUES (?,?,?,?)"; // 添加一条sql语句
                    // 创建一个Statment对象
                    ps9 = connection.prepareStatement(sql9);
                    //数量
                    ps9.setInt(1, number);
                    //描述
                    ps9.setString(2, b_Item_info);
                    //条形码
                    ps9.setString(3, barcode);
                    //订单号
                    ps9.setString(4, o_id);
                    ps9.executeUpdate();
                    //添加到临时表一条信息
                    String sql10 = "INSERT INTO sorting(s_orderid,s_category,s_Item_info,s_count,s_barcode,s_customername,s_number,order_time) VALUES (?,?,?,?,?,?,?,?)"; // 添加一条sql语句
                    ps10 = connection.prepareStatement(sql10);
                    //订单号
                    ps10.setString(1, o_id);
                    //品类
                    ps10.setString(2, rs4.getString("o_category"));
                    //物品描述
                    ps10.setString(3, b_Item_info);
                    //数量
                    ps10.setInt(4, number);
                    //条形码
                    ps10.setString(5, barcode);
                    //客户名称
                    ps10.setString(6, rs4.getString("o_customername"));
                    //订单状态
                    ps10.setInt(7, 0);
                    //时间
                    ps10.setString(8, sdf.format(d));
                    ps10.executeUpdate();
                }
                //查询显示表是否存在此条码所对应订单号的信息
                String sql6 = " SELECT * FROM cursors WHERE c_order=?";
                ps6 = connection.prepareStatement(sql6);
                ps6.setString(1, o_id);
                rs6 = ps6.executeQuery();
                //如果显示表存在此订单的信息,修改显示表的总数量
                if (rs6.next()) {
                    String sql7 = "UPDATE cursors SET c_count=? WHERE c_order=?";
                    ps7 = connection.prepareStatement(sql7);
                    ps7.setInt(1, rs4.getInt("o_number") + number);
                    ps7.setString(2, o_id);
                    ps7.executeUpdate();
                }
                //修改对应此条码的订单的总数量,分拣状态为0
                String sql3 = "UPDATE orders SET o_number=?,o_complete=? WHERE o_orderid=?";
                ps3 = connection.prepareStatement(sql3);
                System.out.println("数量1" + rs4.getInt("o_number"));
                ps3.setInt(1, rs4.getInt("o_number") + number);
                ps3.setInt(2, 0);
                ps3.setString(3, o_id);
                System.out.println("");
                ps3.executeUpdate();
                System.out.println("修改订单表:" + ps3.executeUpdate());
            } else {
                //不存在此数据,添加到物品表
              /*  String sql8 = " INSERT INTO barcode(b_number,b_Item_info,b_barcode,o_id) VALUES (?,?,?,?)"; // 添加一条sql语句
                // 创建一个Statment对象
                ps8 = connection.prepareStatement(sql8);
                //数量
                ps8.setInt(1, number);
                //描述
                ps8.setString(2, b_Item_info);
                //条形码
                ps8.setString(3, barcode);
                //订单号
                ps8.setString(4, o_id);
                ps8.executeUpdate();*/


                //添加到订单表

                //添加到临时表


            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps1 != null) {
                try {
                    ps1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps2 != null) {
                try {
                    ps2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps3 != null) {
                try {
                    ps3.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps4 != null) {
                try {
                    ps4.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps5 != null) {
                try {
                    ps5.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps6 != null) {
                try {
                    ps6.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps7 != null) {
                try {
                    ps7.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps8 != null) {
                try {
                    ps8.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps9 != null) {
                try {
                    ps9.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps10 != null) {
                try {
                    ps10.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs1 != null) {
                try {
                    rs1.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs2 != null) {
                try {
                    rs2.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs3 != null) {
                try {
                    rs3.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs4 != null) {
                try {
                    rs4.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs5 != null) {
                try {
                    rs5.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs6 != null) {
                try {
                    rs6.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs7 != null) {
                try {
                    rs7.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs8 != null) {
                try {
                    rs8.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs9 != null) {
                try {
                    rs9.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (rs10 != null) {
                try {
                    rs10.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        show();
    }


}
