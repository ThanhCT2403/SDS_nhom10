package com.tn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddDelEmpToDept {
    ConnectJDBC connectJDBC = new ConnectJDBC();
    Connection connection = connectJDBC.connect();

    public void addDelEmployee(int n) throws SQLException, ClassNotFoundException {
        System.out.println("-------------------------------------");
        System.out.println("1. Thêm nhân viên vào phòng ban");
        System.out.println("2. Xóa nhân viên khỏi phong ban");
        System.out.println("3. Thoát!");
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
        while (true) {
            if (n == 1) {
                System.out.println("Thêm nhân viên vào phòng ban trống");
                String sql = "SELECT * FROM employee WHERE department_id is NULL";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println(rs);
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
                Scanner scanner = new Scanner(System.in);
                int m=0;
                do{
                    System.out.println("Nhập vào id nhân viên thêm phòng ban");
                    scanner = new Scanner(System.in);
                    strn = scanner.nextLine();
                }while (gfg.isNumber(strn)==false);
                m = Integer.parseInt(strn);


                String sql1 = "SELECT * FROM department";
                //stmt.executeUpdate(sql);
                rs = stmt.executeQuery(sql1);
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
                System.out.println("Nhập vào id phòng ban");
                int k=0;
                do{
                    System.out.println("Nhập vào id phòng ban");
                    scanner = new Scanner(System.in);
                    strn = scanner.nextLine();
                }while (gfg.isNumber(strn)==false);
                k = Integer.parseInt(strn);

                String sql2 = "update employee set department_id = '" + k + "' where id='" + m + "'";
                stmt.executeUpdate(sql2);
                //System.out.println(rs2);
                String sql4 = "SELECT full_name FROM employee WHERE id ='"+m+"'";
                rs = stmt.executeQuery(sql4);
                rs.next();
                String v = rs.getString("full_name");
                sql4 = "SELECT name FROM department WHERE id ='"+k+"'";
                rs = stmt.executeQuery(sql4);
                rs.next();
                String g = rs.getString("name");
                System.out.println("Nhân viên "+v+" được thêm vào phòng ban "+k+" thành công");
                break;

            } else if (n == 2) {
                System.out.println("Xóa nhân viên khỏi phòng ban ");
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
                Scanner scanner = new Scanner(System.in);

                int h=0;
                do{
                    System.out.println("Nhập vào id phòng ban có nhân viên cần xóa khỏi:");
                    scanner = new Scanner(System.in);
                    strn = scanner.nextLine();
                }while (gfg.isNumber(strn)==false);
                h = Integer.parseInt(strn);

                String sql = "SELECT * FROM employee WHERE department_id ='" + h + "'";
                rs = stmt.executeQuery(sql);
                System.out.println(rs);
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
                scanner = new Scanner(System.in);
                int m=0;
                do{
                    System.out.println("Nhập vào id nhân viên cần xóa khỏi phòng ban:");
                    scanner = new Scanner(System.in);
                    strn = scanner.nextLine();
                }while (gfg.isNumber(strn)==false);
                m = Integer.parseInt(strn);



                String sql2 = "update employee set department_id = null where id='" + m + "'";
                stmt.executeUpdate(sql2);
                //System.out.println(rs2);
                String sql3 = "SELECT full_name FROM employee WHERE id ='"+m+"'";
                rs = stmt.executeQuery(sql3);
                rs.next();
                String v = rs.getString("full_name");
                System.out.println("Nhân viên "+v+" được xóa phòng ban thành công");
                break;
            }if(n==3){
                Menu menu = new Menu();
                menu.Menu();
            }
        }
    }
}