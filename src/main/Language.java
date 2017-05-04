package main;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language
{

	public static String getTranslation(String word)
	{
		Locale currentLocale = new Locale(Config.currentLanguage, Config.currentCountry);
		ResourceBundle translations = ResourceBundle.getBundle("localization/LanguagesBundle", currentLocale);
		return translations.getString(word);
	}

}
