// ============================================================
// Exercise 9: Command Pattern
// File: Ex9_Command_HomeAutomation.java
// ============================================================

interface Command {
    void execute();
}

class Light {
    public void turnOn()  { System.out.println("Light is ON."); }
    public void turnOff() { System.out.println("Light is OFF."); }
}

class LightOnCommand implements Command {
    private Light light;
    LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.turnOn(); }
}

class LightOffCommand implements Command {
    private Light light;
    LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.turnOff(); }
}

class RemoteControl {
    private Command command;
    public void setCommand(Command command) { this.command = command; }
    public void pressButton()              { command.execute(); }
}

public class Ex9_Command_HomeAutomation {
    public static void main(String[] args) {
        Light light = new Light();
        RemoteControl remote = new RemoteControl();

        remote.setCommand(new LightOnCommand(light));
        remote.pressButton();

        remote.setCommand(new LightOffCommand(light));
        remote.pressButton();
    }
}
