package org.usfirst.frc.team6326.robot;

public class Intake extends PneumaticElement {
	
	// accept intake motors
	public Intake(int moduleId, int out, int in) {
		super(moduleId, out, in);
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
	public void eat() {
		// run intake motors in
	}
	
	// eject cube
	public void vomit() {
		// run intake motors out
	}
}
