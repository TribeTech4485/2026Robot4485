// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.IntakeRotater;
import frc.robot.subsystems.Shooter;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class CABASD extends SequentialCommandGroup {
  /** Creates a new CenterAuto. */
  public CABASD(DriveSubsystem driveSubsystem, Intake fuelSubsystem, Shooter shooter, Conveyor convey,
      IntakeRotater rotate) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        shooter.autoCommand().withTimeout(.1),
        new AutoDrive(driveSubsystem, -.5, 0).withTimeout(.90),
        new AutoDrive(driveSubsystem, .5, 0).withTimeout(.15),
        new AutoDrive(driveSubsystem, 0, 0).withTimeout(1.75),
        convey.ConveyorForword().withTimeout(5),
        shooter.stopCommand().withTimeout(0.01),
        convey.stop().withTimeout(0.01),
        rotate.rotateIntakeDown().withTimeout(.01));
  }
}