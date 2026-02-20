// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.FuelConstants.*;

public class FuelSubsystem extends SubsystemBase {
  private final SparkMax feederRoller;
  private final SparkMax intakeRoller;
  private final SparkMax conveyorRoller;
 // private final SparkMax launcherRoller;

  /** Creates a new CANBallSubsystem. */
  public FuelSubsystem() {
    // create brushless motors for each of the motors on the launcher mechanism
    intakeRoller = new SparkMax(INTAKE_MOTOR_ID, MotorType.kBrushless);
    feederRoller = new SparkMax(FEEDER_MOTOR_ID, MotorType.kBrushless);
   // launcherRoller = new SparkMax(SHOOTER_MOTOR_ID, MotorType.kBrushless);
    conveyorRoller = new SparkMax(CONVEY_MOTOR_ID, MotorType.kBrushless);

    // create the configuration for the feeder roller, set a current limit and apply
    // the config to the controller
    SparkMaxConfig feederConfig = new SparkMaxConfig();
    feederConfig.smartCurrentLimit(FEEDER_MOTOR_CURRENT_LIMIT);
    feederRoller.configure(feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // create the configuration for the launcher roller, set a current limit, set
    // the motor to inverted so that positive values are used for both intaking and
    // launching, and apply the config to the controller
    SparkMaxConfig launcherConfig = new SparkMaxConfig();
    launcherConfig.inverted(false);
    launcherConfig.smartCurrentLimit(LAUNCHER_MOTOR_CURRENT_LIMIT);
    //launcherRoller.configure(launcherConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // create the configuration for the conveyor roller, set a current limit and apply
    // the config to the controller - JRW
    SparkMaxConfig conveyorConfig = new SparkMaxConfig();
    conveyorConfig.smartCurrentLimit(FEEDER_MOTOR_CURRENT_LIMIT);
    conveyorRoller.configure(feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // create the configuration for the intake roller, set a current limit and apply the config to the controller
    SparkMaxConfig intakeConfig = new SparkMaxConfig();
    intakeConfig.smartCurrentLimit(LAUNCHER_MOTOR_CURRENT_LIMIT);
    intakeRoller.configure(launcherConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    // put default values for various fuel operations onto the dashboard
    // all commands using this subsystem pull values from the dashbaord to allow
    // you to tune the values easily, and then replace the values in Constants.java
    // with your new values. For more information, see the Software Guide.
    SmartDashboard.putNumber("Feeder roller value", FEEDER_VOLTAGE);
    SmartDashboard.putNumber("Intake roller value", INTAKING_VOLTAGE);
    //SmartDashboard.putNumber("Launching value", LAUNCHING_VOLTAGE);
    SmartDashboard.putNumber("Conveyor roller value", CONVEYOR_VOLTAGE);
    SmartDashboard.putNumber("Spin-up feeder roller value", SPIN_UP_FEEDER_VOLTAGE);
  }

  // A method to set the voltage of the intake roller
  public void setIntakeRoller(double voltage) {
    intakeRoller.setVoltage(voltage);
  }

  // A method to set the voltage of the intake roller
  public void setFeederRoller(double voltage) {
    feederRoller.setVoltage(voltage);
  }

    // A method to set the voltage of the intake roller
  //public void setlauncherRoller(double voltage) {
    //launcherRoller.setVoltage(7);
  //}

  // A method to set the voltage of the intake roller
  public void setconveyorRoller(double voltage) {
    conveyorRoller.setVoltage(voltage);
  }

  // A method to stop the rollers
  public void stop() {
    feederRoller.set(0);
    intakeRoller.set(0);
    conveyorRoller.set(0);
 //   launcherRoller.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
