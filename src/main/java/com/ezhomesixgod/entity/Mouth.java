package com.ezhomesixgod.entity;

/**
 * @author renyang
 * @description
 * @createTime 2018-09-27 10:21
 */
public class Mouth {
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void sayMouth(){
        System.out.println("Mouth sayMouth");
    }
}
