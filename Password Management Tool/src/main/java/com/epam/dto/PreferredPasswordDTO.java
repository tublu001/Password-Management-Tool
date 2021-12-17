package com.epam.dto;

import com.epam.service.passwordOperations.PreferredPassword;
import lombok.Data;

public @Data
class PreferredPasswordDTO
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
        preferredPassword.setPreferredPasswordLength(Integer.parseInt(this.preferredPasswordLength));
        return preferredPassword;
    }

}
