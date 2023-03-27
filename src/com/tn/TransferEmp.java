package com.tn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TransferEmp {
    ConnectJDBC connectJDBC = new ConnectJDBC();
    Connection conn = connectJDBC.connect();

    public void transfer() throws SQLException,ClassNotFoundException{
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
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
        GFG gfg = new GFG();
        int a=0;
        String strn;
        do{
            System.out.println("Nhap vao tên id phòng ban có nhân viên cần điều chuyển: ");
            Scanner scanner = new Scanner(System.in);
            strn = scanner.nextLine();
        }while (gfg.isNumber(strn)==false);
        a = Integer.parseInt(strn);
        String sql2 = "SELECT b.* FROM department a JOIN employee b ON a.id = b.department_id WHERE a.id ='"+a+"'";
        rs = stmt.executeQuery(sql2);
        if (rs.next() == true) {
            System.out.println("___________________________________________________________________________________________________________________________________________________________");
            System.out.printf("|%-5s | %-20s | %-20s | %-10s | %-10s | %-20s | %-10s | %-10s | %-10s | %-10s | \n", "Id", "Full name", "Email", "Gender", "Phone", "Address", "Birthday", "Salary", "Department", "Position","|");
            System.out.println("|_________________________________________________________________________________________________________________________________________________________|");
            rs.beforeFirst();
            while (rs.next()) {
                int id2 = rs.getInt("id");
                String fn = rs.getString("full_name");
                String em = rs.getString("email");
                String gd = rs.getString("gender");
                String ph = rs.getString("phone");
                String ad = rs.getString("address");
                String bd = rs.getString("dateofbirth");
                String sl = rs.getString("salary");
                double sl1 = Double.parseDouble(sl);
                int dp = rs.getInt("department_id");
                String im = rs.getString("position");
                System.out.printf("|%-5s | %-20s | %-20s | %-10s | %-10s | %-20s | %-10s | %-10s | %-10s | %-10s | \n", id2, fn, em, gd, ph, ad, bd, String.format("%,.0f",sl1), dp, im);
            }
            System.out.println("|_________________________________________________________________________________________________________________________________________________________|");
        } else {
            System.out.println("Khong co du lieu");
            Menu menu = new Menu();
            menu.Menu();
        }
        int b= 0;
        do{
            System.out.println("Nhap vao id nhân viên cần điều chuyển: ");
            Scanner scanner = new Scanner(System.in);
            strn = scanner.nextLine();
        }while (gfg.isNumber(strn)==false);
        b = Integer.parseInt(strn);

        String sql3 = "SELECT id, name FROM department WHERE id !='"+a+"'";
        //stmt.executeUpdate(sql);
        rs = stmt.executeQuery(sql3);
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
        int c= 0;
        do{
            System.out.println("Nhap vao id phòng ban nhân viên chuyển đến: ");
            Scanner scanner = new Scanner(System.in);
            strn = scanner.nextLine();
        }while (gfg.isNumber(strn)==false);
        c = Integer.parseInt(strn);

        String sql5 = "SELECT COUNT(id) as coun from department";
        rs = stmt.executeQuery(sql5);
        rs.next();
        int h = rs.getInt("coun");
            while (c < 0 || c > h) {
                do {
                    System.out.println("Nhap lại id phòng ban nhân viên chuyển đến: ");
                    Scanner scanner = new Scanner(System.in);
                    strn = scanner.nextLine();
                }while (gfg.isNumber(strn)==false);
                c = Integer.parseInt(strn);
            }

        String sql4 = "UPDATE employee SET department_id = '" + c + "' WHERE id='" + b + "'";
        stmt.executeUpdate(sql4);
        sql3 = "SELECT full_name FROM employee WHERE id ='"+b+"'";
        rs = stmt.executeQuery(sql3);
        rs.next();
        String v = rs.getString("full_name");
        sql4 = "SELECT name FROM department WHERE id ='"+c+"'";
        rs = stmt.executeQuery(sql4);
        rs.next();
        String g = rs.getString("name");

        System.out.println("Chuyển nhân viên "+v+" sang phòng ban "+g+" thành công");

        int m;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\nNhập vào 1 để thoát");
            m = scanner.nextInt();
        }while(m!=1);
        if(m==1){
            Menu menu = new Menu();
            menu.Menu();
        }
    }
}
