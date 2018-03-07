package org.usfirst.frc.team6326.robot;

import edu.wpi.first.wpilibj.Spark;

public class Intake extends PneumaticElement {
	
	private Spark leftMotor;
	private Spark rightMotor;

	// accept intake motors
	public Intake(int moduleId, int out, int in, int left, int right) {
		super(moduleId, out, in);
		this.leftMotor = new Spark(left);
		this.rightMotor = new Spark(right);
	}
	
	// open intake
	public void open() {
		super.set(Position.OPEN);
	}
	
	// close intake
	public void close() {
		super.set(Position.CLOSED);
	}
	
	// pull cube in
	public void eat(double power) {
		this.leftMotor.set(power);
		this.rightMotor.set(power);
	}
	
	// eject cube
	public void vomit(double power) {
		this.leftMotor.set(-power);
		this.leftMotor.set(-power);
	}
}
