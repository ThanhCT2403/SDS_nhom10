package com.tn;


import java.lang.ref.Cleaner;
import java.sql.*;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws SQLException,ClassNotFoundException {

        ConnectJDBC connectJDBC = new ConnectJDBC();
        Connection conn = connectJDBC.connect();
//        System.out.println("-------------------------------------");
//        System.out.println("1. Đăng nhập");
//        System.out.println("2. Nhân viên");
//        System.out.println("3. Phòng ban");
//        System.out.println("4. Tìm kiếm nhân viên");
//        System.out.println("2. Thoát!");
//        int n;
//
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Nhap vao lua chon: ");
//        n = scanner.nextInt();
//        while (n != 1) {
//            System.out.println("Ban chua login, can nhap lai: ");
//            scanner = new Scanner(System.in);
//            n = scanner.nextInt();
//        }
//        if (n == 1) {
        System.out.println("ĐĂNG NHẬP VÀO HỆ THỐNG");
            Scanner scanner = new Scanner(System.in);
            System.out.printf("Username: ");
            String username = scanner.nextLine();
            scanner = new Scanner(System.in);
            System.out.printf("Password: ");
            String password = scanner.nextLine();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM account";
            //stmt.executeUpdate(sql);
            ResultSet rs = stmt.executeQuery(sql);

            //Duyệt kết quả trả về
            if (rs.next()) {  //Di chuyển con trỏ xuống bản ghi kế tiếp
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                while (username.equals(username1)==false && password.equals(password1)==false) {
                        System.out.println("Nhập sai thông tin username hoặc mật khẩu. Dề nghị nhập lại");
                        scanner = new Scanner(System.in);
                        System.out.printf("Username: ");
                        username = scanner.nextLine();
                        scanner = new Scanner(System.in);
                        System.out.printf("Password: ");
                        password = scanner.nextLine();
                }

                Menu menu = new Menu();
                menu.Menu();
//                System.out.println("1. Thông tin nhân viên");
//                System.out.println("2. Thông tin phòng ban");
//                System.out.println("3. Tìm kiếm nhân viên");
//                System.out.println("4. Thêm xóa nhân viên vào phòng ban");
//                System.out.println("5. Chuyển phòng ban cho nhân viên");
//                System.out.println("6. Tính thuế thu nhập cá nhân cho nhân viên");
//                System.out.println("7. Thoát");
//                scanner = new Scanner(System.in);
//                System.out.println("Nhap vao lua chon tu 1-5: ");
//                int n = scanner.nextInt();
//                while (n<1||n>7) {
//                    System.out.println("Ban chua login, can nhap lai: ");
//                    scanner = new Scanner(System.in);
//                    n = scanner.nextInt();
//                }
//
//                while (true) {
//                    if (n == 1) {
//                        Employee employee = new Employee();
//                        employee.employ(n);
//
//
//
//                    } else if (n == 2) {
//                        Department department = new Department();
//                        department.depart(n);
//
//
//                    } else if (n == 3) {
//                        Search search = new Search();
//                        search.search(n);
//
//                    } else if (n == 4) {
//                        AddDelEmpToDept addDelEmpToDept = new AddDelEmpToDept();
//                        addDelEmpToDept.addDelEmployee(n);
//
//                    } else if (n == 5) {
//                        TransferEmp transferEmp = new TransferEmp();
//                        transferEmp.transfer();
//
//                    } else if (n == 6) {
//                        TaxEmployee taxEmployee = new TaxEmployee();
//                        taxEmployee.taxEmployee();
//
//                    }
//                }
            }
        }

    //}
}
