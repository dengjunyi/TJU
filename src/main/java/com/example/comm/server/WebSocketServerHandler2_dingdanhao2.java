package com.example.comm.server;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WebSocketServerHandler2_dingdanhao2 extends ChannelHandlerAdapter {

    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 当客户端连接到服务器端之后，需要有一个连接激活的方法，此方法可以直接向客户端发送消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("************* 硬件channelRead *************");
        System.out.println("************* 还差扫描器扫描出的数据 *************");
        ByteBuf in = (ByteBuf) msg; // 1、接收消息内容
        String inputStrs = in.toString(CharsetUtil.UTF_8); // 2、得到用户发送的数据
        inputStrs = new String(inputStrs.getBytes("gbk"), "utf-8");
        //System.out.println("==" + inputStrs.split("#")[1].toString());
        String inputStr = inputStrs.replaceAll(" ", "");
        System.out.println("长度:" + inputStr.length());
        if (inputStr != null) {
            //数据库连接
            Connection connection = null;
            //预编译的Statement
            PreparedStatement preparedStatement1 = null;
            PreparedStatement preparedStatement2 = null;
            PreparedStatement preparedStatement4 = null;
            PreparedStatement ps = null;
            PreparedStatement preparedStatement5 = null;
            PreparedStatement preparedStatement6 = null;
            PreparedStatement preparedStatement7 = null;
            PreparedStatement preparedStatement8 = null;
            PreparedStatement preparedStatement9 = null;
            PreparedStatement preparedStatement10 = null;
            PreparedStatement preparedStatement11 = null;
            PreparedStatement preparedStatement12 = null;
            //自动分配端口
            PreparedStatement preparedStatement13 = null;
            PreparedStatement preparedStatement14 = null;
            PreparedStatement preparedStatement15 = null;
            PreparedStatement preparedStatement16 = null;
            PreparedStatement preparedStatement17 = null;
            PreparedStatement preparedStatement18 = null;
            PreparedStatement preparedStatement19 = null;
            PreparedStatement preparedStatement20 = null;

            //结果集对象
            ResultSet resultSet1 = null;
            ResultSet resultSet2 = null;
            ResultSet resultSet4 = null;
            ResultSet resultSet6 = null;
            ResultSet resultSet7 = null;
            ResultSet resultSet8 = null;
            ResultSet resultSet9 = null;
            ResultSet resultSet10 = null;
            ResultSet resultSet11 = null;
            ResultSet resultSet12 = null;
            //自动分配端口
            ResultSet resultSet13 = null;
            ResultSet resultSet14 = null;
            ResultSet resultSet15 = null;
            ResultSet resultSet16 = null;
            ResultSet resultSet17 = null;
            ResultSet resultSet18 = null;
            ResultSet resultSet19 = null;
            ResultSet resultSet20 = null;

            //获取当前时间
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
            //System.out.println("格式化输出：" + sdf.format(d));
            //获取存在此条形码的所有订单号
            String o_orderid = null;

            //返回值给端口的值
            String echoContent;
            try {
                //加载数据驱动
                Class.forName("com.mysql.jdbc.Driver");
                //通过驱动管理类获取数据库连接
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/expresssorting?charaterEncoding=utf-8", "root", "root");

                //判断是否返回值给端口
                Boolean isBean = true;
                System.out.println("1" + inputStr);
                String sub1 = inputStr.substring(0, inputStr.length() - 1);
                System.out.println("截取最后一个字符串生成的新字符串为: " + inputStr.substring(0, inputStr.length() - 1));//abcdef
                String sub2 = inputStr.substring(inputStr.length() - 1);
                System.out.println("2" + inputStr.substring(inputStr.length() - 1));//输出d

                if (inputStr.length() == 10) {
                    System.out.println("机器端返回的值");
                    sub1 = inputStr.substring(0, 3);
                    System.out.println("sub1:" + sub1);
                    sub2 = inputStr.substring(6, 7);
                    System.out.println("sub2:" + sub2);
                }
                if (!sub1.equals("&00")) {
                    //判断获取的端口的返回值
                    String sql17 = "SELECT * FROM porttable WHERE p_port=?";
                    preparedStatement17 = connection.prepareStatement(sql17);
                    preparedStatement17.setString(1, sub1);
                    resultSet17 = preparedStatement17.executeQuery();

                    if (resultSet17.next()) {
                        System.out.println("说明这个端口号存在");
                        //如果截取到最后一个字符等于s
                        if (sub2.equals("s")) {
                            System.out.println("这个端口已完成订单");
                            String sql18 = "UPDATE porttable SET p_state=0 WHERE p_port=?";
                            preparedStatement18 = connection.prepareStatement(sql18);
                            preparedStatement18.setString(1, sub1);
                            preparedStatement18.executeUpdate();
                            System.out.println("修改端口状态:" + preparedStatement18.executeUpdate());
                            isBean = false;
                        }
                        if (sub2.equals("#")) {
                            System.out.println("不用返回值给端口");
                            isBean = false;
                        }
                    }
                }
                System.out.println();
                if (sub2.equals("#") && sub1.equals("&00")) {
                    System.out.println("不用返回值给端口");
                    isBean = false;
                }

                // if (isBean) {
                //自动分配端口
                String sql13 = "SELECT * FROM porttable WHERE p_state=0";
                preparedStatement13 = connection.prepareStatement(sql13);
                resultSet13 = preparedStatement13.executeQuery();
                //端口
                String port = "";
                //订单号
                String order = "";
                while (resultSet13.next()) {
                    port += resultSet13.getString("p_port") + ",";
                }
                if (!port.isEmpty()) {
                    System.out.println("自动分配端口号");
                    String[] split = port.split(",");
                    for (int i = 0; i < split.length; i++) {
                        //System.out.println(order);
                        System.out.println("端口号:" + split[i]);
                        String sql4 = "SELECT * FROM orders WHERE o_complete=0 AND ISNULL(o_port) OR  o_port =''";
                        preparedStatement14 = connection.prepareStatement(sql4);
                        resultSet14 = preparedStatement14.executeQuery();
                        while (resultSet14.next()) {
                            order += resultSet14.getString("o_orderid") + ",";
                        }
                        System.out.println("order" + order);
                        if (order != "" || order == null) {
                            String[] splits = order.split(",");
                            for (int j = 0; j < splits.length; i++) {
                                System.out.println("订单号" + splits[i]);
                                String sql15 = "UPDATE orders SET o_port=? WHERE o_orderid=?";
                                preparedStatement15 = connection.prepareStatement(sql15);
                                preparedStatement15.setString(1, split[i]);
                                preparedStatement15.setString(2, splits[i]);
                                preparedStatement15.executeUpdate();
                                String sql16 = "UPDATE porttable SET p_state=1 WHERE p_port=?";
                                preparedStatement16 = connection.prepareStatement(sql16);
                                preparedStatement16.setString(1, split[i]);
                                preparedStatement16.executeUpdate();
                                System.out.println("66" + preparedStatement15.executeUpdate() + preparedStatement16.executeUpdate());
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("为空");
                }


                //定义sql语句，问号表示占位符
                String sql1 = "select * from barcode where b_barcode = ?";// 查询sql语句
                //获取预处理statement
                preparedStatement1 = connection.prepareStatement(sql1);
                //preparedStatement1，第一个参数为sql语句的序号（从1开始），第二个参数为设置的参数值
                preparedStatement1.setString(1, inputStr);
                //向数据库发出sql执行查询，查询出结果集
                resultSet1 = preparedStatement1.executeQuery();
                //  System.out.println("物品表是否存在此条形码:" + resultSet1.next());
                echoContent = "&00";
                //条形码在数据库存在


                while (resultSet1.next()) {
                    o_orderid += "," + resultSet1.getString("o_id");
                }
                System.out.println("o_orderid" + o_orderid);
                if (o_orderid != (null)) {
                    System.out.println("条形码在数据库存在");
                    System.out.println("o_orderid" + o_orderid);
                    System.out.println("订单号:" + o_orderid);
                    String[] split = o_orderid.split(",");
                    for (int i = 0; i < split.length; i++) {
                        //System.out.print(split[i]+" ");
                        //根据订单ID获取订单表
                        String sql7 = "SELECT * FROM sorting WHERE s_orderid=? AND s_barcode=?";// 查询sql语句
                        preparedStatement7 = connection.prepareStatement(sql7);
                        preparedStatement7.setString(1, split[i]);
                        preparedStatement7.setString(2, inputStr);
                        resultSet7 = preparedStatement7.executeQuery();
                        //判断详细表是否存在关于这条条形码和订单号的信息
                        System.out.println("详细表是否存在关于这条条形码和订单号的信息:" + resultSet7.next());
                        System.out.println("订单号" + split[i]);
                        resultSet7.next();
                        if (resultSet7.first()) {
                            System.out.println("进入resultSet7");
                            //获取物品的信息
                            String sql9 = "SELECT * FROM barcode WHERE b_barcode = ? AND o_id=?";
                            preparedStatement9 = connection.prepareStatement(sql9);
                            preparedStatement9.setString(1, inputStr);
                            preparedStatement9.setString(2, split[i]);
                            resultSet9 = preparedStatement9.executeQuery();
                            resultSet9.next();
                            String b_Item_info = resultSet9.getString("b_Item_info");
                            int b_number = resultSet9.getInt("b_number");
                            //根据订单ID获取订单表
                            String sql2 = "select * from orders where o_orderid = ?";// 查询sql语句
                            preparedStatement2 = connection.prepareStatement(sql2);
                            preparedStatement2.setString(1, split[i]);
                            resultSet2 = preparedStatement2.executeQuery();
                            System.out.println("sql2" + sql2);
                            //通过订单表获取到客户名称
                            resultSet2.next();
                            System.out.println("客户公司名称:" + resultSet2.getString("o_customername"));
                            echoContent = resultSet2.getString("o_port");
                            if (resultSet7.getInt("s_count") == resultSet7.getInt("s_number")) {
                                System.out.println("此条形码在这个" + split[i] + "订单号已分拣完成,判断下一个订单号");
                                echoContent = "&00";
                                continue;
                            }
                            System.out.println("存在关于这条条形码和订单号的信息");
                            //修改详细表
                            System.out.println("修改详细表");
                            String sql8 = "UPDATE sorting SET s_number=?,order_time=?  WHERE s_orderid=? AND s_barcode=?";
                            System.out.println("sql" + sql8);
                            preparedStatement8 = connection.prepareStatement(sql8);
                            preparedStatement8.setInt(1, resultSet7.getInt("s_number") + 1);
                            preparedStatement8.setString(2, sdf.format(d));
                            preparedStatement8.setString(3, split[i]);
                            preparedStatement8.setString(4, inputStr);

                            preparedStatement8.executeUpdate();
                            System.out.println(preparedStatement8.executeUpdate() + "修改成功!");
                            System.out.println("临时表");
                            String sql4 = "SELECT * FROM cursors WHERE c_order=? ";// 查询sql语句
                            preparedStatement4 = connection.prepareStatement(sql4);
                            preparedStatement4.setString(1, split[i]);
                            resultSet4 = preparedStatement4.executeQuery();
                            System.out.println("条形码是否在数据库中的临时表存在:" + resultSet1.next());
                            if (resultSet4.next()) {
                                System.out.println("临时表存在此订单的信息");
                                //如果存在则修改临时表中的分拣数量
                                //获取已分拣数量
                                int number = resultSet4.getInt("c_number") + 1;
                                int count = resultSet4.getInt("c_count");
                                if (number > count) {
                                    echoContent = "&00";
                                } else if (number <= count) {
                                    System.out.println("已分拣数量:" + resultSet4.getInt("c_number"));
                                    System.out.println("修改后的分拣数量:" + number);
                                    String sql5 = "UPDATE cursors SET c_number=?,c_barcode=?,c_current=? WHERE c_order=?";
                                    System.out.println("修改语句:" + sql5);
                                    preparedStatement5 = connection.prepareStatement(sql5);
                                    preparedStatement5.setInt(1, number);
                                    preparedStatement5.setString(2, inputStr);
                                    preparedStatement5.setString(3, sdf.format(d));
                                    preparedStatement5.setString(4, split[i]);
                                    preparedStatement5.executeUpdate();
                                    System.out.println(preparedStatement5.executeUpdate() + "修改成功!");
                                    //判断这个订单的数量是否分拣完
                                    if (number == count) {
                                        //修改订单表
                                        String sql10 = "UPDATE orders SET o_complete=1 WHERE o_orderid=?";
                                        System.out.println("修改语句sql10:" + sql10);
                                        preparedStatement10 = connection.prepareStatement(sql10);
                                        preparedStatement10.setString(1, split[i]);
                                        preparedStatement10.executeUpdate();
                                        System.out.println("修改是否成功!" + preparedStatement10.executeUpdate());
                                        System.out.println("订单" + split[i] + "已完成");
                                        echoContent = echoContent + "f";
                                    }
                                }
                                WebSocketServerHandler.channelGroup.writeAndFlush(new TextWebSocketFrame(split[i]));
                                break;
                            } else {
                                System.out.println("临时表不存在这个订单的信息");
                                System.out.println("添加到临时表的信息");
                                //添加到临时表
                                String sql3 = "insert into cursors (c_order,c_barcode,c_out,c_count,c_specification,c_describe,c_current,c_customername,c_number) values(?,?,?,?,?,?,?,?,?)"; // 添加一条sql语句
                                // 创建一个Statment对象
                                ps = connection.prepareStatement(sql3);
                                ps.setString(1, split[i]);
                                System.out.println("1" + split[i]);
                                ps.setString(2, inputStr);
                                System.out.println("2" + inputStr);
                                ps.setString(3, echoContent);
                                System.out.println("3" + echoContent);
                                ps.setInt(4, resultSet2.getInt("o_number"));
                                //  System.out.println("4" + resultSet1.getString("o_number"));
                                ps.setString(5, resultSet2.getString("o_Specifications"));
                                System.out.println("5" + resultSet2.getString("o_Specifications"));
                                //resultSet1.next();
                                ps.setString(6, b_Item_info);
                                System.out.println("6" + b_Item_info);
                                ps.setString(7, sdf.format(d));
                                System.out.println("7" + sdf.format(d));
                                ps.setString(8, resultSet2.getString("o_customername"));
                                System.out.println("8" + resultSet2.getString("o_customername"));
                                ps.setInt(9, 1);
                                ps.executeUpdate();

                                //判断是否只有一个数量
                              /*  if (b_number == 1) {
                                    echoContent = "&0f";
                                }*/
                                // 关闭数据库连接对象
                                // connection.close();
                                System.out.println("插入完毕！！！");
                                WebSocketServerHandler.channelGroup.writeAndFlush(new TextWebSocketFrame(split[i]));
                                break;
                            }


                            //break;
                        } else if (split[i].equals("null")) {
                            System.out.println("不存在关于这条条形码和订单号的信息");
                            continue;
                        }
                    }
                }
                //记录没有匹配到的条形码
                if (echoContent.equals("&00")) {
                    System.out.println("inputStr" + inputStr);
                    String sql20 = "SELECT * FROM ng ORDER BY n_id DESC LIMIT 0,1 ;";
                    preparedStatement20 = connection.prepareStatement(sql20);
                    resultSet20 = preparedStatement20.executeQuery();
                    resultSet20.next();
                    if (isBean) {
                        String sql9 = "INSERT INTO ng(n_barcode,n_date,n_number) VALUES (?,?,?)"; // 添加一条sql语句
                        // 创建一个Statment对象
                        preparedStatement19 = connection.prepareStatement(sql9);
                        preparedStatement19.setString(1, inputStr);
                        preparedStatement19.setString(2, sdf.format(d));
                        //System.out.println(resultSet20.next());
                        if (resultSet20.last()) {
                            System.out.println("有数据");
                            preparedStatement19.setInt(3, resultSet20.getInt("n_id") + 1);
                        } else {
                            System.out.println("没有数据");
                            preparedStatement19.setInt(3, 1);
                        }
                        int i = preparedStatement19.executeUpdate();
                        System.out.println("添加到ng表:" + i);
                        WebSocketServerHandler.channelGroup.writeAndFlush(new TextWebSocketFrame("ng"));
                    }

                }
                System.out.println("boolean:" + isBean);
                if (isBean) {
                    System.out.println("返回给端口的值:" + echoContent);
                    ByteBuf echoBuf = Unpooled.buffer(echoContent.length());
                    echoBuf.writeBytes(echoContent.getBytes());
                    group.writeAndFlush(echoBuf);
                }
                // }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放资源
                if (resultSet1 != null) {
                    try {
                        resultSet1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (resultSet2 != null) {
                    try {
                        resultSet2.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (resultSet6 != null) {
                    try {
                        resultSet6.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (ps != null) {
                    try {
                        preparedStatement1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (preparedStatement1 != null) {
                    try {
                        preparedStatement1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (preparedStatement2 != null) {
                    try {
                        preparedStatement1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (preparedStatement4 != null) {
                    try {
                        preparedStatement1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (preparedStatement5 != null) {
                    try {
                        preparedStatement1.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


    /**
     * 新客户端第一次连接会调用此次方法
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 先写入到客户端，最后再将自己添加到ChannelGroup中
        group.add(channel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }


}
