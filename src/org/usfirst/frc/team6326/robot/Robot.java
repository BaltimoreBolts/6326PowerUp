/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6326.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends IterativeRobot {
	
	// Constants (ports, etc...)
	private static final int DRIVER_CONTROLLER = 0;
	private static final int OPERATOR_CONTROLLER = 1;
	
	private static final int LEFT_DRIVE_MOTOR = 0;
	private static final int RIGHT_DRIVE_MOTOR = 1;
	
	private static final int ELEVATOR_MOTOR = 2;
	
	private static final int PCM_ID = 1;
	
	private static final int huggerOut = 0;
	private static final int huggerIn = 1;
	
	private static final int intakeOut = 2;
	private static final int intakeIn = 3;
	
	// objects (controllers, sparks, talons, etc....)
	private XboxController driver;
	private XboxController operator;
	
	private Navigation navigation;
	private Hugger hugger;
	private Elevator elevator;
	private Intake intake;

	@Override
	public void robotInit() {
		
		// init controllers
		this.driver = new XboxController(DRIVER_CONTROLLER);
		this.operator = new XboxController(OPERATOR_CONTROLLER);
		
		// init motors
		Spark leftDrive = new Spark(LEFT_DRIVE_MOTOR);
		Spark rightDrive = new Spark(RIGHT_DRIVE_MOTOR);
		
		// init drivetrain
		DifferentialDrive driveTrain = new DifferentialDrive(leftDrive, rightDrive);
		
		// init subsystems
		this.navigation = new Navigation(driveTrain);
		this.hugger = new Hugger(PCM_ID, huggerOut, huggerIn);
		this.intake = new Intake(PCM_ID, intakeOut, intakeIn);
		this.elevator = new Elevator(ELEVATOR_MOTOR);
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
		
	}

	@Override
	public void teleopPeriodic() {
	}
	
}
