package com.thrivikraman.sreejith.dev.splitter.models;


/* The model class for creating the object for the entity "expense" */
public class expenses {

    private String date,expenseName, friendName,status,groupStatus;
    private long amount;

    public expenses(String date, String expenseName, String friendName, String status, String groupStatus, long amount) {
        this.date = date;
        this.expenseName = expenseName;
        this.friendName = friendName;
        this.status = status;
        this.groupStatus = groupStatus;
        this.amount = amount;
    }

    public String getDate() { return date; }

    public String getExpenseName() { return expenseName; }

    public String getFriendName() { return friendName; }

    public String getStatus() { return status; }

    public String getGroupStatus() { return groupStatus; }

    public long getAmount() { return amount; }


}
