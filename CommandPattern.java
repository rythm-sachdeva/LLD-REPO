import java.util.Stack;


class CommandPattern{

public static void main(String[] args){

Thermostat thermostat= new Thermostat();
Light light = new Light();

Command LightOn = new LightOnCommand(light);
Command LightOff = new LightOffCommand(light);
Command Settemprature = new SetTempratureCommand(thermostat,22);

SmartButton button = new SmartButton();


System.out.println("-> Turning Light on");
button.setCommand(LightOn);
button.press();

System.out.println("-> Turning Light off");
button.setCommand(LightOff);
button.press();

System.out.println("-> Changing Thermostat temp");
button.setCommand(Settemprature);
button.press();

System.out.println("-> Undoing last  on");
button.undoLast();

}


}



interface Command{
void execute();
void undo();
}

class Light{
public void on(){
System.out.println("Lights Turned On");
}

public void off(){
System.out.println("Lights Turned off");
}

}

class Thermostat{

private int currentTemp = 20;

public void setTemprature(int temp){
System.out.println("System Temprature set to " + temp );
currentTemp = temp;
}

public int getTemprature(){
return currentTemp;
}

}


class LightOnCommand implements Command{
private final Light light;

public LightOnCommand(Light light){
this.light = light;
}

@Override
public void execute(){
this.light.on();
}

@Override
public void undo(){
this.light.off();
}

}

class LightOffCommand implements Command{
private final Light light;

public LightOffCommand(Light light){
this.light = light;
}

@Override
public void execute(){
this.light.off();
}

@Override
public void undo(){
this.light.on();
}

}

class SetTempratureCommand implements Command{
private final Thermostat thermostat;
private final int newTemprature;
private int prevTemprature;

public SetTempratureCommand(Thermostat thermostat,int temp){
this.thermostat = thermostat;
this.newTemprature = temp;
}

@Override
public void execute(){
prevTemprature = this.thermostat.getTemprature();
this.thermostat.setTemprature(this.newTemprature);
}



@Override
public void undo(){
this.thermostat.setTemprature(this.prevTemprature);
}


}

class SmartButton{


private Command CurrentCommand;
private final Stack<Command> history = new Stack<>();

public void setCommand(Command command){
CurrentCommand = command;
}

public void press(){
if(CurrentCommand != null){
CurrentCommand.execute();
history.push(CurrentCommand);
}
else{
System.out.println("Command Not Set");
}
}


public void undoLast(){
if(!history.empty()){
Command lastCommand = history.pop();
lastCommand.undo();
}

else{
System.out.println("Nothing to undo");
}
}

}












