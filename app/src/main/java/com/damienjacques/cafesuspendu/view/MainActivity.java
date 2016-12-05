package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
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
    private Button clickPourClient;

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

        /*new LoadTerminal().execute();
        new LoadBooking().execute();
        new LoadCharity().execute();
        new LoadTimeTable().execute();*/
    }

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

    private void creationLayout()
    {
        clickRegistration = (Button)findViewById(R.id.buttonRegistrationMain);
        clickConnection = (Button)findViewById(R.id.buttonConnection);
        clickPourClient = (Button)findViewById(R.id.buttonInutile);

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
                new LoadUser().execute();
            }
        });

        clickPourClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,ReceptionClientActivity.class);
                startActivity(intent);
            }
        });
    }

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
                Log.i("Test", token.toString());
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }

            return users;
        }

        @Override
        protected void onPostExecute(ArrayList<User> users)
        {
            if(userName.equals("") || password.equals(""))
            {
                Toast.makeText(MainActivity.this,"Vous devez remplir les champs identifiants et mot de passe pour pouvoir accèder à votre compte", Toast.LENGTH_SHORT).show();
            }
            else
            {
                int i;
                for(i = 0 ; i < users.size() && !users.get(i).getUserName().equals(userName); i++)
                {
                    Log.i("Nom ", users.get(i).getUserName().toString());
                }

                if(i == users.size())
                {
                    Toast.makeText(MainActivity.this,"Identifiant ou mot de passe incorrect", Toast.LENGTH_LONG).show();
                }
                else
                {

                    Toast.makeText(MainActivity.this,"Un identifiant a été trouvé", Toast.LENGTH_LONG).show();
                    /*Intent intent = new Intent(MainActivity.this,ReceptionCoffeeActivity.class);
                    startActivity(intent);*/
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

    private class LoadCharity extends AsyncTask<String, Void, ArrayList<Charity>>
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

        @Override
        protected void onPostExecute(ArrayList<Charity> charities)
        {
            Log.i("Test Charity", charities.toString());
        }
    }

    private class LoadBooking extends AsyncTask<String, Void, ArrayList<Booking>>
    {
        @Override
        protected ArrayList<Booking> doInBackground(String... params)
        {
            BookingDAO bookingDAO = new BookingDAO();
            ArrayList<Booking> bookings = new ArrayList<>();
            try
            {
                bookings = bookingDAO.getAllBooking();
            }
            catch(Exception e)
            {
                return bookings;
            }

            return bookings;
        }

        @Override
        protected void onPostExecute(ArrayList<Booking> bookings)
        {
            Log.i("Test Booking", bookings.toString());
        }
    }
/*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(userNameTextView.getText(), userName);
        outState.putString(passwordTextView.getText(), password);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        userName = savedInstanceState.getString(userNameTextView.getText());
        password = savedInstanceState.getString(passwordTextView.getText());
    }
    */
}
