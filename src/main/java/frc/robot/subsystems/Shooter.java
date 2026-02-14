// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
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

import frc.robot.Constants.ShooterConstants;
import frc.robot.Constants.ShooterConstants.*;

public class Shooter extends SubsystemBase {
  public static Command shoot;
  public static Object Idle;
/** Creates a new Shooter. */
  private final SparkMax shooterMotor;
  private final SparkMaxConfig config;
  private double targetRPM;


  public Shooter() {
  shooterMotor = new SparkMax(ShooterConstants.SHOOTERMOTOR_ID,MotorType.kBrushless);
  config = new SparkMaxConfig();

  config.inverted(false);
  config.smartCurrentLimit(40);

  config.closedLoop.feedbackSensor(FeedbackSensor.kPrimaryEncoder);
  config.closedLoop.p(0.00016541);
  config.closedLoop.i(0);
  config.closedLoop.d(0);
  config.closedLoop.velocityFF(0.0001);

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
    return Math.abs(getRPM() - targetRPM) < 100;
  }
  public Command shoot() {
    targetRPM = 3000;
    return null;
  }
  public Command Idle() {
    targetRPM = 2000;
    return null;
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
