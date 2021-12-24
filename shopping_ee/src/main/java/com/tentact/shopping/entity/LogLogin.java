package com.tentact.shopping.entity;

public class LogLogin {
    private int logId;
    private String logTime;
    private String logIp;
    private int accountId;
    private Account account;

    public LogLogin() {

    }

    public LogLogin(int logId, String logTime, String logIp, int accountId, Account account) {
        this.logId = logId;
        this.logTime = logTime;
        this.logIp = logIp;
        this.accountId = accountId;
        this.account = account;
    }

    @Override
    public String toString() {
        return "LogLogin{" +
                "logId=" + logId +
                ", logTime='" + logTime + '\'' +
                ", logIp='" + logIp + '\'' +
                ", accountId=" + accountId +
                ", account=" + account +
                '}';
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
