package com.example.android.jobprofile.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Milind Amrutkar on 26-02-2019.
 */
public class Industry {

    @SerializedName("id")
    private Integer id;

    @SerializedName("name")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
