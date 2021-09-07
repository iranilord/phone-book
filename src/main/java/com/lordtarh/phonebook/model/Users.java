package com.lordtarh.phonebook.model;

import com.lordtarh.phonebook.annotations.*;
import lombok.Data;

@Data
@Table(name = "users")
public class Users {
    @Column()
    @PrimaryKey
    private int id;
    @Column()
    private String name;
    @Column()
    private String family ;
    @Column()
    private String phone;
}
