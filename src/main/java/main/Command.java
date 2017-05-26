package main.java.main;

public class Command
{
	private String command, extraInfo;

	public Command(String command, String extraInfo)
	{
		this.command = command;
		this.extraInfo = extraInfo;
	}

	public Command(String command)
	{
		this(command, "");
	}

	public String getCommand()
	{
		return command;
	}

	public String getExtraInfo()
	{
		return extraInfo;
	}

	public static Command SortCommand(String cmd)
	{
		String exI = "";
		cmd = cmd.substring(cmd.indexOf("<") + 1, cmd.indexOf(">"));

		if (cmd.contains("[") && cmd.contains("]"))
		{
			exI = cmd.substring(cmd.indexOf("[") + 1, cmd.indexOf("]"));
			cmd = cmd.split("\\[")[0];
		}
		return new Command(cmd, exI);
	}
}
