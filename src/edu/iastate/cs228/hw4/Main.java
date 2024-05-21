package edu.iastate.cs228.hw4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


/*
    @author Rafat Momin
 */
public class Main {
    public static void main(String[] args) {
        try {

            /*
                Using Static file path
             */
//            String filePath = "src/edu/iastate/cs228/hw4/res/monalisa.arch";
//            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            /*
                Using Dynamic file path
             */
            Scanner scanner = new Scanner(System.in);
            System.out.println("Please enter filename to decode: ");
            String filename = scanner.nextLine();
            BufferedReader reader = new BufferedReader(new FileReader(filename));


            StringBuilder treeString = new StringBuilder();
            String encodedMessage;
            String line = reader.readLine();
            treeString.append(line);
            line = reader.readLine();
            if (!line.matches("^[01]+$")) {
                treeString.append('\n').append(line);
                encodedMessage = reader.readLine();
            } else {
                encodedMessage = line;
            }

            MsgTree root = new MsgTree(treeString.toString());
            System.out.println("character code\n-------------------------");
            MsgTree.printCodes(root, "");
//            root.showTree();
            root.decode(root, encodedMessage);
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }


}
