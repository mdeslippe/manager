package manager.plugin;

import javax.annotation.Nonnull;

/**
 * A record about plugin data.
 * 
 * @author Myles Deslippe.
 */
public class PluginData {

	/**
	 * The name of the plugin.
	 */
	private final String name;

	/**
	 * The description of the plugin.
	 */
	private final String description;

	/**
	 * The version of the plugin.
	 */
	private final String version;

	/**
	 * The author of the plugin.
	 */
	private final String author;

	/**
	 * Create a plugin data record.
	 * 
	 * @param name        The name of the plugin.
	 * @param description A description of the plugin.
	 * @param version     The version of the plugin.
	 * @param author      The author of the plugin.
	 */
	public PluginData(@Nonnull String name, @Nonnull String description, @Nonnull String version, @Nonnull String author) {
		this.name = name;
		this.description = description;
		this.version = version;
		this.author = author;
	}

	/**
	 * Get the name of the plugin.
	 * 
	 * @return The name of the plugin.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Get the description of the plugin.
	 * 
	 * @return The description of the plugin.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get the version of the plugin.
	 * 
	 * @return The version of the plugin.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Get the author of the plugin.
	 * 
	 * @return The author of the plugin.
	 */
	public String getAuthor() {
		return author;
	}

}
