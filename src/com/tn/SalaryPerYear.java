package com.tn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SalaryPerYear {
    ConnectJDBC connectJDBC = new ConnectJDBC();
    Connection connection = connectJDBC.connect();

    public void salaryPerYear(int n) throws SQLException, ClassNotFoundException {
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        String sql = "SELECT YEAR(datejoin), SUM(salary)\n" +
                "FROM employee\n" +
                "GROUP BY YEAR(datejoin)";
        ResultSet rs = stmt.executeQuery(sql);
        //System.out.println(rs);
        System.out.println("print successfully");

        if (rs.next()) {
            System.out.println("____________________________________");
            System.out.printf("|%-10s | %-20s | \n", "Year", "Sum");
            System.out.println("|__________________________________|");
            rs.beforeFirst();
            while (rs.next()) {
                int year = rs.getInt("YEAR(datejoin)");
                String sum = rs.getString("SUM(salary)");
                Double sum1 = Double.parseDouble(sum);

                System.out.printf("|%-10s | %-20s | \n",year,String.format("%,.0f",sum1));
            }
            System.out.println("|__________________________________|");
        } else {
            System.out.println("ko có dữ liệu");
            Menu menu = new Menu();
            menu.Menu();
        }
        GFG gfg = new GFG();
        int m=0;
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
