package com.epam.passwordOperations;

import java.util.Scanner;

public class PreferredPassword extends Generator
{

	private boolean IncludeUpper=true;
	private boolean IncludeLower=true;
	private boolean IncludeNum=true;
	private boolean IncludeSym=true;
	private int length = 10;
	

//	public PreferredPassword() 
//	{}
	
//	@Override
	@SuppressWarnings(value = {"all"})
	public void setPrefferdPassword()
	{
		String Input;
		Scanner in = new Scanner(System.in);
		
		System.out.println();
		System.out.println("Hello, welcome to the Password Generator :) answer"
				+" the following questions by Yes or No \n");
		
		
		while(true) {
			System.out.println("Do you want Lowercase letters \"abcd...\" to be used? ");
	        Input = in.nextLine();

	        if (Input.equals("YES") || Input.equals("Yes") || Input.equals("yes")) {
	        	setIncludeLower(true);
	        }
	        
	        else {setIncludeLower(false);
	        	if (!(Input.equals("NO")) && (!Input.equals("No")) && (!Input.equals("no"))) {
	        		PasswordRequestError();
	        		break;
	        	}
	        }
	      
			System.out.println("Do you want Uppercase letters \"ABCD...\" to be used? ");
			Input = in.nextLine();

			if (Input.equals("YES") || Input.equals("Yes") || Input.equals("yes")) {
				setIncludeUpper(true);
	        }
	        
			else {setIncludeUpper(false);
	        	if (!(Input.equals("NO")) && (!Input.equals("No")) && (!Input.equals("no"))) {
	        		PasswordRequestError();
	        		break;
	        	}
	        }
			
			System.out.println("Do you want Numbers \"1234...\" to be used? ");
			Input = in.nextLine();

			if (Input.equals("YES") || Input.equals("Yes") || Input.equals("yes")) {
				setIncludeNum(true);
	        }
	        
			else {setIncludeNum(false);
	        	if ((Input.equals("NO")==false) && (Input.equals("No")==false) && (Input.equals("no")==false)) {
	        		PasswordRequestError();
	        		break;
	        	}
	        }
			
			System.out.println("Do you want Symbols \"!@#$...\" to be used? ");
			Input = in.nextLine();

			if (Input.equals("YES") || Input.equals("Yes") || Input.equals("yes")) {
				setIncludeSym(true);
	        }
	        
			else {setIncludeSym(false);
	        	if ((Input.equals("NO")==false) && (Input.equals("No")==false) && (Input.equals("no")==false)) {
	        		PasswordRequestError();
	        		break;
	        	}
	        }
			
			//No Pool Selected
			if(!IncludeUpper && !IncludeLower && !IncludeNum && !IncludeSym) {
				System.out.println("You have selected no characters to generate your password at least one of your answers should be Yes");
				break;
			}
			
	        System.out.println("Great! Now enter the length of the password");
	        int val = in.nextInt();
	        setLength(val);
	        in.nextLine();
	        
//	        generator = new Generator (IncludeUpper, IncludeLower, IncludeNum, IncludeSym);
	        
//	        in.close();
	        break;
		}
		
	}
	
	
	
	@Override
	public String toString() {
		return "PreferredPassword [IncludeUpper=" + IncludeUpper + ", IncludeLower=" + IncludeLower + ", IncludeNum="
				+ IncludeNum + ", IncludeSym=" + IncludeSym + ", length=" + length + "]";
	}



	public boolean isIncludeUpper() {
		return IncludeUpper;
	}



	public void setIncludeUpper(boolean includeUpper) {
		this.IncludeUpper = includeUpper;
	}



	public boolean isIncludeLower() {
		return IncludeLower;
	}



	public void setIncludeLower(boolean includeLower) {
		this.IncludeLower = includeLower;
	}



	public boolean isIncludeNum() {
		return IncludeNum;
	}



	public void setIncludeNum(boolean includeNum) {
		this.IncludeNum = includeNum;
	}



	public boolean isIncludeSym() {
		return IncludeSym;
	}



	public void setIncludeSym(boolean includeSym) {
		this.IncludeSym = includeSym;
	}



	public int getLength() {
		return length;
	}



	public void setLength(int length) {
		this.length = length;
	}



	public static void PasswordRequestError() {
		System.out.println("You have entered something incorrect let's go over it again \n");
	}

}
