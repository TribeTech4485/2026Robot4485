// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Intake;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class DoNothing extends SequentialCommandGroup {
  /** Creates a new DoNothing. */
  public DoNothing(DriveSubsystem driveSubsystem,Intake fuelSubsystem,Shooter shooter,Conveyor convey) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      shooter.nothingAutoShoot().withTimeout(3),
      convey.ConveyorForword().withTimeout(5),
      shooter.stopCommand().withTimeout(0.01),
      convey.stop().withTimeout(0.01),
      new AutoDrive(driveSubsystem, 0, 0).withTimeout(20)
    );
  }
}
