package manager.plugin;

/**
 * An abstract plugin class, extend this class in plugin applications.
 * 
 * @author Myles Deslippe.
 */
public abstract class Plugin {

	/**
	 * Plugin data that will be injected at runtime.
	 */
	protected PluginData pluginData;

	/**
	 * This method will be invoked when the plugin is enabled.
	 */
	public abstract void onEnable();

	/**
	 * This method will be invoked when the plugin is disabled.
	 */
	public abstract void onDisable();

}
