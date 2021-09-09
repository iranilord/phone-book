package com.lordtarh.phonebook.console;

import com.lordtarh.phonebook.database.dbConnections;
import com.lordtarh.phonebook.database.userdao.UsersDao;
import com.lordtarh.phonebook.database.userdao.UsersImpDao;
import com.lordtarh.phonebook.model.Users;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class Dialogs {
    private  static  Scanner scanner = new Scanner(System.in);
    private  static  UsersDao<Users> usersDao = new UsersImpDao();
    private  static  List<Users> usersList = new ArrayList<>();
    private  static LinkedHashMap<Integer,Integer> userIndex = new LinkedHashMap<>();
    private  static  int count = 1 ;


    public static int mainMenu(){
        System.out.print("Salam Be Daftarche Telephone khosh Amadid :D \n" +
                "* Lotfan Az Miyan Gozine Haye Zir Entekhab Koonid *\n" +
                "***********************\n" +
                "1) Add User \uD83D\uDC64 \n" +
                "2) Search User \uD83D\uDD0E \n" +
                "3) Edit User \uD83E\uDD10 \n" +
                "4) Delete User \uD83D\uDE1E \n" +
                "**********************+\n" +
                "Coded By LORD V1.0\n" +
                "Enter Your Choice : ");
        return scanner.nextInt();    }

    public static void menuSelector(){
        int selected = mainMenu();
        switch (selected){
            case 1 :
                    addUser();
                break;
            case 2 :
                searchUsers();
                break;

            case 3:
                editUsermebu();
                menuSelector();
                break;

            case 4:
                deleteUser();
                menuSelector();
                break;

            default:
                System.out.println("Error ! Bad Choice");
        }
    }

    public static void editUsermebu(){
        System.out.print("Darsorati ke ID karbar ramidanid Vared konid Darghire in sorat (Find) ravared konid : ");
        String selected = scanner.next().toLowerCase();
        if (selected.equals("find")){
            searchUsers();
        }else {
            int id = Integer.valueOf(selected);
            editUser(id);
        }
    }

    private static void editUser(int id) {
        int map = userIndex.get(id);
        Users userX = null;
        for (Users users : usersList) {
            if (users.getId() == map){
                userX = users;
            }
        }
        System.out.print(userX.getName()+" => ");
        userX.setName(scanner.next());
        System.out.print(userX.getFamily()+" => ");
        userX.setFamily(scanner.next());
        System.out.print(userX.getPhone()+" => ");
        userX.setPhone(scanner.next());

        usersDao.update(userX);

        System.out.println("Karbar ba Movafaghiyat UPDATE shood !");
        scanner.next();

    }

    public static void searchUsers(){
        System.out.println("baraye namayesh Tamamli Karbaran ((ALL)) \n" +
                "dar ghire in sorat ((Name Karbar Morenazar)) khod ra Vared Koonid !\n" +
                "baraye barghasht be Menu Asli ((exit)) ra vared Konid ");
        String choice = scanner.next().toLowerCase();
        if (choice.equals("all")){
            usersList = usersDao.search("");
            userMapping(usersList);
        }else if (choice.equals("exit")){
            menuSelector();
        }else{
            usersList = usersDao.search(choice);
            userMapping(usersList);
        }
        scanner.next();
        menuSelector();

    }

    public static void addUser(){
        while (true) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            String name, family, phone;
            System.out.print("Enter User Name : ");
            name = scanner.next();
            System.out.print("Enter User Family : ");
            family = scanner.next();
            System.out.print("Enter User PhoneNO : ");
            phone = scanner.next();

            System.out.println(name + " " + family + " " + phone + "\n Taaaid Mikonoid ? Y/n");
            String conf = scanner.next().toLowerCase();
            if (conf.charAt(0) == 'y') {
                usersDao.create(Users.builder().name(name).family(family).phone(phone).build());
                break;
            }
        }
    }

    private static void userMapping(List<Users> usersList){
       count =1 ;
        userIndex.clear();
        for (Users users : usersList) {
            userIndex.put(count,users.getId());
            System.out.printf("%d) %10s %10s %10s %n",count,users.getName(),users.getFamily(),users.getPhone());
            count++;
        }
    }

    private static void deleteUser(){
        System.out.print("Darsorati ke ID karbar ramidanid Vared konid Darghire in sorat (Find) ravared konid : ");
        String selected = scanner.next().toLowerCase();
        if (selected.equals("find")){
            searchUsers();
        }else {
            int id = Integer.valueOf(selected);
            deleteUserAc(id);
        }
    }

    private static void deleteUserAc(int id) {
        int map = userIndex.get(id);
        usersDao.delete(map);
        System.out.println("KArbar ba Movafaghiyat DELETE shoood !!!!");
        scanner.next();
    }
}
