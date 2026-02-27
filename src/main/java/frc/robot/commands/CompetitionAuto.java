// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj2.command.*;

public class CompetitionAuto extends SequentialCommandGroup {

    public CompetitionAuto(
        DriveSubsystem drive,
        ConveyorSubsystem conveyor,
        ShooterSubsystem shooter
    ) {

        addCommands(

            // Shoot preload
            new RunCommand(
                () -> shooter.shoot(),
                shooter
            ).withTimeout(2),

            new RunCommand(
                () -> shooter.stop(),
                shooter
            ).withTimeout(0.2),

            // Drive to second piece
            new RunCommand(
                () -> drive.arcadeDrive(0.6, 0),
                drive
            ).withTimeout(3),

            new InstantCommand(drive::stop),

            // Run conveyor to intake
            new RunCommand(
                () -> conveyor.runForward(),
                conveyor
            ).withTimeout(2),

            new InstantCommand(conveyor::stop),

            // Return
            new RunCommand(
                () -> drive.arcadeDrive(-0.6, 0),
                drive
            ).withTimeout(3),

            new InstantCommand(drive::stop)

        );
    }
}