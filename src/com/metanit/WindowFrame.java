package com.metanit;

import util.JTableUtils;
import util.SwingUtils;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;



public class WindowFrame {


    private JPanel panelMain;
    private JTable tableInput;
    private JTable tableOutput;
    private JButton buttonSaveOutputIntoFile;
    private JButton buttonSaveInputInfoFile;
    private JButton buttonExecute;
    private JButton buttonLoadInputFromFile;
    private JTextField TextField;


    private JFileChooser fileChooserOpen;
    private JFileChooser fileChooserSave;
    private JMenuBar menuBarMain;
    private JMenu menuLookAndFeel;

    public int studentCount;

    public WindowFrame() {

        JFrame jfrm = new JFrame();

        jfrm.setTitle("Task_10");
        jfrm.setContentPane(panelMain);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfrm.pack();

        JTableUtils.initJTableForArray(tableInput, 200, true, true, true, true);
        JTableUtils.initJTableForArray(tableOutput, 200, true, true, true, true);
        tableInput.setRowHeight(25);
        tableOutput.setRowHeight(25);

        fileChooserOpen = new JFileChooser();
        fileChooserSave = new JFileChooser();
        fileChooserOpen.setCurrentDirectory(new File("."));
        fileChooserSave.setCurrentDirectory(new File("."));
        FileFilter filter = new FileNameExtensionFilter("Text files", "txt");
        fileChooserOpen.addChoosableFileFilter(filter);
        fileChooserSave.addChoosableFileFilter(filter);

        fileChooserSave.setAcceptAllFileFilterUsed(false);
        fileChooserSave.setDialogType(JFileChooser.SAVE_DIALOG);
        fileChooserSave.setApproveButtonText("Save");

        menuBarMain = new JMenuBar();
        jfrm.setJMenuBar(menuBarMain);

        menuLookAndFeel = new JMenu();
        menuLookAndFeel.setText("Вид");
        menuBarMain.add(menuLookAndFeel);
        SwingUtils.initLookAndFeelMenu(menuLookAndFeel);

        jfrm.pack();
        jfrm.setVisible(true);

        buttonLoadInputFromFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (fileChooserOpen.showOpenDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String[][] arr = DataFile.readFile(fileChooserOpen.getSelectedFile().getPath());
                        JTableUtils.writeArrayToJTable(tableInput, arr);
                    }
                } catch (Exception e) {
                    SwingUtils.showErrorMessageBox(e);
                }
            }
        });

        buttonSaveInputInfoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                            String[][] matrix = JTableUtils.readStringMatrixFromJTable(tableInput);
                            String file = fileChooserSave.getSelectedFile().getPath();
                            if (!file.toLowerCase().endsWith(".txt")) {
                                file += ".txt";
                            }
                        DataFile.writeToFile(matrix,file);
                    }

                } catch (Exception except) {
                    SwingUtils.showErrorMessageBox(except);
                }
            }
        });

        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String[][] dataString = JTableUtils.readStringMatrixFromJTable(tableInput);
                    List<Student> list = Main.compound(dataString);
                    List<Student> answer = Logic.students(list);
                    JTableUtils.writeArrayToJTable(tableOutput, Main.outputToStringArrayTable(answer, studentCount));

                } catch (Exception except) {
                    SwingUtils.showErrorMessageBox(except);
                }
            }
        });

        buttonSaveOutputIntoFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (fileChooserSave.showSaveDialog(panelMain) == JFileChooser.APPROVE_OPTION) {
                        String[][] matrix = JTableUtils.readStringMatrixFromJTable(tableOutput);
                        String file = fileChooserSave.getSelectedFile().getPath();
                        if (!file.toLowerCase().endsWith(".txt")) {
                            file += ".txt";
                        }
                      DataFile.writeToFile(matrix, file);
                    }
                } catch (Exception except) {
                    SwingUtils.showErrorMessageBox(except);
                }
            }
        });


        TextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text;
                text = TextField.getText();
                studentCount = Integer.parseInt(text);
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
