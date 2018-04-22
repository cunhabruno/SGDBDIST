package databaseMgmt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ConfigFile {
	private String filePath;
	private int port;
	private String dataFile;
	private String studentServerHost;
	private int studentServerPort;
	private String classServerHost;
	private int classServerPort;
	private String managerServerHost;
	private int managerServerPort;
	private long cacheTimeout;

	public ConfigFile() {

	}

	public ConfigFile(String filePath) {
		this.filePath = filePath;
	}

	public int getPort() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		String fileContent = this.getFileContent(new File(this.filePath));

		ConfigFile configFile = new ConfigFile();
		configFile = gson.fromJson(fileContent, this.getClass());
		return configFile.port;
	}

	public String getStudentServerHost() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		String fileContent = this.getFileContent(new File(this.filePath));

		ConfigFile configFile = new ConfigFile();
		configFile = gson.fromJson(fileContent, this.getClass());
		return configFile.studentServerHost;
	}

	public int getStudentServerPort() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		String fileContent = this.getFileContent(new File(this.filePath));

		ConfigFile configFile = new ConfigFile();
		configFile = gson.fromJson(fileContent, this.getClass());
		return configFile.studentServerPort;
	}

	public String getClassServerHost() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		String fileContent = this.getFileContent(new File(this.filePath));

		ConfigFile configFile = new ConfigFile();
		configFile = gson.fromJson(fileContent, this.getClass());
		return configFile.classServerHost;
	}

	public int getClassServerPort() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		String fileContent = this.getFileContent(new File(this.filePath));

		ConfigFile configFile = new ConfigFile();
		configFile = gson.fromJson(fileContent, this.getClass());
		return configFile.classServerPort;
	}

	public String getManagerServerHost() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		String fileContent = this.getFileContent(new File(this.filePath));

		ConfigFile configFile = new ConfigFile();
		configFile = gson.fromJson(fileContent, this.getClass());
		return configFile.managerServerHost;
	}

	public int getManagerServerPort() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		String fileContent = this.getFileContent(new File(this.filePath));

		ConfigFile configFile = new ConfigFile();
		configFile = gson.fromJson(fileContent, this.getClass());
		return configFile.managerServerPort;
	}

	public long getCacheTimeOut() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		String fileContent = this.getFileContent(new File(this.filePath));

		ConfigFile configFile = new ConfigFile();
		configFile = gson.fromJson(fileContent, this.getClass());
		return configFile.cacheTimeout;
	}

	public String getDataFile() {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.setPrettyPrinting().create();
		String fileContent = this.getFileContent(new File(this.filePath));

		ConfigFile configFile = new ConfigFile();
		configFile = gson.fromJson(fileContent, this.getClass());
		return configFile.dataFile;
	}

	private String getFileContent(File aFile) {
		StringBuilder contents = new StringBuilder();

		try {
			BufferedReader input = new BufferedReader(new FileReader(aFile));
			try {
				String line = null;
				while ((line = input.readLine()) != null) {
					contents.append(line);
					contents.append(System.getProperty("line.separator"));
				}
			} finally {
				input.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return contents.toString();
	}
}
