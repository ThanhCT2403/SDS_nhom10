package com.tn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Search {
    ConnectJDBC connectJDBC = new ConnectJDBC();
    Connection conn = connectJDBC.connect();

    public void search(int n) throws SQLException,ClassNotFoundException{
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

                    System.out.println("Tìm kiếm nhân viên ");

                        System.out.println("Nhap vào Mã nhân viên, Tên nhân viên, SĐt, Email : ");
                        Scanner scanner = new Scanner(System.in);
                        String strn = scanner.nextLine();
                        //Integer bn = Integer.parseInt(strn);

                    String sql6 = "SELECT * FROM employee WHERE id ='" + strn + "' or full_name like '"+strn+"' or phone like '"+strn+"' or email like '"+strn+"'";
                    ResultSet rs = stmt.executeQuery(sql6);

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
        GFG gfg = new GFG();
        int m=0;
        String str;
        do {
            do {
                 scanner = new Scanner(System.in);
                System.out.println("\nNhập vào 1 để thoát");
                str = scanner.nextLine();
            }while (gfg.isNumber(str)==false);
            m=Integer.parseInt(str);
        }while(m!=1);
        if(m==1){
            Menu menu = new Menu();
            menu.Menu();
        }
    }

}


