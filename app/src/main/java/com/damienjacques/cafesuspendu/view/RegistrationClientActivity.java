package com.damienjacques.cafesuspendu.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.util.Size;
import android.view.View;
import android.widget.*;

import com.damienjacques.cafesuspendu.R;
import com.damienjacques.cafesuspendu.dao.UserDAO;
import com.damienjacques.cafesuspendu.exception.EmailFalseException;
import com.damienjacques.cafesuspendu.exception.EmptyInputException;
import com.damienjacques.cafesuspendu.exception.ExistingUserNameException;
import com.damienjacques.cafesuspendu.exception.PasswordDifferentException;
import com.damienjacques.cafesuspendu.exception.PasswordNotGoodException;
import com.damienjacques.cafesuspendu.exception.PasswordTooShortException;
import com.damienjacques.cafesuspendu.exception.PhoneNumberFalseException;
import com.damienjacques.cafesuspendu.exception.SizeNameFirstNameException;
import com.damienjacques.cafesuspendu.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class RegistrationClientActivity extends AppCompatActivity
{
    private Button clickRegistration;
    private TextView userNameTextView;
    private TextView passwordTextView;
    private TextView nameTextView;
    private TextView firstNameTextView;
    private TextView mailTextView;
    private TextView phoneTextView;
    private TextView confirmationPasswordTextView;

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationclient);
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
            setContentView(R.layout.activity_registrationclient);
            createLayout();
        }
        else
        {
            setContentView(R.layout.activity_registrationclient);
            createLayout();
        }
    }

    //***********************COMMENTAIRE****************************
    //Permet de crééer le layout et de pouvoir le refaire pour si
    //il y a un changement d'orientation
    //**************************************************************
    private void createLayout()
    {
        userNameTextView = (TextView) findViewById(R.id.userNameClientEdit);
        passwordTextView = (TextView) findViewById(R.id.passwordClientEdit);
        confirmationPasswordTextView = (TextView) findViewById(R.id.confirmationClientEdit);
        nameTextView = (TextView) findViewById(R.id.nameClientEdit);
        firstNameTextView = (TextView) findViewById(R.id.firstNameClientEdit);
        mailTextView = (TextView) findViewById(R.id.mailClientEdit);
        phoneTextView = (TextView) findViewById(R.id.phoneClientEdit);

        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        clickRegistration = (Button) findViewById(R.id.buttonRegistrationClient);

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
        PhoneNumberFalseException phoneException;
        ExistingUserNameException existingUserNameException;
        PasswordDifferentException passwordDifferentException;
        SizeNameFirstNameException sizeNameFirstNameException;
        PasswordNotGoodException passwordNotGoodException;
        PasswordTooShortException passwordTooShortException;

        String userName = userNameTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String confirmationPassword = confirmationPasswordTextView.getText().toString();
        String name = nameTextView.getText().toString();
        String firstName = firstNameTextView.getText().toString();
        String email = mailTextView.getText().toString();
        String phoneNumber = phoneTextView.getText().toString();
        String userPerson ="userperson";

        User newPerson = new User(userName,password,confirmationPassword,firstName,name,email,phoneNumber,userPerson);

        @Override
        protected ArrayList<User> doInBackground(String... params)
        {
            UserDAO userDAO = new UserDAO();
            ArrayList<User> users = new ArrayList<>();
            try
            {
                if (userName.equals("") || password.equals("") || confirmationPassword.equals("") || name.equals("") || firstName.equals("") || email.equals("") || phoneNumber.equals(""))
                {
                    throw new EmptyInputException();
                }

                if(!validEmail(email))
                {
                    throw new EmailFalseException();
                }

                if(phoneNumber.length()> 15)
                {
                    throw new PhoneNumberFalseException();
                }
                if (!password.equals(confirmationPassword))
                {
                    throw new PasswordDifferentException();
                }

                Boolean exist = false;

                ArrayList<User> usersTestUserName = new ArrayList<User>();
                usersTestUserName = userDAO.getAllUsers();
                for(int i = 0 ; i < usersTestUserName.size(); i++)
                {
                    if(usersTestUserName.get(i).getUserName().equals(userName))
                        exist = true;
                }

                if(exist)
                {
                    throw new ExistingUserNameException();
                }

                if(name.length() > 30 || firstName.length() > 30)
                {
                    throw new SizeNameFirstNameException();
                }

                Boolean upper = false;
                for(int i = 0; i < password.length();i++)
                {
                    if(Character.isUpperCase(password.charAt(i)))
                    {
                        upper = true;
                    }
                }

                Boolean number = false;
                for(int i = 0; i < password.length();i++)
                {
                    if(java.lang.Character.isDigit(password.charAt(i)))
                    {
                        number = true;
                    }
                }

                if(!number)
                {
                    throw new PasswordNotGoodException();
                }

                if(!upper)
                {
                    throw new PasswordNotGoodException();
                }

                userDAO.postNewRegistrationPerson(newPerson);
            }
            catch(PasswordTooShortException e)
            {
                passwordTooShortException = e;
            }
            catch(PasswordNotGoodException e)
            {
                passwordNotGoodException = e;
            }
            catch(SizeNameFirstNameException e)
            {
                sizeNameFirstNameException = e;
            }
            catch(EmptyInputException e)
            {
                inputException = e;
            }
            catch(PasswordDifferentException e)
            {
                passwordDifferentException = e;
            }
            catch(ExistingUserNameException e)
            {
                existingUserNameException = e;
            }
            catch(EmailFalseException e)
            {
                emailException = e;
            }
            catch(PhoneNumberFalseException e)
            {
                phoneException = e;
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
                Toast.makeText(RegistrationClientActivity.this, "Erreur lors de l'enregistrement de l'inscription.Erreur de connexion.", Toast.LENGTH_LONG).show();
                spinner.setVisibility(View.GONE);
            }
            else
            {
                if(inputException != null)
                {
                    Toast.makeText(RegistrationClientActivity.this, inputException.getMessage(), Toast.LENGTH_LONG).show();
                    spinner.setVisibility(View.GONE);
                }
                else
                {
                    if(passwordDifferentException != null)
                    {
                        Toast.makeText(RegistrationClientActivity.this, passwordDifferentException.getMessage(), Toast.LENGTH_LONG).show();
                        spinner.setVisibility(View.GONE);
                    }
                    else
                    {
                        if(existingUserNameException != null)
                        {
                            Toast.makeText(RegistrationClientActivity.this, existingUserNameException.getMessage(), Toast.LENGTH_LONG).show();
                            spinner.setVisibility(View.GONE);
                        }
                        else
                        {
                            if(emailException != null)
                            {
                                Toast.makeText(RegistrationClientActivity.this, emailException.getMessage(), Toast.LENGTH_LONG).show();
                                spinner.setVisibility(View.GONE);
                            }
                            else
                            {
                                if(phoneException !=null)
                                {
                                    Toast.makeText(RegistrationClientActivity.this, phoneException.getMessage(), Toast.LENGTH_LONG).show();
                                    spinner.setVisibility(View.GONE);
                                }
                                else
                                {
                                    if(sizeNameFirstNameException != null)
                                    {
                                        Toast.makeText(RegistrationClientActivity.this, sizeNameFirstNameException.getMessage(), Toast.LENGTH_LONG).show();
                                        spinner.setVisibility(View.GONE);
                                    }
                                    else
                                    {
                                        if(passwordNotGoodException != null)
                                        {
                                            Toast.makeText(RegistrationClientActivity.this, passwordNotGoodException.getMessage(), Toast.LENGTH_LONG).show();
                                            spinner.setVisibility(View.GONE);
                                        }
                                        else
                                        {
                                            if(passwordTooShortException != null)
                                            {
                                                Toast.makeText(RegistrationClientActivity.this, passwordTooShortException.getMessage(), Toast.LENGTH_LONG).show();
                                                spinner.setVisibility(View.GONE);
                                            }
                                            else
                                            {
                                                Intent intentReservation = new Intent(RegistrationClientActivity.this, MainActivity.class);
                                                startActivity(intentReservation);
                                                Toast.makeText(RegistrationClientActivity.this, "L'inscription a bien été effectuée, vous pouvez maintenant vous connecter", Toast.LENGTH_LONG).show();
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

    public boolean isNumber(char number) {
        try
        {
            java.lang.Character.isDigit(number);
        }
        catch (NumberFormatException e)
        {
            return false;
        }

        return true;
    }
}
