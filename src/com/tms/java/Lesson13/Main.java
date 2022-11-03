package com.tms.java.Lesson13;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> filePaths = new ArrayList<>();
        Set<String> uniqNumbers = new HashSet<>();
        Map<String,String> documentLog = new HashMap<>();
        String path;

        System.out.println("Enter file paths with document numbers: ");
        while(!(path = scanner.next()).equals("0")){
            filePaths.add(path);
            try (BufferedReader reader = new BufferedReader(
                         new FileReader(path));
            ) {
                String documentNumber;
                while ((documentNumber = reader.readLine()) != null) {
                    uniqNumbers.add(documentNumber);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for(String documentNumber : uniqNumbers){
            if(documentNumber.length() == 15 && (documentNumber.startsWith("contract") || documentNumber.startsWith("docnum"))) {
                        documentLog.put(documentNumber," - Document is valid\n");
                    } else if(documentNumber.length() != 15 && !documentNumber.startsWith("contract") && !documentNumber.startsWith("docnum")) {
                        documentLog.put(documentNumber,
                                " - Incorrect size of document name and incorrect start of the document name\n");
                    }
                    else if(documentNumber.length() != 15) {
                        documentLog.put(documentNumber, " - Incorrect size of document name\n");
                    }
                    else {
                        documentLog.put(documentNumber, " - Incorrect start of the document name\n");
                    }
        }

        for(Map.Entry<String, String> log : documentLog.entrySet()){
            try (BufferedWriter logWriter = new BufferedWriter(
                    new FileWriter("C:\\Users\\Lenovo\\IdeaProjects\\TMS\\files\\Lesson13\\log.txt",true))) {
                logWriter.write(log.getKey() + log.getValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done!");
    }
}