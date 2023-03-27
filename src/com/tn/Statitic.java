package com.tn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Statitic {
    ConnectJDBC connectJDBC = new ConnectJDBC();
    Connection connection = connectJDBC.connect();

    public void staticEm(int n) throws SQLException, ClassNotFoundException {
        System.out.println("-------------------------------------");
        System.out.println("1. top 5 nhân viên lương cao nhất");
        System.out.println("2. top 5 nhân viên lương thấp nhất");
        System.out.println("3. Thoát");
        GFG gfg = new GFG();

        n=0;
        String strn;
        do{
            do{
                System.out.println("Nhập vào lựa chọn từ 1-3 : ");
                Scanner scanner = new Scanner(System.in);
                strn = scanner.nextLine();
            }while (gfg.isNumber(strn)==false||strn==null);
            n = Integer.parseInt(strn);
        }while(n<1||n>3);

        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        if (n == 1) {
            System.out.println("top 5 nhân viên lương cao");
            String sql = "SELECT * FROM employee\n" +
                    "ORDER BY salary DESC LIMIT 5";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("success");
            if (rs.next()) {
                System.out.println("__________________________________________________________________________________");
                System.out.printf("|%-10s | %-20s | %-20s | %-20s | \n", "Id", "Full Name", "Phone", "Salary");
                System.out.println("|________________________________________________________________________________|");
                rs.beforeFirst();
                while (rs.next()) {
                    int id3 = rs.getInt("id");
                    String fullName = rs.getString("full_name");
                    String phone = rs.getString("phone");
                    String salary = rs.getString("salary");
                    Double sl1 = Double.parseDouble(salary);
                    System.out.printf("|%-10s | %-20s | %-20s | %-20s | \n", id3, fullName, phone, String.format("%,.0f",sl1));
                }
                System.out.println("|________________________________________________________________________________|");
            } else {
                System.out.printf("ko có dữ liệu");
                Menu menu = new Menu();
                menu.Menu();
            }
        }if (n == 2) {
            System.out.println("top 5 nhân viên lương thấp ");
            String sql = "SELECT * FROM employee\n" +
                    "ORDER BY salary asc LIMIT 5";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("success");
            if (rs.next()) {
                System.out.println("__________________________________________________________________________________");
                System.out.printf("|%-10s | %-20s | %-20s | %-20s | \n", "Id", "Full Name", "Phone", "Salary");
                System.out.println("|________________________________________________________________________________|");
                rs.beforeFirst();
                while (rs.next()) {
                    int id3 = rs.getInt("id");
                    String fullName = rs.getString("full_name");
                    String phone = rs.getString("phone");
                    String salary = rs.getString("salary");
                    Double sl1 = Double.parseDouble(salary);
                    System.out.printf("|%-10s | %-20s | %-20s | %-20s | \n", id3, fullName, phone, String.format("%,.0f",sl1));
                }
                System.out.println("|________________________________________________________________________________|");
            } else {
                System.out.printf("ko có dữ liệu");
                Menu menu = new Menu();
                menu.Menu();
            }
        } else if (n==3) {
            Menu menu = new Menu();
            menu.Menu();
        }
    }
}
