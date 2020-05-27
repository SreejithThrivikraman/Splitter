package com.thrivikraman.sreejith.dev.splitter.models;


/* Status flag for  confirming login and sign-in status */

public class Status {

    private String message;
    private boolean flag;

    public Status(String message, boolean flag) {
        this.message = message;
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
