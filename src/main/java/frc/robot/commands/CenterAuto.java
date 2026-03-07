// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FuelSubsystem;
import frc.robot.subsystems.Shooter;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class CenterAuto extends SequentialCommandGroup {
  /** Creates a new CenterAuto. */
  public CenterAuto(DriveSubsystem driveSubsystem,FuelSubsystem fuelSubsystem,Shooter shooter) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    shooter.shootCommand().withTimeout(.1),
    new AutoDrive(driveSubsystem, -.5, 0).withTimeout(.85),
    new AutoDrive(driveSubsystem, .5, 0).withTimeout(.1),
    new AutoDrive(driveSubsystem, 0, 0).withTimeout(1),
    new Conveyor(fuelSubsystem).withTimeout(5),
    shooter.stopCommand().withTimeout(0.01),
    new AutoDrive(driveSubsystem, -.5, 0).withTimeout(.5)
    );
  }
}
