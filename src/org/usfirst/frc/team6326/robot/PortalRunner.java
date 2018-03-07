package org.usfirst.frc.team6326.robot;

import edu.wpi.first.wpilibj.Spark;

public class PortalRunner {
	Spark motor;

	public PortalRunner(int motorPort) {
		this.motor = new Spark(motorPort);
	}
	
	public void run(Direction direction, double power) {
		this.motor.set(power);
	}
	
	public void stop() {
		this.motor.set(0.0);
	}
}
