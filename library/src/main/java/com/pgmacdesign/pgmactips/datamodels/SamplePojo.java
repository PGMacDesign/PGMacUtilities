package com.pgmacdesign.pgmactips.datamodels;

import com.google.gson.annotations.SerializedName;

/**
 * Sample POJO that demonstrates serialization
 * Created by pmacdowell on 8/18/2016.
 */
public class SamplePojo {
	
	@SerializedName("id")
    private long id;
	@SerializedName("name")
    private String name;
	@SerializedName("age")
    private int age;
    @SerializedName("gender")
    private String gender;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
