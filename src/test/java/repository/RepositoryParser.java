package repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RepositoryParser {

	private static final Logger log = LoggerFactory.getLogger(RepositoryParser.class);

	private String repositoryFile;
	private Properties propertyFile = new Properties();

	public RepositoryParser(String repositoryFile) {
		this.repositoryFile = repositoryFile;
		repositoryInit();
	}

	private void repositoryInit(){
		try(InputStream fileInputStream = getClass().getClassLoader().getResourceAsStream(repositoryFile);) {
			propertyFile.load(fileInputStream);
		} catch (IOException e) {
			log.debug("Unable to load: {}", repositoryFile);
		}
	}

	public String getValue(String key) {
		return (String) propertyFile.get(key);
	}

}
