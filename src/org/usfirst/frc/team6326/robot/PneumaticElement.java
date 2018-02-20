package org.usfirst.frc.team6326.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class PneumaticElement {
	private Position state;
	private Solenoid out;
	private Solenoid in;
	
	public PneumaticElement(int moduleId, int outPort, int inPort) {
		this.out = new Solenoid(moduleId, outPort);
		this.in = new Solenoid(moduleId, inPort);
	}
	
	public void set(Position pos) {
		if (pos == Position.OPEN) {
			this.out.set(true);
			this.in.set(false);
			this.state = Position.OPEN;
		} else if (pos == Position.CLOSED) {
			this.out.set(false);
			this.in.set(true);
			this.state = Position.CLOSED;
		}
	}
}
