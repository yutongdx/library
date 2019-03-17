import java.util.*;
import java.io.*;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Run {

    public static void main(String[] args) {
        ArrayList<CommonStaff> staffList = new ArrayList<>();
        ArrayList<Book> bookList = new ArrayList<>();
        Curator employee = new Curator();
        int option;
        boolean running = true;


        try {
            FileInputStream fileInputStream = new FileInputStream("User.ser");
            ObjectInputStream readDate = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                staffList.add((CommonStaff) readDate.readObject());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            FileInputStream fileInputStream = new FileInputStream("Book.ser");
            ObjectInputStream readDate = new ObjectInputStream(fileInputStream);
            while (fileInputStream.available() > 0) {
                bookList.add((Book) readDate.readObject());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        while (running) {

            System.out.println("***************图书馆管理系统***************");
            System.out.println();
            System.out.println("1.注册");
            System.out.println("2.登录");
            System.out.println();
            System.out.println("*******************************************");
            System.out.println("请输入功能选项：");

            Scanner input = new Scanner(System.in);
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    employee.register(staffList, employee);
                    break;
                case 2:
                    int index;
                    System.out.println("*****************登录*******************");
                    index = employee.signIn(staffList);
                    if(index>=0){
                        employee = (Curator) staffList.get(index);
                        if (employee.getPosition().equals("馆长")) {
                            running = employee.curatorMenu(employee, staffList, bookList);
                        } else {
                            running = employee.staffMenu(employee, staffList, bookList);
                        }
                    }else{
                        running=false;
                    }
                    break;
            }

        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("User.ser");
            ObjectOutputStream writeDate = new ObjectOutputStream(fileOutputStream);
            for (CommonStaff people : staffList) {
                writeDate.writeObject(people);
            }
            writeDate.close();
            fileOutputStream = new FileOutputStream("Book.ser");
            writeDate = new ObjectOutputStream(fileOutputStream);
            for (Book book : bookList) {
                writeDate.writeObject(book);
            }
            writeDate.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
