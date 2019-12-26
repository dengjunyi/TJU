package com.example.comm.server;


import com.example.comm.controller.SortingController;
import com.example.comm.pojo.barcode.Barcode;
import com.example.comm.pojo.cursors.Cursors;
import com.example.comm.pojo.customer.Customer;
import com.example.comm.pojo.ng.Ng;
import com.example.comm.pojo.orders.Orders;
import com.example.comm.pojo.porttable.Porttable;
import com.example.comm.pojo.sorting.Sorting;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

//客户和订单自动分配,都是安照一个订单完成后,端口清空,分配下一个订单
public class WebSocketServerHandler2_dingdanhao5 extends ChannelHandlerAdapter {


    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    List<ChannelId> list = new ArrayList<>();

    /**
     * 当客户端连接到服务器端之后，需要有一个连接激活的方法，此方法可以直接向客户端发送消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("************* 硬件channelRead *************");
        System.out.println("msg" + msg);
        String inputStr;

        //group.find(channel.id());
        if (msg instanceof String) {
            //从网页传过来的条形码
            System.out.println("我是一个字符串");
            inputStr = (String) msg;
        } else {
            //硬件返回的值
            System.out.println("我不是一个字符串");
            ByteBuf in = (ByteBuf) msg; // 1、接收消息内容
            inputStr = in.toString(CharsetUtil.UTF_8); // 2、得到用户发送的数据
        }
        inputStr = inputStr.replaceAll(" ", "");
        System.out.println("长度:" + inputStr.length());
        if (inputStr != null) {
            //获取当前时间
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
            System.out.println("格式化输出：" + sdf.format(d));
            //获取存在此条形码的所有订单号
            String o_orderid = "";
            //返回值给端口的值
            String echoContent = null;
            //端口
            String port = "";
            //客户未分拣完成的ID
            String c_id = "";
            //订单号(客户所对应的未分配订单ID)
            String order = "";

            //判断是否返回值给端口
            Boolean isBean = true;
            String sub1 = inputStr.substring(0, inputStr.length() - 1);
            System.out.println("截取最后一个字符串生成的新字符串为: " + inputStr.substring(0, inputStr.length() - 1));//abcdef
            String sub2 = inputStr.substring(inputStr.length() - 1);
            System.out.println("最后一个字符" + inputStr.substring(inputStr.length() - 1));//输出d

            if (inputStr.length() == 10) {
                System.out.println("机器端返回的值");
                sub1 = inputStr.substring(0, 3);
                System.out.println("sub1:" + sub1);
                sub2 = inputStr.substring(6, 7);
                System.out.println("sub2:" + sub2);
            }
            if (!sub1.equals("&00")) {
                //判断获取的端口的返回值
                Porttable porttableByPort = SortingController.porttableService2.getPorttableByPort(sub1);
                if (porttableByPort != null) {
                    System.out.println("说明这个端口号存在");
                    //如果截取到最后一个字符等于s
                    if (sub2.equals("s")) {
                        System.out.println("这个端口已完成订单");
                        //修改订单状态为0
                        int updatePorttablei = SortingController.porttableService2.updatePorttable(sub1);
                        System.out.println("updatePorttablei:" + updatePorttablei);
                        isBean = false;
                    }
                    if (sub2.equals("#")) {
                        System.out.println("不用返回值给端口");
                        isBean = false;
                    }
                }
            }
            if (sub2.equals("#") && sub1.equals("&00")) {
                System.out.println("不用返回值给端口");
                isBean = false;
            }

            //自动分配端口,分配给订单端口
            List<Porttable> porttableList = SortingController.porttableService2.getPorttable();
            System.out.println("未分配端口的个数:" + porttableList.size());
            if (porttableList.size() > 0) {
                for (Porttable porttable : porttableList) {
                    port += porttable.getP_port() + ",";
                }
                System.out.println("所有未分配的端口号:" + port);
                if (!port.isEmpty()) {
                    System.out.println("自动分配端口号");
                    String[] split = port.split(",");
                    for (int i = 0; i < split.length; i++) {
                        System.out.println("端口号:" + split[i]);
                        if (split[i].isEmpty()) {
                            System.out.println("没有端口号要分配的");
                            break;
                        }
                        //根据客户分拣
                        if (inputStr.equals("customer")) {
                            show1(split[i], c_id, order);
                        }
                        //根据订单分拣
                        if (inputStr.equals("barcode")) {
                             show2(i,order,split[i]);
                        }
                    }
                } else {
                    System.out.println("没有要分配的端口");
                }
            }
            System.out.println("inputStr" + inputStr);
            System.out.println(!inputStr.equals("customer") && !inputStr.equals("barcode"));
            if (!inputStr.equals("customer") && !inputStr.equals("barcode")) {
                //条形码进行对比
                List<Barcode> barcodeByBarcodeList = SortingController.barcodeService2.getBarcodeByBarcode(inputStr);
                System.out.println("条码对象的个数:" + barcodeByBarcodeList.size());
                if (barcodeByBarcodeList.size() > 0) {
                    for (Barcode barcode : barcodeByBarcodeList) {
                        o_orderid += barcode.getO_id() + ",";
                    }
                    System.out.println("条形码对应的所有订单ID:" + o_orderid);
                    String[] split = o_orderid.split(",");
                    for (int i = 0; i < split.length; i++) {
                        System.out.print(split[i] + " ");
                        //根据订单ID和条形码查询详细表
                        System.out.println("订单号" + split[i]);
                        Sorting sortingByOidByBarcode = SortingController.sortingService2.getSortingByOidByBarcode(split[i], inputStr);
                        if (sortingByOidByBarcode != null) {
                            Barcode barcodeByOidByBarcode = SortingController.barcodeService2.getBarcodeByOidByBarcode(inputStr, split[i]);
                            if (barcodeByOidByBarcode != null) {
                                Orders ordersByOid = SortingController.ordersService2.getOrdersByOid(split[i]);
                                //获取对应的端口号
                                echoContent = ordersByOid.getO_port();
                                System.out.println("echoContent" + echoContent);
                                //判断此订单是否存在端口号
                                if (echoContent != null) {
                                    String b_Item_info = barcodeByOidByBarcode.getB_Item_info();
                                    int b_number = barcodeByOidByBarcode.getB_number();
                                    Customer customerByCid = SortingController.customerService2.getCustomerByCid(ordersByOid.getC_id());
                                    System.out.println("客户公司名称:" + customerByCid.getCustomer_name());
                                    if (sortingByOidByBarcode.getS_count() == sortingByOidByBarcode.getS_number()) {
                                        System.out.println("此条形码在这个" + split[i] + "订单号已分拣完成,判断下一个订单号");
                                        echoContent = "&00";
                                        continue;
                                    }
                                    System.out.println("存在关于这条条形码和订单号的信息");
                                    //修改详细表
                                    System.out.println("修改详细表");
                                    Sorting sorting = new Sorting();
                                    sorting.setS_number(sortingByOidByBarcode.getS_number() + 1);
                                    sorting.setOrder_time(sdf.format(d));
                                    sorting.setS_orderid(split[i]);
                                    sorting.setS_barcode(inputStr);
                                    int updateSorting = SortingController.sortingService2.updateSorting(sorting);
                                    System.out.println("修改详细表:" + updateSorting);

                                    //重复
                                    Cursors cursorsBybarcode = SortingController.cursoursService2.getCursorsBybarcode(split[i]);
                                    System.out.println("临时表");
                                    if (cursorsBybarcode != null) {
                                        System.out.println("临时表存在此订单的信息");
                                        //如果存在则修改临时表中的分拣数量
                                        //获取已分拣数量
                                        int number = cursorsBybarcode.getC_number() + 1;
                                        int count = cursorsBybarcode.getC_count();
                                        if (number > count) {
                                            echoContent = "&00";
                                        } else if (number <= count) {
                                            System.out.println("已分拣数量:" + (number - 1));
                                            System.out.println("修改后的分拣数量:" + number);
                                            Cursors cursors = new Cursors();
                                            cursors.setC_number(number);
                                            cursors.setC_barcode(inputStr);
                                            cursors.setC_time(d);
                                            cursors.setC_order(split[i]);
                                            int updateCursors = SortingController.cursoursService2.updateCursors(cursors);
                                            System.out.println("临时表:" + updateCursors);
                                            //判断这个订单的数量是否分拣完
                                            if (number == count) {
                                                //修改订单表
                                                //订单ID split[i]
                                                int updateOrdersByOid = SortingController.ordersService2.updateOrdersByOid(split[i]);
                                                System.out.println("修改是否成功!" + updateOrdersByOid);
                                                System.out.println("订单" + split[i] + "已完成");
                                                echoContent = echoContent + "f";
                                                //判断此客户ID的所有订单未完成的个数
                                                int ordersByCids = SortingController.ordersService2.getOrdersByCids(ordersByOid.getC_id());
                                                if (ordersByCids == 0) {
                                                    int updateCustomerByCids = SortingController.customerService2.updateCustomerByCids(ordersByOid.getC_id());
                                                    System.out.println("客户状态是否修改成功:" + updateCustomerByCids);
                                                }
                                            }
                                        }
                                        WebSocketServerHandler.channelGroup.writeAndFlush(new TextWebSocketFrame(split[i]));
                                        break;
                                    } else {
                                        System.out.println("临时表不存在这个订单的信息");
                                        System.out.println("添加到临时表的信息");
                                        //添加到临时表
                                        Cursors cursors = new Cursors();
                                        cursors.setC_order(split[i]);
                                        System.out.println("订单号:" + split[i]);
                                        cursors.setC_barcode(inputStr);
                                        System.out.println("条形码:" + inputStr);
                                        cursors.setC_out(echoContent);
                                        System.out.println("返回值:" + echoContent);
                                        cursors.setC_count(ordersByOid.getO_number());
                                        System.out.println("此订单的总数量:" + ordersByOid.getO_number());
                                        cursors.setC_specification(ordersByOid.getO_Specifications());
                                        System.out.println("规格:" + ordersByOid.getO_Specifications());
                                        cursors.setC_Item_info(b_Item_info);
                                        System.out.println("物品描述:" + b_Item_info);
                                        cursors.setC_time(d);
                                        System.out.println("时间:" + sdf.format(d));
                                        cursors.setC_customername(customerByCid.getCustomer_name());
                                        System.out.println("客户名称:" + customerByCid.getCustomer_name());
                                        cursors.setC_number(1);
                                        int addCursors = SortingController.cursoursService2.addCursors(cursors);
                                        System.out.println("临时表是否添加成功:" + addCursors);
                                        //判断是否只有一个数量
                                        if (b_number == 1) {
                                            echoContent = "&0f";
                                        }
                                        // 关闭数据库连接对象
                                        // connection.close();
                                        System.out.println("插入完毕！！！");
                                        WebSocketServerHandler.channelGroup.writeAndFlush(new TextWebSocketFrame(split[i]));
                                        break;
                                    }
                                } else {
                                    System.out.println("此订单没有分配端口,流入NG");
                                    continue;
                                }
                            }
                        } else if (split[i].equals("null")) {
                            System.out.println("不存在关于这条条形码和订单号的信息");
                            continue;
                        }
                    }
                } else {
                    echoContent = "&00";
                }

                System.out.println("echoContent" + echoContent);
                //记录没有匹配到的条形码
                if (echoContent.equals("&00")) {
                    System.out.println("inputStr" + inputStr);
                    Ng ng = SortingController.ngService2.getNg();
                    System.out.println("isBean:" + isBean);
                    if (isBean) {
                        Ng ng1 = new Ng();
                        ng1.setN_barcode(inputStr);
                        ng1.setN_date(d);
                        if (ng != null) {
                            ng1.setN_number(ng.getN_id() + 1);
                        } else {
                            ng1.setN_number(1);
                        }
                        int addNg = SortingController.ngService2.addNg(ng1);
                        System.out.println("添加到ng表:" + addNg);
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
            } else {
                System.out.println("端口分配");
            }

        }
    }

    /**
     * 新客户端第一次连接会调用此次方法
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println("新客户端第一次连接会调用此次方法");
        System.out.println("3:" + channel.id());
       /* if (channel.id() != null) {
            list.add(channel.id());
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("第" + (i + 1) + "客户端的ID:" + list.get(i));
        }*/


       /* ByteBuf echoBuf = Unpooled.buffer(echoContent.length());
        echoBuf.writeBytes(echoContent.getBytes());
        group.writeAndFlush(echoBuf);*/
        //channel.writeAndFlush(new TextWebSocketFrame("ng1"));

        // 先写入到客户端，最后再将自己添加到ChannelGroup中
        group.add(channel);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    public static void show1(String port, String c_id, String order) throws Exception {
        System.out.println("跟据客户分配端口");
        //订单号(客户所对应的未分配订单ID)
        List<Customer> customerBystate = SortingController.customerService2.getCustomerBystate();
        c_id = "";
        if (customerBystate.size() > 0) {
            for (Customer customer : customerBystate) {
                c_id += customer.getC_id() + ",";
            }
            System.out.println("所有未分配的客户ID:" + c_id);
            System.out.println("有客户还未完成分拣");
            String[] splits = c_id.split(",");
            for (int i1 = 0; i1 < splits.length; i1++) {
                if (splits[i1].isEmpty()) {
                    System.out.println("未分拣完的客户的订单已有端口号");
                    break;
                }
                order = "";
                System.out.println("i1:" + i1);
                System.out.println("客户所对应的ID:" + splits[i1]);
                //查询此客户的订单里未分拣完成的订单
                List<Orders> ordersByCid = SortingController.ordersService2.getOrdersByCid(parseInt(splits[i1]));
                System.out.println("ordersByCid:" + ordersByCid.size());
                for (Orders orders : ordersByCid) {
                    order += orders.getO_orderid() + ",";
                }
                System.out.println("客户所对应的订单号:" + order);
                if (order.isEmpty()) {
                    break;
                }
                if (!order.isEmpty()) {
                    String[] splitss = order.split(",");
                    int s = splitss.length;
                    for (int k = 0; k < splitss.length; k++) {
                        System.out.println("s" + s);
                        System.out.println("订单号" + splitss[k]);
                        //修改订单表,分配给订单端口号
                        int updateOrdersByOidPort = SortingController.ordersService2.updateOrdersByOidPort(port, splitss[k]);
                        System.out.println("updateOrdersByOidPort:" + updateOrdersByOidPort);
                        //修改这个端口的状态
                        int updatePorttableByPort = SortingController.porttableService2.updatePorttableByPort(port);
                        System.out.println("updatePorttableByPort:" + updatePorttableByPort);
                        break;
                    }
                    //修改此订单端口分配状态(重复)
                    //客户ID
                    System.out.println("ID:" + splits[i1]);
                    List<Orders> ordersByCid1 = SortingController.ordersService2.getOrdersByCid(parseInt(splits[i1]));
                    System.out.println("ordersByCid1的个数1:" + ordersByCid1.size());
                    int count = 0;
                    for (Orders orders : ordersByCid1) {
                        count++;
                    }
                    System.out.println("count:" + count);
                    if (count == 0) {
                        //客户的所有订单都分配到端口号,修改客户的端口分配状态
                        System.out.println("修改的ID:" + splits[i1]);
                        int updateCustomerByCid = SortingController.customerService2.updateCustomerByCid(parseInt(splits[i1]));
                        System.out.println("updateCustomerByCid:" + updateCustomerByCid);
                        break;
                    } else {
                        System.out.println("进行下一轮端口分配");
                        break;
                    }
                }
            }
        }
    }

    public static void show2(Integer i,String order,String port) throws Exception {
        System.out.println("根据订单自动分配端口号");
        //查询订单表未分拣完成的和未分配端口号
        List<Orders> ordersList = SortingController.ordersService2.getOrders();
        System.out.println("ordersList:" + ordersList);
        if (ordersList.size() > 0) {
            order = "";
            for (Orders orders : ordersList) {
                order += orders.getO_orderid() + ",";
            }
            System.out.println("order" + order);
            if (order != "" || order != null) {
                String[] splits = order.split(",");
                for (int j = 0; j < splits.length; i++) {
                    System.out.println("订单号" + splits[j]);
                    if(splits[j].isEmpty()){
                        System.out.println("订单为空");
                        break;
                    }
                    System.out.println("端口号:" + port);
                    //修改订单表,分配给订单端口号(重复)
                    int updateOrdersByOidPort = SortingController.ordersService2.updateOrdersByOidPort(port, splits[j]);
                    System.out.println("updateOrdersByOidPort:" + updateOrdersByOidPort);
                    //修改这个端口的状态(重复)
                    System.out.println("111");
                    SortingController.porttableService2.updatePorttableByPort(port);
                    //修改此订单端口分配状态(重复)
                    System.out.println("2222");
                    //客户ID
                    System.out.println("订单ID:" + splits[j]);
                    Orders ordersByOid = SortingController.ordersService2.getOrdersByOid(splits[j]);
                    System.out.println("Cid:" + ordersByOid.getC_id());
                    List<Orders> ordersByCid1 = SortingController.ordersService2.getOrdersByCid(ordersByOid.getC_id());
                    System.out.println("ordersByCid1的个数1:" + ordersByCid1.size());
                    int count = 0;
                    for (Orders orders : ordersByCid1) {
                        count++;
                    }
                    System.out.println("count:" + count);
                    if (count == 0) {
                        //客户的所有订单都分配到端口号,修改客户的端口分配状态
                        System.out.println("修改的ID:" + ordersByOid.getC_id());
                        int updateCustomerByCid = SortingController.customerService2.updateCustomerByCid(ordersByOid.getC_id());
                        System.out.println("updateCustomerByCid:" + updateCustomerByCid);
                    } else {
                        System.out.println("进行下一轮端口分配");
                    }
                    break;
                }
            }
        }
    }


}
