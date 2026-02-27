// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class DriveCommand extends Command {

    private final DriveSubsystem drive;
    private final CommandXboxController controller;

    public DriveCommand(DriveSubsystem drive,
                        CommandXboxController controller) {

        this.drive = drive;
        this.controller = controller;

        addRequirements(drive);
    }

    @Override
    public void execute() {

        double forward =
                -controller.getLeftY()
                * Constants.Drive.MAX_SPEED;

        double rotation =
                controller.getRightX()
                * Constants.Drive.MAX_ROTATION;

        drive.arcadeDrive(forward, rotation);
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }
}