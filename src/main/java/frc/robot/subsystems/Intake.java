// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

  package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.FuelConstants.*;

public class Intake extends SubsystemBase {
  //private final SparkMax feederRoller;
  private final SparkMax intakeRoller;
  //private final SparkMax conveyorRoller;
 // private final SparkMax launcherRoller;

  /** Creates a new CANBallSubsystem. */
  public Intake() {
    intakeRoller = new SparkMax(INTAKE_MOTOR_ID, MotorType.kBrushless);
 
    SparkMaxConfig intakeConfig = new SparkMaxConfig();
    intakeConfig.smartCurrentLimit(LAUNCHER_MOTOR_CURRENT_LIMIT);
    intakeRoller.configure(intakeConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }


  public Command forword() {
    return run(() -> intakeRoller.setVoltage(12)).finallyDo(() -> intakeRoller.set(0));
  }
  public Command backword() {
    return run(() -> intakeRoller.setVoltage(-12)).finallyDo(() -> intakeRoller.set(0));
  } 
  public void stop() {
    intakeRoller.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("Intake on", intakeRoller.get() != 0);
  }
}