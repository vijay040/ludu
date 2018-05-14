package com.vsoftcoders.ludogame.model;

import android.support.annotation.NonNull;

/**
 * Created by Vijay on 1/24/2018.
 */

public class User  implements Comparable<User>{

    private String name;
    private String score;
    private String id;
    private String email;
    private String country;
    private String contact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public int compareTo(@NonNull User user) {
        if(Integer.parseInt(score)==Integer.parseInt(user.getScore()))
            return 0;
        else if(Integer.parseInt(score)>Integer.parseInt(user.getScore()))
            return 1;
        else
            return -1;
    }

   /* @Override
    public int compare(User u1, User u2) {
        return u2.getScore().compareTo(u1.getScore());
    }*/
}
