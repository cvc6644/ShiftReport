/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.shiftreportjava;

/**
 *
 * @author snowyowl
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ShiftReport {

    
    private HashMap<String, String> Labs;
    private ArrayList<ArrayList<String>> footprints;
    private ArrayList<ArrayList<String>> misc;
    private String username;
    private final String Head = "<!DOCTYPE html PUBLIC \" -//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
            +"<html xmlns=\"http://www.w3.org/1999/xhtml\">"
            +"<head>"
            + "<style>"
            + "table, th, td {"
            + " border: 1px solid black;"
            + " border-collapse: collapse;"
            + "}"
            + "th, td{"
            + " padding: 5px;"
            + "}"
            + "th{"
            + " text-align: left;"
            + "}"
            + "</style>"
            +"<meta http-equiv=\"Content-Type\" content=\"text/html ;charset  = UTF - 8\" />"
            +"<meta name=\"viewport\" content=\"width  = device - width, initial-scale  = 1.0\"/>"
            +"</head>"
            +"<body>";
    private final String foot = "</body>"
            + "</html>";

    public ShiftReport(String _username, HashMap<String, String> _labs) {
        username = _username;
        Labs = _labs;
        footprints = new ArrayList<>();
        
        misc = new ArrayList<>();
    }

    public void describeLab(String name, String description) {
        Labs.put(name, description);
    }
    public void addFootprintsTicket(int TicketNum, String _status, String _details){
        footprints.add(new ArrayList<>(Arrays.asList(Integer.toString(TicketNum),_status,_details)));
    }
    public void addMisc(String event, String _details){
        misc.add(new ArrayList<>(Arrays.asList(event,_details)));
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String buildEmail() {
        return Head
            +buildFirstLine()
            +buildLabs()
            +buildFootprints()
            +buildMisc()
            +foot;
    }

    public void sendEmail(String _email) {
        //Mail.send(email);
    }

    public void buildAndSend() {
        sendEmail(buildEmail());
    }

    public String buildFirstLine() {
        return "<h1>" + getUsername() + "'s Shift Report</h1></br>";
    }

    

    

    private String buildLabs() {
        String section = "</br><h2>Lab Rounds:</h2>< /br></br>"
                +"<table style=\"width:100%;\">"
                + "<tr>"
                + "<th>Lab</th>"
                + "<th>Status</th>"
                + "</tr>";
        for(String K : Labs.keySet()){
            section +="<tr>"
                    + "<td>"
                    + K
                    + "</td>"
                    + "<td>"
                    + Labs.get(K)
                    +"</td></tr>";
        }
            section += "</table>";
        return section;
    }

    private String buildFootprints() {
        String section = "</br><h2>Footprints Tickets:</h2>< /br></br>"
                +"<table style=\"width:100%;\">"
                + "<tr>"
                + "<th>Ticket#</th>"
                + "<th>Status</th>"
                + "<th>Details</th>"
                + "</tr>";
        for(ArrayList<String> i : footprints){
            section+="<tr>";
            for(String s : i){
                section+="<td>"+s+"</td>";
            }
            section+="</tr>";
        }
        section += "</table>";
        return section;
    }

    private String buildMisc() {
        String section = "</br><h2>Misc:</h2>< /br></br>"
                +"<table style=\"width:100%;\">"
                + "<tr>"
                + "<th>Event/Task</th>"
                + "<th>Details</th>"
                + "</tr>";
        for(ArrayList<String> i : misc){
            section+="<tr>";
            for(String s : i){
                section+="<td>"+s+"</td>";
            }
            section+="</tr>";
        }
        section += "</table>";
        return section;
    }
}
