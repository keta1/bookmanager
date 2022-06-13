package icu.ketal.bookmanager.entry;

import java.util.Objects;

public class Operator {
    // 操作员编号、用户名、性别、年龄、证件号码、工作时间、电话号码、是否为管理员、密码
    private int id;
    private String name;
    private boolean sex;
    private int age;
    private String IDNum;
    private long workTime;
    private boolean isAdmin;
    private String password;

    public Operator() {
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

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getIDNum() {
        return IDNum;
    }

    public void setIDNum(String IDNum) {
        this.IDNum = IDNum;
    }

    public long getWorkTime() {
        return workTime;
    }

    public void setWorkTime(long workTime) {
        this.workTime = workTime;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operator operator)) return false;
        return id == operator.id && sex == operator.sex && age == operator.age && isAdmin == operator.isAdmin && Objects.equals(name, operator.name) && Objects.equals(IDNum, operator.IDNum) && Objects.equals(workTime, operator.workTime) && Objects.equals(password, operator.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex, age, IDNum, workTime, isAdmin, password);
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", IDNum='" + IDNum + '\'' +
                ", workTime='" + workTime + '\'' +
                ", isAdmin=" + isAdmin +
                ", password='" + password + '\'' +
                '}';
    }
}
