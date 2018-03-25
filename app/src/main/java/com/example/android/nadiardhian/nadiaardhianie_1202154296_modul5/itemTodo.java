package com.example.android.nadiardhian.nadiaardhianie_1202154296_modul5;

/**
 * Created by user on 3/25/2018.
 */

public class itemTodo {
    String todo, desc, prior; //deklarasi variable yang akan di get datanya

    public itemTodo(String todo, String desc, String prior) { //item yang digunakan menggunakan tipe data string
        this.todo = todo; //get nama todo
        this.desc = desc; //get desc
        this.prior = prior; //get prior
    }

    public String getTodo() { //get nama todo
        return todo;
    }

    public void setTodo(String todo) { //set nama todo
        this.todo = todo;
    }

    public String getDesc() { //get desc
        return desc;
    }

    public void setDesc(String desc) { //set desc
        this.desc = desc;
    }

    public String getPrior() { //get prior
        return prior;
    }

    public void setPrior(String prior) { //set prior
        this.prior = prior;
    }
}

