/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.pkg2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 *
 * @author aspar
 */

public class Project2{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Schedulers algorithm = new Schedulers();
        String loc = "E:\\";
        
        try{
        PrintStream o = new PrintStream(new File(loc + "17437589.txt"));
        System.setOut(o);
        System.out.println(algorithm.FCFS());
        System.out.println(algorithm.SJF());
        System.out.println(algorithm.SRTF());
        System.out.println(algorithm.MLF());
        }   
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
}
