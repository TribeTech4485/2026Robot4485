// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimberConstants;

import static frc.robot.Constants.ClimberConstants;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  private final SparkMax leftClimber;

  private final SparkMax rightClimber;

  public Climber() {

    leftClimber = new SparkMax(ClimberConstants.LEFT_CLIMBER_ID, MotorType.kBrushless);
    rightClimber = new SparkMax(ClimberConstants.RIGHT_CLIMBER_ID, MotorType.kBrushless);

    SparkMaxConfig config = new SparkMaxConfig();
    config.voltageCompensation(12);
    config.smartCurrentLimit(ClimberConstants.CLIMBER_VOLTAGE);

    rightClimber.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    config.inverted(true);
    leftClimber.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  public Command climbUp() {
    return run(() -> {
      leftClimber.set(0.75);
      rightClimber.set(0.75);
    });
  }

  public Command climbDown() {
    return run(() -> {
      leftClimber.set(0.75);
      rightClimber.set(0.75);
    });
  }

  public Command stopClimb() {
    return run(() -> {
      leftClimber.set(0);
      rightClimber.set(0);
    });
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putBoolean("left Climber Up", leftClimber.get() > 0);
    SmartDashboard.putBoolean("right Climber up", rightClimber.get() < 0);
    SmartDashboard.putBoolean("left Climber Down", leftClimber.get() < 0);
    SmartDashboard.putBoolean("right Climber down", rightClimber.get() > 0);
  }
}
