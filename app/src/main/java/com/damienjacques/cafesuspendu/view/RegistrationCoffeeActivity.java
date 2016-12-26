package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.icu.util.DateInterval;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.UserDAO;
import com.damienjacques.cafesuspendu.model.TimeTable;
import com.damienjacques.cafesuspendu.model.User;

import java.sql.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RegistrationCoffeeActivity extends AppCompatActivity
{
    private Button clickRegistration;
    private TextView userNameTextView;
    private TextView passwordTextView;
    private TextView confirmationPasswordTextView;
    private TextView streetTextView;
    private TextView numberTextView;
    private TextView nbCoffeePromotionTextView;
    private TextView promotionValueTextView;
    private TextView coffeNameTextView;

    private TextView mondayBeginHourTextView;
    private TextView mondayEndHourTextView;

    private TextView thusdayBeginHourTextView;
    private TextView thusdayEndHourTextView;

    private TextView wednsedayBeginHourTextView;
    private TextView wednsedayEndHourTextView;

    private TextView thursdayBeginHourTextView;
    private TextView thursdayEndHourTextView;

    private TextView fridayBeginHourTextView;
    private TextView fridayEndHourTextView;

    private TextView saturdayBeginHourTextView;
    private TextView saturdayEndHourTextView;

    private TextView sundayBeginHourTextView;
    private TextView sundayEndHourTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationcoffee);
        createLayout();
    }

    //***********************COMMENTAIRE****************************
    //Permet de gérer le changement d'orientation
    //**************************************************************
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE)
        {
            setContentView(R.layout.activity_registrationcoffee);
            createLayout();
        }
        else
        {
            setContentView(R.layout.activity_registrationcoffee);
            createLayout();
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    private void createLayout()
    {
        clickRegistration = (Button)findViewById(R.id.buttonRegistrationCoffee);

        userNameTextView = (TextView)findViewById(R.id.userNameCoffeeEdit);
        passwordTextView = (TextView)findViewById(R.id.passwordCoffeeEdit);
        confirmationPasswordTextView = (TextView)findViewById(R.id.confirmationPasswordCoffeeEdit);
        streetTextView = (TextView)findViewById(R.id.streetCoffeeEdit);
        numberTextView = (TextView)findViewById(R.id.numberCoffeeEdit);
        nbCoffeePromotionTextView= (TextView)findViewById(R.id.promotionCoffeeEdit);
        promotionValueTextView = (TextView)findViewById(R.id.promotionValueEdit);
        coffeNameTextView = (TextView) findViewById(R.id.coffeeNameEdit);

        mondayBeginHourTextView = (TextView) findViewById(R.id.mondayBeginEdit);
        mondayEndHourTextView = (TextView) findViewById(R.id.mondayEndEdit);

        thusdayBeginHourTextView = (TextView) findViewById(R.id.tuesdayBeginEdit);
        thusdayEndHourTextView = (TextView) findViewById(R.id.thusdayEndEdit);

        wednsedayBeginHourTextView = (TextView) findViewById(R.id.wednesayBeginEdit);
        wednsedayEndHourTextView = (TextView) findViewById(R.id.wednsedayEndEdit);

        thursdayBeginHourTextView = (TextView) findViewById(R.id.thursdayBeginEdit);
        thursdayEndHourTextView = (TextView) findViewById(R.id.thursdayEndEdit);

        fridayBeginHourTextView = (TextView) findViewById(R.id.fridayBeginEdit);
        fridayEndHourTextView = (TextView) findViewById(R.id.fridayEndEdit);

        saturdayBeginHourTextView = (TextView) findViewById(R.id.saturdayBeginEdit);
        saturdayEndHourTextView = (TextView) findViewById(R.id.saturdayEndEdit);

        sundayBeginHourTextView = (TextView) findViewById(R.id.sundayBeginEdit);
        sundayEndHourTextView = (TextView) findViewById(R.id.sundayEndEdit);


        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                new LoadNewUser().execute();
            }
        });
    }

    private class LoadNewUser extends AsyncTask<String, Void, ArrayList<User>>
    {
        Exception exception;

        String userName = userNameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String confirmationPassword = confirmationPasswordTextView.getText().toString();
        String street = streetTextView.getText().toString();
        String number = numberTextView.getText().toString();
        String promotionAfter = nbCoffeePromotionTextView.getText().toString();
        String promoValue = promotionValueTextView.getText().toString();
        String coffeeName = coffeNameTextView.getText().toString();
        String userCoffee = "userCoffee";

        String mondayBeginHour = mondayBeginHourTextView.getText().toString();
        String mondayEndHour = mondayEndHourTextView.getText().toString();

        String thusdayBeginHour = thusdayBeginHourTextView.getText().toString();
        String thusdayEndHour = thusdayEndHourTextView.getText().toString();

        String wednsedayBeginHour = wednsedayBeginHourTextView.getText().toString();
        String wednsedayEndHour = wednsedayEndHourTextView.getText().toString();

        String thursdayBeginHour = thursdayBeginHourTextView.getText().toString();
        String thursdayEndHour = thursdayEndHourTextView.getText().toString();

        String fridayBeginHour = fridayBeginHourTextView.getText().toString();
        String fridayEndHour = fridayEndHourTextView.getText().toString();

        String saturdayBeginHour = saturdayBeginHourTextView.getText().toString();
        String saturdayEndHour = saturdayEndHourTextView.getText().toString();

        String sundayBeginHour = sundayBeginHourTextView.getText().toString();
        String sundayEndHour = sundayEndHourTextView.getText().toString();


        int intPromotionAfter = Integer.parseInt(promotionAfter);
        Float doublePromoValue = Float.parseFloat(promoValue);

        ArrayList<String> hourDay = new ArrayList<String>();
        User newCoffee = new User(coffeeName,userName,password,street,number,intPromotionAfter,doublePromoValue,userCoffee);
        @Override
        protected ArrayList<User> doInBackground(String... params)
        {
            hourDay.add(mondayBeginHour+":00");
            hourDay.add(mondayEndHour+":00");
            hourDay.add(thusdayBeginHour+":00");
            hourDay.add(thusdayEndHour+":00");
            hourDay.add(wednsedayBeginHour+":00");
            hourDay.add(wednsedayEndHour+":00");
            hourDay.add(thursdayBeginHour+":00");
            hourDay.add(thursdayEndHour+":00");
            hourDay.add(fridayBeginHour+":00");
            hourDay.add(fridayEndHour+":00");
            hourDay.add(saturdayBeginHour+":00");
            hourDay.add(saturdayEndHour+":00");
            hourDay.add(sundayBeginHour+":00");
            hourDay.add(sundayEndHour+":00");

            /*IMPORTANT
            DATE DU JOUR
            Date today = Calendar.getInstance().getTime();
             */

            UserDAO userDAO = new UserDAO();
            ArrayList<User> users = new ArrayList<>();
            try
            {
                ArrayList<String> dateDay = new ArrayList<String>();
                for(int i = 0; i < hourDay.size(); i++)
                {
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                    Date date = format.parse(hourDay.get(i));
                    Format formatter = new SimpleDateFormat("HH:mm:ss");
                    String hour = formatter.format(date);
                    dateDay.add(hour);
                }

                TimeTable timeTableMonday = new TimeTable(1, dateDay.get(0),dateDay.get(1));
                TimeTable timeTableThusday = new TimeTable(2,dateDay.get(2),dateDay.get(3));
                TimeTable timeTableWednseday = new TimeTable(3,dateDay.get(4),dateDay.get(5));
                TimeTable timeTableThursday = new TimeTable(4,dateDay.get(6),dateDay.get(7));
                TimeTable timeTableFriday = new TimeTable(5,dateDay.get(8),dateDay.get(9));
                TimeTable timeTableSaturday = new TimeTable(6,dateDay.get(10),dateDay.get(11));
                TimeTable timeTableSunday = new TimeTable(7,dateDay.get(12),dateDay.get(13));

                ArrayList<TimeTable> timeTables = new ArrayList<TimeTable>();
                timeTables.add(timeTableMonday);
                timeTables.add(timeTableThusday);
                timeTables.add(timeTableWednseday);
                timeTables.add(timeTableThursday);
                timeTables.add(timeTableFriday);
                timeTables.add(timeTableSaturday);
                timeTables.add(timeTableSunday);

                userDAO.postNewRegistrationCoffee(newCoffee,timeTables);
            }
            catch(Exception e)
            {
                exception = e;
            }

            return users;
        }

        //***********************COMMENTAIRE****************************
        //Permet d'executer quelque chose après le chargement des données
        //**************************************************************
        @Override
        protected void onPostExecute(ArrayList<User> users)
        {
            if (exception != null)
            {
                if (!password.equals(confirmationPassword))
                {
                    Toast.makeText(RegistrationCoffeeActivity.this, "Les mot de passes tapés sont différents", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(userName.equals("") || password.equals("") || confirmationPassword.equals("") || street.equals("") ||number.equals("") || promotionAfter.equals("") || promoValue.equals("") || coffeeName.equals(""))
                    {
                        Toast.makeText(RegistrationCoffeeActivity.this, "Tout les champs doivent être remplis obligatoirememnt", Toast.LENGTH_LONG).show();
                    }
                }
            }
            else
            {
                Intent intentReservation = new Intent(RegistrationCoffeeActivity.this,MainActivity.class);
                startActivity(intentReservation);
                Toast.makeText(RegistrationCoffeeActivity.this, "L'inscription a bien été effectuée, vous pouvez maintenant vous connecter", Toast.LENGTH_LONG).show();
            }
        }
    }
}
