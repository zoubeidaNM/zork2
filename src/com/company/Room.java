package com.company;

import java.util.Random;

public class Room {

    // class variables:

    //the room name
    String name;
    //the room number
    private int number;
    //the room doors: their directions and connecting rooms
    private String[] doorsDirections;
    // door direction initials
    private String doorsDirectionsInitials[];
    //the user choice connected to the door direction
    private int[] userChoices;
    //connecting rooms to that room
    private int[] connectingRooms;
    //the items in the room
    private String[] items;
    //the description of the room
    private String description;
    //is it a quit room
    private boolean quitRoom;
    //has the room been visited
    private boolean visited;
    //the money in the room
    private int money;
    //is the money taken from this room
    boolean moneyTaken;
    //check if the character is in this room
    boolean characterIsHere;
    //character name
    String characterName;

    // initialize the class variables
    public void initialize(String toname, int tonumber, String[] todoorsDirections,String[] totodoorsDirectionsInitials,
                           int[] toconnectingRoom, int[] touserChoices, String[] toitems, String todescription) {
        name = toname;
        number = tonumber;
        doorsDirections = todoorsDirections;
        doorsDirectionsInitials = totodoorsDirectionsInitials;
        connectingRooms = toconnectingRoom;
        userChoices = touserChoices;
        items = toitems;
        description = todescription;
        quitRoom = false;
        visited = false;
        Random generator = new Random();
        money = generator.nextInt(1000);
        moneyTaken = false;
        characterIsHere = false;
        characterName = "Golden Glider";
    }


    // methods to get and set Room variables
    //name
    public String getName() {
        return name;
    }

    public void setName(String toname) {
        name = toname;
    }

    //number
    public int getNumber() {
        return number;
    }

    public void setNumber(int tonumber) {
        number = tonumber;
    }

    //doors directions
    public String[] getDoorsDirections() {
        return doorsDirections;
    }

    public void setDoorsDirections(String[] todoorsDirections) {
        doorsDirections = todoorsDirections;
    }

    //doors directions initials
    public String[] getDoorsDirectionsInitials() {
        return doorsDirectionsInitials;
    }

    public void setDoorsDirectionsInitials(String[] todoorsDirectionsInitials) {
        doorsDirectionsInitials = todoorsDirectionsInitials;
    }

    //doors connecting rooms
    public int[] getConnectingRooms() {
        return connectingRooms;
    }

    public void setConnectingRooms(int[] toconnectingRooms) {
        connectingRooms = toconnectingRooms;
    }

    //user choices
    public int[] getUserChoices() {
        return userChoices;
    }

    public void setUserChoices(int[] touserChoices) {
        userChoices = touserChoices;
    }

    //room items
    public String[] getItems() {
        return items;
    }

    public void setItems(String[] toitems) {
        items = toitems;
    }

    //description
    public String getDescription() {
        return description;
    }

    public void setDescription(String todescription) {
        description = todescription;
    }

    //check if it is a quit room
    public boolean isAQuitRoom() {
        return quitRoom;
    }

    public void setQuitRoom(boolean toquitRoom) {
        quitRoom = toquitRoom;
    }

    //check if the room had been visited
    public boolean isVisited() {  return visited; }

    public void setVisited(boolean tovisited) { visited = tovisited; }

    //money
    public int getMoney() {return money;}

    public void setMoney(int tomoney) {
        money = tomoney;
    }

    //moneyTaken
    public boolean isMoneyTaken() {return moneyTaken; }

    public void setMoneyTaken(boolean tomoneyTaken) {moneyTaken = tomoneyTaken;}

    //character
    public boolean isCharacterHere() {return characterIsHere;}

    public void setCharacterIsHere(boolean tocharacterIsHere) {characterIsHere = tocharacterIsHere;}

    public String getCharacterName() { return characterName;}

    //describe the doors to the user so that he can make a choice
    public void describeDoors() {
        System.out.print("\n{");
        for (int i = 0; i < doorsDirections.length; i++) {

            System.out.print("You can press (" + doorsDirectionsInitials[i] + ") to exit to the " + doorsDirections[i]);
            if (i < doorsDirections.length - 1) {
                System.out.print(" or ");
            }
        }
        if (money != 0) {
            System.out.print(" or press (T) to take the money");
        }
        if (isAQuitRoom()) {
            System.out.print(" or press (Q) to quit");
        }
        System.out.print("}\n");
    }

    //describe items in the room
    public void describeItems() {
        for (int i = 0; i < items.length; i++) {

            System.out.println("You see " + items[i] + ".");
        }
        if (money != 0) {
            System.out.println("You see ***$" + money + "***.");
        }
    }
}
