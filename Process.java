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
public class Process {
    private int starttime;
    private int runtime;
    private int realruntime;
    private int waittime;
    private int turnaround;
    private int priority;
    private int totalquantum;
    private int quantum;
    public Process(){
        starttime = 0;
        realruntime = 0;
        runtime = 0;
        waittime = 0;
        turnaround = 0;
        priority = 5;
        totalquantum = 1;
        quantum = 0;
    }
    public boolean done(){
        if(runtime == realruntime)
            return true;
        else
            return false;
    }
    public int starttime(){
        return starttime;
    }
    public void setstart(int start){
        starttime = start;
    }
    public void decstarttime(int a){
        if(starttime >= a)
            starttime -= a;
        else
            System.out.println("can't decrement down to a negative number");
    }
    public void incstarttime(int a){
        starttime += a;
    }
    public int realruntime(){
        return realruntime;
    }
    public int runtime(){
        return runtime;
    }
    public void setrun(int run){
        runtime = run;
    }
    public void decruntime(int a){
        if(runtime >= a)
            runtime -= a;
        else
            System.out.println("can't decrement down to a negative number");
    }
    public void incruntime(int a){
        realruntime += a;
    }
    public int remainingruntime(){
        int remaining = runtime - realruntime;
        return remaining;
    }
    public int waittime(){
        return waittime;
    }
    public void decwaittime(int a){
        if(waittime >= a)
            waittime -= a;
        else
            System.out.println("can't decrement down to a negative number");
    }
    public void incwaittime(int a){
        waittime += a;
    }
    public int turnaround(){
        turnaround = waittime + runtime;
        return turnaround;
    }
    public int priority(){
        return priority;
    }
    public void incQuantum(int a){
        quantum += a;
        if(quantum == totalquantum){
            priority -= 1;
            quantum = 0;
            totalquantum = totalquantum * 2;
        }
    }
    public int Quantum(){
        return quantum;
    }
    
}
