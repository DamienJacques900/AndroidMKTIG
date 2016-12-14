package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.*;
import com.damienjacques.cafesuspendu.model.*;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private Button clickRegistration;
    private Button clickConnection;

    private ProgressBar spinner;

    private TextView userNameTextView;
    private TextView passwordTextView;

    private String userName;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creationLayout();
        //new LoadCharity().execute();
        //new LoadTerminal().execute();
        //new LoadTimeTable().execute();
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
            setContentView(R.layout.activity_main);
            creationLayout();
        }
        else
        {
            setContentView(R.layout.activity_main);
            creationLayout();
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    private void creationLayout()
    {
        clickRegistration = (Button)findViewById(R.id.buttonRegistrationMain);
        clickConnection = (Button)findViewById(R.id.buttonConnection);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        userNameTextView = (TextView)findViewById(R.id.userNameConnection);
        passwordTextView = (TextView)findViewById(R.id.passwordConnection);

        clickRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,ChooseRegistrationActivity.class);
                startActivity(intent);
            }
        });

        clickConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                spinner.setVisibility(View.VISIBLE);
                new LoadUser().execute();
            }
        });
    }

    //***********************COMMENTAIRE****************************
    //Permet de charger les données de l'API
    //**************************************************************
    private class LoadUser extends AsyncTask<String, Void, ArrayList<User>>
    {
        String userName = userNameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        @Override
        protected ArrayList<User> doInBackground(String... params)
        {
            UserDAO userDAO = new UserDAO();
            ArrayList<User> users = new ArrayList<>();
            try
            {
                users = userDAO.getAllUsers();
                String token = userDAO.getUserWithUserNameAndPw(userName, password);
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }

            return users;
        }

        //***********************COMMENTAIRE****************************
        //Permet d'executer quelque chose après le chargement des données
        //**************************************************************
        @Override
        protected void onPostExecute(ArrayList<User> users)
        {
            if(userName.equals("") || password.equals(""))
            {
                Toast.makeText(MainActivity.this,"Vous devez remplir les champs identifiants et mot de passe pour pouvoir accèder à votre compte", Toast.LENGTH_SHORT).show();
                spinner.setVisibility(View.GONE);
            }
            else
            {
                int i;
                for(i = 0 ; i < users.size() && !users.get(i).getUserName().equals(userName); i++)
                {
                    /*Log.i("Nom ", users.get(i).getUserName().toString());
                    Log.i("Role", users.get(i).getRoles().toString());
                    Log.i("Email", users.get(i).getEmail().toString());
                    Log.i("Phone", users.get(i).getPhoneNumber().toString());
                    Log.i("NbCoffee", users.get(i).getNbCoffeeRequiredForPromotion().toString());
                    Log.i("PromoValue", users.get(i).getPromotionValue().toString());*/
                }

                if(i == users.size())
                {
                    Toast.makeText(MainActivity.this,"Identifiant ou mot de passe incorrect", Toast.LENGTH_LONG).show();
                    spinner.setVisibility(View.GONE);
                }
                else
                {
                    //***********************COMMENTAIRE****************************
                    //Permet de pouvoir récuperer les données partout dans le code
                    //par la suite en stockant les données dans un sharePreference
                    //**************************************************************
                    SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                    //***********************COMMENTAIRE****************************
                    //Permet d'éditer le sharePreference
                    //**************************************************************
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("userName",users.get(i).getUserName().toString());
                    editor.putString("role",users.get(i).getRoles().toString());
                    editor.putString("email",users.get(i).getEmail().toString());
                    editor.putString("phoneNumber",users.get(i).getPhoneNumber().toString());
                    editor.putInt("nbCoffeeRequiredForPromotion",users.get(i).getNbCoffeeRequiredForPromotion());
                    editor.putFloat("promotionValue",users.get(i).getPromotionValue());
                    editor.commit();

                    if(users.get(i).getRoles().equals("userperson"))
                    {//User
                        spinner.setVisibility(View.GONE);
                        Intent intent = new Intent(MainActivity.this,ReceptionClientActivity.class);
                        startActivity(intent);
                    }
                    else
                    {//Coffee
                        spinner.setVisibility(View.GONE);
                        Intent intent = new Intent(MainActivity.this,ReceptionCoffeeActivity.class);
                        startActivity(intent);
                    }
                }
            }
        }
    }

    private class LoadTerminal extends AsyncTask<String, Void, ArrayList<Terminal>>
    {
        @Override
        protected ArrayList<Terminal> doInBackground(String... params)
        {

            TerminalDAO terminalDAO = new TerminalDAO();
            ArrayList<Terminal> terminals = new ArrayList<>();
            try
            {
                terminals = terminalDAO.getAllTerminals();
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }

            return terminals;
        }

        @Override
        protected void onPostExecute(ArrayList<Terminal> terminals)
        {
            Log.i("Test Terminal", terminals.toString());
        }
    }

    private class LoadTimeTable extends AsyncTask<String, Void, ArrayList<TimeTable>>
    {
        @Override
        protected ArrayList<TimeTable> doInBackground(String... params)
        {
            TimeTableDAO timeTableDAO = new TimeTableDAO();
            ArrayList<TimeTable> timeTables = new ArrayList<>();
            try
            {
                timeTables = timeTableDAO.getAllTimeTables();
            }
            catch(Exception e)
            {
                return timeTables;
            }

            return timeTables;
        }

        @Override
        protected void onPostExecute(ArrayList<TimeTable> timeTables)
        {
            Log.i("Test TimeTable", timeTables.toString());
        }
    }

    public class LoadCharity extends AsyncTask<String, Void, ArrayList<Charity>>
    {
        @Override
        protected ArrayList<Charity> doInBackground(String... params)
        {
            CharityDAO charityDAO = new CharityDAO();
            ArrayList<Charity> charities = new ArrayList<>();
            try
            {
                charities = charityDAO.getAllCharities();
            }
            catch(Exception e)
            {
                return charities;
            }

            return charities;
        }

        //***********************COMMENTAIRE****************************
        //Permet d'executer quelque chose après le chargement des données
        //**************************************************************
        @Override
        protected void onPostExecute(ArrayList<Charity> charities)
        {
            //***********************COMMENTAIRE****************************
            //Permet de pouvoir récuperer les données partout dans le code
            //par la suite en stockant les données dans un sharePreference
            //**************************************************************
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
            ArrayList<Charity> charitiesClient = new ArrayList<Charity>();

            for(int i = 0 ; i < charities.size(); i++)
            {
                if(charities.get(i).getUserPerson().getUserName().equals(pref.getString("userName",null)))
                {
                    charitiesClient.add(charities.get(i));
                }
            }

            //***********************COMMENTAIRE****************************
            //Permet d'éditer le sharePreference
            //**************************************************************
            SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            for(int i = 1; i <= charitiesClient.size(); i++)
            {
                editor.putString("coffeeName"+i, charitiesClient.get(i-1).getUserCafe().getUserName());
                editor.putInt("nbCoffeeOffered"+i, charitiesClient.get(i-1).getNbCoffeeOffered());
                editor.putString("dateOffering"+i, charitiesClient.get(i-1).getOfferingTime().toString());
                System.out.println("Nom : "+charitiesClient.get(i-1).getUserCafe().getUserName()+" nbCoffe : "+charitiesClient.get(i-1).getNbCoffeeOffered()+" date : "+charitiesClient.get(i-1).getOfferingTime().toString());
            }
            editor.putInt("SizeCharities",charitiesClient.size());
            editor.commit();
        }
    }
}
