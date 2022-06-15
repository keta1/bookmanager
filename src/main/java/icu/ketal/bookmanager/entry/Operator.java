package icu.ketal.bookmanager.entry;

import java.util.Objects;

public class Operator {
    // 操作员编号、用户名、性别、年龄、证件号码、工作时间、电话号码、是否为管理员、密码
    private int id;
    private String username;
    private boolean sex;
    private int age;
    private String IDNum;
    private long workTime;
    private long phoneNum;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
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
        return id == operator.id && sex == operator.sex && age == operator.age && workTime == operator.workTime && phoneNum == operator.phoneNum && isAdmin == operator.isAdmin && Objects.equals(username, operator.username) && Objects.equals(IDNum, operator.IDNum) && Objects.equals(password, operator.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, sex, age, IDNum, workTime, phoneNum, isAdmin, password);
    }

    @Override
    public String toString() {
        return "Operator{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", IDNum='" + IDNum + '\'' +
                ", workTime=" + workTime +
                ", phoneNum=" + phoneNum +
                ", isAdmin=" + isAdmin +
                ", password='" + password + '\'' +
                '}';
    }
}
