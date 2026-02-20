// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



import com.revrobotics.ResetMode;
import com.revrobotics.spark.FeedbackSensor;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig;
import com.revrobotics.spark.SparkBase;

import frc.robot.RobotContainer;

import edu.wpi.first.math.util.Units;

import frc.robot.Constants.FuelConstants;
import frc.robot.Constants.FuelConstants.*;

public class Shooter extends SubsystemBase {
/** Creates a new Shooter. */
  private final SparkMax shooterMotor;
  private final SparkMaxConfig config;
  private double targetRPM = 0;

  private final static double kP = 0.00016541;
  private final static double kI = 0.0;
  private final static double kD = 0.0;
  private final static double kFF = 0.0001;

  private final static double RPM_TOLERANCE = 100;

  public Shooter() {
  shooterMotor = new SparkMax(FuelConstants.SHOOTER_MOTOR_ID,MotorType.kBrushless);
  config = new SparkMaxConfig();

  config.inverted(false);
  config.smartCurrentLimit(40);

  config.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);
  config.closedLoop.p(kP);
  config.closedLoop.i(kI);
  config.closedLoop.d(kD);
  config.closedLoop.velocityFF(kFF);

  shooterMotor.configure(config, SparkBase.ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
 }

  public void setTargetRPM(double rpm) {
    targetRPM = rpm;

    shooterMotor.getClosedLoopController().setReference(targetRPM, com.revrobotics.spark.SparkBase.ControlType.kVelocity);
  }

  public double getRPM() {
    return shooterMotor.getEncoder().getVelocity();
  }

  public boolean atTargetRPM() {
    return Math.abs(getRPM() - targetRPM) < RPM_TOLERANCE;
  }
  public Command shootCommand() {
    return run (() -> targetRPM = 3800);
  }
  public Command IdleCommand() {
  return run (() -> targetRPM = 2000);
  }
public Command stop() {
    return run (() -> targetRPM = 0);
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
