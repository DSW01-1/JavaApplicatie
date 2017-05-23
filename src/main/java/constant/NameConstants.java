package main.java.constant;

import java.util.Random;

public class NameConstants
{
	private static String[] productNameArray =
	{ "ToothBrush", "Pepsi", "Cola", "Car", "Plane", "Boat", "Website", "Box" };

	private static String[] titleNameArray =
	{ "You underestimate the power of COFFEE!",
			"Why i wear glasses? Well, because I don't C#.",
			"Java: turning coffee into code.",
			"What's a programmer's favorite bar? The Foo Bar.",
			"[\"hip\",\"hip\"]",
			"Lubarsky’s Law of Cybernetic Entomology: There’s always one more bug.",
			"The worst part of \"Go to hell\" is the \"go to\".",
			"Algorithm: a word used when programmers don't want to explain what they are doing.",
			"Have you tried turning it off and on again?",
			"Semicolon: hide and seek champion since 1958",
			"If you put a Unix shell to your ear, will you hear the C?",
			"Why don't programmers like nature? There's too many bugs.",
			"What do Communism and C have in common? There's no classes.",
			"System.gc : Java's very own Men In Black.",
			"How many bits of bait need to be on a fishing hook? 8, or else the fish won't byte.",
			"To understand what recursion is, you must first understand recursion.",
			"Error 404: Code not found.",
			"= != ==",
			"while(!asleep()) sheep++",
			"If Java is the answer, it must have been a really verbose question.",
			"In theory, there ought to be no difference between theory and practice. In practice, there is.",

	};

	public static String GetRandomProductName()
	{
		Random rand = new Random();
		return productNameArray[rand.nextInt(productNameArray.length)];
	}

	public static String GetRandomTitle()
	{
		Random rand = new Random();
		return titleNameArray[rand.nextInt(titleNameArray.length)];
	}

}
