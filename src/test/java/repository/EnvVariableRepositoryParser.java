package repository;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * The Variable set on environment always have priority above the environment properties
 */
public class EnvVariableRepositoryParser extends RepositoryParser {

	private static EnvVariableRepositoryParser instance = null;

	EnvVariableRepositoryParser(String repositoryFile) {
		super(repositoryFile);
	}


	public static EnvVariableRepositoryParser getInstance() {
		if (instance == null) {
			Map<String, String> getenv = System.getenv();
			String fromEnvironment = getenv.get("selenium.env");
			String environment = StringUtils.defaultIfBlank(fromEnvironment, "qa");
			instance = new EnvVariableRepositoryParser("env/"+ environment + ".properties");
		}

		return instance;
	}

	@Override
	public String getValue(String key) {
		Map<String, String> getenv = System.getenv();
		String fromEnvironment = getenv.get(key);
		String fromProperties = StringUtils.defaultIfBlank(fromEnvironment, super.getValue(key));
		return StringUtils.defaultIfBlank(fromProperties, key);
	}

}
