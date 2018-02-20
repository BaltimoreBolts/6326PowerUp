package org.usfirst.frc.team6326.robot;

public class Hugger extends PneumaticElement {
	public Hugger(int moduleId, int out, int in) {
		super(moduleId, out, in);
	}
	
	public void hug() {
		// do other hugger-related things here
		super.set(Position.CLOSED);
	}
	
	public void release() {
		super.set(Position.OPEN);
		// kicker??? may need slight delay before activating kicker
	}
}
