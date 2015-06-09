package org.age.mag.console;

import java.util.HashMap;
import java.util.Map;

/**
 * Container for each command execution.
 *
 */
public class CommandHistory {
    private static Map<Integer, Execution> map = new HashMap<Integer, Execution>();
    private static Integer taskCounter = new Integer(0);

    /**
     * Returns execution described by given taskId
     * 
     * @param taskId
     *            of execution command
     * @return execution information
     */
    public Execution getExecution(Integer taskId) {
        return map.get(taskId);
    }

    /**
     * Saves information about execution to history.
     * 
     * @param execution
     * @return id of saved execution
     */
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
