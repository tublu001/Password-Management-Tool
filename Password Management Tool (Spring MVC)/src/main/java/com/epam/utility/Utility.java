package com.epam.utility;

import org.springframework.stereotype.Component;

@Component
public class Utility
{
    public boolean isValidString(String input)
    {
        boolean isValid = !(input == null) && !input.equals("");
        return isValid;
    }
}