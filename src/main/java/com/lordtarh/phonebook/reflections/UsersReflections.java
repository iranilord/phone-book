package com.lordtarh.phonebook.reflections;

import com.lordtarh.phonebook.annotations.Column;
import com.lordtarh.phonebook.annotations.PrimaryKey;
import com.lordtarh.phonebook.annotations.Table;
import com.lordtarh.phonebook.model.Users;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class UsersReflections {
    private static Class UserCanno= Users.class;


    public static String getUsersCAnno(){
        String tableName="";
        if (UserCanno.isAnnotationPresent(Table.class)){
            Table table = (Table) UserCanno.getAnnotation(Table.class);
            if (!table.name().equals("")){
                tableName = table.name();
            }else {
                tableName = UserCanno.getSimpleName();
            }

        }
        return tableName;
    }

    public static List<String> getUsersFAnno(){
        List<String> list = new ArrayList<String>();
        Field[] fileds= UserCanno.getDeclaredFields();
        for (Field field : fileds) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if ( annotation instanceof Column){
                Column column = field.getAnnotation(Column.class);
                    if (column.name().equals("")){
                        list.add(field.getName());
                    }else {
                        list.add(column.name());
                    }
                }
            }

        }

        return list;

    }

    public static String getUsersPKanno(){
        String PK="";
        Field[] fileds= UserCanno.getDeclaredFields();
        for (Field field : fileds) {
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if ( annotation instanceof PrimaryKey){
                    PrimaryKey primaryKey = field.getAnnotation(PrimaryKey.class);
                    if (primaryKey.name().equals("")){
                        PK = field.getName();
                    }else {
                        PK = field.getName();
                    }
                }
            }

        }
        return PK;
    }

}
