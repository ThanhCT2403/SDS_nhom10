package com.tn;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    ConnectJDBC connectJDBC = new ConnectJDBC();
    Connection conn = connectJDBC.connect();
    public void Menu()throws SQLException,ClassNotFoundException{
        System.out.println("1. Thông tin nhân viên");
        System.out.println("2. Thông tin phòng ban");
        System.out.println("3. Tìm kiếm nhân viên");
        System.out.println("4. Thêm xóa nhân viên vào phòng ban");
        System.out.println("5. Chuyển phòng ban cho nhân viên");
        System.out.println("6. Tính thuế thu nhập cá nhân cho nhân viên");
        System.out.println("7. Thống kê lương nhân viên");
        System.out.println("8. Thống kê lương theo năm");

        GFG gfg = new GFG();

        int n=0;
        String strn;
        do{
            do{
                System.out.println("Nhập vào lựa chọn từ 1-8 : ");
                Scanner scanner = new Scanner(System.in);
                strn = scanner.nextLine();
            }while (gfg.isNumber(strn)==false ||strn==null);
            n = Integer.parseInt(strn);
        }while(n<1||n>8);

        while (true) {
            if (n == 1) {
                Employee employee = new Employee();
                employee.employ(n);



            } else if (n == 2) {
                Department department = new Department();
                department.depart(n);


            } else if (n == 3) {
                Search search = new Search();
                search.search(n);

            } else if (n == 4) {
                AddDelEmpToDept addDelEmpToDept = new AddDelEmpToDept();
                addDelEmpToDept.addDelEmployee(n);

            } else if (n == 5) {
                TransferEmp transferEmp = new TransferEmp();
                transferEmp.transfer();

            } else if (n == 6) {
                TaxEmployee taxEmployee = new TaxEmployee();
                taxEmployee.taxEmployee();

            }else if (n == 7) {
                Statitic statitic = new Statitic();
                statitic.staticEm(n);
            } else if (n==8) {
                SalaryPerYear salaryPerYear = new SalaryPerYear();
                salaryPerYear.salaryPerYear(n);
            }
        }
    }
}
