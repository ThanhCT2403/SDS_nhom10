package com.tn;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TaxEmployee {
    ConnectJDBC connectJDBC = new ConnectJDBC();
    Connection conn = connectJDBC.connect();

    public void taxEmployee() throws SQLException,ClassNotFoundException {
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        GFG gfg = new GFG();
        int d=0;
        String strn;
        do{
            System.out.println("Nhap vao id nhân viên cần tính thuế TNCN: ");
            Scanner scanner = new Scanner(System.in);
            strn = scanner.nextLine();
        }while (gfg.isNumber(strn)==false);
        d = Integer.parseInt(strn);
        String sql = "SELECT * FROM employee WHERE id='" + d + "'";
        ResultSet rs = stmt.executeQuery(sql);
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
        sql = "SELECT full_name, salary FROM employee WHERE id='" + d + "'";
        rs = stmt.executeQuery(sql);
        rs.next();
        String fn = rs.getString("full_name");
        float sl = rs.getFloat("salary");
        Tax tax = new Tax();
        float t = tax.tax(sl);
        System.out.println("Thuế TNCN của nhân viên "+fn+" là: "+t);
        String sql1 = "UPDATE employee SET tax = '" + t + "' WHERE id='" + d + "'";
        stmt.executeUpdate(sql1);
        int m;
        String str;
        do {
            do {
                Scanner scanner = new Scanner(System.in);
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
