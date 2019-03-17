import java.util.*;

public class Curator extends CommonStaff {
    private String email;
    private static final long serialVersionUID=322123361480870454L;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void reviseCuratorInformation(ArrayList<CommonStaff> staffList, Curator employee) {

        int option;
        boolean running = true;
        boolean validation;
        String userName;
        String password;

        while (running) {
            System.out.println("**************************信息修改**************************");
            System.out.println();
            System.out.println("Id:"+employee.getId());
            System.out.println("姓名:"+employee.getName());
            System.out.println("性别:"+employee.getSex());
            System.out.println("职位:"+employee.getPosition());
            System.out.println("联系电话:"+employee.getTelephone());
            System.out.println("Email:"+employee.getEmail());
            System.out.println();
            System.out.println("           1.用户名   2.密码     3.姓名    4.性别");
            System.out.println("           5.邮箱    6.联系电话  7.返回菜单");
            System.out.println();
            System.out.println("***********************************************************");
            System.out.println();
            System.out.println("请输入功能选项：");
            Scanner input = new Scanner(System.in);
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    validation = true;
                    while (validation) {
                        validation = false;
                        System.out.println("请输入用户名：");
                        userName = input.nextLine();
                        employee.setUserName(userName);
                        for (CommonStaff people : staffList) {
                            if (people.getUserName().equals(userName) && people.getPassword().equals(employee.getPassword())) {
                                System.out.println("用户名和密码已被注册，请重新修改！");
                                validation = true;
                                break;
                            }
                        }
                    }
                    break;
                case 2:
                    validation = true;
                    while (validation) {
                        validation = false;
                        System.out.println("请输入密码：");
                        password = input.nextLine();
                        employee.setPassword(password);
                        for (CommonStaff people : staffList) {
                            if (people.getUserName().equals(employee.getUserName()) && people.getPassword().equals(password)) {
                                System.out.println("用户名和密码已被注册，请重新修改！");
                                validation = true;
                                break;
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("请输入姓名：");
                    employee.setName(input.nextLine());
                    break;
                case 4:
                    System.out.println("请输入性别：");
                    employee.setSex(input.nextLine());
                    break;
                case 5:
                    System.out.println("请输入邮箱：");
                    employee.setEmail(input.nextLine());
                    break;
                case 6:
                    System.out.println("请输入联系电话：");
                    employee.setTelephone(input.nextLong());
                    break;
                case 7:
                    running = false;
                    break;
            }
        }

    }

    public void manageStaff(ArrayList<CommonStaff> staffList) {
        Curator employee = null;
        String name;
        int index;
        Scanner input = new Scanner(System.in);

        System.out.println("全体职员：");
        for (CommonStaff staff : staffList) {
            System.out.println(staff.getName());
        }
        System.out.println();

        System.out.println("请输入员工姓名：");
        name = input.nextLine();
        for (CommonStaff staff : staffList) {
            if (staff.getName().equals(name)) {
                index = staffList.indexOf(staff);
                employee =(Curator) staffList.get(index);
                break;
            }
        }
        if (employee == null) {
            System.out.println("查无此人！");
        } else {
            employee.reviseStaffInformation(staffList, employee);
        }
    }

    public void managePosition(ArrayList<CommonStaff> staffList) {
        CommonStaff employee = null;
        String name;
        String position;
        int index;
        Scanner input = new Scanner(System.in);

        System.out.println("全体职员：");
        for (CommonStaff staff : staffList) {
            System.out.println(staff.getName());
        }
        System.out.println();
        System.out.println("请输入员工姓名：");
        name = input.nextLine();
        for (CommonStaff staff : staffList) {
            if (staff.getName().equals(name)) {
                index = staffList.indexOf(staff);
                employee = staffList.get(index);
                break;
            }
        }
        if (employee == null) {
            System.out.println("查无此人！");
        } else {
            System.out.println("请输入调配的职位(输入“解雇”则删除该成员信息)：");
            position = input.nextLine();
            if(position.equals("解雇")){
                staffList.remove(employee);
            }else{
                employee.setPosition(position);
            }
        }
    }

    public boolean curatorMenu(Curator employee, ArrayList<CommonStaff> staffList, ArrayList<Book> bookList) {
        int option;
        boolean running = true;

        while (running) {
            System.out.println("******************功能菜单******************");
            System.out.println();
            System.out.println("                1.添加书本");
            System.out.println("                2.修改书本信息");
            System.out.println("                3.删除书本");
            System.out.println("                4.查找书本");
            System.out.println("                5.列出所有书本信息");
            System.out.println("                6.修改个人信息");
            System.out.println("                7.职员信息管理");
            System.out.println("                8.职位调配");
            System.out.println("                9.关闭程序");
            System.out.println();
            System.out.println("*******************************************");
            System.out.println();
            System.out.println("请输入功能选项：");
            Scanner input = new Scanner(System.in);
            option = input.nextInt();
            input.nextLine();
            switch (option) {
                case 1:
                    employee.addBook(bookList);
                    break;
                case 2:
                    employee.reviseBook(employee.searchBook(bookList), bookList);
                    break;
                case 3:
                    employee.deleteBook(employee.searchBook(bookList), bookList);
                    break;
                case 4:
                    employee.inquireOneBook(bookList);
                    break;
                case 5:
                    employee.inquireAllBook(bookList);
                    break;
                case 6:
                    employee.reviseCuratorInformation(staffList, employee);
                    break;
                case 7:
                    employee.manageStaff(staffList);
                    break;
                case 8:
                    employee.managePosition(staffList);
                    break;
                case 9:
                    running = false;
                    break;
            }
        }
        return false;
    }

}