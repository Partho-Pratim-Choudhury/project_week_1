package org.example;

import java.io.File;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        String folderPath = "/home/parthochoudhury/Documents/PW1FileFolder";
        File dir = new File(folderPath);
        File[] files = dir.listFiles();

        if(files != null){
            int wordCount = 0;
            int lineCount = 0;
            ExecutorService service = Executors.newFixedThreadPool(3);
            for(File file : files){
                FileReaderClass obj = new FileReaderClass(file);
                Future<Integer[]> future = service.submit(obj);
                try {
                    Integer[] res = future.get();
                    wordCount += res[0];
                    lineCount += res[1];
                } catch (InterruptedException | ExecutionException e) {
                    System.out.println("Error in getting future value");
                    e.printStackTrace();
                }
            }
            System.out.println("Total Lines : " + lineCount);
            System.out.println("Total Words : " + wordCount);
            service.shutdown();
        }
    }
}