package org.age.mag.console;

/**
 * Command instance which is passed to console for commend execution
 *
 */
public final class CommandInstance {

    private String name;
    private String operation = "";
    private String option = "";
    private String optionValue = "";

    /**
     * Method for converting command to supported by JCommander form.
     * 
     * @return command with operation and option if specified
     * @throws NullCommandException
     *             if name is null
     */
    public String getFullCommand() throws NullCommandException {
        if (name == null) {
            throw new NullCommandException();
        }
        option = optionValue.equals("") ? "" : option;
        return name + " " + operation + " " + option + " " + optionValue;
    }

    public String getOperation() {
        return operation;
    }

    public String getOption() {
        return option;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOptionValue() {
        return optionValue;
    }

    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

}
