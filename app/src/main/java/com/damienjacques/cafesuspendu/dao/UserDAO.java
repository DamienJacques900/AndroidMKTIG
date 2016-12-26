package com.damienjacques.cafesuspendu.dao;

import android.util.Log;

import com.damienjacques.cafesuspendu.model.*;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserDAO extends ModifyDBDAO
{

    //***********************COMMENTAIRE****************************
    //Permet de changer des valeurs dans les options du café.
    //**************************************************************
    public void putChangeOptionCoffee(String token, User userCoffee) throws Exception
    {
        String optionJSON = optionToJSON(userCoffee);
        putJsonStringWithURL(token,optionJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/updatePromotionInformations?cafeId="+userCoffee.getId()+"&nbCoffeeRequiredForPromotion="+userCoffee.getNbCoffeeRequiredForPromotion()+"&promotionValue="+userCoffee.getPromotionValue());

    }

    public String optionToJSON(User user) throws Exception
    {
        JSONObject optionCoffee = new JSONObject();

        //***********************COMMENTAIRE****************************
        //Permet de créer un JSON object avec les valeurs que l'on
        //souhaite.
        //**************************************************************
        optionCoffee.accumulate("id",user.getId());
        optionCoffee.accumulate("userName", user.getUserName());
        optionCoffee.accumulate("cafeName", user.getCafeName());
        optionCoffee.accumulate("street", user.getStreet());
        optionCoffee.accumulate("number", user.getNumber());
        optionCoffee.accumulate("nbCoffeeRequiredForPromotion", user.getNbCoffeeRequiredForPromotion());
        optionCoffee.accumulate("promotionValue", user.getPromotionValue());
        optionCoffee.accumulate("bookings", user.getBookings());
        optionCoffee.accumulate("timeTables", user.getTimesTables());
        optionCoffee.accumulate("firstName", user.getFirstName());
        optionCoffee.accumulate("lastName", user.getLastName());
        optionCoffee.accumulate("email", user.getEmail());
        optionCoffee.accumulate("emailConfirmed", user.getEmailConfirmed());
        optionCoffee.accumulate("phoneNumber", user.getPhoneNumber());
        optionCoffee.accumulate("roles", user.getRoles());

        return optionCoffee.toString();
    }

    //***********************COMMENTAIRE****************************
    //Permet de changer le mail pour les utilisateurs
    //**************************************************************
    public void postChangeOptionPersonPhone(String token, User userPerson) throws Exception
    {
        String optionJSON = optionToJSON(userPerson);
        postJsonStringWithURL(token,optionJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/ChangePhoneNumber?userId="+userPerson.getId()+"&phoneNumber="+userPerson.getPhoneNumber());
    }

    //***********************COMMENTAIRE****************************
    //Permet de changer le numéro de téléphone pour les utilisateurs.
    //**************************************************************
    public void postChangeOptionPersonEmail(String token, User userPerson) throws Exception
    {
        String optionJSON = optionToJSON(userPerson);
        postJsonStringWithURL(token,optionJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/ChangeEmail?userId="+userPerson.getId()+"&email="+userPerson.getEmail());
    }

    //***********************COMMENTAIRE****************************
    //Permet d'ajouter une nouvelle inscription pour les cafés.
    //**************************************************************
    public void postNewRegistrationCoffee(User userCoffee,ArrayList<TimeTable> timeTables) throws Exception
    {
        String registrationJSON = registratinCoffeeToJSON(userCoffee,timeTables);
        postJsonStringRegistrationWithURL(registrationJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/create");
    }

    //***********************COMMENTAIRE****************************
    //Permet de créer le fichier JSON pour un nouveau café.
    //**************************************************************
    public String registratinCoffeeToJSON(User userCoffee,ArrayList<TimeTable> timeTables) throws Exception
    {
        JSONObject newCoffee = new JSONObject();

        JSONArray timeTable = new JSONArray();
        JSONArray roles = new JSONArray();
        roles.put(userCoffee.getRoles());

        newCoffee.accumulate("userName",userCoffee.getUserName());
        newCoffee.accumulate("cafeName",userCoffee.getCafeName());
        newCoffee.accumulate("street",userCoffee.getStreet());
        newCoffee.accumulate("number",userCoffee.getNumber());
        newCoffee.accumulate("nbCoffeeRequiredForPromotion",userCoffee.getNbCoffeeRequiredForPromotion());
        newCoffee.accumulate("promotionValue",userCoffee.getPromotionValue());
        newCoffee.accumulate("bookings",JSONObject.NULL);

        //***********************COMMENTAIRE****************************
        //Timetable contient plusieurs objets timeTable pour les cafés,
        //il faut donc en créer sept pour tout les jours de la
        //semaine.
        //**************************************************************
        JSONObject dayOne = new JSONObject();
        dayOne.accumulate("openingHour",timeTables.get(0).getOpeningHour());
        dayOne.accumulate("closingHour",timeTables.get(0).getClosingHour());
        dayOne.accumulate("dayOfWeek",timeTables.get(0).getTimeTableID());
        timeTable.put(dayOne);

        JSONObject dayTwo = new JSONObject();
        dayTwo.accumulate("openingHour",timeTables.get(1).getOpeningHour());
        dayTwo.accumulate("closingHour",timeTables.get(1).getClosingHour());
        dayTwo.accumulate("dayOfWeek",timeTables.get(1).getTimeTableID());
        timeTable.put(dayTwo);

        JSONObject dayThree = new JSONObject();
        dayThree.accumulate("openingHour",timeTables.get(2).getOpeningHour());
        dayThree.accumulate("closingHour",timeTables.get(2).getClosingHour());
        dayThree.accumulate("dayOfWeek",timeTables.get(2).getTimeTableID());
        timeTable.put(dayThree);

        JSONObject dayFour = new JSONObject();
        dayFour.accumulate("openingHour",timeTables.get(3).getOpeningHour());
        dayFour.accumulate("closingHour",timeTables.get(3).getClosingHour());
        dayFour.accumulate("dayOfWeek",timeTables.get(3).getTimeTableID());
        timeTable.put(dayFour);

        JSONObject dayFive = new JSONObject();
        dayFive.accumulate("openingHour",timeTables.get(4).getOpeningHour());
        dayFive.accumulate("closingHour",timeTables.get(4).getClosingHour());
        dayFive.accumulate("dayOfWeek",timeTables.get(4).getTimeTableID());
        timeTable.put(dayFive);

        JSONObject daySix = new JSONObject();
        daySix.accumulate("openingHour",timeTables.get(5).getOpeningHour());
        daySix.accumulate("closingHour",timeTables.get(5).getClosingHour());
        daySix.accumulate("dayOfWeek",timeTables.get(5).getTimeTableID());
        timeTable.put(daySix);

        JSONObject daySeven = new JSONObject();
        daySeven.accumulate("openingHour",timeTables.get(6).getOpeningHour());
        daySeven.accumulate("closingHour",timeTables.get(6).getClosingHour());
        daySeven.accumulate("dayOfWeek",timeTables.get(6).getTimeTableID());
        timeTable.put(daySeven);

        newCoffee.accumulate("timeTables",timeTable);


        newCoffee.accumulate("firstName",JSONObject.NULL);
        newCoffee.accumulate("lastName",JSONObject.NULL);
        newCoffee.accumulate("email",userCoffee.getEmail());
        newCoffee.accumulate("phoneNumber",JSONObject.NULL);
        newCoffee.accumulate("roles",roles);
        newCoffee.accumulate("password",userCoffee.getPassword());
        newCoffee.accumulate("confirmPassword",userCoffee.getConfirmPassword());

        return newCoffee.toString();
    }

    //***********************COMMENTAIRE****************************
    //Permet de créer un nouvel utilisateur.
    //**************************************************************
    public void postNewRegistrationPerson(User userPerson) throws Exception
    {
        String registrationJSON = registrationPersonToJSON(userPerson);
        System.out.println(registrationJSON);
        postJsonStringRegistrationWithURL(registrationJSON, "http://cafesuspenduappweb.azurewebsites.net/api/Accounts/create");
    }

    //***********************COMMENTAIRE****************************
    //Permet de créer un fichier JSON pour un nouvel utilisateur.
    //**************************************************************
    public String registrationPersonToJSON(User userPerson) throws Exception
    {
        JSONObject newPerson = new JSONObject();
        JSONArray timeTableArray = new JSONArray();
        JSONArray roles = new JSONArray();
        roles.put(userPerson.getRoles());

        newPerson.accumulate("userName",userPerson.getUserName());
        newPerson.accumulate("cafeName",JSONObject.NULL);
        newPerson.accumulate("street",JSONObject.NULL);
        newPerson.accumulate("number",JSONObject.NULL);
        newPerson.accumulate("nbCoffeeRequiredForPromotion",JSONObject.NULL);
        newPerson.accumulate("promotionValue",0.1);
        newPerson.accumulate("bookings",JSONObject.NULL);
        newPerson.accumulate("password",userPerson.getPassword());
        newPerson.accumulate("confirmPassword",userPerson.getConfirmPassword());
        newPerson.accumulate("timeTables",timeTableArray);
        newPerson.accumulate("firstName",userPerson.getFirstName());
        newPerson.accumulate("lastName",userPerson.getLastName());
        newPerson.accumulate("email",userPerson.getEmail());
        newPerson.accumulate("phoneNumber",userPerson.getPhoneNumber());
        newPerson.accumulate("roles",roles);

        return newPerson.toString();
    }

    //***********************COMMENTAIRE****************************
    //Permet de gérer la connexion et d'autoriser ceux qui ont
    //accès.
    //**************************************************************
    public String getUserWithUserNameAndPw(String userName, String password) throws Exception
    {
        //***********************COMMENTAIRE****************************
        //Permet d'établir la connexion pour récuper le token
        //**************************************************************
        URL url = new URL("http://cafesuspenduappweb.azurewebsites.net/token");
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        //***********************COMMENTAIRE****************************
        //Permet de dire qu'on va faire une requête POST avec la propriété
        //qui suit
        //**************************************************************
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-type", "x-www-form-urlencoded");
        urlConnection.setDoInput(true);


        //***********************COMMENTAIRE****************************
        //Permet d'écrire dans la requête(comme dans fiddler)
        //**************************************************************
        OutputStream out = urlConnection.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(out);
        urlConnection.connect();

        //***********************COMMENTAIRE****************************
        //C'est la requête pour récupérer le token
        //**************************************************************
        writer.write("username="+userName+"&password="+password+"&grant_type=password");
        writer.flush();
        BufferedReader br;
        if (200 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() <= 299)
        {
            br = new BufferedReader(new InputStreamReader((urlConnection.getInputStream())));
        } else
        {
            br = new BufferedReader(new InputStreamReader((urlConnection.getErrorStream())));
        }

        StringBuilder sb = new StringBuilder();
        String stringJSON = "",line;
        while((line = br.readLine()) != null)
        {
            sb.append(line);
        }
        br.close();
        stringJSON = sb.toString();

        writer.close();
        out.close();

        urlConnection.disconnect();
        return jsonToToken(stringJSON);
    }

    //***********************COMMENTAIRE****************************
    //Ici pas besoin de token pour récupérer les valeurs car c'est
    //pour la connexion et donc pas encor le token pour accéder à
    //ses données
    //**************************************************************
    public ArrayList<User> getAllUsers() throws Exception
    {
        //***********************COMMENTAIRE****************************
        //Permet d'établir la connexion
        //**************************************************************
        URL url = new URL("http://cafesuspenduappweb.azurewebsites.net/api/accounts/Users");
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
        System.out.println(stringJSON.toString());
        return jsonToUsers(stringJSON);
    }

    //***********************COMMENTAIRE****************************
    //Permet de convertir le format JSON de l'API en arrayList
    //**************************************************************
    private ArrayList<User> jsonToUsers(String stringJSON) throws Exception
    {
        ArrayList<User> users = new ArrayList<>();
        User user;
        JSONArray jsonArray = new JSONArray(stringJSON);
        //***********************COMMENTAIRE****************************
        //Tant que toutes les données du JSON ne sont pas parcourues
        //**************************************************************
        for(int i = 0; i < jsonArray.length();i++)
        {
            JSONObject jsonUser = jsonArray.getJSONObject(i);

            JSONArray roles = jsonUser.getJSONArray("roles");
            if(roles.getString(0).equals("userperson"))
            {
                user = new User(jsonUser.getString("userName"), roles.getString(0), jsonUser.getString("email"), jsonUser.getString("phoneNumber"), 0, (float)jsonUser.getDouble("promotionValue"),jsonUser.getString("id"));
            }
            else
            {
                user = new User(jsonUser.getString("userName"),roles.getString(0),jsonUser.getString("email"),jsonUser.getString("phoneNumber"),jsonUser.getInt("nbCoffeeRequiredForPromotion"),(float)jsonUser.getDouble("promotionValue"),jsonUser.getString("id"));
            }
            users.add(user);
        }
        return users;
    }

    //***********************COMMENTAIRE****************************
    //Permet de récupérer le token
    //**************************************************************
    private String jsonToToken(String stringJSON) throws Exception
    {
        String token = new JSONObject(stringJSON).getString("access_token");
        return token;
    }

    //***********************COMMENTAIRE****************************
    //Est utile pour les options et donc la besoin du token pour
    //accéder aux données
    //**************************************************************
    public ArrayList<User> getAllUsersOption(String token) throws Exception
    {
        String stringJSON = getJsonStringWithURL(token,"http://cafesuspenduappweb.azurewebsites.net/api/accounts/Users");
        return jsonToUsers(stringJSON);
    }

}
