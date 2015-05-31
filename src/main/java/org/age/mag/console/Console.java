package org.age.mag.console;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.regex.Pattern;

import org.age.console.CommandIntrospector;
import org.age.console.command.BaseCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.beust.jcommander.JCommander;
import com.google.common.collect.Iterables;

public final class Console {
	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final Pattern WHITESPACE = Pattern.compile("\\s");

	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
			"spring-context.xml");
	private static CommandIntrospector introspector = applicationContext
			.getBean(CommandIntrospector.class);

	public Collection<Command> listOfAvailableCommands() {
		final JCommander commander = new JCommander();
		final HashMap<String, Command> commands = new HashMap<String, Command>();
		applicationContext.getBeansOfType(BaseCommand.class).values().stream()
				.filter(cmd -> (cmd instanceof BaseCommand))
				.forEach(commander::addCommand);
		Set<String> commandNames = commander.getCommands().keySet();
		commandNames.forEach(cmd -> commands.put(cmd, new Command(cmd)));
		commandNames.forEach(cmd -> {
			Command command = commands.get(cmd);
			introspector.parametersOfCommand(cmd)
					.forEach(command::addParameter);
		});
		return Collections.unmodifiableCollection(commands.values());
	}

	public void executeCommand(CommandInstance commandInstance,
			OutputWriter outputWriter) throws IOException {
		try {
			log.info("Executing {}", commandInstance.getFullCommand());
			final JCommander mainCommander = new JCommander();
			final Collection<BaseCommand> commands = applicationContext
					.getBeansOfType(BaseCommand.class).values();
			commands.forEach(mainCommander::addCommand);
			mainCommander.parse(WHITESPACE.split(commandInstance
					.getFullCommand()));
			final String parsedCommand = mainCommander.getParsedCommand();

			final JCommander commander = mainCommander.getCommands().get(
					parsedCommand);
			final BaseCommand command = (BaseCommand) Iterables
					.getOnlyElement(commander.getObjects());

			command.execute(commander, null, outputWriter);
		} catch (NullCommandException e) {
			log.error(e.getClass().getSimpleName() + " " + e.getMessage());
		}

	}

}
