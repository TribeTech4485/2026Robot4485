// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.FeedbackSensor;

import frc.robot.Constants.FuelConstants;

public class Shooter extends SubsystemBase {

  private final SparkMax shooterMotor;
  private final SparkMaxConfig config;

  private double targetRPM = 0;

  private static final double kP = 0.0003;
  // 16541;
  private static final double kI = 0.0;
  private static final double kD = 0.0;
  private static final double kFF = 0.0001;

  private static final double RPM_TOLERANCE = 600;

  public Shooter() {

    shooterMotor = new SparkMax(
        FuelConstants.SHOOTER_MOTOR_ID,
        MotorType.kBrushless);

    config = new SparkMaxConfig();

    config.inverted(false);
    config.smartCurrentLimit(40);

    config.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);
    config.closedLoop.p(kP);
    config.closedLoop.i(kI);
    config.closedLoop.d(kD);
    config.closedLoop.velocityFF(kFF);

    shooterMotor.configure(
        config,
        SparkBase.ResetMode.kResetSafeParameters,
        SparkBase.PersistMode.kPersistParameters);
  }

  public void setTargetRPM(double rpm) {
    targetRPM = rpm;

    shooterMotor.getClosedLoopController().setReference(
        targetRPM,
        SparkBase.ControlType.kVelocity);
  }

  public double getRPM() {
    return shooterMotor.getEncoder().getVelocity();
  }

  public boolean atTargetRPM() {
    return Math.abs(getRPM() - targetRPM) < RPM_TOLERANCE;
  }

  // Shoot at full speed
  public Command shootCommand() {
    return new InstantCommand(() -> setTargetRPM(-3500));
  }

  public Command autoCommand() {
    return new InstantCommand(() -> setTargetRPM(-3500));
  }

  public Command nothingAutoShoot() {
    return new InstantCommand(() -> setTargetRPM(-2750));
  }

  // Idle speed
  public Command idleCommand() {
    return new InstantCommand(() -> setTargetRPM(-2700));
  }

  // Stop shooter
  public Command stopCommand() {
    return new InstantCommand(() -> setTargetRPM(0));
  }

  public Command waitTillSpeed() {
    return new WaitUntilCommand((() -> atTargetRPM()));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("shooter RPM", getRPM());
    SmartDashboard.putNumber("shooter TargetRPM", targetRPM);
    SmartDashboard.putBoolean("shooter at target", atTargetRPM());
  }
}