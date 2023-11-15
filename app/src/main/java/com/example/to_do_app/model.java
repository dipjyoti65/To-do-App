package com.example.to_do_app;

public class model {
    String task;
    String primaryKey;

    String UserEmail;

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimarykey(String primaryKey){
        this.primaryKey=primaryKey;
    }
    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public model(String primaryKey,String task) {
        this.primaryKey=primaryKey;
        this.task = task;

    }
}
