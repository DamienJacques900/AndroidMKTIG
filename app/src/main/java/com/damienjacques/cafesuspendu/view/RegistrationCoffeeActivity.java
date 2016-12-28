package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.icu.util.DateInterval;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.*;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.UserDAO;
import com.damienjacques.cafesuspendu.exception.BeginHourBeforeEndException;
import com.damienjacques.cafesuspendu.exception.BetweenZeroAndSixtyException;
import com.damienjacques.cafesuspendu.exception.BetweenZeroAndTwentyFourException;
import com.damienjacques.cafesuspendu.exception.EmailFalseException;
import com.damienjacques.cafesuspendu.exception.EmptyInputException;
import com.damienjacques.cafesuspendu.exception.ExistingCoffeeNameException;
import com.damienjacques.cafesuspendu.exception.ExistingUserNameException;
import com.damienjacques.cafesuspendu.exception.NbGreaterThanOneException;
import com.damienjacques.cafesuspendu.exception.PasswordDifferentException;
import com.damienjacques.cafesuspendu.exception.PasswordNotGoodException;
import com.damienjacques.cafesuspendu.exception.PasswordTooShortException;
import com.damienjacques.cafesuspendu.exception.PhoneNumberFalseException;
import com.damienjacques.cafesuspendu.exception.SizeNumberException;
import com.damienjacques.cafesuspendu.exception.SizeStreetException;
import com.damienjacques.cafesuspendu.exception.TooMuchException;
import com.damienjacques.cafesuspendu.model.TimeTable;
import com.damienjacques.cafesuspendu.model.User;

import java.io.IOException;
import java.sql.Array;
import java.sql.Time;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

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
    private TextView emailTextView;

    private ProgressBar spinner;

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
        emailTextView = (TextView) findViewById(R.id.emailCoffeeEdit);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

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
                spinner.setVisibility(View.VISIBLE);
                new LoadNewUser().execute();
            }
        });
    }

    private class LoadNewUser extends AsyncTask<String, Void, ArrayList<User>>
    {
        Exception exception;
        EmptyInputException inputException;
        EmailFalseException emailException;
        ExistingUserNameException existingUserNameException;
        PasswordDifferentException passwordDifferentException;
        NbGreaterThanOneException nbGreaterThanOneException;
        PasswordNotGoodException passwordNotGoodException;
        SizeStreetException sizeStreetException;
        SizeNumberException sizeNumberException;
        TooMuchException tooMuchException;
        ExistingCoffeeNameException existingCoffeeNameException;
        NumberFormatException numberFormatException;
        BeginHourBeforeEndException beginHourBeforeEndException;
        BetweenZeroAndSixtyException betweenZeroAndSixtyException;
        BetweenZeroAndTwentyFourException betweenZeroAndTwentyFourException;
        java.text.ParseException parseException;
        PasswordTooShortException passwordTooShortException;

        String userName = userNameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String confirmationPassword = confirmationPasswordTextView.getText().toString();
        String street = streetTextView.getText().toString();
        String number = numberTextView.getText().toString();

        String promotionAfter = nbCoffeePromotionTextView.getText().toString();

        String promoValue = promotionValueTextView.getText().toString();

        String coffeeName = coffeNameTextView.getText().toString();
        String email = emailTextView.getText().toString();
        String userCoffee = "usercafe";

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



        ArrayList<String> hourDay = new ArrayList<String>();
        @Override
        protected ArrayList<User> doInBackground(String... params)
        {
            int intPromotionAfter;
            if(promotionAfter.equals(""))
                intPromotionAfter = 0;
            else
                intPromotionAfter = Integer.parseInt(promotionAfter);

            Float doublePromoValue;
            if(promoValue.equals(""))
                doublePromoValue = Float.parseFloat("0");
            else
                doublePromoValue = Float.parseFloat(promoValue);

            User newCoffee = new User(coffeeName,userName,password,confirmationPassword,street,number,email,intPromotionAfter,doublePromoValue,userCoffee);


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

            UserDAO userDAO = new UserDAO();
            ArrayList<User> users = new ArrayList<>();
            try
            {
                if(userName.equals("") || password.equals("") || confirmationPassword.equals("") || street.equals("") || number.equals("") || promoValue.equals("") || promotionAfter.equals("") || coffeeName.equals("") || email.equals(""))
                {
                    throw new EmptyInputException();
                }

                if(mondayBeginHour.equals("") || mondayEndHour.equals("") || thusdayBeginHour.equals("") || thusdayEndHour.equals("") || wednsedayBeginHour.equals("") || wednsedayEndHour.equals("") || thursdayBeginHour.equals("") || thursdayEndHour.equals("") || fridayBeginHour.equals("") || fridayEndHour.equals("") || saturdayBeginHour.equals("") || saturdayEndHour.equals("") || sundayBeginHour.equals("") || sundayEndHour.equals(""))
                {
                    throw new EmptyInputException();
                }


                //------------------------JOUR INSPECTION DES HORAIRES------------------------
                //------------------------LUNDI-------------------------------------
                //***********************COMMENTAIRE****************************
                //Récupération des valeurs de min et des heures
                //**************************************************************
                String hourMondayBegin = "";
                String minMondayBegin = "";
                for(int i = 0; i < mondayBeginHour.length(); i++)
                {

                    if(!(mondayBeginHour.charAt(i)==':'))
                    {
                        hourMondayBegin+=mondayBeginHour.charAt(i);
                    }
                    else
                    {
                        minMondayBegin = mondayBeginHour.substring(i+1);
                        break;
                    }
                }
                System.out.println("Heure lundi : "+hourMondayBegin+" Minute lundi : "+minMondayBegin);

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourMondayBegin;
                if(hourMondayBegin.equals(""))
                    intHourMondayBegin = 0;
                else
                    intHourMondayBegin = Integer.parseInt(hourMondayBegin);


                int intMinMondayBegin;
                if(minMondayBegin.equals(""))
                    intMinMondayBegin = 0;
                else
                    intMinMondayBegin = Integer.parseInt(minMondayBegin);



                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                System.out.println("Heure début = "+intHourMondayBegin);
                if(intHourMondayBegin > 23)
                {
                    System.out.println("Heure début = "+intHourMondayBegin);
                    throw new BetweenZeroAndTwentyFourException();
                }

                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                System.out.println("Min début = "+intMinMondayBegin);
                if(intMinMondayBegin > 59)
                {
                    System.out.println("Min début = "+intMinMondayBegin);
                    throw new BetweenZeroAndSixtyException();
                }


                String hourMondayEnd = "";
                String minMondayEnd = "";
                for(int i = 0; i < mondayEndHour.length(); i++)
                {

                    if(!(mondayEndHour.charAt(i)==':'))
                    {
                        hourMondayEnd+=mondayEndHour.charAt(i);
                    }
                    else
                    {
                        minMondayEnd = mondayEndHour.substring(i+1);
                        break;
                    }
                }
                System.out.println("Heure lundi fin : "+hourMondayEnd+" Minute lundi fin : "+minMondayEnd);

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourMondayEnd;
                if(hourMondayEnd.equals(""))
                    intHourMondayEnd = 0;
                else
                    intHourMondayEnd = Integer.parseInt(hourMondayEnd);




                int intMinMondayEnd;
                if(minMondayEnd.equals(""))
                    intMinMondayEnd = 0;
                else
                    intMinMondayEnd = Integer.parseInt(minMondayEnd);


                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                System.out.println("Heure fin = "+intHourMondayEnd);
                if(intHourMondayEnd > 23)
                {
                    System.out.println("Heure fin = "+intHourMondayEnd);
                    throw new BetweenZeroAndTwentyFourException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                System.out.println("Min = "+intMinMondayEnd);
                if(intMinMondayEnd > 59)
                {
                    System.out.println("Min = "+intMinMondayEnd);
                    throw new BetweenZeroAndSixtyException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification que l'heure de début est bien supérieur à celle de fin
                //**************************************************************
                if(intHourMondayBegin > intHourMondayEnd)
                {
                    System.out.println("Heure début > heure fin");
                    throw new BeginHourBeforeEndException();
                }
                else
                {
                    if(intHourMondayBegin == intHourMondayEnd)
                    {
                        if(intMinMondayBegin >= intMinMondayEnd)
                        {
                            System.out.println("Heure début == heure fin et min début >= min fin");
                            throw new BeginHourBeforeEndException();
                        }
                    }
                }

                //------------------------------MARDI---------------------------------
                //***********************COMMENTAIRE****************************
                //Récupération des valeurs de min et des heures
                //**************************************************************
                String hourTuesdayBegin = "";
                String minTuesdayBegin = "";
                for(int i = 0; i < thusdayBeginHour.length(); i++)
                {

                    if(!(thusdayBeginHour.charAt(i)==':'))
                    {
                        hourTuesdayBegin+=thusdayBeginHour.charAt(i);
                    }
                    else
                    {
                        minTuesdayBegin = thusdayBeginHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourTuesdayBegin;
                if(hourTuesdayBegin.equals(""))
                    intHourTuesdayBegin = 0;
                else
                    intHourTuesdayBegin = Integer.parseInt(hourTuesdayBegin);


                int intMinTuesdayBegin;
                if(minTuesdayBegin.equals(""))
                    intMinTuesdayBegin = 0;
                else
                    intMinTuesdayBegin = Integer.parseInt(minTuesdayBegin);



                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourTuesdayBegin > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }

                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinTuesdayBegin > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                String hourTuesdayEnd = "";
                String minTuesdayEnd = "";
                for(int i = 0; i < thusdayEndHour.length(); i++)
                {

                    if(!(thusdayEndHour.charAt(i)==':'))
                    {
                        hourTuesdayEnd+=thusdayEndHour.charAt(i);
                    }
                    else
                    {
                        minTuesdayEnd = thusdayEndHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourTuesdayEnd;
                if(hourTuesdayEnd.equals(""))
                    intHourTuesdayEnd = 0;
                else
                    intHourTuesdayEnd = Integer.parseInt(hourTuesdayEnd);


                int intMinTuesdayEnd;
                if(minTuesdayEnd.equals(""))
                    intMinTuesdayEnd = 0;
                else
                    intMinTuesdayEnd = Integer.parseInt(minTuesdayEnd);


                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourTuesdayEnd > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinTuesdayEnd > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification que l'heure de début est bien supérieur à celle de fin
                //**************************************************************
                if(intHourTuesdayBegin > intHourTuesdayEnd)
                {
                    throw new BeginHourBeforeEndException();
                }
                else
                {
                    if(intHourTuesdayBegin == intHourTuesdayEnd)
                    {
                        if(intMinTuesdayBegin >= intMinTuesdayEnd)
                        {
                            throw new BeginHourBeforeEndException();
                        }
                    }
                }

                //------------------------------MERCREDI------------------------------

                //***********************COMMENTAIRE****************************
                //Récupération des valeurs de min et des heures
                //**************************************************************
                String hourWednesdayBegin = "";
                String minWednesdayBegin = "";
                for(int i = 0; i < wednsedayBeginHour.length(); i++)
                {

                    if(!(wednsedayBeginHour.charAt(i)==':'))
                    {
                        hourWednesdayBegin+=wednsedayBeginHour.charAt(i);
                    }
                    else
                    {
                        minWednesdayBegin = wednsedayBeginHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourWednesdayBegin;
                if(hourWednesdayBegin.equals(""))
                    intHourWednesdayBegin = 0;
                else
                    intHourWednesdayBegin = Integer.parseInt(hourWednesdayBegin);


                int intMinWednesdayBegin;
                if(minWednesdayBegin.equals(""))
                    intMinWednesdayBegin = 0;
                else
                    intMinWednesdayBegin = Integer.parseInt(minWednesdayBegin);



                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourWednesdayBegin > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }

                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinWednesdayBegin > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                String hourWednesdayEnd = "";
                String minWednesdayEnd = "";
                for(int i = 0; i < wednsedayEndHour.length(); i++)
                {

                    if(!(wednsedayEndHour.charAt(i)==':'))
                    {
                        hourWednesdayEnd+=wednsedayEndHour.charAt(i);
                    }
                    else
                    {
                        minWednesdayEnd = wednsedayEndHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourWednesdayEnd;
                if(hourWednesdayEnd.equals(""))
                    intHourWednesdayEnd = 0;
                else
                    intHourWednesdayEnd = Integer.parseInt(hourWednesdayEnd);


                int intMinWednesdayEnd;
                if(minWednesdayEnd.equals(""))
                    intMinWednesdayEnd = 0;
                else
                    intMinWednesdayEnd = Integer.parseInt(minWednesdayEnd);


                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourWednesdayEnd > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinWednesdayEnd > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification que l'heure de début est bien supérieur à celle de fin
                //**************************************************************
                if(intHourWednesdayBegin > intHourWednesdayEnd)
                {
                    throw new BeginHourBeforeEndException();
                }
                else
                {
                    if(intHourWednesdayBegin == intHourWednesdayEnd)
                    {
                        if(intMinWednesdayBegin >= intMinWednesdayEnd)
                        {
                            throw new BeginHourBeforeEndException();
                        }
                    }
                }

                //------------------------------JEUDI---------------------------------

                //***********************COMMENTAIRE****************************
                //Récupération des valeurs de min et des heures
                //**************************************************************
                String hourThursdayBegin = "";
                String minThursdayBegin = "";
                for(int i = 0; i < thursdayBeginHour.length(); i++)
                {

                    if(!(thursdayBeginHour.charAt(i)==':'))
                    {
                        hourThursdayBegin+=thursdayBeginHour.charAt(i);
                    }
                    else
                    {
                        minThursdayBegin = thursdayBeginHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourThursdayBegin;
                if(hourThursdayBegin.equals(""))
                    intHourThursdayBegin = 0;
                else
                    intHourThursdayBegin = Integer.parseInt(hourThursdayBegin);


                int intMinThursdayBegin;
                if(minThursdayBegin.equals(""))
                    intMinThursdayBegin = 0;
                else
                    intMinThursdayBegin = Integer.parseInt(minThursdayBegin);



                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourThursdayBegin > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }

                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinThursdayBegin > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                String hourThursdayEnd = "";
                String minThursdayEnd = "";
                for(int i = 0; i < thursdayEndHour.length(); i++)
                {

                    if(!(thursdayEndHour.charAt(i)==':'))
                    {
                        hourThursdayEnd+=thursdayEndHour.charAt(i);
                    }
                    else
                    {
                        minThursdayEnd = thursdayEndHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourThursdayEnd;
                if(hourThursdayEnd.equals(""))
                    intHourThursdayEnd = 0;
                else
                    intHourThursdayEnd = Integer.parseInt(hourThursdayEnd);


                int intMinThursdayEnd;
                if(minThursdayEnd.equals(""))
                    intMinThursdayEnd = 0;
                else
                    intMinThursdayEnd = Integer.parseInt(minThursdayEnd);


                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourThursdayEnd > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinThursdayEnd > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification que l'heure de début est bien supérieur à celle de fin
                //**************************************************************
                if(intHourThursdayBegin > intHourThursdayEnd)
                {
                    throw new BeginHourBeforeEndException();
                }
                else
                {
                    if(intHourThursdayBegin == intHourThursdayEnd)
                    {
                        if(intMinThursdayBegin >= intMinThursdayEnd)
                        {
                            throw new BeginHourBeforeEndException();
                        }
                    }
                }

                //------------------------------VENDREDI------------------------------

                //***********************COMMENTAIRE****************************
                //Récupération des valeurs de min et des heures
                //**************************************************************
                String hourFridayBegin = "";
                String minFridayBegin = "";
                for(int i = 0; i < fridayBeginHour.length(); i++)
                {

                    if(!(fridayBeginHour.charAt(i)==':'))
                    {
                        hourFridayBegin+=fridayBeginHour.charAt(i);
                    }
                    else
                    {
                        minFridayBegin = fridayBeginHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourFridayBegin;
                if(hourFridayBegin.equals(""))
                    intHourFridayBegin = 0;
                else
                    intHourFridayBegin = Integer.parseInt(hourFridayBegin);


                int intMinFridayBegin;
                if(minFridayBegin.equals(""))
                    intMinFridayBegin = 0;
                else
                    intMinFridayBegin = Integer.parseInt(minFridayBegin);



                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourFridayBegin > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }

                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinFridayBegin > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                String hourFridayEnd = "";
                String minFridayEnd = "";
                for(int i = 0; i < fridayEndHour.length(); i++)
                {

                    if(!(fridayEndHour.charAt(i)==':'))
                    {
                        hourFridayEnd+=fridayEndHour.charAt(i);
                    }
                    else
                    {
                        minFridayEnd = fridayEndHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourFridayEnd;
                if(hourFridayEnd.equals(""))
                    intHourFridayEnd = 0;
                else
                    intHourFridayEnd = Integer.parseInt(hourFridayEnd);


                int intMinFridayEnd;
                if(minFridayEnd.equals(""))
                    intMinFridayEnd = 0;
                else
                    intMinFridayEnd = Integer.parseInt(minFridayEnd);


                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourFridayEnd > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinFridayEnd > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification que l'heure de début est bien supérieur à celle de fin
                //**************************************************************
                if(intHourFridayBegin > intHourFridayEnd)
                {
                    throw new BeginHourBeforeEndException();
                }
                else
                {
                    if(intHourFridayBegin == intHourFridayEnd)
                    {
                        if(intMinFridayBegin >= intMinFridayEnd)
                        {
                            throw new BeginHourBeforeEndException();
                        }
                    }
                }

                //------------------------------SAMEDI--------------------------------

                //***********************COMMENTAIRE****************************
                //Récupération des valeurs de min et des heures
                //**************************************************************
                String hourSaturdayBegin = "";
                String minSaturdayBegin = "";
                for(int i = 0; i < saturdayBeginHour.length(); i++)
                {

                    if(!(saturdayBeginHour.charAt(i)==':'))
                    {
                        hourSaturdayBegin+=saturdayBeginHour.charAt(i);
                    }
                    else
                    {
                        minSaturdayBegin = saturdayBeginHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourSaturdayBegin;
                if(hourSaturdayBegin.equals(""))
                    intHourSaturdayBegin = 0;
                else
                    intHourSaturdayBegin = Integer.parseInt(hourSaturdayBegin);


                int intMinSaturdayBegin;
                if(minSaturdayBegin.equals(""))
                    intMinSaturdayBegin = 0;
                else
                    intMinSaturdayBegin = Integer.parseInt(minSaturdayBegin);



                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourSaturdayBegin > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }

                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinSaturdayBegin > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                String hourSaturdayEnd = "";
                String minSaturdayEnd = "";
                for(int i = 0; i < saturdayEndHour.length(); i++)
                {

                    if(!(saturdayEndHour.charAt(i)==':'))
                    {
                        hourSaturdayEnd+=saturdayEndHour.charAt(i);
                    }
                    else
                    {
                        minSaturdayEnd = saturdayEndHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourSaturdayEnd;
                if(hourSaturdayEnd.equals(""))
                    intHourSaturdayEnd = 0;
                else
                    intHourSaturdayEnd = Integer.parseInt(hourSaturdayEnd);


                int intMinSaturdayEnd;
                if(minSaturdayEnd.equals(""))
                    intMinSaturdayEnd = 0;
                else
                    intMinSaturdayEnd = Integer.parseInt(minSaturdayEnd);


                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourSaturdayEnd > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinSaturdayEnd > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification que l'heure de début est bien supérieur à celle de fin
                //**************************************************************
                if(intHourSaturdayBegin > intHourSaturdayEnd)
                {
                    throw new BeginHourBeforeEndException();
                }
                else
                {
                    if(intHourSaturdayBegin == intHourSaturdayEnd)
                    {
                        if(intMinSaturdayBegin >= intMinSaturdayEnd)
                        {
                            throw new BeginHourBeforeEndException();
                        }
                    }
                }

                //------------------------------DIMANCHE------------------------------

                //***********************COMMENTAIRE****************************
                //Récupération des valeurs de min et des heures
                //**************************************************************
                String hourSundayBegin = "";
                String minSundayBegin = "";
                for(int i = 0; i < sundayBeginHour.length(); i++)
                {

                    if(!(sundayBeginHour.charAt(i)==':'))
                    {
                        hourSundayBegin+=sundayBeginHour.charAt(i);
                    }
                    else
                    {
                        minSundayBegin = sundayBeginHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourSundayBegin;
                if(hourSundayBegin.equals(""))
                    intHourSundayBegin = 0;
                else
                    intHourSundayBegin = Integer.parseInt(hourSundayBegin);


                int intMinSundayBegin;
                if(minSundayBegin.equals(""))
                    intMinSundayBegin = 0;
                else
                    intMinSundayBegin = Integer.parseInt(minSundayBegin);



                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourSundayBegin > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }

                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinSundayBegin > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                String hourSundayEnd = "";
                String minSundayEnd = "";
                for(int i = 0; i < sundayEndHour.length(); i++)
                {

                    if(!(sundayEndHour.charAt(i)==':'))
                    {
                        hourSundayEnd+=sundayEndHour.charAt(i);
                    }
                    else
                    {
                        minSundayEnd = sundayEndHour.substring(i+1);
                        break;
                    }
                }

                //***********************COMMENTAIRE****************************
                //Si aucune valeur n'as été notée, zéro par défaut
                //**************************************************************
                int intHourSundayEnd;
                if(hourSundayEnd.equals(""))
                    intHourSundayEnd = 0;
                else
                    intHourSundayEnd = Integer.parseInt(hourSundayEnd);


                int intMinSundayEnd;
                if(minSundayEnd.equals(""))
                    intMinSundayEnd = 0;
                else
                    intMinSundayEnd = Integer.parseInt(minSundayEnd);


                //***********************COMMENTAIRE****************************
                //Vérification si les heures sont bien compris entre 0 et 23
                //**************************************************************
                if(intHourSundayEnd > 23)
                {
                    throw new BetweenZeroAndTwentyFourException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification si les minutes sont bien compris entre 0 et 59
                //**************************************************************
                if(intMinSundayEnd > 59)
                {
                    throw new BetweenZeroAndSixtyException();
                }


                //***********************COMMENTAIRE****************************
                //Vérification que l'heure de début est bien supérieur à celle de fin
                //**************************************************************
                if(intHourSundayBegin > intHourSundayEnd)
                {
                    throw new BeginHourBeforeEndException();
                }
                else
                {
                    if(intHourSundayBegin == intHourSundayEnd)
                    {
                        if(intMinSundayBegin >= intMinSundayEnd)
                        {
                            throw new BeginHourBeforeEndException();
                        }
                    }
                }

                //----------------JOUR INSPECTION DES HORAIRES------------------------





                ArrayList<String> dateDay = new ArrayList<String>();
                for(int i = 0; i < hourDay.size(); i++)
                {
                    //***********************COMMENTAIRE****************************
                    //Permet d'avoir le format voulu pour les heures
                    //**************************************************************
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


                if(Float.parseFloat(promoValue) < 0.1 || Integer.parseInt(promotionAfter) < 1)
                {
                    throw new NbGreaterThanOneException();
                }

                if (Integer.parseInt(promotionAfter)> 100 || Float.parseFloat(promoValue) > 100.0)
                {
                    throw new TooMuchException();
                }

                if(!validEmail(email))
                {
                    throw new EmailFalseException();
                }

                if (!password.equals(confirmationPassword))
                {
                    throw new PasswordDifferentException();
                }

                Boolean exist = false;
                Boolean existCoffeeName = false;

                ArrayList<User> usersTestUserName = new ArrayList<User>();
                usersTestUserName = userDAO.getAllUsers();
                for(int i = 0 ; i < usersTestUserName.size(); i++)
                {
                    if(usersTestUserName.get(i).getUserName().equals(userName))
                        exist = true;

                    if(usersTestUserName.get(i).getCafeName().equals(coffeeName))
                        existCoffeeName = true;
                }

                if(exist)
                {
                    throw new ExistingUserNameException();
                }

                Boolean upper = false;
                for(int i = 0; i < password.length();i++)
                {
                    if(Character.isUpperCase(password.charAt(i)))
                    {
                        upper = true;
                    }
                }

                Boolean numberPassword = false;
                for(int i = 0; i < password.length();i++)
                {
                    if(java.lang.Character.isDigit(password.charAt(i)))
                    {
                        numberPassword = true;
                    }
                }

                if(!numberPassword)
                {
                    throw new PasswordNotGoodException();
                }

                if(!upper)
                {
                    throw new PasswordNotGoodException();
                }


                if(existCoffeeName)
                {
                    throw new ExistingCoffeeNameException();
                }

                if(number.length() > 5)
                {
                    throw new SizeNumberException();
                }

                if(street.length() > 50)
                {
                    throw new SizeStreetException();
                }

                if(password.length() < 6)
                {
                    throw new PasswordTooShortException();
                }

                userDAO.postNewRegistrationCoffee(newCoffee,timeTables);
            }
            catch(PasswordTooShortException e)
            {
                passwordTooShortException = e;
            }
            catch(java.text.ParseException e)
            {
                parseException = e;
            }
            catch(BetweenZeroAndTwentyFourException e)
            {
                betweenZeroAndTwentyFourException = e;
            }
            catch(BetweenZeroAndSixtyException e)
            {
                betweenZeroAndSixtyException = e;
            }
            catch(BeginHourBeforeEndException e)
            {
                beginHourBeforeEndException = e;
            }
            catch(NumberFormatException e) //OK---------------
            {
                numberFormatException = e;
            }
            catch(ExistingCoffeeNameException e) //OK---------------
            {
                existingCoffeeNameException = e;
            }
            catch(TooMuchException e) //OK---------------
            {
                tooMuchException = e;
            }
            catch(SizeNumberException e) //OK---------------
            {
                sizeNumberException = e;
            }
            catch(SizeStreetException e) //OK---------------
            {
                sizeStreetException = e;
            }
            catch(PasswordNotGoodException e) //OK---------------
            {
                passwordNotGoodException = e;
            }
            catch(EmptyInputException e) //OK---------------
            {
                inputException = e;
            }
            catch(NbGreaterThanOneException e)  //OK---------------
            {
                nbGreaterThanOneException = e;
            }
            catch(PasswordDifferentException e) //OK---------------
            {
                passwordDifferentException = e;
            }
            catch(ExistingUserNameException e) //OK---------------
            {
                existingUserNameException = e;
            }
            catch(EmailFalseException e) //OK---------------
            {
                emailException = e;
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
                System.out.println(exception);
                Toast.makeText(RegistrationCoffeeActivity.this, "Erreur dans de connexion", Toast.LENGTH_LONG).show();
                Intent intentReservation = new Intent(RegistrationCoffeeActivity.this, MainActivity.class);
                startActivity(intentReservation);
            }
            else
            {
                if(existingCoffeeNameException != null)
                {
                    Toast.makeText(RegistrationCoffeeActivity.this, existingCoffeeNameException.getMessage(), Toast.LENGTH_LONG).show();
                    spinner.setVisibility(View.GONE);
                }
                else
                {
                    if(tooMuchException != null)
                    {
                        Toast.makeText(RegistrationCoffeeActivity.this, tooMuchException.getMessage(), Toast.LENGTH_LONG).show();
                        spinner.setVisibility(View.GONE);
                    }
                    else
                    {
                        if(sizeNumberException != null)
                        {
                            Toast.makeText(RegistrationCoffeeActivity.this, sizeNumberException.getMessage(), Toast.LENGTH_LONG).show();
                            spinner.setVisibility(View.GONE);
                        }
                        else
                        {
                            if(sizeStreetException != null)
                            {
                                Toast.makeText(RegistrationCoffeeActivity.this, sizeStreetException.getMessage(), Toast.LENGTH_LONG).show();
                                spinner.setVisibility(View.GONE);
                            }
                            else
                            {
                                if(passwordNotGoodException != null)
                                {
                                    Toast.makeText(RegistrationCoffeeActivity.this, passwordNotGoodException.getMessage(), Toast.LENGTH_LONG).show();
                                    spinner.setVisibility(View.GONE);
                                }
                                else
                                {
                                    if(inputException != null)
                                    {
                                        Toast.makeText(RegistrationCoffeeActivity.this, inputException.getMessage(), Toast.LENGTH_LONG).show();
                                        spinner.setVisibility(View.GONE);
                                    }
                                    else
                                    {
                                        if(nbGreaterThanOneException != null)
                                        {
                                            Toast.makeText(RegistrationCoffeeActivity.this, nbGreaterThanOneException.getMessage(), Toast.LENGTH_LONG).show();
                                            spinner.setVisibility(View.GONE);
                                        }
                                        else
                                        {
                                            if(passwordDifferentException != null)
                                            {
                                                Toast.makeText(RegistrationCoffeeActivity.this, passwordDifferentException.getMessage(), Toast.LENGTH_LONG).show();
                                                spinner.setVisibility(View.GONE);
                                            }
                                            else
                                            {
                                                if(existingUserNameException != null)
                                                {
                                                    Toast.makeText(RegistrationCoffeeActivity.this, existingUserNameException.getMessage(), Toast.LENGTH_LONG).show();
                                                    spinner.setVisibility(View.GONE);
                                                }
                                                else
                                                {
                                                    if(emailException != null)
                                                    {
                                                        Toast.makeText(RegistrationCoffeeActivity.this, emailException.getMessage(), Toast.LENGTH_LONG).show();
                                                        spinner.setVisibility(View.GONE);
                                                    }
                                                    else
                                                    {
                                                        if(numberFormatException != null)
                                                        {
                                                            Toast.makeText(RegistrationCoffeeActivity.this, "Les champs valeur de la promotion et nombre de café avant promotion doivent être remplies ou une date comprend plusieurs :'", Toast.LENGTH_LONG).show();
                                                            spinner.setVisibility(View.GONE);
                                                        }
                                                        else
                                                        {
                                                            if(beginHourBeforeEndException != null)
                                                            {
                                                                Toast.makeText(RegistrationCoffeeActivity.this, beginHourBeforeEndException.getMessage(), Toast.LENGTH_LONG).show();
                                                                spinner.setVisibility(View.GONE);
                                                            }
                                                            else
                                                            {
                                                                if(betweenZeroAndSixtyException !=null)
                                                                {
                                                                    Toast.makeText(RegistrationCoffeeActivity.this, betweenZeroAndSixtyException.getMessage(), Toast.LENGTH_LONG).show();
                                                                    spinner.setVisibility(View.GONE);
                                                                }
                                                                else
                                                                {
                                                                    if(betweenZeroAndTwentyFourException != null)
                                                                    {
                                                                        Toast.makeText(RegistrationCoffeeActivity.this, betweenZeroAndTwentyFourException.getMessage(), Toast.LENGTH_LONG).show();
                                                                        spinner.setVisibility(View.GONE);
                                                                    }
                                                                    else
                                                                    {
                                                                        if(parseException !=null)
                                                                        {
                                                                            Toast.makeText(RegistrationCoffeeActivity.this, "Une ou plusieurs heures sont mal écrites.", Toast.LENGTH_LONG).show();
                                                                            spinner.setVisibility(View.GONE);
                                                                        }
                                                                        else
                                                                        {
                                                                            if(passwordTooShortException != null)
                                                                            {
                                                                                Toast.makeText(RegistrationCoffeeActivity.this, passwordTooShortException.getMessage(), Toast.LENGTH_LONG).show();
                                                                                spinner.setVisibility(View.GONE);
                                                                            }
                                                                            else
                                                                            {
                                                                                Intent intentReservation = new Intent(RegistrationCoffeeActivity.this, MainActivity.class);
                                                                                startActivity(intentReservation);
                                                                                Toast.makeText(RegistrationCoffeeActivity.this, "L'inscription a bien été effectuée, vous pouvez maintenant vous connecter", Toast.LENGTH_LONG).show();
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                  }
        }
    }

    public boolean validEmail(String email)
    {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
