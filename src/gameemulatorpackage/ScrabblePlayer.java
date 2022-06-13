
package gameemulatorpackage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ScrabblePlayer extends Player {
    private int skipTurn;
    private int turns;
    private  ArrayList<String> rack = new ArrayList<String>();
    private String date;
    public ScrabblePlayer(String name){
        super(name);
        skipTurn = 0;
        turns = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
    }

    public void setDate(String date){this.date = date;}

    public void incSkipTurn() {
        skipTurn++;
    }

    public void incTurns() {
        turns++;
    }

    public String getName() {
        return super.getName();
    }

    public int getScore() {
        return super.getScore();
    }

    public int getSkipTurn() {
        return skipTurn;
    }

    public int getTurns() {
        return turns;
    }

    public ArrayList<String> getCloneRack(){
        ArrayList<String> cloneRack = new ArrayList<String>();
        cloneRack = (ArrayList<String>) rack.clone();
        return cloneRack;
    }
    public ArrayList<String> getRack(){
        return rack;
    }

    public String getDate(){
        return date;
    }

    public void addToRack(String letter){
        rack.add(letter);
    }

}
