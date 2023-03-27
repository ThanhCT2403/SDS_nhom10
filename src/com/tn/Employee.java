package com.tn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;
import java.io.*;

public class Employee {

    ConnectJDBC connectJDBC = new ConnectJDBC();
    Connection connection = connectJDBC.connect();

    public void employ(int n) throws SQLException,ClassNotFoundException {
        System.out.println("1. Danh sách nhân sự");
        System.out.println("2. Thêm nhân sự");
        System.out.println("3. Sửa nhân sự");
        System.out.println("4. Xoá nhân sự");
        System.out.println("5. Thoát");
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
        Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
        while(true) {
            if (n == 1) {
                System.out.println("Danh sách nhân viên");
                ResultSet rs = stmt.executeQuery("Select * from employee");

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
                break;
            }
            if (n == 2) {
                System.out.println("Chức năng thêm nhân sự");
                System.out.println("Nhap vao tên nhân sự: ");
                Scanner scanner = new Scanner(System.in);
                String fn = scanner.nextLine();


                System.out.println("Nhap vào email: ");
                scanner = new Scanner(System.in);
                String em = scanner.nextLine();

                String sql6 = "SELECT full_name FROM employee where email = '"+em+"'";
                ResultSet rs = stmt.executeQuery(sql6);
                //rs.next();
                //String mail = rs.getString("full_name");
                while (rs.next()==true) {
                        System.out.println("Nhap email khác do email đã nhập tồn tại trong CSDL: ");
                        scanner = new Scanner(System.in);
                        em = scanner.nextLine();
                }

                String gd;
                do {
                    System.out.println("Nhap vao giới tính (1. female, 2. male):");
                    scanner = new Scanner(System.in);
                    gd = scanner.next();
                } while (!(gd.equalsIgnoreCase("female") || gd.equalsIgnoreCase("male")));
                EnumGender eg = EnumGender.valueOf(gd.toLowerCase());
                System.out.println("Nhap vao SĐT: ");
                scanner = new Scanner(System.in);
                String ph = scanner.nextLine();


                System.out.println("Nhap vao CMND: ");
                scanner = new Scanner(System.in);
                String idc = scanner.nextLine();
                sql6 = "SELECT full_name FROM employee where cmnd = '"+idc+"'";
                rs = stmt.executeQuery(sql6);
                //rs.next();
                //String mail = rs.getString("full_name");
                while (rs.next()==true) {
                    System.out.println("Nhap CMND khác do CMND đã nhập tồn tại trong CSDL: ");
                    scanner = new Scanner(System.in);
                    idc = scanner.nextLine();
                }

                System.out.println("Nhap vao địa chỉ: ");
                scanner = new Scanner(System.in);
                String add = scanner.nextLine();
                String dob;
                //while( ==true) {
                System.out.println("Nhap vao ngày sinh (theo định dạng yyyy-MM-dd): ");
                scanner = new Scanner(System.in);
                dob = scanner.nextLine();
                Date date = java.sql.Date.valueOf(dob);
                //}

                scanner = new Scanner(System.in);
                System.out.println("Nhập vao chức vụ ( Manager, Staff, Intern): ");
                String po = scanner.next();
                while (!(po.equalsIgnoreCase("manager") || po.equalsIgnoreCase("intern") || po.equalsIgnoreCase("staff"))) {
                    scanner = new Scanner(System.in);
                    System.out.println("Nhập vao chức vụ ( Manager, Staff, Intern): ");
                    po = scanner.next();
                }
                EnumPos e = EnumPos.valueOf(po.toLowerCase());
                System.out.println("Nhap vao ngày vào (theo định dạng yyyy-MM-dd): ");
                scanner = new Scanner(System.in);
                String dj = scanner.nextLine();
                Date date2 = java.sql.Date.valueOf(dj);

                Salary salary = new Salary();
                //Statement stmt1 = conn.createStatement();
                String sql2 = "INSERT INTO employee(full_name, email, gender, phone, cmnd, address, dateofbirth, position,salary, datejoin) VALUES ( '" + fn + "', '" + em + "', '" + eg + "', '" + ph + "', '" + idc + "', '" + add + "', '" + date + "', '" + e + "', '" + salary.salary(e) + "', '" + date2 + "')";
                stmt.executeUpdate(sql2);
                System.out.println("Them nhân viên thanh cong");
                String sql3 = "select * from employee where email = '"+ em +"'";
                rs = stmt.executeQuery(sql3);
                if (rs.next() == true) {
                    System.out.println("___________________________________________________________________________________________________________________________________________________________");
                    System.out.printf("|%-5s | %-20s | %-20s | %-10s | %-10s | %-20s | %-10s | %-10s | %-10s | %-10s | \n", "Id", "Full name", "Email", "Gender", "Phone", "Address", "Birthday", "Salary", "Department", "Position","|");
                    System.out.println("|_________________________________________________________________________________________________________________________________________________________|");
                    rs.beforeFirst();
                    while (rs.next()) {
                        int id2 = rs.getInt("id");
                        String fn1 = rs.getString("full_name");
                        String em1 = rs.getString("email");
                        String gd1 = rs.getString("gender");
                        String ph1 = rs.getString("phone");
                        String ad = rs.getString("address");
                        String bd = rs.getString("dateofbirth");
                        String sl = rs.getString("salary");
                        double sl1 = Double.parseDouble(sl);
                        int dp = rs.getInt("department_id");
                        String im = rs.getString("position");
                        System.out.printf("|%-5s | %-20s | %-20s | %-10s | %-10s | %-20s | %-10s | %-10s | %-10s | %-10s | \n", id2, fn1, em1, gd1, ph1, ad, bd, String.format("%,.0f",sl1), dp, im);
                    }
                    System.out.println("|_________________________________________________________________________________________________________________________________________________________|");
                } else {
                    System.out.println("Khong co du lieu");
                    Menu menu = new Menu();
                    menu.Menu();
                }
                break;
            }
            if (n == 3) {
                System.out.println("Sửa nhân viên");
                int d=0;
                do{
                    System.out.println("Nhap vao id nhân viên cần sửa: ");
                    Scanner scanner = new Scanner(System.in);
                    strn = scanner.nextLine();
                }while (gfg.isNumber(strn)==false);
                d = Integer.parseInt(strn);
                String sql6 = "SELECT * FROM employee WHERE id='" + d + "'";
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
                System.out.println("-------------------------------------");
                System.out.println("Bạn xác nhận muốn sửa thông tin phong ban này\n(1. Yes/2. No)");
                do{
                    do{
                        System.out.println("Nhập vào lựa chọn từ (1.Yes-2.No) : ");
                        Scanner scanner = new Scanner(System.in);
                        strn = scanner.nextLine();
                    }while (gfg.isNumber(strn)==false);
                    n = Integer.parseInt(strn);
                }while(n<1||n>2);
                if (n == 1) {
                    System.out.println("Chọn phần cần sửa");
                    System.out.println("1. Update Tên nhân viên");
                    System.out.println("2. Update Email");
                    System.out.println("3. Update Giới tính");
                    System.out.println("4. Update SĐT");
                    System.out.println("5. Update CMND");
                    System.out.println("6. Update Địa chỉ");
                    System.out.println("7. Update Ngày sinh");
                    System.out.println("8. Update Chức vụ");
                    System.out.println("9. Update Ngày vào");


                    do{
                        do{
                            System.out.println("Nhập vào lựa chọn từ 1-9 : ");
                            Scanner scanner = new Scanner(System.in);
                            strn = scanner.nextLine();
                        }while (gfg.isNumber(strn)==false);
                        n = Integer.parseInt(strn);
                    }while(n<1||n>9);

                        if( n==1) {
                            System.out.println("Nhập vao tên nhân sự cần sửa: ");
                            Scanner scanner = new Scanner(System.in);
                            String fn = scanner.nextLine();
                            String sql3 = "UPDATE employee SET full_name = '" + fn + "' WHERE id='" + d + "'";
                            stmt.executeUpdate(sql3);
                            System.out.println("Sửa tên nhân viên thành công");
                            break;
                        }
                        if( n==2) {
                            System.out.println("Nhập vào email nhân sự cần sửa: ");
                            Scanner scanner = new Scanner(System.in);
                            String em = scanner.nextLine();
                            sql6 = "SELECT full_name FROM employee where email = '"+em+"'";
                            rs = stmt.executeQuery(sql6);
                            //rs.next();
                            //String mail = rs.getString("full_name");
                            while (rs.next()==true) {
                                System.out.println("Nhap email khác do email đã nhập tồn tại trong CSDL: ");
                                scanner = new Scanner(System.in);
                                em = scanner.nextLine();
                            }
                            String sql3 = "UPDATE employee SET email = '" + em + "' WHERE id='" + d + "'";
                            stmt.executeUpdate(sql3);
                            System.out.println("Sửa email nhân viên thành công");
                        }
                        if(n==3) {
                            System.out.println("Nhap vao giới tính cần sửa (female,male):");
                            Scanner scanner = new Scanner(System.in);
                            String gd = scanner.nextLine();
                            String sql3 = "UPDATE employee SET gender = '" + gd + "' WHERE id='" + d + "'";
                            stmt.executeUpdate(sql3);
                            System.out.println("Sửa giới tính nhân viên thành công");
                            break;
                        }
                        if(n== 4) {
                            System.out.println("Nhap vao SĐT cần sửa: ");
                            Scanner scanner = new Scanner(System.in);
                            String ph = scanner.nextLine();
                            String sql3 = "UPDATE employee SET phone = '" + ph + "' WHERE id='" + d + "'";
                            stmt.executeUpdate(sql3);
                            System.out.println("Sửa SĐT nhân viên thành công");
                            break;
                        }
                        if(n== 5) {
                            System.out.println("Nhap vao CMND cần sửa: ");
                            Scanner scanner = new Scanner(System.in);
                            String idc = scanner.nextLine();
                            sql6 = "SELECT full_name FROM employee where cmnd = '"+idc+"'";
                            rs = stmt.executeQuery(sql6);
                            //rs.next();
                            //String mail = rs.getString("full_name");
                            while (rs.next()==true) {
                                System.out.println("Nhap CMND khác do CMND đã nhập tồn tại trong CSDL: ");
                                scanner = new Scanner(System.in);
                                idc = scanner.nextLine();
                            }
                            String sql3 = "UPDATE employee SET cmnd = '" + idc + "' WHERE id='" + d + "'";
                            stmt.executeUpdate(sql3);
                            System.out.println("Sửa CMND nhân viên thành công");
                            break;
                        }
                        if(n== 6) {
                            System.out.println("Nhap vao địa chỉ: ");
                            Scanner scanner = new Scanner(System.in);
                            String add = scanner.nextLine();
                            String sql3 = "UPDATE employee SET address = '" + add + "' WHERE id='" + d + "'";
                            stmt.executeUpdate(sql3);
                            System.out.println("Sửa địa chỉ nhân viên thành công");
                            break;
                        }
                        if(n== 7) {
                            System.out.println("Nhap vao ngày sinh cần sửa (theo định dạng yyyy-MM-dd): ");
                            Scanner scanner = new Scanner(System.in);
                            String dob = scanner.nextLine();
                            Date date = java.sql.Date.valueOf(dob);
                            String sql3 = "UPDATE employee SET dateofbirth = '" + date + "' WHERE id='" + d + "'";
                            stmt.executeUpdate(sql3);
                            System.out.println("Sửa ngày sinh nhân viên thành công");
                            break;
                        }
                        if(n== 8) {
                            System.out.println("Nhập vao chức vụ cần sửa: ");
                            Scanner scanner = new Scanner(System.in);
                            String po = scanner.nextLine();
                            String sql3 = "UPDATE employee SET position = '" + po + "' WHERE id='" + d + "'";
                            stmt.executeUpdate(sql3);
                            System.out.println("Sửa chức vụ nhân viên thành công");
                            break;
                        }
                        if(n== 9) {
                            System.out.println("Nhap vao ngày vào cần sửa (theo định dạng yyyy-MM-dd): ");
                            Scanner scanner = new Scanner(System.in);
                            String dj = scanner.nextLine();
                            Date date2 = java.sql.Date.valueOf(dj);
                            String sql3 = "UPDATE employee SET datejoin = '" + date2 + "' WHERE id='" + d + "'";
                            stmt.executeUpdate(sql3);
                            System.out.println("Sửa ngày vào nhân viên thành công");
                            break;
                        }
                    }

                 else if (n == 2) {
                    System.out.println("thoát sửa nhân viên");

                        Menu menu = new Menu();
                        menu.Menu();

                }
                break;
            }

            if (n == 4) {
                System.out.println("xóa nhân sự");
                int e=0;
                do{
                    System.out.println("Nhap vao id nhân sự cần xóa: ");
                    Scanner scanner = new Scanner(System.in);
                    strn = scanner.nextLine();
                }while (gfg.isNumber(strn)==false);
                e = Integer.parseInt(strn);

                String sql6 = "SELECT * FROM employee WHERE id='" + e + "'";
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


                System.out.println("-------------------------------------");
                System.out.println("Bạn xác nhận muốn xóa thông tin nhân sự này\n(1. Yes/2. No)");
                Scanner scanner = new Scanner(System.in);
                do{
                    do{
                        System.out.println("Nhập vào lựa chọn từ (1.Yes-2.No) : ");
                        scanner = new Scanner(System.in);
                        strn = scanner.nextLine();
                    }while (gfg.isNumber(strn)==false);
                    n = Integer.parseInt(strn);
                }while(n<1||n>2);
                if (n == 1) {
                    String sql5 = "DELETE FROM employee WHERE id='" + e + "'";
                    stmt.executeUpdate(sql5);
                    System.out.println("xóa nhân sự thành công");

                } else if (n == 2) {
                    System.out.println("Thoát xóa nhân viên");
                    Menu menu = new Menu();
                    menu.Menu();
                }
                break;
            }if (n==5){
                Menu menu = new Menu();
                menu.Menu();
            }

        }
    }

}
