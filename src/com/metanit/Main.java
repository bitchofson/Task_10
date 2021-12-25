package com.metanit;

import javax.swing.*;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static class InputArgs {
        public String inputFile;
        public String outputFile;
        public String abiturWin;
        public boolean error;
        public boolean help;
        public boolean window;
    }

    public static InputArgs parseArgs(String[] args) {
        InputArgs params = new InputArgs();

        if (args.length > 0) {
            if (args[0].equals("--help")) {
                params.help = true;
                return params;
            }
            if (args[0].equals("--window")) {
                params.window = true;
                return params;
            }
            if (args.length < 2) {
                params.help = true;
                params.error = true;
                return params;
            }
            params.abiturWin = args[0];
            if (args.length > 1) {
                params.inputFile  = args[1];
                params.outputFile = args[2];
            }
        } else {
            params.help = true;
            params.error = true;
        }
        return params;
    }

    public static List<Student> compound(String[][] dataString) {

        List<Student> list = new ArrayList<>();

        for (String[] arr : dataString) {
            list.add(new Student(arr[0], arr[1], arr[2], Integer.parseInt(arr[3]), Integer.parseInt(arr[4]), Integer.parseInt(arr[5])));
        }

        return list;
    }

    public static String[][] outputToStringArrayConsole(List<Student> list, int N){

        int k = 7;
        String[][] s = new String[N][k];

        for (int i = 0; i < N; i++) {
            if (i < list.size()) {
                Student student = list.get(i);

                for (int j = 0; j < k; j++) {
                    if (j == 0) s[i][j] = "ФИО: | " + student.name;
                    if (j == 1) s[i][j] = " " + student.surname;
                    if (j == 2) s[i][j] = " " + student.patronymic + "\t";
                    if (j == 3) s[i][j] = "\t|Oбщий балл:\t" + student.mark;
                    if (j == 4) s[i][j] = "\t|Русский:\t" + student.markRusLang;
                    if (j == 5) s[i][j] = "\t|Математика:\t" + student.markMath;
                    if (j == 6) s[i][j] = "\t|Физика:\t" + student.markPhysics;
                }
            } else {
                for (int j = 0; j < k; j++) {
                    if (j == 0) s[i][j] = "ФИО: | " + " ";
                    if (j == 1) s[i][j] = " " + " ";
                    if (j == 2) s[i][j] = " " + " " + "\t";
                    if (j == 3) s[i][j] = "\t|Oбщий балл:\t" + " ";
                    if (j == 4) s[i][j] = "\t|Русский:\t" + " ";
                    if (j == 5) s[i][j] = "\t|Математика:\t" + " ";
                    if (j == 6) s[i][j] = "\t|Физика:\t" + " ";

                }
            }


        }
        return s;
    }

    public static String[][] outputToStringArrayTable(List<Student> list, int N) {

        int k = 7;
        String[][] s = new String[N][k];

        for (int i = 0; i < N; i++) {
            if (i < list.size()) {
                Student student = list.get(i);

                for (int j = 0; j < k; j++) {
                    if (j == 0) s[i][j] = "ФИО: " + student.name;
                    if (j == 1) s[i][j] = " " + student.surname;
                    if (j == 2) s[i][j] = " " + student.patronymic;
                    if (j == 3) s[i][j] = "Oбщий балл: " + student.mark;
                    if (j == 4) s[i][j] = "Русский: " + student.markRusLang;
                    if (j == 5) s[i][j] = "Математика: " + student.markMath;
                    if (j == 6) s[i][j] = "Физика: " + student.markPhysics;
                }
            } else {
                for (int j = 0; j < k; j++) {
                    if (j == 0) s[i][j] = "ФИО: " + " ";
                    if (j == 1) s[i][j] = " " + " ";
                    if (j == 2) s[i][j] = " " + " ";
                    if (j == 3) s[i][j] = "Oбщий балл: " + " ";
                    if (j == 4) s[i][j] = "Русский: " + " ";
                    if (j == 5) s[i][j] = "Математика: " + " ";
                    if (j == 6) s[i][j] = "Физика: " + " ";

                }

            }
        }
        return s;
    }

    public static void winMain() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new WindowFrame();
            }
        });
    }


    public static void main(String[] args) throws RuntimeException {

        InputArgs params = parseArgs(args);

        if (params.help) {
            PrintStream out = params.error ?  System.err : System.out;
            out.println("Usage:");
            out.println("  <cmd> args <abitur-count> <input-file> <output-file>");
            out.println("  <cmd> --help");
            out.println("  <cmd> --window  // show window");
            System.exit(params.error ? 1 : 0);
        }

        if (params.window) {
            winMain();
        } else {

                String[][] arr = DataFile.readFile(params.inputFile);
                int studentCountWin = Integer.parseInt(params.abiturWin);

                List<Student> list = compound(arr);
                List<Student> answer = Logic.students(list);


                String[][] outArr = outputToStringArrayConsole(answer, studentCountWin);
                DataFile.writeToFile(outArr, params.outputFile);

        }



    }
}

