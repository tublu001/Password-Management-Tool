package com.epam.service.passwordOperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.Embeddable;
import java.util.Scanner;

@Embeddable
public class PreferredPassword extends Generator
{
    private static final Logger LOGGER = LogManager.getLogger(PreferredPassword.class);
    private boolean includeUpperLetters = true;
    private boolean includeLowerLetters = true;
    private boolean includeNumbers = true;
    private boolean includeSymbols = true;
    private int preferredPasswordLength = 10;

    @SuppressWarnings(value = {"all"})
    public void setPreferredPassword()
    {
        String input;
        Scanner scannerInput = new Scanner(System.in);

        LOGGER.info("\nHello, welcome to the Password Generator :) answer"
                + " the following questions by Yes or No \n");


        while (true)
        {
            LOGGER.info("Do you want Lowercase letters \"abcd...\" to be used? ");
            input = scannerInput.nextLine();

            if (input.equals("YES") || input.equals("Yes") || input.equals("yes"))
            {
                setIncludeLowerLetters(true);
            } else
            {
                setIncludeLowerLetters(false);
                if (!(input.equals("NO")) && (!input.equals("No")) && (!input.equals("no")))
                {
                    PasswordRequestError();
                    break;
                }
            }

            LOGGER.info("Do you want Uppercase letters \"ABCD...\" to be used? ");
            input = scannerInput.nextLine();

            if (input.equals("YES") || input.equals("Yes") || input.equals("yes"))
            {
                setIncludeUpperLetters(true);
            } else
            {
                setIncludeUpperLetters(false);
                if (!(input.equals("NO")) && (!input.equals("No")) && (!input.equals("no")))
                {
                    PasswordRequestError();
                    break;
                }
            }

            LOGGER.info("Do you want Numbers \"1234...\" to be used? ");
            input = scannerInput.nextLine();

            if (input.equals("YES") || input.equals("Yes") || input.equals("yes"))
            {
                setIncludeNumbers(true);
            } else
            {
                setIncludeNumbers(false);
                if ((input.equals("NO") == false) && (input.equals("No") == false) && (input.equals("no") == false))
                {
                    PasswordRequestError();
                    break;
                }
            }

            LOGGER.info("Do you want Symbols \"!@#$...\" to be used? ");
            input = scannerInput.nextLine();

            if (input.equals("YES") || input.equals("Yes") || input.equals("yes"))
            {
                setIncludeSymbols(true);
            } else
            {
                setIncludeSymbols(false);
                if ((input.equals("NO") == false) && (input.equals("No") == false) && (input.equals("no") == false))
                {
                    PasswordRequestError();
                    break;
                }
            }

            if (!includeUpperLetters && !includeLowerLetters && !includeNumbers && !includeSymbols)
            {
                LOGGER.info("You have selected no characters to generate your password at least one of your answers should be Yes");
                break;
            }

            LOGGER.info("Great! Now enter the length of the password");
            int val = scannerInput.nextInt();
            setPreferredPasswordLength(val);
            scannerInput.nextLine();
            break;
        }

    }

    public void setPreferredPasswordByInstance(PreferredPassword preferredPasswordObject)
    {
        this.includeUpperLetters = preferredPasswordObject.isIncludeUpperLetters();
        this.includeLowerLetters = preferredPasswordObject.isIncludeLowerLetters();
        this.includeNumbers = preferredPasswordObject.isIncludeNumbers();
        this.includeSymbols = preferredPasswordObject.isIncludeSymbols();
        this.preferredPasswordLength = preferredPasswordObject.getPreferredPasswordLength();
    }


    @Override
    public String toString()
    {
        return "PreferredPassword [IncludeUpper=" + includeUpperLetters + ", IncludeLower=" + includeLowerLetters + ", IncludeNum="
                + includeNumbers + ", IncludeSym=" + includeSymbols + ", length=" + preferredPasswordLength + "]";
    }


    public boolean isIncludeUpperLetters()
    {
        return includeUpperLetters;
    }


    public void setIncludeUpperLetters(boolean includeUpperLetters)
    {
        this.includeUpperLetters = includeUpperLetters;
    }


    public boolean isIncludeLowerLetters()
    {
        return includeLowerLetters;
    }


    public void setIncludeLowerLetters(boolean includeLowerLetters)
    {
        this.includeLowerLetters = includeLowerLetters;
    }


    public boolean isIncludeNumbers()
    {
        return includeNumbers;
    }


    public void setIncludeNumbers(boolean includeNumbers)
    {
        this.includeNumbers = includeNumbers;
    }


    public boolean isIncludeSymbols()
    {
        return includeSymbols;
    }


    public void setIncludeSymbols(boolean includeSymbols)
    {
        this.includeSymbols = includeSymbols;
    }


    public int getPreferredPasswordLength()
    {
        return preferredPasswordLength;
    }


    public void setPreferredPasswordLength(int preferredPasswordLength)
    {
        this.preferredPasswordLength = preferredPasswordLength;
    }


    public static void PasswordRequestError()
    {
        LOGGER.info("You have entered something incorrect let's go over it again \n");
    }

}
