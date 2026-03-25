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

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RATCSP extends SequentialCommandGroup {
  /** Creates a new ExampleAuto. */
  public RATCSP(DriveSubsystem driveSubsystem, Intake fuelSubsystem, Shooter shooter, Conveyor convey,
      IntakeRotater rotate) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        // Drive backwards for .25 seconds. The driveArcadeAuto command factory
        // intentionally creates a command which does not end which allows us to control
        // the timing using the withTimeout decorator
        rotate.rotateIntakeDown().withTimeout(.01),
        shooter.idleCommand().withTimeout(.5),
        new AutoDrive(driveSubsystem, -0.5, 0.0).withTimeout(1.1),
        new AutoDrive(driveSubsystem, 0, -.55).withTimeout(.16),
        new AutoDrive(driveSubsystem, .25, 0).withTimeout(.10),
        shooter.autoCommand().withTimeout(.01),
        new AutoDrive(driveSubsystem, 0, 0).withTimeout(1.5),
        convey.ConveyorForword().withTimeout(5),
        shooter.stopCommand().withTimeout(.01),
        rotate.rotateIntakeUp().withTimeout(0.01),
        new AutoDrive(driveSubsystem, .5, 0).withTimeout(.5),
        new AutoDrive(driveSubsystem, 0, -.5).withTimeout(.25),
        new AutoDrive(driveSubsystem, .5, 0).withTimeout(.25),
        new AutoDrive(driveSubsystem, 0, .5).withTimeout(.25));
  }
}