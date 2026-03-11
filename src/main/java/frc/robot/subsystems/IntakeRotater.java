// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.IntakeConstants;
public class IntakeRotater extends SubsystemBase {
  /** Creates a new IntakeRotater. */
  private final SparkMax intakeRotater;
  public IntakeRotater() {
    intakeRotater = new SparkMax(IntakeConstants.INTAKE_ROTATER_ID, MotorType.kBrushless);
  }
  public Command rotateIntakeUp() {
    return run(() -> {
      intakeRotater.set(0.5);
    });
  }
  public Command rotateIntakeDown() {
    return run(() -> {
      intakeRotater.set(-0.5);
    });
  }
  public Command stopIntakeRotation() {
    return run(() -> {  
      intakeRotater.set(0);
    });
  } 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
