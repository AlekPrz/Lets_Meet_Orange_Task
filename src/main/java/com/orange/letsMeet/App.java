package com.orange.letsMeet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {


        MenuService menuService = new MenuService("calendar1.json","calendar2.json");
        menuService.mainMenu();


    }

}

