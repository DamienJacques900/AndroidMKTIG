package com.damienjacques.cafesuspendu.dao;

import com.damienjacques.cafesuspendu.model.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class TimeTableDAO
{
    public ArrayList<TimeTable> getAllTimeTables() throws Exception
    {
        URL url = new URL("http://cafesuspenduappweb.azurewebsites.net/api/TimeTables");
        URLConnection connection = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String stringJSON = "",line;
        while((line=br.readLine())!=null)
        {
            sb.append(line);
        }
        br.close();
        stringJSON = sb.toString();
        return jsonToTimeTables(stringJSON);
    }

    private ArrayList<TimeTable> jsonToTimeTables(String stringJSON) throws Exception
    {
        ArrayList<TimeTable> timeTables = new ArrayList<>();
        TimeTable timeTable;
        JSONArray jsonArray = new JSONArray(stringJSON);
        System.out.println("TimeTable : "+jsonArray.toString());
        for(int i = 0; i < jsonArray.length();i++)
        {
            JSONObject jsonTimeTable = jsonArray.getJSONObject(i);
            timeTable = new TimeTable(jsonTimeTable.getInt("timeTableID"));
            timeTables.add(timeTable);
        }
        return timeTables;
    }
}
