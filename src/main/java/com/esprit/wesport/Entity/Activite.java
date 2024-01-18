/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.wesport.Entity;

public class Activite {
    private Integer id;
    private String activiteName;
    private String image;

    public Activite(Integer id, String activiteName, String image) {
        this.id = id;
        this.activiteName = activiteName;
        this.image = image;

    }

    public Activite(String activiteName, String image) {
        this.activiteName = activiteName;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getActiviteName() {
        return activiteName;
    }

    public String getImage() {
        return image;
    }
}
