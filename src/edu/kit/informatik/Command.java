package edu.kit.informatik;

public class Command {

    private Connect6Commands command;
    private String[] parameters;

    public Command(Connect6Commands command, String[] parameters) {
        this.command = command;
        this.parameters = parameters;
    }
}
