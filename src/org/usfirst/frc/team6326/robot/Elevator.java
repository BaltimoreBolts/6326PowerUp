package org.usfirst.frc.team6326.robot;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;

@SuppressWarnings("deprecation")
public class Elevator {
	CANTalon motor;
	int position = 0;
	
	// accept motor ref
	public Elevator(int port) {
		this.motor = new CANTalon(port);
	}
	
	public void setPower(double power) {
		this.motor.set(ControlMode.PercentOutput, power);
	}
	
	public int getPosition() {
		return position;
	}
}
