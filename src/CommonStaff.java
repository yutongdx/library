import java.util.*;

public class CommonStaff extends User {

    private static final long serialVersionUID=-4549877563019322619L;

    public void register(ArrayList<CommonStaff> staffList, CommonStaff employee) {
        String name;
        String userName;
        String sex;
        Long telephone;
        String password;
        boolean validation = true;

        while (validation) {
            Scanner input = new Scanner(System.in);
            System.out.println("******************用户注册******************");
            System.out.println();
            System.out.println("请输入用户名：");
            userName = input.nextLine();
            employee.setUserName(userName);

            System.out.println("请输入密码：");
            password = input.nextLine();
            employee.setPassword(password);

            System.out.println("请输入姓名：");
            name = input.nextLine();
            employee.setName(name);

            System.out.println("请输入性别：");
            sex = input.nextLine();
            employee.setSex(sex);

            System.out.println("请输入联系电话：");
            telephone = input.nextLong();
            employee.setTelephone(telephone);

            validation = false;
            for (CommonStaff people : staffList) {
                if (people.getUserName().equals(userName) && people.getPassword().equals(password)) {
                    System.out.println("用户名和密码已被注册，请重新注册！");
                    validation = true;
                    break;
                }
            }
        }

        employee.setPosition("职员");
        staffList.add(employee);
        System.out.println("注册成功！");

    }

    public int signIn(ArrayList<CommonStaff> staffList) {
        String inputUserName;
        String inputPassword;
        boolean check = true;
        boolean name=false;
        int inputTimes = 0;
        int index = 0;

        while (check && inputTimes < 3) {
            Scanner input = new Scanner(System.in);
            System.out.println();
            System.out.println("请输入用户名：");
            inputUserName = input.nextLine();
            System.out.println("请输入密码");
            inputPassword = input.nextLine();
            check = true;
            for (CommonStaff people : staffList) {
                if (people.getUserName().equals(inputUserName) && people.getPassword().equals(inputPassword)) {
                    check = false;
                    index = staffList.indexOf(people);
                } else if (people.getUserName().equals(inputUserName)){
                    name=true;
                }
            }

            if(!name&&check){
                System.out.println("用户不存在！请重新输入！（仅三次机会）");
            }
            if(name){
                System.out.println("密码错误请重新输入！（仅三次机会）");
                name=false;
            }
            inputTimes++;
        }
        if (inputTimes <=3) {
            System.out.println("登录成功！");
            System.out.println();
            return index;
        } else {
            System.out.println("登录失败！");
            System.out.println();
            index=-1;
            return index;
        }

    }

    public void addBook(ArrayList<Book> bookList) {
        String ISBN;
        String name;
        String type;
        Integer surplus;
        Integer price;
        String author;

        Book newBook = new Book();
        Scanner input = new Scanner(System.in);

        System.out.println("请输入书本名称：");
        name = input.nextLine();
        newBook.setName(name);

        System.out.println("请输入书本作者：");
        author = input.nextLine();
        newBook.setAuthor(author);

        System.out.println("请输入书本ISBN码：");
        ISBN = input.nextLine();
        newBook.setISBN(ISBN);

        System.out.println("请输入书本类型：");
        type = input.nextLine();
        newBook.setType(type);

        System.out.println("请输入书本剩余数目：");
        surplus = input.nextInt();
        newBook.setSurplus(surplus);

        System.out.println("请输入书本价格：");
        price = input.nextInt();
        newBook.setPrice(price);

        bookList.add(newBook);

    }

    public Book searchBook(ArrayList<Book> bookList) {
        String bookName;
        int index = 0;
        Book targetBook;

        Scanner input = new Scanner(System.in);
        System.out.println("请输入书名：");
        bookName = input.nextLine();
        for (Book book : bookList) {
            if (bookName.equals(book.getName())) {
                index = bookList.indexOf(book);
                break;
            }
        }
        if (index > bookList.size() - 1) {
            System.out.println("查无藏书！");
            return null;
        } else {
            targetBook = bookList.get(index);
            return targetBook;
        }

    }

    public void reviseBook(Book book, ArrayList<Book> bookList) {
        String ISBN;
        String type;
        Integer surplus;
        Integer price;
        String author;
        int option;

        Scanner input = new Scanner(System.in);
        System.out.println("请输入需要修改项序号：");
        System.out.println("1.名称");
        System.out.println("2.类型");
        System.out.println("3.剩余数目");
        System.out.println("4.价格");
        System.out.println("5作者");
        option = input.nextInt();
        input.nextLine();

        switch (option) {
            case 1:
                System.out.println("请输入书本名称：");
                ISBN = input.nextLine();
                book.setISBN(ISBN);
                break;
            case 2:
                System.out.println("请输入书本类型：");
                type = input.nextLine();
                book.setType(type);
                break;
            case 3:
                System.out.println("请输入书本剩余数目：");
                surplus = input.nextInt();
                book.setSurplus(surplus);
                break;
            case 4:
                System.out.println("请输入书本价格：");
                price = input.nextInt();
                book.setPrice(price);
                break;
            case 5:
                System.out.println("请输入书本作者：");
                author = input.nextLine();
                book.setAuthor(author);
                break;
        }
        System.out.println("修改成功！");

        bookList.add(book);
    }

    public void deleteBook(Book book, ArrayList<Book> bookList) {
        bookList.remove(book);
    }

    public void inquireAllBook(ArrayList<Book> bookList) {
        for (Book book : bookList) {
            System.out.println("Id:"+book.getId());
            System.out.println("书名:"+book.getName());
            System.out.println("ISBN:"+book.getISBN());
            System.out.println("作者:"+book.getAuthor());
            System.out.println("书本类型:"+book.getType());
            System.out.println("书本剩余:"+book.getSurplus());
            System.out.println("价格:"+book.getPrice());
        }

    }

    public void inquireOneBook(ArrayList<Book> bookList) {
        Book book;

        book = searchBook(bookList);
        System.out.println("Id:"+book.getId());
        System.out.println("书名:"+book.getName());
        System.out.println("ISBN:"+book.getISBN());
        System.out.println("作者:"+book.getAuthor());
        System.out.println("书本类型:"+book.getType());
        System.out.println("书本剩余:"+book.getSurplus());
        System.out.println("价格:"+book.getPrice());
    }

    public void reviseStaffInformation(ArrayList<CommonStaff> staffList, CommonStaff employee) {

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
            System.out.println("1.用户名   2.密码    3.姓名   4.性别  5.联系电话  6.返回菜单");
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
                    System.out.println("请输入联系电话：");
                    employee.setTelephone(input.nextLong());
                    break;
                case 6:
                    running = false;
                    break;
            }
        }

    }

    public void curatorRegister(Curator employee) {

        int registerWord;

        Scanner input = new Scanner(System.in);
        System.out.println("请输入注册码：");
        registerWord = input.nextInt();
        if (registerWord == 23333) {
            System.out.println("请输入邮箱：");
            employee.setEmail(input.nextLine());
            employee.setPosition("馆长");
            System.out.println("注册成功！");
        } else {
            System.out.println("注册码错误！");
        }

    }

    public boolean staffMenu(Curator employee, ArrayList<CommonStaff> staffList, ArrayList<Book> bookList) {

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
            System.out.println("                7.注册馆长");
            System.out.println("                8.关闭程序");
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
                    employee.reviseStaffInformation(staffList, employee);
                    break;
                case 7:
                    employee.curatorRegister(employee);
                    employee.curatorMenu(employee, staffList, bookList);
                    running = false;
                    break;
                case 8:
                    running = false;
                    break;
            }
        }
        return false;
    }

}
