package icu.ketal.bookmanager.entry;

import java.util.Objects;

public class Reader {
    // 读者编号、读者姓名、性别、年龄、证件号码、会员证件有效日期、最大借书量、电话号码、押金、证件类型、职业、办证日期。
    private int id;
    private String name;
    private boolean sex;
    private int age;
    private String IDNum;
    private long effectiveDate;
    private int maxToBorrow;
    private long phoneNumber;
    private double deposit;
    private String certificateType;
    private String job;
    private long issueDate;

    public Reader() {
    }

    public Reader(int id, String name, boolean sex, int age, String IDNum, long effectiveDate, int maxToBorrow, long phoneNumber, double deposit, String certificateType, String job, long issueDate) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.IDNum = IDNum;
        this.effectiveDate = effectiveDate;
        this.maxToBorrow = maxToBorrow;
        this.phoneNumber = phoneNumber;
        this.deposit = deposit;
        this.certificateType = certificateType;
        this.job = job;
        this.issueDate = issueDate;
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

    public long getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(long effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public int getMaxToBorrow() {
        return maxToBorrow;
    }

    public void setMaxToBorrow(int maxToBorrow) {
        this.maxToBorrow = maxToBorrow;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public String getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(String certificateType) {
        this.certificateType = certificateType;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public long getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(long issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reader reader)) return false;
        return id == reader.id && sex == reader.sex && age == reader.age && effectiveDate == reader.effectiveDate && maxToBorrow == reader.maxToBorrow && phoneNumber == reader.phoneNumber && Double.compare(reader.deposit, deposit) == 0 && issueDate == reader.issueDate && Objects.equals(name, reader.name) && Objects.equals(IDNum, reader.IDNum) && Objects.equals(certificateType, reader.certificateType) && Objects.equals(job, reader.job);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex, age, IDNum, effectiveDate, maxToBorrow, phoneNumber, deposit, certificateType, job, issueDate);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", IDNum='" + IDNum + '\'' +
                ", effectiveDate=" + effectiveDate +
                ", maxToBorrow=" + maxToBorrow +
                ", phoneNumber=" + phoneNumber +
                ", deposit=" + deposit +
                ", certificateType='" + certificateType + '\'' +
                ", job='" + job + '\'' +
                ", issueDate=" + issueDate +
                '}';
    }
}
