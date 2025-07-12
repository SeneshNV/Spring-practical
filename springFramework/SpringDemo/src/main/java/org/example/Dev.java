package org.example;

public class Dev {

    private Computer com;

    public Dev() {
        System.out.println("Dev constructor called ...!");
    }

    public Computer getCom() {
        return com;
    }

    public void setCom(Computer com) {
        this.com = com;
    }

    public void build(){
        System.out.println("Dev build...!");
        com.compile();
    }
}
