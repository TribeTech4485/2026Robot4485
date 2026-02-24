// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {

    private final DriveSubsystem drive =
        new DriveSubsystem();

    private final ConveyorSubsystem conveyor =
        new ConveyorSubsystem();

    private final ShooterSubsystem shooter =
        new ShooterSubsystem();

    private final VisionSubsystem vision =
        new VisionSubsystem();

    private final CommandXboxController controller =
        new CommandXboxController(0);

    private final SendableChooser<Command> autoChooser =
        new SendableChooser<>();

    public RobotContainer() {

        drive.setDefaultCommand(
            new DriveCommand(drive, controller)
        );

        configureBindings();
        configureAuto();
    }

    private void configureBindings() {

        controller.a().whileTrue(
            new ConveyorCommand(conveyor)
        );

        controller.b().whileTrue(
            new RunCommand(shooter::shoot, shooter)
        );

        controller.x().whileTrue(
            new AprilTagAlignCommand(drive, vision)
        );
    }

    private void configureAuto() {

        autoChooser.setDefaultOption("Do Nothing", null);

        autoChooser.addOption(
            "2 Piece Auto",
            new CompetitionAuto(drive, conveyor, shooter)
        );

        SmartDashboard.putData("Auto Mode", autoChooser);
    }

    public Command getAutonomousCommand() {
        return autoChooser.getSelected();
    }
}