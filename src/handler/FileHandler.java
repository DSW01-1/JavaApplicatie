package handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileHandler
{
	public static void CreateNewFile(File file)
	{
		try
		{
			Files.createFile(file.toPath());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
