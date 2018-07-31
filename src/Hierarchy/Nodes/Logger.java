package Hierarchy.Nodes;

public interface Logger {
	public void Info(String Message);
	
	public void Debug(String Message);
	
	public void Warning(String Message);
	
	public void Error(String Message);
	
	public void CustomLevelMessage(String Level,String Message);
}
