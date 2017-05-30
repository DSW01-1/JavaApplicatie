package main.java.main;

import java.util.Locale;
import java.util.ResourceBundle;

import main.java.constant.Constants;

public class Language
{
	public static String currentLanguage = "nl";
	public static String currentCountry = "NL";

	public static String getTranslation(String word)
	{
		Locale currentLocale = new Locale(currentLanguage, currentCountry);
		ResourceBundle translations = ResourceBundle.getBundle(Constants.localizationLocation + "LanguagesBundle",
				currentLocale);
		return translations.getString(word);
	}

	public static void ChangeLanguage(String languageName)
	{
		switch (languageName)
		{
		case "Nederlands":
			currentCountry = "NL";
			currentLanguage = "nl";
			break;
		case "English":
			currentCountry = "GB";
			currentLanguage = "en";
			break;
		default:
			break;
		}
	}

	public static String GetCurrentLanguage()
	{
		switch (currentLanguage)
		{
		case "nl":
			return "Nederlands";
		case "en":
			return "English";
		default:
			return "Nederlands";
		}
	}
}
