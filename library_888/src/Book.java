import java.io.Serial;
import java.io.Serializable;

public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    String bookname;//书名

    String author;//作家

    float price;//价格

    String type;//类型

    State state;//借阅状态
    //借阅状态
//    - `AVAILABLE`：可借
//- `BORROWED`：已借出
//- `OVERDUE`：逾期未还
    public enum State{
        AVAILABLE,BORROWED,OVERDUE
    }

    public Book(String bookname, String author, float price, String type) {
        this.bookname = bookname;
        this.author = author;
        this.price = price;
        this.type = type;
    }

    public String getBookname() {
        return bookname;
    }

    public String getAuthor() {
        return author;
    }

    public float getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
