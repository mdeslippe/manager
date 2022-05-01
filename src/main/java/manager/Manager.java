package manager;

import java.util.HashSet;
import java.util.logging.Logger;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

import manager.plugin.PluginManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

/**
 * The main class.
 * 
 * @author Myles Deslippe.
 */
public class Manager {

	/**
	 * The Manager bot's singleton instance.
	 */
	private static Manager manager;

	/**
	 * The bot's logger.
	 */
	private final Logger logger = Logger.getLogger(Manager.class.getName());

	/**
	 * The builder that is used to create the instances.
	 */
	private final JDABuilder builder;

	/**
	 * The bot's instances.
	 */
	private final HashSet<JDA> instances = new HashSet<JDA>();

	/**
	 * The bot's plugin manager.
	 */
	private final PluginManager pluginManager = new PluginManager();

	/**
	 * The entry point of the program.
	 * 
	 * @param args Arguments passed in through the command-line. The first argument
	 *             should be the bot's token, if not supplied the program will
	 *             terminate. The second argument is optional and can be used to
	 *             specify the amount of shards.
	 * 
	 * @throws InterruptedException  If the thread is inturrupted while attempting
	 *                               to authenticate the bot.
	 * @throws LoginException        If an error occured while attempting to
	 *                               authenticate the bot.
	 * @throws NumberFormatException This error will not occur.
	 */
	public static void main(String[] args) throws NumberFormatException, LoginException, InterruptedException {

		if (args.length == 0) {
			System.out.println("Error: You must specify the bot's token!");
			System.exit(1);
		}

		if (args.length > 1 && (!args[1].matches("[0-9]+") || args[1] == "0")) {
			System.out.println("Error: You have specified an invalid amount of shards!");
			System.exit(1);
		}

		Manager.manager = new Manager(args[0]);

		if (args.length == 1) {
			manager.start();
		} else {
			manager.start(Integer.valueOf(args[1]));
		}

	}

	/**
	 * The the Manager's singleton instance.
	 * 
	 * @return The Manager's instance.
	 */
	public static Manager getSingleton() {
		return Manager.manager;
	}

	/**
	 * Create a basic Manager without sharding.
	 * 
	 * <p>
	 * To start the bot you must use {@link #start()}.
	 * </p>
	 * 
	 * @param token The <a href=
	 *              "https://discord.com/developers/docs/topics/oauth2">bot's
	 *              token.</a>
	 */
	private Manager(@Nonnull final String token) {
		this.getLogger().info("Creating the bot.");
		this.builder = JDABuilder.createDefault(token);
		this.configureMemoryUsage(this.builder);
	}

	/**
	 * Get the bot's logger.
	 * 
	 * @return The bot's logger.
	 */
	public Logger getLogger() {
		return logger;
	}

	/**
	 * Configure a builder's memory usage.
	 * 
	 * @param builder The builder to configure.
	 */
	private void configureMemoryUsage(@Nonnull final JDABuilder builder) {
		this.getLogger().info("Configuring the bot's memory usage.");
		builder.disableCache(CacheFlag.ACTIVITY, CacheFlag.MEMBER_OVERRIDES);
		builder.setChunkingFilter(ChunkingFilter.NONE);
		builder.disableIntents(GatewayIntent.DIRECT_MESSAGE_TYPING);
		builder.setLargeThreshold(100);
	}

	/**
	 * Start the bot.
	 * 
	 * <p>
	 * This method will block until the connection is ready.
	 * </p>
	 * 
	 * <p>
	 * This method will not use sharding, to use sharding see {{@link #start(int)}.
	 * <p>
	 * 
	 * @throws LoginException       If there was an error logging into the bot user.
	 * @throws InterruptedException If the thread is inturrupted while
	 *                              authenticating the bot.
	 */
	public void start() throws LoginException, InterruptedException {
		this.getLogger().info("Starting the bot.");
		this.addInstance(this.builder.build().awaitReady());
	}

	/**
	 * Start the bot with sharding.
	 * 
	 * <p>
	 * This method will block until all of the shards have been started.
	 * </p>
	 * 
	 * @param numberOfShards The number of shards to create.
	 * 
	 * @throws LoginException       If there was an error logging into the bot user.
	 * @throws InterruptedException If the thread is inturrupted while starting the
	 *                              shards.
	 */
	public void start(@Nonnull final int numberOfShards) throws LoginException, InterruptedException {
		for (int i = 0; i < numberOfShards; i++) {
			this.getLogger().info("Starting shard " + (i + 1) + " of " + numberOfShards + ".");
			this.addInstance(this.builder.useSharding(i, numberOfShards).build().awaitReady());
		}
	}

	/**
	 * Add a JDA instance to the instance set.
	 * 
	 * @param instance The instance to add.
	 */
	private void addInstance(@Nonnull final JDA instance) {
		this.instances.add(instance);
	}

	/**
	 * Remove a JDA instance from the instance set.
	 * 
	 * @param instance The instance to remove.
	 */
	private void removeInstance(@Nonnull final JDA instance) {
		this.instances.remove(instance);
	}

	/**
	 * Check if an instance is in the instance set.
	 * 
	 * @param instance The instance to check for.
	 * @return If the instance is contained in the set or not.
	 */
	private boolean containsInstance(@Nonnull final JDA instance) {
		return this.instances.contains(instance);
	}

	/**
	 * Get the bot's plugin manager.
	 * 
	 * @return The bot's plugin manager.
	 */
	public PluginManager getPluginManager() {
		return pluginManager;
	}

}
