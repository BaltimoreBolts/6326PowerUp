/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6326.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	private static final int intakeLeft = 2;
	private static final int intakeRight = 3;
	
	private static final int portalRunnerPort = 4;

	// autonomous
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "Special 0";
	private static final String kSpecialAuto1 = "Special 1";
	private static final String kSpecialAuto2 = "Special 2";
	public String plateData = "Plate Assignments";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	public String gameData;

	// objects (controllers, sparks, talons, etc....)
	private XboxController driver;
	private XboxController operator;

	private Navigation navigation;
	private Hugger hugger;
	private Elevator elevator;
	private Intake intake;
	private PortalRunner portalRunner;

	// - init encoders
	public Encoder encLeft = new Encoder(0, 1);
	public Encoder encRight = new Encoder(2, 3);
	AHRS navx;
	
	private static final int PORTAL_RUNNER_DURATION = 500;
	
	boolean turning = false;
	long remainingPortalTime = 0;
	long runPortalToTime = 0;
	boolean portalRunnerActive = false;
	Direction portalRunnerDirection = null;

	@Override
	public void robotInit() {
		try {
			/***********************************************************************
			 * navX-MXP: - Communication via RoboRIO MXP (SPI, I2C, TTL UART) and USB. - See
			 * http://navx-mxp.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * navX-Micro: - Communication via I2C (RoboRIO MXP or Onboard) and USB. - See
			 * http://navx-micro.kauailabs.com/guidance/selecting-an-interface.
			 * 
			 * Multiple navX-model devices on a single robot are supported.
			 ************************************************************************/
			System.out.println("Trying to start navX...");
			DriverStation.reportWarning("Trying to start navX...", false); // debug
			navx = new AHRS(SPI.Port.kMXP);
		} catch (RuntimeException ex) {
			DriverStation.reportError("Error instantiating navX MXP:  " + ex.getMessage(), true);
			System.out.println("navX could not be started!");
		}

		// init controllers
		this.driver = new XboxController(DRIVER_CONTROLLER);
		this.operator = new XboxController(OPERATOR_CONTROLLER);

		// init motors
		Spark leftDrive = new Spark(LEFT_DRIVE_MOTOR);
		Spark rightDrive = new Spark(RIGHT_DRIVE_MOTOR);

		// init drivetrain
		DifferentialDrive driveTrain = new DifferentialDrive(leftDrive, rightDrive);

		// init subsystems
		this.navigation = new Navigation(driveTrain, encLeft, encRight);
		this.hugger = new Hugger(PCM_ID, huggerOut, huggerIn);
		this.intake = new Intake(PCM_ID, intakeOut, intakeIn, intakeLeft, intakeRight);
		this.elevator = new Elevator(ELEVATOR_MOTOR);
		this.portalRunner = new PortalRunner(portalRunnerPort);

		// auto init
		m_chooser.addDefault("Default Auto", kDefaultAuto);
		m_chooser.addObject("Special Auto 0", kCustomAuto);
		m_chooser.addObject("Special Auto 1", kSpecialAuto1);
		m_chooser.addObject("Special Auto 2", kSpecialAuto2);
		gameData = DriverStation.getInstance().getGameSpecificMessage();

		encLeft.reset(); // encoder reset
		encRight.reset(); // encoder reset

	}

	@Override
	public void autonomousInit() {
		encLeft.reset(); // encoder reset
		encRight.reset(); // encoder reset

	}

	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
		case kCustomAuto:
			// Put custom auto code here

			break;
		case kSpecialAuto1:
			// Put custom auto code here

			break;
		case kSpecialAuto2:
			// Put custom auto code here

			break;
		case kDefaultAuto:
		default:
			// default
			for (int i = 0; i < 5; i++) {
				this.navigation.arcade(0.5, 0.5);
				if (i == 4) {
					this.navigation.arcade(0, 0);
				}
				Timer.delay(5);
			}

			break;
		}
	}

	@Override
	public void teleopPeriodic() {
		// Driver

		if (driver.getRawButton(Controller.XBOX.BUMPER.RIGHT)) {
			this.intake.open();
			SmartDashboard.putBoolean("Intake Open", true);
		}

		if (driver.getRawButton(Controller.XBOX.BUMPER.LEFT)) {
			this.intake.close();
			SmartDashboard.putBoolean("Intake Open", false);
		}
		
		if (driver.getRawButtonPressed(Controller.XBOX.Y) || turning) {
			if (!turning) {
				navx.reset();
			}
			turning = navigation.turn(180, navx.getAngle(), Direction.CLOCKWISE, true);
		}
		
		if (driver.getRawButton(Controller.XBOX.X)) {
			this.portalRunner.run(this.portalRunnerDirection, 0.4);
			SmartDashboard.putString("Portal Runner", "LEFT");
		} else if (driver.getRawButton(Controller.XBOX.B)) {
			this.portalRunner.run(this.portalRunnerDirection, 0.4);
			SmartDashboard.putString("Portal Runner", "LEFT");
		} else {
			this.portalRunner.stop();
			SmartDashboard.putString("Portal Runner", "STOPPED");
		}
		
		if (driver.getRawButton(Controller.XBOX.START)) {
			navx.reset();
		}
		
		double rightTrigger = driver.getRawAxis(Controller.XBOX.TRIGGER.RIGHT);
		double leftTrigger = driver.getRawAxis(Controller.XBOX.TRIGGER.LEFT);
		
		this.intake.eat(rightTrigger);
		this.intake.vomit(leftTrigger);
		
		if (rightTrigger == 0 && leftTrigger == 0) {
			SmartDashboard.putString("Intake Direction", "STOPPED");
		} else if (rightTrigger > 0) {
			SmartDashboard.putString("Intake Direction", "IN");
		} else if (leftTrigger > 0) {
			SmartDashboard.putString("Intake Direction", "OUT");
		}
		
		SmartDashboard.putNumber("Intake Power", rightTrigger);
		
		if (!turning) {
			this.navigation.arcade(driver.getRawAxis(Controller.XBOX.STICK.LEFT.X), driver.getRawAxis(Controller.XBOX.STICK.LEFT.Y));
		}
		
		SmartDashboard.putBoolean("Gyro Turn in Progress", turning);

		// Operator

		this.elevator.setPower(operator.getRawAxis(Controller.XBOX.STICK.LEFT.Y));

		if (operator.getRawButtonPressed(Controller.XBOX.BUMPER.RIGHT)) {
			hugger.set(Position.CLOSED);
		}

		if (operator.getRawButtonPressed(Controller.XBOX.BUMPER.LEFT)) {
			hugger.set(Position.OPEN);
		}
	}

}
