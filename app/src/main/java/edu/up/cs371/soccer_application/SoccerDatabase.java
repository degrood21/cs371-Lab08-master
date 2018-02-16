package edu.up.cs371.soccer_application;

import android.support.annotation.NonNull;
import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */

    Hashtable< String, SoccerPlayer> Players = new Hashtable< String, SoccerPlayer>();
    SoccerPlayer sP;

    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
        sP = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);

        if(!Players.containsKey(firstName+"##"+lastName))
        {
            Players.put(firstName+"##"+lastName,sP);
            return true;
        }
        return false;
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {

        if(Players.containsKey(firstName+"##"+lastName))
        {
            Players.remove(firstName+"##"+lastName);
            return true;
        }

        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {

        if(Players.containsKey(firstName+"##"+lastName))
        {
            return Players.get(firstName+"##"+lastName);

        }
        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {

        if(Players.containsKey(firstName+"##"+lastName))
        {
            sP.bumpGoals();
            return true;
        }

        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        if(Players.containsKey(firstName+"##"+lastName))
        {
            sP.bumpAssists();
            return true;
        }
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        if(Players.containsKey(firstName+"##"+lastName))
        {
            sP.bumpShots();
            return true;
        }
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        if(Players.containsKey(firstName+"##"+lastName))
        {
            sP.bumpSaves();
            return true;
        }
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        if(Players.containsKey(firstName+"##"+lastName))
        {
            sP.bumpFouls();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if(Players.containsKey(firstName+"##"+lastName))
        {
            sP.bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if(Players.containsKey(firstName+"##"+lastName))
        {
            sP.bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {

        int p = 0;
        Set<String> keys = Players.keySet();
        for(String key : keys)
        {
            if(teamName == (null))
            {
                p++;
            }
            else if(Players.get(key).getTeamName().equals(teamName)){

                p++;
            }
        }
        return p;
	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName)
    {
       // if(teamName == null)
        int count = 0;
        Set<String> keys = Players.keySet();
        for(String key : keys)
        {
            if(teamName == (null))
            {
                if(count == idx){
                    return Players.get(key);
                }
                else{
                    count++;
                }
            }
            else if(Players.get(key).getTeamName().equals(teamName)){
                if(count == idx){

                    return Players.get(key);

                }
                else{

                    count++;
                }
            }
        }
        return null;
    }


    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        Writer w = new Writer(file) {
            @Override
            public void close() throws IOException {

            }

            @Override
            public void flush() throws IOException {

            }

            @Override
            public void write(@NonNull char[] chars, int i, int i1) throws IOException {

            }
        };


        PrintWriter pW = new PrintWriter(w);

        Set<String> keys = Players.keySet();
        for (String key : keys) {


            pW.println(logString(Players.get(key).getFirstName()));

            return true;
        }

        return false;
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
