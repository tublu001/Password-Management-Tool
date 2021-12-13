package com.epam.model;

import com.epam.passwordOperations.PreferredPassword;
import org.springframework.stereotype.Component;

public class PreferredPasswordObject
{
    private String includeUpperLetters = "NA";
    private String includeLowerLetters = "NA";
    private String includeNumbers = "NA";
    private String includeSymbols = "NA";
    private String preferredPasswordLength = "10";


    public PreferredPassword getPreferredPasswordObject()
    {
        PreferredPassword preferredPassword = new PreferredPassword();
        preferredPassword.setIncludeUpperLetters(this.includeUpperLetters.equals("yes"));
        preferredPassword.setIncludeLowerLetters(this.includeLowerLetters.equals("yes"));
        preferredPassword.setIncludeNumbers(this.includeNumbers.equals("yes"));
        preferredPassword.setIncludeSymbols(this.includeSymbols.equals("yes"));
//        preferredPassword.setIncludeUpperLetters(!this.includeUpperLetters.equals(null));
//        preferredPassword.setIncludeLowerLetters(!this.includeLowerLetters.equals(null));
//        preferredPassword.setIncludeNumbers(!this.includeNumbers.equals(null));
//        preferredPassword.setIncludeSymbols(!this.includeSymbols.equals(null));
        preferredPassword.setPreferredPasswordLength(Integer.parseInt(this.preferredPasswordLength));
        return preferredPassword;
    }

    public String getIncludeUpperLetters()
    {
        return includeUpperLetters;
    }

    public void setIncludeUpperLetters(String includeUpperLetters)
    {
        this.includeUpperLetters = includeUpperLetters;
    }

    public String getIncludeLowerLetters()
    {
        return includeLowerLetters;
    }

    public void setIncludeLowerLetters(String includeLowerLetters)
    {
        this.includeLowerLetters = includeLowerLetters;
    }

    public String getIncludeNumbers()
    {
        return includeNumbers;
    }

    public void setIncludeNumbers(String includeNumbers)
    {
        this.includeNumbers = includeNumbers;
    }

    public String getIncludeSymbols()
    {
        return includeSymbols;
    }

    public void setIncludeSymbols(String includeSymbols)
    {
        this.includeSymbols = includeSymbols;
    }

    public String getPreferredPasswordLength()
    {
        return preferredPasswordLength;
    }

    public void setPreferredPasswordLength(String preferredPasswordLength)
    {
        this.preferredPasswordLength = preferredPasswordLength;
    }

    @Override
    public String toString()
    {
        return "PreferredPasswordObject{" +
                "includeUpperLetters='" + includeUpperLetters + '\'' +
                ", includeLowerLetters='" + includeLowerLetters + '\'' +
                ", includeNumbers='" + includeNumbers + '\'' +
                ", includeSymbols='" + includeSymbols + '\'' +
                ", preferredPasswordLength='" + preferredPasswordLength + '\'' +
                '}';
    }
}
