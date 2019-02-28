package com.example.android.jobprofile.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Milind Amrutkar on 26-02-2019.
 */
public class Employee {

    @SerializedName("id")
    private Integer id;

    @SerializedName("image")
    private String imageUrl;

    @SerializedName("name")
    private String name;

    @SerializedName("designation")
    private Designation designation;

    @SerializedName("location")
    private String location;

    @SerializedName("highest_qualification")
    private HighestQualification highestQualification;

    @SerializedName("experience")
    private String experience;

    @SerializedName("expected_ctc")
    private String expectedCtc;

    @SerializedName("skills")
    private List<Skill> skills = null;

    @SerializedName("work_functions")
    private List<WorkFunction> workFunctions = null;

    @SerializedName("industries")
    private List<Industry> industries = null;

    @SerializedName("latitude")
    private Double latitude;

    @SerializedName("longitude")
    private Double longitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Designation getDesignation() {
        return designation;
    }

    public void setDesignation(Designation designation) {
        this.designation = designation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HighestQualification getHighestQualification() {
        return highestQualification;
    }

    public void setHighestQualification(HighestQualification highestQualification) {
        this.highestQualification = highestQualification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getExpectedCtc() {
        return expectedCtc;
    }

    public void setExpectedCtc(String expectedCtc) {
        this.expectedCtc = expectedCtc;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<WorkFunction> getWorkFunctions() {
        return workFunctions;
    }

    public void setWorkFunctions(List<WorkFunction> workFunctions) {
        this.workFunctions = workFunctions;
    }

    public List<Industry> getIndustries() {
        return industries;
    }

    public void setIndustries(List<Industry> industries) {
        this.industries = industries;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
