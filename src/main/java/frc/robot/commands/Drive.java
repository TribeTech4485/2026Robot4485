// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static frc.robot.Constants.OperatorConstants.*;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.DriveSubsystem;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class Drive extends Command {
  /** Creates a new Drive. */
  DriveSubsystem driveSubsystem;
  CommandXboxController controller;

  public static double deadband(double input, double deadband) {
    if (Math.abs(input) < deadband) {
      return 0;
    } else {
      return (input - (Math.abs(input) / input * deadband)) / (1 - deadband);
    }
  }

  /** Exponent that keeps the sign of the input */
  public static double smartExp(double x, double exponent) {
    double result = Math.pow(x, exponent);
    if (Math.signum(result) == Math.signum(x)) {
      return result;
    }
    return -result;
  }

  double proccessInput(double input) {
    return smartExp(deadband(input, 0.15), 1.5);
  }

  public Drive(DriveSubsystem driveSystem, CommandXboxController driverController) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSystem);
    driveSubsystem = driveSystem;
    controller = driverController;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  // The Y axis of the controller is inverted so that pushing the
  // stick away from you (a negative value) drives the robot forwards (a positive
  // value). The X axis is scaled down so the rotation is more easily
  // controllable.
  @Override
  public void execute() {
    driveSubsystem.driveArcade(proccessInput(controller.getLeftY()),
        proccessInput(controller.getRightX()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveSubsystem.driveArcade(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
