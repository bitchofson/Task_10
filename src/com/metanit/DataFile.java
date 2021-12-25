package com.metanit;

import java.io.*;
import java.util.*;

public class DataFile {

    public static void writeToFile(String[][] matrix, String file){
        try {
            PrintWriter writer = new PrintWriter(file,"UTF-8");
            for(int i = 0; i < matrix.length;i++){
                for(int j = 0; j < matrix[0].length;j++){
                    writer.print((j==0 ?"":" ") + matrix[i][j]);
                }
                writer.println();
            }
            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.err.println("Ошибка");
        }
    }

    public static String[][] readFile(String file){
        int length = 0;
        String[][] matrix = new String[length][3];
        try {
            Scanner scanner = new Scanner(new File(file), "UTF-8");
            while (scanner.hasNextLine()) {
                length++;
                String line = scanner.nextLine();
                String[] array = line.split(", ");
                matrix = Arrays.copyOf(matrix, length);
                matrix[length - 1] = array;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.print("Error");
        }
        return matrix;
    }

}
