// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.subsystems.*;
import frc.robot.commands.*;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

<<<<<<< HEAD
import static frc.robot.Constants.OperatorConstants.*;

import frc.robot.commands.Drive;
import frc.robot.commands.Conveyor;
import frc.robot.commands.Auto;
import frc.robot.commands.AutoOne;
import frc.robot.commands.Intake;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FuelSubsystem;
import frc.robot.subsystems.Shooter;

=======
>>>>>>> 385b4f835e1e9963a447b4535a4e6ef354ad7715
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

<<<<<<< HEAD
    autoChooser.setDefaultOption(
        "Default Auto",
        new Auto(driveSubsystem, fuelSubsystem, shooter));
    autoChooser.setDefaultOption(
        "Default One",
        new AutoOne(driveSubsystem, fuelSubsystem, shooter));
    SmartDashboard.putData("Auto Mode", autoChooser);
  }
  private void configureBindings() {
=======
    public RobotContainer() {

        drive.setDefaultCommand(
            new DriveCommand(drive, controller)
        );

        configureBindings();
        configureAuto();
    }
>>>>>>> 385b4f835e1e9963a447b4535a4e6ef354ad7715

    private void configureBindings() {

        controller.a().whileTrue(
            new ConveyorCommand(conveyor)
        );

<<<<<<< HEAD
    
    operatorController.rightTrigger()
        .onTrue(shooter.shootCommand())
        .onFalse(shooter.idleCommand());
        
    operatorController.leftBumper()
    .onTrue(shooter.stopCommand());
=======
        controller.b().whileTrue(
            new RunCommand(shooter::shoot, shooter)
        );
>>>>>>> 385b4f835e1e9963a447b4535a4e6ef354ad7715

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