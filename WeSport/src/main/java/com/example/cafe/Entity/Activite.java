/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.cafe.Entity;

import java.sql.Date;


public class Activite {

    private Integer id;
    private String activiteId;
    private String activiteName;
    private String type;
    private String image;


    public Activite(Integer id, String activiteId,  String activiteName, String image) {
        this.id = id;
        this.activiteId = activiteId;
        this.activiteName = activiteName;
        this.image = image;

    }
    
    public Activite(Integer id, String activiteId, String activiteName,
                    String type, Integer quantity, Double price, String image, Date date){
        this.id = id;
        this.activiteId = activiteId;
        this.activiteName = activiteName;
        this.type = type;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public String getActiviteId() {
        return activiteId;
    }

    public String getActiviteName() {
        return activiteName;
    }

    public String getType() {
        return type;
    }

    public String getImage() {
        return image;
    }
}
