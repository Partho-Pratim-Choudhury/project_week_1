package org.example;

import java.io.*;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;

public class FileReaderClass implements Callable<Integer[]> {
    File file;
    FileReader fr;
    BufferedReader br = null;
    FileReaderClass(File file){
        try {
            this.file = file;
            this.fr = new FileReader(file.getAbsolutePath());
            this.br = new BufferedReader(fr);
        }catch(FileNotFoundException e){
            System.out.println("File Not Found !");
            e.printStackTrace();
        }
    }

    public Integer[] call() {
        System.out.println( file.getName() + " is processed by : " + Thread.currentThread().getName());
        String line;
        int wordCount = 0;
        int lineCount = 0;
        try {
            while ((line = br.readLine()) != null) {
                lineCount++;
                StringTokenizer st = new StringTokenizer(line);
                while(st.hasMoreTokens()){
                    st.nextToken();
                    wordCount++;
                }
            }
            System.out.println("File : " + file.getName() + " , Lines : " + lineCount + " , Words : " + wordCount);
        }catch(IOException e){
            System.out.println("Line could not be read from the provided file.");
            e.printStackTrace();
        }finally {
            try {
                fr.close();
                br.close();
            }catch (IOException e){
                System.out.println("Error in closing the file.");
                e.printStackTrace();
            }
            System.out.println(file.getName() + " closed successfully.\n");
        }
        return new Integer[] {wordCount, lineCount};
    }
}
