package org.usfirst.frc.team6326.robot;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Elevator {
	CANTalon motor;
	int position = 0;
	
	// accept motor ref
	public Elevator(int port) {
		this.motor = new CANTalon(port);
		this.motor.set(ControlMode.PercentOutput, port);
	}
	
	public void setPosition(double pos) {
		this.motor.setPosition(double pos);
	}
	
	public int getPosition() {
		return position;
	}
}
