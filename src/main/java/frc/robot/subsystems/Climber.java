// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.ClimberConstants;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Climber extends SubsystemBase {
  /** Creates a new Climber. */
  private final SparkMax leftClimber;
  private final SparkMax rightClimber;
  public Climber() {
    leftClimber = new SparkMax(ClimberConstants.LEFT_CLIMBER_ID, MotorType.kBrushless);
    rightClimber = new SparkMax(ClimberConstants.RIGHT_CLIMBER_ID, MotorType.kBrushless);
  }
  
  public Command climbUp() {
    return run(() -> {
      leftClimber.set(0.5);
      rightClimber.set(-0.5);
    });
  }
  public Command climbDown() {
    return run(() -> {
      leftClimber.set(-0.5);
      rightClimber.set(0.5);
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
  }
}
