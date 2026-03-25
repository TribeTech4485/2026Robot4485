// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.Constants.IntakeConstants;
import com.revrobotics.spark.config.SparkMaxConfig;
import static frc.robot.Constants.IntakeConstants;

public class IntakeRotater extends SubsystemBase {
  /** Creates a new IntakeRotater. */
  private final SparkMax intakeRotater;
  private final ProfiledPIDController rotaterPIDController;
  private final ArmFeedforward rotaterFeedforward;

  public IntakeRotater() {
    rotaterPIDController = new ProfiledPIDController(IntakeConstants.kP, IntakeConstants.kI, IntakeConstants.kD,
        new TrapezoidProfile.Constraints(IntakeConstants.MAXVELOCITY, IntakeConstants.MAXACCELERATION));
    rotaterFeedforward = new ArmFeedforward(IntakeConstants.kS, IntakeConstants.kV, IntakeConstants.kA,
        IntakeConstants.kG);
    intakeRotater = new SparkMax(IntakeConstants.INTAKE_ROTATER_ID, MotorType.kBrushless);

    SparkMaxConfig config = new SparkMaxConfig();
    config.smartCurrentLimit(IntakeConstants.INTAKE_ROTATER_CURRENT_LIMIT);
    config.encoder.positionConversionFactor(IntakeConstants.positionConversionFactor);
    config.encoder.velocityConversionFactor(IntakeConstants.positionConversionFactor / 60); // convert min to sec
    config.softLimit.forwardSoftLimit(IntakeConstants.MAX_LOWER);
    config.softLimit.reverseSoftLimit(IntakeConstants.MAX_UPPER);
    intakeRotater.configure(config, SparkMax.ResetMode.kResetSafeParameters, SparkMax.PersistMode.kPersistParameters);
    intakeRotater.getEncoder().setPosition(IntakeConstants.STARTING_POS);
    rotaterPIDController.setGoal(IntakeConstants.STARTING_POS);
  }

  public Command rotateIntakeUp() {
    return new InstantCommand(() -> {
      rotaterPIDController.setGoal(IntakeConstants.MAX_UPPER);
    });
  }

  public Command rotateIntakeDown() {
    return new InstantCommand(() -> {
      rotaterPIDController.setGoal(IntakeConstants.MAX_LOWER);
    });
  }

  public void holdPosistion() {
    rotaterPIDController.setGoal(intakeRotater.getEncoder().getPosition());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    intakeRotater.setVoltage(rotaterFeedforward.calculate(rotaterPIDController.getSetpoint().position,
        rotaterPIDController.getSetpoint().velocity)
        + rotaterPIDController.calculate(intakeRotater.getEncoder().getPosition()));
    SmartDashboard.putNumber("Intake Rotater Current Position", intakeRotater.getEncoder().getPosition());
    SmartDashboard.putNumber("Intake Rotater Goal position", rotaterPIDController.getGoal().position);
    SmartDashboard.putNumber("Intake Rotater Target position", rotaterPIDController.getSetpoint().position);
  }
}
