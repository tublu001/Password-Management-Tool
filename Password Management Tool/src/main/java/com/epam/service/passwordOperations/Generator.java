package com.epam.service.passwordOperations;


public class Generator
{
    public String generatePassword(PreferredPassword preferredPassword)
    {
        boolean includeUpperLetters = preferredPassword.isIncludeUpperLetters();
        boolean includeLowerLetters = preferredPassword.isIncludeLowerLetters();
        boolean includeNumbers = preferredPassword.isIncludeNumbers();
        boolean includeSymbols = preferredPassword.isIncludeSymbols();
        int preferredPasswordLength = preferredPassword.getPreferredPasswordLength();
        Alphabet alphabet;

        alphabet = new Alphabet(includeUpperLetters, includeLowerLetters, includeNumbers, includeSymbols);

        final StringBuilder pass = new StringBuilder();

        final int alphabetLength = alphabet.getAlphabet().length();

        int max = alphabetLength - 1;
        int min = 0;
        int range = max - min + 1;

        for (int i = 0; i < preferredPasswordLength; i++)
        {

            int index = (int) (Math.random() * range) + min;

            pass.append(alphabet.getAlphabet().charAt(index));
        }

        return pass.toString();

    }
}
