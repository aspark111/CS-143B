package project.pkg2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aspar
 */

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.text.DecimalFormat;

public class Schedulers extends Process{
    String loc = "E:\\";
    private final ArrayList<Process> processes = new ArrayList<>();
    
    public Schedulers(){
       
    }
    public String FCFS(){
        loadInputs();
        int quantum = 0;
        int done = 0; 
        int next;
        
        while(done < processes.size()){
            next = -1;
            for(int i = 0; i < processes.size(); i++){
                if(processes.get(i).done() == false && processes.get(i).starttime() <= quantum){
                    next = i;
                    break;
                }
            }
            if(next == -1)
                quantum += 1;
            else if(processes.get(next).starttime() <= quantum && processes.get(next).done() == false){
                while(processes.get(next).starttime() <= quantum && processes.get(next).done() == false){
                    processes.get(next).incruntime(1);
                    quantum += 1;
                    for(int j = 0; j < processes.size(); j++){
                        if(next != j && processes.get(j).done() == false && processes.get(j).starttime() < quantum){
                            processes.get(j).incwaittime(1);
                        }
                    }
                    if(processes.get(next).done() == true)
                        done += 1;
                }
            }
        }
        return outputResult();
    }
    public String SJF(){
       
        loadInputs();
        int quantum = 0;
        int done = 0;
        int shortest;
        int largest = 0;
        
        for(int i = 0; i < processes.size(); i++){
            if(processes.get(i).runtime() > processes.get(largest).runtime())
                largest = i;
        }
        while(done < processes.size()){
            shortest = largest;
            for(int j = 0; j < processes.size(); j++){
                if(processes.get(j).starttime() <= quantum && processes.get(j).done() == false && processes.get(j).runtime() < processes.get(shortest).runtime())
                    shortest = j;
            }
            if(processes.get(shortest).starttime() > quantum)
                quantum += 1;
            else if (processes.get(shortest).done() == false){
                while(processes.get(shortest).done() == false){
                    quantum += 1;
                    processes.get(shortest).incruntime(1);
                    for(int i = 0; i < processes.size(); i++){
                        if(i != shortest && processes.get(i).done() == false && processes.get(i).starttime() < quantum)
                            processes.get(i).incwaittime(1);
                    }
                }
            }
            if(processes.get(shortest).done() == true)
                done += 1;
        }

        return outputResult();
    }
    public String SRTF(){
        loadInputs();
        int quantum = 0;
        int done = 0;
        int largest = 0;
        int srt;
        
        while(done < processes.size()){
            for(int i = 0; i < processes.size(); i++){
                if(processes.get(i).remainingruntime() > processes.get(largest).remainingruntime())
                    largest = i;
            }
            quantum += 1;
            srt = largest;
            for(int i = 0; i < processes.size(); i++){
                if(processes.get(i).done() == false &&
                        processes.get(i).starttime() < quantum && 
                        processes.get(i).remainingruntime() < processes.get(srt).remainingruntime())
                    srt = i;
            }
            
            if(processes.get(srt).done() == false && processes.get(srt).starttime() < quantum){
                processes.get(srt).incruntime(1);
                for(int i = 0; i < processes.size(); i++){
                    if(i != srt && processes.get(i).done() == false && processes.get(i).starttime() < quantum)
                        processes.get(i).incwaittime(1);
                }
            }
            if(processes.get(srt).done() == true)
                done += 1;
        }
        return outputResult();
    }
    public String MLF(){
        loadInputs();
        int done = 0;
        int quantum = 0;
        int next;
        while(done < processes.size()){
            while(true){
                next = -1;
                for(int i = 0; i < processes.size(); i++){
                    if(processes.get(i).priority() == 5 && processes.get(i).done() == false && processes.get(i).starttime() <= quantum){
                        next = i;
                        break;
                    }
                }
                if(next != -1)
                    break;
                for(int i = 0; i < processes.size(); i++){
                    if(processes.get(i).priority() == 4 && processes.get(i).done() == false && processes.get(i).starttime() <= quantum){
                        next = i;
                        break;
                    }
                }
                if(next != -1)
                    break;
                for(int i = 0; i < processes.size(); i++){
                    if(processes.get(i).priority() == 3 && processes.get(i).done() == false && processes.get(i).starttime() <= quantum){
                        next = i;
                        break;
                    }
                }
                if(next != -1)
                    break;
                for(int i = 0; i < processes.size(); i++){
                    if(processes.get(i).priority() == 2 && processes.get(i).done() == false && processes.get(i).starttime() <= quantum){
                        next = i;
                        break;
                    }
                }
                if(next != -1)
                    break;
                for(int i = 0; i < processes.size(); i++){
                    if(processes.get(i).priority() == 1 && processes.get(i).done() == false && processes.get(i).starttime() <= quantum){
                        next = i;
                        break;
                    }
                }
                if(next != -1)
                    break;
                quantum += 1;
            }
            
            processes.get(next).incruntime(1);
            processes.get(next).incQuantum(1);
            quantum += 1;
            for(int i = 0; i < processes.size(); i++){
                if(i != next && processes.get(i).done() == false && processes.get(i).starttime() < quantum)
                    processes.get(i).incwaittime(1);
            }
            if(processes.get(next).done() == true)
                done += 1;
            
        }
        return outputResult();
    }
    public void loadInputs(){
        if(!processes.isEmpty()){
            processes.clear();
        }
        try{
        File f = new File(loc + "input.txt");
        Scanner sc = new Scanner(f);
        int index = 0;
        int state1 = 1;
        int state2 = 0;
        int start;
        int run;
        
        while(sc.hasNextInt() == true){
            if(state1 == 1){
                start = sc.nextInt();
                processes.add(new Process());
                processes.get(index).setstart(start);
                state1 = 0; state2 = 1;
            }
            else if(state2 == 1){
                run = sc.nextInt();
                processes.get(index).setrun(run);
                state1 = 1; state2 = 0;
                index += 1;
            }
        }
        }
        catch(FileNotFoundException e){
        }
    }
    
    //prints out the average of all the turnarounds and then each individual processes' turnaround
    public String outputResult(){
        //returns the string of avgturnaround and total run times
        StringBuilder result = new StringBuilder();
        DecimalFormat two = new DecimalFormat(".00");
        double avgturnaround = 0;
        for(int i = 0; i < processes.size(); i++){
            avgturnaround += processes.get(i).turnaround();
        }
        avgturnaround = (avgturnaround/processes.size());
        result.append(two.format(avgturnaround));
        result.append(" ");
        for(int i = 0; i < processes.size(); i++){
            String a = processes.get(i).turnaround() + " ";
            result.append(a);
        }
        return result.toString();
    }

}
