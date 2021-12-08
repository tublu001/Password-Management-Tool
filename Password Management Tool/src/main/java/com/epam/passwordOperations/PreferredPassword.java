package com.epam.passwordOperations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.Embeddable;
import java.util.Scanner;

@Embeddable
public class PreferredPassword extends Generator
{
    private static final Logger LOGGER = LogManager.getLogger(PreferredPassword.class);
    private boolean includeUpper = true;
    private boolean includeLower = true;
    private boolean includeNum = true;
    private boolean includeSym = true;
    private int length = 10;


    //	@Override
    @SuppressWarnings(value = {"all"})
    public void setPrefferdPassword()
    {
        String input;
        Scanner in = new Scanner(System.in);

        LOGGER.info("\nHello, welcome to the Password Generator :) answer"
                + " the following questions by Yes or No \n");


        while (true)
        {
            LOGGER.info("Do you want Lowercase letters \"abcd...\" to be used? ");
            input = in.nextLine();

            if (input.equals("YES") || input.equals("Yes") || input.equals("yes"))
            {
                setIncludeLower(true);
            } else
            {
                setIncludeLower(false);
                if (!(input.equals("NO")) && (!input.equals("No")) && (!input.equals("no")))
                {
                    PasswordRequestError();
                    break;
                }
            }

            LOGGER.info("Do you want Uppercase letters \"ABCD...\" to be used? ");
            input = in.nextLine();

            if (input.equals("YES") || input.equals("Yes") || input.equals("yes"))
            {
                setIncludeUpper(true);
            } else
            {
                setIncludeUpper(false);
                if (!(input.equals("NO")) && (!input.equals("No")) && (!input.equals("no")))
                {
                    PasswordRequestError();
                    break;
                }
            }

            LOGGER.info("Do you want Numbers \"1234...\" to be used? ");
            input = in.nextLine();

            if (input.equals("YES") || input.equals("Yes") || input.equals("yes"))
            {
                setIncludeNum(true);
            } else
            {
                setIncludeNum(false);
                if ((input.equals("NO") == false) && (input.equals("No") == false) && (input.equals("no") == false))
                {
                    PasswordRequestError();
                    break;
                }
            }

            LOGGER.info("Do you want Symbols \"!@#$...\" to be used? ");
            input = in.nextLine();

            if (input.equals("YES") || input.equals("Yes") || input.equals("yes"))
            {
                setIncludeSym(true);
            } else
            {
                setIncludeSym(false);
                if ((input.equals("NO") == false) && (input.equals("No") == false) && (input.equals("no") == false))
                {
                    PasswordRequestError();
                    break;
                }
            }

            //No Pool Selected
            if (!includeUpper && !includeLower && !includeNum && !includeSym)
            {
                LOGGER.info("You have selected no characters to generate your password at least one of your answers should be Yes");
                break;
            }

            LOGGER.info("Great! Now enter the length of the password");
            int val = in.nextInt();
            setLength(val);
            in.nextLine();
            break;
        }

    }


    @Override
    public String toString()
    {
        return "PreferredPassword [IncludeUpper=" + includeUpper + ", IncludeLower=" + includeLower + ", IncludeNum="
                + includeNum + ", IncludeSym=" + includeSym + ", length=" + length + "]";
    }


    public boolean isIncludeUpper()
    {
        return includeUpper;
    }


    public void setIncludeUpper(boolean includeUpper)
    {
        this.includeUpper = includeUpper;
    }


    public boolean isIncludeLower()
    {
        return includeLower;
    }


    public void setIncludeLower(boolean includeLower)
    {
        this.includeLower = includeLower;
    }


    public boolean isIncludeNum()
    {
        return includeNum;
    }


    public void setIncludeNum(boolean includeNum)
    {
        this.includeNum = includeNum;
    }


    public boolean isIncludeSym()
    {
        return includeSym;
    }


    public void setIncludeSym(boolean includeSym)
    {
        this.includeSym = includeSym;
    }


    public int getLength()
    {
        return length;
    }


    public void setLength(int length)
    {
        this.length = length;
    }


    public static void PasswordRequestError()
    {
        LOGGER.info("You have entered something incorrect let's go over it again \n");
    }

}
