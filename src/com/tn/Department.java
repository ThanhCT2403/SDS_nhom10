package com.tn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Department{
    ConnectJDBC connectJDBC = new ConnectJDBC();
    Connection conn = connectJDBC.connect();

    public void depart(int n) throws SQLException,ClassNotFoundException{
        System.out.println("-------------------------------------");
        System.out.println("1. Danh sách phòng ban");
        System.out.println("2. Thêm phong ban");
        System.out.println("3. Sửa phòng ban");
        System.out.println("4. Xóa phòng ban");
        System.out.println("5. Thoát!");
        GFG gfg = new GFG();

        n=0;
        String strn;
        do{
            do{
                System.out.println("Nhập vào lựa chọn từ 1-5 : ");
                Scanner scanner = new Scanner(System.in);
                strn = scanner.nextLine();
            }while (gfg.isNumber(strn)==false||strn==null);
            n = Integer.parseInt(strn);
        }while(n<1||n>5);
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        while(true) {
            if(n== 1) {
                System.out.println("Danh sách phòng ban");
                String sql1 = "SELECT * FROM department";
                //stmt.executeUpdate(sql);
                ResultSet rs = stmt.executeQuery(sql1);
                //System.out.println(rs);

                if (rs.next() == true) {
                    System.out.println("___________________________________________________________");
                    System.out.printf("|%-10s | %-20s | %-20s | \n", "Id", "Department Name", "Department location");
                    System.out.println("|_________________________________________________________|");
                    rs.beforeFirst();
                    while (rs.next()) {
                        int id2 = rs.getInt("id");
                        String deptname = rs.getString("name");
                        String deptloc = rs.getString("location");
                        System.out.printf("|%-10s | %-20s | %-20s | \n", id2, deptname, deptloc);
                    }
                    System.out.println("|_________________________________________________________|");
                } else {
                    System.out.println("Khong co du lieu");
                    Menu menu = new Menu();
                    menu.Menu();
                }
                break;
            }
            if(n== 2) {
                System.out.println("Thêm phòng ban");
                System.out.println("Nhap vao tên phòng ban: ");
                Scanner scanner = new Scanner(System.in);
                String a = scanner.nextLine();
                String sql6 = "SELECT id FROM department where name = '"+a+"'";
                ResultSet rs = stmt.executeQuery(sql6);
                //rs.next();
                //String mail = rs.getString("full_name");
                while (rs.next()==true) {
                    System.out.println("Nhap vào tên phòng ban khác do phòng ban này đã tồn tại trong CSDL: ");
                    scanner = new Scanner(System.in);
                    a = scanner.nextLine();
                }
                System.out.println("Nhap vao địa chỉ: ");
                scanner = new Scanner(System.in);
                String b = scanner.nextLine();

                //Statement stmt1 = conn.createStatement();

                    String sql2 = "INSERT INTO department(name, location) VALUES ( '" + a + "', '" + b + "')";
                    stmt.executeUpdate(sql2);
                    System.out.println("Them phòng ban "+a+" thanh cong");


                break;
            }
            if(n== 3) {
                System.out.println("Sửa phòng ban");
                int d=0;
                do{
                    System.out.println("Nhap vao id phòng ban cần sửa: ");
                    Scanner scanner = new Scanner(System.in);
                    strn = scanner.nextLine();
                }while (gfg.isNumber(strn)==false);
                d = Integer.parseInt(strn);
                String sql6 = "SELECT * FROM department WHERE id='" + d + "'";
                ResultSet rs = stmt.executeQuery(sql6);

                if (rs.next() == true) {
                    System.out.println("___________________________________________________________");
                    System.out.printf("|%-10s | %-20s | %-20s | \n", "Id", "Department Name", "Department location");
                    System.out.println("|_________________________________________________________|");
                    rs.beforeFirst();
                    while (rs.next()) {
                        int id2 = rs.getInt("id");
                        String deptname = rs.getString("name");
                        String deptloc = rs.getString("location");
                        System.out.printf("|%-10s | %-20s | %-20s | \n", id2, deptname, deptloc);
                    }
                    System.out.println("|_________________________________________________________|");
                } else {
                    System.out.println("Khong co du lieu");
                    Menu menu = new Menu();
                    menu.Menu();
                }
                System.out.println("-------------------------------------");
                System.out.println("Bạn xác nhận muốn sửa thông tin phong ban này\n(1. Yes/2. No)");
                Scanner scanner = new Scanner(System.in);
                strn = scanner.nextLine();
                do{
                    do{
                        System.out.println("Nhập vào lựa chọn từ (1.Yes-2.No) : ");
                        scanner = new Scanner(System.in);
                        strn = scanner.nextLine();
                    }while (gfg.isNumber(strn)==false);
                    n = Integer.parseInt(strn);
                }while(n<1||n>2);
                if (n == 1) {
                    System.out.println("Nhap vao tên phòng ban cần sửa: ");
                    scanner = new Scanner(System.in);
                    String fn = scanner.nextLine();
                    sql6 = "SELECT id FROM department where name = '"+fn+"'";
                    rs = stmt.executeQuery(sql6);
                    //rs.next();
                    //String mail = rs.getString("full_name");
                    while (rs.next()==true) {
                        System.out.println("Nhap vào tên phòng ban khác do phòng ban này đã tồn tại trong CSDL: ");
                        scanner = new Scanner(System.in);
                        fn = scanner.nextLine();
                    }
                    System.out.println("Nhap vào địa chỉ phòng ban cần sửa: ");
                    scanner = new Scanner(System.in);
                    String un = scanner.nextLine();

                    //Connection connection1 = null;

                    //Statement stmt2 = conn.createStatement();
                        String sql3 = "UPDATE department SET name = '" + fn + "',location = '" + un + "' WHERE id='" + d + "'";
                        stmt.executeUpdate(sql3);
                        System.out.println("Sửa phòng ban thành công");

                } else if (n == 2) {
                    System.out.println("Thoát sửa phòng ban");
                    Menu menu = new Menu();
                    menu.Menu();

                }
                break;
            }
            if(n== 4) {
                System.out.println("Xoá phòng ban");
                int e=0;
                do{
                    System.out.println("Nhap vao id phòng ban cần xóa: ");
                    Scanner scanner = new Scanner(System.in);
                    strn = scanner.nextLine();
                }while (gfg.isNumber(strn)==false);
                e = Integer.parseInt(strn);


                String sql6 = "SELECT * FROM department WHERE id='" + e + "'";
                ResultSet rs = stmt.executeQuery(sql6);
                if (rs.next() == true) {
                    System.out.println("___________________________________________________________");
                    System.out.printf("|%-10s | %-20s | %-20s | \n", "Id", "Department Name", "Department location");
                    System.out.println("|_________________________________________________________|");
                    rs.beforeFirst();
                    while (rs.next()) {
                        int id2 = rs.getInt("id");
                        String deptname = rs.getString("name");
                        String deptloc = rs.getString("location");
                        System.out.printf("|%-10s | %-20s | %-20s | \n", id2, deptname, deptloc);
                    }
                    System.out.println("|_________________________________________________________|");
                } else {
                    System.out.println("Khong co du lieu");
                    Menu menu = new Menu();
                    menu.Menu();
                }


                System.out.println("-------------------------------------");
                System.out.println("Bạn xác nhận muốn xóa thông tin phòng ban này\n(1. Yes/2. No)");
                Scanner scanner = new Scanner(System.in);
                do{
                    do{
                        System.out.println("Nhập vào lựa chọn từ (1.Yes-2.No) : ");
                        scanner = new Scanner(System.in);
                        strn = scanner.nextLine();
                    }while (gfg.isNumber(strn)==false);
                    n = Integer.parseInt(strn);
                }while(n<1||n>2);
                sql6 = "SELECT COUNT(b.id) as soluong FROM department a JOIN employee b ON a.id = b.department_id WHERE a.id='" + e + "'";
                rs = stmt.executeQuery(sql6);
                rs.next();
                int h = rs.getInt("soluong");
                if (n == 1) {
                    if (h == 0) {
                        String sql4 = "DELETE FROM department WHERE id='" + e + "'";
                        stmt.executeUpdate(sql4);
                        System.out.println("Xóa phòng ban thành công");
                    } else {
                        System.out.println("Khong the xoa do con nhan vien");
                    }
                } else if (n == 2) {
                    System.out.println("Thoát xóa phòng ban");
                    Menu menu = new Menu();
                    menu.Menu();
                }
                break;
            }
            if(n== 5){
                Menu menu = new Menu();
                menu.Menu();
            }
        }


    }
}
