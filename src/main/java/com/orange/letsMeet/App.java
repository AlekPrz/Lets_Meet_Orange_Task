package com.orange.letsMeet;


import com.orange.letsMeet.service.MenuService;

public class App
{
    public static void main( String[] args )
    {

        MenuService menuService = new MenuService("calendar1.json","calendar2.json");
        menuService.mainMenu();


    }

}

