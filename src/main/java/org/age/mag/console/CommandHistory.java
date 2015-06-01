package org.age.mag.console;

import java.util.HashMap;
import java.util.Map;

public class CommandHistory {
	private static Map<Integer, Execution> map = new HashMap<Integer, Execution>();
	private static Integer taskCounter = new Integer(0);

	public Execution getExecution(Integer taskId) {
		return map.get(taskId);
	}

	public Integer save(Execution execution) {
		Integer taskId = generateTaskId();
		map.put(taskId, execution);
		return taskId;
	}

	private Integer generateTaskId() {
		taskCounter++;
		return taskCounter;
	}

}
