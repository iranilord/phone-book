package com.lordtarh.phonebook.model;

import com.lordtarh.phonebook.annotations.*;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
@Table(name = "users")
public class Users {
    @Column()
    @PrimaryKey
    private int id = -1;
    @Column()
    @NonNull
    private String name;
    @Column()
    @NonNull
    private String family ;
    @Column()
    @NonNull
    private String phone;
}
