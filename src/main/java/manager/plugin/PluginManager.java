package manager.plugin;

import java.util.HashSet;

import javax.annotation.Nonnull;

/**
 * A utility to manage plugins.
 * 
 * @author Myles Deslippe.
 */
public final class PluginManager {

	/**
	 * The set of plugins that have been registered with the plugin manager.
	 */
	private HashSet<Plugin> plugins = new HashSet<Plugin>();

	/**
	 * Register a plugin with the plugin manager.
	 * 
	 * @param plugin The plugin to register.
	 */
	public void registerPlugin(@Nonnull final Plugin plugin) {
		this.plugins.add(plugin);
	}

	/**
	 * Unregister a plugin with the plugin manager.
	 * 
	 * @param plugin The plugin to unregister.
	 */
	public void unregisterPlugin(@Nonnull final Plugin plugin) {
		this.plugins.remove(plugin);
	}

	/**
	 * Check if a plugin is registered with the plugin manager.
	 * 
	 * @param plugin The plugin to check for.
	 * @return If the plugin is registered with the plugin manager or not.
	 */
	public boolean containsPlugin(@Nonnull final Plugin plugin) {
		return this.plugins.contains(plugin);
	}

	/**
	 * Get a plugin by it's name.
	 * 
	 * @param name The name of the plugin.
	 * @return The plugin if it is registered, otherwise null.
	 */
	public Plugin getPlugin(String name) {
		for (Plugin plugin : this.plugins)
			if (plugin.pluginData.name().equals(name))
				return plugin;

		return null;
	}

}
