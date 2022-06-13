package icu.ketal.bookmanager.entry;

import java.util.Objects;

public class Category {
    // 图书类别编号、图书类别名称、可借天数、迟还一天的罚款数目
    private int id;
    private String name;
    private int daysToReturn;
    private double finePerDay;

    public Category() {
    }

    public Category(int id, String name, int daysToReturn, int finePerDay) {
        this.id = id;
        this.name = name;
        this.daysToReturn = daysToReturn;
        this.finePerDay = finePerDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDaysToReturn() {
        return daysToReturn;
    }

    public void setDaysToReturn(int daysToReturn) {
        this.daysToReturn = daysToReturn;
    }

    public double getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(double finePerDay) {
        this.finePerDay = finePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category category)) return false;
        return id == category.id && daysToReturn == category.daysToReturn && finePerDay == category.finePerDay && Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, daysToReturn, finePerDay);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", daysToReturn=" + daysToReturn +
                ", finePerDay=" + finePerDay +
                '}';
    }
}
