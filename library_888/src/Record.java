import java.time.LocalDate;

public class Record {

//    - 借阅人姓名
    String    borrowerName;

//- 图书名称
    String    bookName;

//- 借出日期
    LocalDate borrowDate;

//- 应还日期
    LocalDate dueDate = borrowDate.plusDays(14);

//- 实际归还日期
    LocalDate actualReturnDate;

//- 当前状态：未归还、已归还、已逾期
//    NOT_RETURNED 未归还
//    RETURNED 已归还
//    OVERDUE 已逾期
    public enum Status {
        NOT_RETURNED, RETURNED, OVERDUE
    }


//    - 借阅记录的构造函数
    public Record(String borrowerName, String bookName) {
        this.borrowerName = borrowerName;
        this.bookName = bookName;
        this.borrowDate = LocalDate.now();
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }
}
