package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    static Room currentRoom;


    public static void main(String[] args) {
        int NumberOfRooms = 8;

        Room[] Rooms = initializeRooms();

        // initialise userMoney
        int userMoney = 0;

        // initialize the seen items
        String[] seenItems = new String[NumberOfRooms];

        // initialize the first room (foyer)
        currentRoom = Rooms[0];

        //put visited flag of the first room to true
        currentRoom.setVisited(true);

        //write the first welcome message to the user
        writeWelcomeMessage();

        // user answer
        String answer = "";
        Scanner keyboard = new Scanner(System.in);

        do {
            //get the user answer
            answer = keyboard.next();
            keyboard.nextLine();

            // if the answer is "t": take the money
            if (answer.equalsIgnoreCase("t")) {
                userMoney = userMoney + currentRoom.getMoney();
                System.out.println("You have $" + userMoney + ".");
                currentRoom.setMoney(0);
                currentRoom.setMoneyTaken(true);
                currentRoom.describeDoors();
            }

            // is the answer is not "q": quit ot "t":take the money
            if ((!answer.equalsIgnoreCase("q")) && (!answer.equalsIgnoreCase("t"))) {

                // get the direction the user choose
                String nextDirection =answer;

                //get the connecting rooms and door direction from the current room
                int[] connectingRooms = currentRoom.getConnectingRooms();
                String[] doorsDirectionsInitials = currentRoom.getDoorsDirectionsInitials();
                //int[] userChoices = currentRoom.getUserChoices();


                //if the user choice is valid, change current room
                boolean valid = false;
                for (int i = 0; i < doorsDirectionsInitials.length; i++) {
                    // when user choice equal one of the room direction
                    if (doorsDirectionsInitials[i].equalsIgnoreCase(nextDirection)) {
                        System.out.println("connectingRooms[i] "+ connectingRooms[i]);
                        currentRoom = Rooms[connectingRooms[i]-1];
                        System.out.println(currentRoom.getDescription());
                        System.out.println("You have (($" + userMoney + ")).");
                        if (currentRoom.isCharacterHere()) {
                            System.out.println("A Golden Glider is here. He will take all your money");
                            userMoney = 0;
                        }
                        currentRoom.describeItems();
                        currentRoom.describeDoors();
                        currentRoom.setVisited(true);
                        if (currentRoom.getNumber() == 6) {
                            // set if the user will find the secret room
                            int[] connectingRooms2 = {7};
                            if (getA25PercentChance()) {
                                connectingRooms2[0] = 8;
                            }
                            currentRoom.setConnectingRooms(connectingRooms2);
                        }

                        valid = true;
                    }
                }
                //if the user choice is invalid:
                if (!valid) {
                    System.out.println("I don't know what you mean...try again");
                }


            }
        } while (!answer.equalsIgnoreCase("q"));

        //count the number of visited rooms and the seen items in every room
        //visited rooms counter
        int roomCounter = 0;
        for (int i = 0; i < Rooms.length; i++) {
            if (Rooms[i].isVisited()) {
                //increment the room counter
                seenItems[roomCounter] = Rooms[i].getItems()[0];
                 roomCounter++;
            }
        }

        //

        //when the user quit, print the number of rooms visitedvand the user money
        System.out.println("\nGoodbye! you visited " + roomCounter + " rooms and you have $" + userMoney + ".");
        // print the items the user saw
        System.out.print("you saw: ");
        for (int i = 0; i < roomCounter; i++) {
            System.out.print(seenItems[i]+" ** ");
        }
        //tell the user if a ghost is following him
        if (getA25PercentChance()) {
            System.out.println("\n----There is a ghost following you!!!!!!-----");
        }

    }


    //display the first welcome message
    private static void writeWelcomeMessage() {
        System.out.println("-----------------------------------------------------");
        System.out.println("Welcome to Zork!");
        System.out.println("-----------------------------------------------------\n");
        System.out.println(currentRoom.getDescription());
        currentRoom.describeItems();
        currentRoom.describeDoors();
    }

    //get a true 25% of the time
    public static boolean getA25PercentChance() {
        //25%chance
        //boolean true25 = (random.nextInt(4) == 0) ? true : false;
        Random random = new Random();
        //For 1 in 4
        int chanceOfTrue = 4;

        int x = random.nextInt(chanceOfTrue);
        //System.out.println("x="+x);
        if (x == 0) {
            return true;
        } else {
            return false;
        }
    }

    //initialize the 8 rooms
    public static Room[] initializeRooms() {

        //instantiate the foyer
        Room foyer = new Room();

        String name = "foyer";
        int number = 1;
        String[] doorsDirections = {"north"};
        String[] doorsDirectionsInitials = {"N"};
        int[] connectingRooms = {2};
        int[] userChoices = {1};
        String[] items = {"a dead scorpion"};
        String description = "You are standing in the foyer of an old house.";

        foyer.initialize(name, number, doorsDirections, doorsDirectionsInitials, connectingRooms, userChoices, items, description);
        foyer.setQuitRoom(true);

        /*--------------------------------------------------------------------------------------*/
        //instantiate the front room
        Room FrontRoom = new Room();

        name = "front room";
        number = 2;
        String[] items1 = {"a piano"};
        String[] doorsDirections1 = {"south", "west", "east"};
        String[] doorsDirectionsInitials1 = {"S","W","E"};
        int[] connectingRooms1 = {1, 3, 4};
        int[] userChoices1 = {1, 2, 3};
        description = "You are standing in the front room.";

        FrontRoom.initialize(name, number, doorsDirections1, doorsDirectionsInitials1, connectingRooms1, userChoices1, items1, description);

          /*--------------------------------------------------------------------------------------*/
        //instantiate the library
        Room Library = new Room();

        name = "library";
        number = 3;
        String[] items2 = {"spiders"};
        String[] doorsDirections2 = {"north", "east"};
        String[] doorsDirectionsInitials2 = {"N","E"};
        int[] connectingRooms2 = {5, 2};
        int[] userChoices2 = {1, 2};
        description = "You are standing in the library room.";

        Library.initialize(name, number, doorsDirections2, doorsDirectionsInitials2, connectingRooms2, userChoices2, items2, description);

          /*--------------------------------------------------------------------------------------*/
        //instantiate the kitchen
        Room Kitchen = new Room();

        name = "kitchen";
        number = 4;
        String[] items3 = {"bats"};
        String[] doorsDirections3 = {"north", "west"};
        String[] doorsDirectionsInitials3 = {"N","W"};
        int[] connectingRooms3 = {7, 2};
        int[] userChoices3 = {1, 2};
        description = "You are standing in the kitchen.";

        Kitchen.initialize(name, number, doorsDirections3, doorsDirectionsInitials3, connectingRooms3, userChoices3, items3, description);

          /*--------------------------------------------------------------------------------------*/
        //instantiate the dinning room
        Room DinningRoom = new Room();

        name = "dinning room";
        number = 5;
        String[] items4 = {"a dusty empty box"};
        String[] doorsDirections4 = {"south"};
        String[] doorsDirectionsInitials4 = {"S"};
        int[] connectingRooms4 = {3};
        int[] userChoices4 = {1};
        description = "You are standing in the dinning room.";

        DinningRoom.initialize(name, number, doorsDirections4, doorsDirectionsInitials4, connectingRooms4, userChoices4, items4, description);

          /*--------------------------------------------------------------------------------------*/
        //instantiate the vault
        Room Vault = new Room();

        name = "vault";
        number = 6;
        String[] items5 = {"3 walking skeletons"};
        String[] doorsDirections5 = {"east"};
        String[] doorsDirectionsInitials5 = {"E"};
        // connect to the secret room 25% of the time
        int[] connectingRooms5 = {7};
        if (getA25PercentChance()) {
            connectingRooms5[0] = 8;
        }
        int[] userChoices5 = {1};
        description = "You are standing in the vault.";

        Vault.initialize(name, number, doorsDirections5, doorsDirectionsInitials5, connectingRooms5, userChoices5, items5, description);

        /*--------------------------------------------------------------------------------------*/
        //instantiate the parlor
        Room Parlor = new Room();

        name = "parlor";
        number = 7;
        String[] items6 = {"a treasure chest"};
        String[] doorsDirections6 = {"west", "south"};
        String[] doorsDirectionsInitials6 = {"W","S"};
        int[] connectingRooms6 = {6, 4};
        int[] userChoices6 = {1, 2};
        description = "You are standing in the parlor.";

        Parlor.initialize(name, number, doorsDirections6, doorsDirectionsInitials6, connectingRooms6, userChoices6, items6, description);

          /*--------------------------------------------------------------------------------------*/
        //instantiate the secret room
        Room SecretRoom = new Room();

        name = "secret room";
        number = 8;
        String[] items7 = {"a pile of gold"};
        String[] doorsDirections7 = {"west"};
        String[] doorsDirectionsInitials7 = {"W"};
        int[] connectingRooms7 = {6};
        int[] userChoices7 = {1};
        description = "You are standing in the secret room";

        SecretRoom.initialize(name, number, doorsDirections7, doorsDirectionsInitials7, connectingRooms7, userChoices7, items7, description);

          /*--------------------------------------------------------------------------------------*/


        Room[] rooms = {foyer, FrontRoom, Library, Kitchen, DinningRoom, Vault, Parlor, SecretRoom};

        // randomly set a character in one of the rooms
        Random generator = new Random();
        int roomNumber = generator.nextInt(7);
        //System.out.println(roomNumber);
        rooms[roomNumber].setCharacterIsHere(true);
        return rooms;
    }

}
