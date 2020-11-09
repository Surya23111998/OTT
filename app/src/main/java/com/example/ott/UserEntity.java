package com.example.ott;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")

public class UserEntity {



    @PrimaryKey(autoGenerate = true)
    Integer id;

    @ColumnInfo(name = "userMobile")
    String userMobile;

    @ColumnInfo(name = "userEmail")
    String userEmail;

    @ColumnInfo(name = "userPassword")
    String userPassword;

    @ColumnInfo(name = "userName")
    String userName;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserMobile() { return userMobile; }

    public void setUserMobile(String userMobile) { this.userMobile = userMobile; }

}
