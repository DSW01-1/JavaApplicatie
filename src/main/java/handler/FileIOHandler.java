package main.java.handler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileIOHandler
{
	public static void CreateNewFile(File file)
	{
		try
		{
			Files.createFile(file.toPath());
		}
		catch (IOException e)
		{
			//TODO
			e.getStackTrace();
		}
	}
}
