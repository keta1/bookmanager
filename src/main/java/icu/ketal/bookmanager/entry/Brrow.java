package icu.ketal.bookmanager.entry;

import java.util.Objects;

public class Brrow {
    // 借阅编号、书籍编号、操作员编号、读者编号、是否归还、借书日期、应还日期
    private int id;
    private int bookId;
    private int operatorId;
    private int readerId;
    private boolean returned;
    private long borrowDate;
    private long shouldReturnDate;

    public Brrow() {
    }

    public Brrow(int id, int bookId, int operatorId, int readerId, boolean returned, long borrowDate, long shouldReturnDate) {
        this.id = id;
        this.bookId = bookId;
        this.operatorId = operatorId;
        this.readerId = readerId;
        this.returned = returned;
        this.borrowDate = borrowDate;
        this.shouldReturnDate = shouldReturnDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public long getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(long borrowDate) {
        this.borrowDate = borrowDate;
    }

    public long getShouldReturnDate() {
        return shouldReturnDate;
    }

    public void setShouldReturnDate(long shouldReturnDate) {
        this.shouldReturnDate = shouldReturnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Brrow brrow)) return false;
        return id == brrow.id && bookId == brrow.bookId && operatorId == brrow.operatorId && readerId == brrow.readerId && returned == brrow.returned && borrowDate == brrow.borrowDate && shouldReturnDate == brrow.shouldReturnDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, operatorId, readerId, returned, borrowDate, shouldReturnDate);
    }
}
