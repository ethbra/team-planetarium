package com.revature.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestDataLoader {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of the file: ");
        String fileName = sc.nextLine();
        List<Object[]> response = importData(fileName);
        
        for (Object[] row : response) {
            for (Object o : row) {
                System.out.print(o + ", ");
            }
            System.out.println();
        }
    }

    public static List<Object[]> importData(String path) throws FileNotFoundException {

        File file = new File(path);
        ArrayList<Object[]> data = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {

//            skip the header line
            scanner.nextLine();

//            list of titles (to keep track of the row)
            List<String> names = new ArrayList<>();

//            the list of fields for the test data
            List<Object> row = new ArrayList<>();


            while (scanner.hasNextLine()) {
//                structured as an array with the name, then value
                String[] line = scanner.nextLine().split(",");
                
                for (int i = 0; i < line.length; i++) {
                    System.out.println("line[i] = " + line[i]);
                }
//                if already exists, then we've looped to a new test set
                if (names.contains(line[0])) {
//                    create a new array
                    names.clear();

                    data.add(row.toArray());

                    row.clear();
                }

                names.add(line[0]);

//                parse and add int for all names of "ID"
                if (line[0].equals("ID")) {
                    try {

                        int id = Integer.parseInt(line[1]);
                        row.add(id);

                    } catch (Exception e) {
                        System.out.println("Not an integer, continue..");
                    }
                } else {
                    row.add(line[1]);
                }
            }
        }

        return data;

    }
}
