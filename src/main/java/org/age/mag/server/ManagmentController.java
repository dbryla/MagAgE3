package org.age.mag.server;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;

import org.age.mag.console.CommandHistory;
import org.age.mag.console.CommandInstance;
import org.age.mag.console.ConsoleService;
import org.age.mag.console.Execution;
import org.age.mag.console.OutputWriter;
import org.age.mag.hazelcast.ClusterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ManagmentController {

	private final Logger log = LoggerFactory
			.getLogger(ManagmentController.class);
	private ClusterService clusterService = new ClusterService();
	private ConsoleService consoleService = new ConsoleService();
	private CommandHistory commandHistory = new CommandHistory();
	private Guard guard = new Guard();

	@RequestMapping("/")
	public String getHome(Model model) {
		if (guard.isConnected()) {
			addMainAttributes(model);
			model.addAttribute("cmd", new CommandInstance());
		} else {
			model.addAttribute("error", "Start cluster before using client.");
		}
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String command(@ModelAttribute("cmd") CommandInstance command,
			Model model) {
		if (guard.isConnected()) {
			addMainAttributes(model);
			model.addAttribute("cmd", command);
			model.addAttribute("operations", getOperations(command));
			model.addAttribute("options", getOptions(command));

		} else {
			model.addAttribute("error", "Start cluster before using client.");
		}
		return "index";
	}

	@RequestMapping(value = "/execution", method = RequestMethod.POST)
	public String execute(Model model,
			@ModelAttribute("cmd") CommandInstance command) {
		if (guard.isConnected()) {
			OutputWriter outputWriter;
			try {
				outputWriter = new OutputWriter();
				consoleService.executeCommand(command, outputWriter);
				Execution execution = new Execution(command, outputWriter);
				Integer taskId = commandHistory.save(execution);
				log.debug("Redirecting to /execute/" + taskId.toString());

				return "redirect:/execution/" + taskId;
			} catch (FileNotFoundException e) {
				log.error(e.getMessage());
			}
		} else {
			model.addAttribute("error", "Start cluster before using client.");
		}

		return "index";

	}

	@RequestMapping(value = "/execution/{taskId}")
	public String executeTask(Model model, @PathVariable("taskId") String taskId) {
		if (guard.isConnected()) {
			log.debug("Task id: " + taskId);
			Execution execution = commandHistory.getExecution(new Integer(
					taskId));
			model.addAttribute("execution", execution);
			return "execute";
		}else{
			model.addAttribute("error", "Start cluster before using client.");
			return "index";
		}	
			
		
	}

	private List<String> getOptions(CommandInstance command) {
		try {
			return consoleService.getCommandList().stream()
					.filter(x -> x.getCmdName().equals(command.getName()))
					.findAny().get().getOptions();
		} catch (NoSuchElementException e) {
			log.info("Command {} doesn't have any option.", command.getName());
			return null;
		}
	}

	private List<String> getOperations(CommandInstance command) {
		try {
			return consoleService.getCommandList().stream()
					.filter(x -> x.getCmdName().equals(command.getName()))
					.findAny().get().getOperations();
		} catch (NoSuchElementException e) {
			log.info("Command {} doesn't have any operation.",
					command.getName());
			return null;
		}

	}

	private void addMainAttributes(Model model) {
		model.addAttribute("cluster", clusterService.getCluster());
		model.addAttribute("nodes", clusterService.getNodes());
		model.addAttribute("commands", consoleService.getCommandList());
	}

}