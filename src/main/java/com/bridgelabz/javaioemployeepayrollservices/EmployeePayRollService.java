package com.bridgelabz.javaioemployeepayrollservices;
/*
 * @author : Ashwini
 * Create an Employee Payroll Service to store Employee Payroll into a File
 */
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayRollService {
    private List<EmployeeData> employePayrollList = new ArrayList<EmployeeData>();
    Scanner scanner = new Scanner(System.in);
    private static final String FILE_PATH = "C:\\Users\\HP\\Desktop\\data.txt";

    public void readEmployeeDataFromConsole() {
        System.out.println("Enter Employee Id");
        int id = scanner.nextInt();
        System.out.println("Enter Employee Name");
        String Name = scanner.next();
        System.out.println("Enter the salary");
        int salary = scanner.nextInt();
        employePayrollList.add(new EmployeeData(id, Name, salary));
    }

    public void writeEmployeeDataInConsole() {
        System.out.println("Writing Employee Pay Roll Data \n" + employePayrollList);
    }

    public void addEmployee(EmployeeData employee) {
        employePayrollList.add(employee);
    }

    public void writeEmployeeDataToFile() {
        checkFile();
        StringBuffer empBuffer = new StringBuffer();
        employePayrollList.forEach(employee -> {
            String employeeDataString = employee.toString().concat("\n");
            empBuffer.append(employeeDataString);
        });
        try {
            Files.write(Paths.get(FILE_PATH), empBuffer.toString().getBytes());
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    private void checkFile() {
        File file = new File(FILE_PATH);
        try {

            if (!file.exists()) {

                file.createNewFile();
                System.out.println("Created a file at " + FILE_PATH);
            }
        } catch (IOException e1) {
            System.err.println("Problem encountered while creating a file");
        }
    }

    public long countEntries() {
        long entries = 0;
        try {
            entries = Files.lines(new File(FILE_PATH).toPath()).count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entries;
    }
}