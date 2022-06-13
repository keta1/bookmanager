package icu.ketal.bookmanager.entry;

import java.util.Objects;

public class Book {
    // 图书编号、类别编号、图书名称、作者、译者、出版社、出版日期、书籍价格
    private int id;
    private int categoryId;
    private String name;
    private String author;
    private String translator;
    private String publisher;
    private long publishDate;
    private double price;

    public Book() {
    }

    public Book(int id, int categoryId, String name, String author, String translator, String publisher, long publishDate, double price) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.author = author;
        this.translator = translator;
        this.publisher = publisher;
        this.publishDate = publishDate;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public long getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(long publishDate) {
        this.publishDate = publishDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return id == book.id && categoryId == book.categoryId && Double.compare(book.price, price) == 0 && Objects.equals(name, book.name) && Objects.equals(author, book.author) && Objects.equals(translator, book.translator) && Objects.equals(publisher, book.publisher) && Objects.equals(publishDate, book.publishDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryId, name, author, translator, publisher, publishDate, price);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", categoryId=" + categoryId +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", translator='" + translator + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishDate='" + publishDate + '\'' +
                ", price=" + price +
                '}';
    }
}
