import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Library {

    static ArrayList <Book> books = new ArrayList <Book> ();///图书列表
    static ArrayList <Record> records = new ArrayList <Record> ();///记录列表
    static HashMap<String,Role> roles = new HashMap<String,Role> ();///用户列表

    //添加图书至图书列表  管理员方法 1成功 -1失败
    public static int addBooks(String bookname,String author,float price,String type){
        Book book = new Book(bookname, author, price, type);
        book.setState(Book.State.AVAILABLE);
        if (book == null) return -1;
        books.add(book);
        return 1;
    }
    //查看所有图书   公共方法
    public static void lookAllBooks(){
        for (Book book : books) {
            System.out.println("图书名称：" + book.getBookname() + "  作者：" + book.getAuthor() + "  价格：" + book.getPrice() + "  类型：" + book.getType()+ "  状态：" + book.getState());
        }
    }
    //查看所有记录   管理员方法
    public static void lookAllRecord(){
        for (Record record : records) {
            System.out.println("借阅人：" + record.getBorrowerName() + "  图书名称：" + record.getBookName() + "  借阅日期：" + record.getBorrowDate() + "  归还日期：" + record.getActualReturnDate());
        }
    }
    //借书   用户方法
    public static int borrowBook(Role shenfen,String bookname){
        for (Book b : books) {
            if (b.getBookname().equals(bookname)) {
                Book book = b;
                if (book.getState() == Book.State.AVAILABLE) {
                    book.setState(Book.State.BORROWED);
                    Record record = new Record(shenfen.getName(), bookname);
                    records.add(record);
                    return 1;//借阅成功
                } else {
                    return -1;//图书已借出
                }

            }
        }
        return -2;//图书不存在
    }
    //还书   用户方法
    public static int returnBook(Role shenfen,String bookname){
        for (Book b : books) {
            if (b.getBookname().equals(bookname)) {
                Book book = b;
                if (book.getState() == Book.State.BORROWED) {
                    for (Record r : records){
                        if(r.getBorrowerName().equals(shenfen.getName()) && r.getBookName().equals(bookname)){
                            book.setState(Book.State.AVAILABLE);
                            System.out.println("归还成功！");
                            r.setActualReturnDate(java.time.LocalDate.now());
                            return 1;//归还成功
                        }else{
                                        return -1;//书不是你借的无法归还
                        }
                    }
                } else if (book.getState() == Book.State.OVERDUE) {
                    return -2;//图书逾期未还
                } else {
                    return -3;//图书未被借出借出
                }
                break;
            }
        }
        return -4;//图书不存在
    }
    //查看我的借阅记录
    public static void myAllRecord(Role shenfen){
        for (Record record : records) {
            if (record.getBorrowerName().equals(shenfen.getName())) {
                System.out.println("借阅人：" + record.getBorrowerName() + "  图书名称：" + record.getBookName() + "  借阅日期：" + record.getBorrowDate() + "  归还日期：" + record.getActualReturnDate());
            }
        }
        System.out.println("查看完毕！");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean isLogin = false;//是否登录
        Role shenfen = null;//用户身份
        //登录界面
        while (!isLogin) {
            System.out.println("====================================");
            System.out.println("===============登录界面===============");
            System.out.println("====================================");
            System.out.println("1.管理员登录  2.用户登录 3.注册 0.退出系统 ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("请输入用户名：");
                    String username = scanner.next();
                    Role role = roles.get(username);
                    if (role != null && role.getRole() == 1) {
                        System.out.println("登录成功！");
                        shenfen = new Role(username, 1);
                        isLogin = true;
                    } else if(role != null ){
                        System.out.println("未注册该用户");
                    }else if (role.getRole() == 2){
                        System.out.println("身份认证失败");
                    }
                    break;
                }
                case 2: {
                    System.out.println("请输入用户名：");
                    String username = scanner.next();
                    Role role = roles.get(username);
                    if (role != null && role.getRole() == 2) {
                        System.out.println("登录成功！");
                        shenfen = new Role(username, 2);
                        isLogin = true;
                    } else if(role != null ){
                        System.out.println("未注册该用户");
                    }else if (role.getRole() == 1){
                        System.out.println("身份认证失败");
                    }
                    break;
                }
                case 3: {
                    System.out.println("请输入用户名：");
                    String username = scanner.next();
                    int roleType = InputUtil.readInt(scanner, "请输入数字：1.管理员 2.用户");
                    if (roleType != 1 && roleType != 2) {
                        System.out.println("输入错误，请输入1或2！");
                        scanner.next(); // 丢弃非法 token，防止死循环
                        continue;
                    }
                    Role role = new Role(username, roleType);
                    roles.put(username, role);
                    System.out.println("注册成功！");
                    break;
                }
                case 0: {
                    System.out.println("欢迎下次使用");
                    System.exit(0);
                    return;
                }
            }
        }

        if(shenfen.getRole() == 1) {
            //图书馆管理系统
            while (true) {
                System.out.println("=========================================");
                System.out.println("===============图书馆管理系统===============");
                System.out.println("=========================================");
                System.out.println("1. 查看所有图书 2.添加图书 3.查看所有借阅记录 0.退出系统 ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1: {
                        lookAllBooks();
                        break;
                    }
                    case 2: {
                        System.out.println("1请输入图书名称：");
                        String bookname = scanner.next();
                        if (bookname == null || bookname.isEmpty()) {
                            System.out.println("输入不能为空");
                            break;
                        }

                        System.out.println("2请输入作者：");
                        String author = scanner.next();
                        if (author == null || author.isEmpty()) {
                            System.out.println("输入不能为空");
                            break;
                        }

                        float price = InputUtil.readFloat(scanner, "3请输入价格：");
                        if (price <= 0) {
                            System.out.println("请输入正确的价格：大于0的浮点数");
                            break;
                        }
                        System.out.println("4请输入类型：");
                        String type = scanner.next();
                        if (type == null || type.isEmpty()) {
                            System.out.println("输入不能为空");
                            break;
                        }
                        int result = addBooks(bookname, author, price, type);
                        if (result == 1) System.out.println("添加成功！");
                        else System.out.println("添加失败！");
                        break;
                    }
                    case 3: {
                        lookAllRecord();
                        break;
                    }
                    case 0: {
                        System.out.println("欢迎下次使用");
                        System.exit(0);
                        return;
                    }
                }

            }
        }else if(shenfen.getRole() == 2) {
            //用户界面
            while (true) {
                System.out.println("=========================================");
                System.out.println("===============图书馆用户系统===============");
                System.out.println("=========================================");
                System.out.println("1. 查看所有图书 2.借阅图书 3.归还图书 4.查看我的借阅记录 0.退出系统 ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1: {
                        lookAllBooks();
                        break;
                    }
                    case 2: {
                        System.out.println("请输入要借阅的图书名称：");
                        String bookname = scanner.next();
                        int result = borrowBook(shenfen, bookname);
                        if (result == 1) System.out.println("借阅成功！");
                        else if (result == -1) System.out.println("图书已借出");
                        else System.out.println("图书不存在");
                        break;
                    }
                    case 3: {
                        System.out.println("请输入要归还的图书名称：");
                        String bookname = scanner.next();
                        int result = returnBook(shenfen, bookname);
                        if (result == 1) System.out.println("归还成功！");
                        else if (result == -1) System.out.println("书不是你借的无法归还");
                        else if (result == -2) System.out.println("图书逾期未还");
                        else if (result == -3) System.out.println("图书未被借出借出");
                        else System.out.println("图书不存在");
                    }
                    case 4: {
                        myAllRecord(shenfen);
                        break;
                    }
                    case 0: {
                        System.out.println("欢迎下次使用");
                        System.exit(0);
                        return;
                    }
                }

            }
        }
    }



}
