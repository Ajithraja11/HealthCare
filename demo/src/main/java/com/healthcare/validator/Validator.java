package com.healthcare.validator;

import java.util.Date;
import java.util.regex.Pattern;

public class Validator {

	public boolean checkName(String name)
	{
		if(name==null || name.trim().length()==0)
		{
			return false;
		}
		return true;
	}
		
	public boolean checkBirthDate(Date date)
	{	
		if(date==null)
		{
			return false;
		}
		return true;
	}
	public boolean checkPhoneNumber(String number)
	{
		if(Pattern.matches(".*[a-zA-Z&%^$@!].*",number))
		{	
			return false;
		}
		return true;
	}				
	
}
