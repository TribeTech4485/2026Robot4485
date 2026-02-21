// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

import static frc.robot.Constants.OperatorConstants.*;

import frc.robot.commands.Drive;
import frc.robot.commands.Conveyor;
import frc.robot.commands.Auto;
import frc.robot.commands.Intake;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FuelSubsystem;
import frc.robot.subsystems.Shooter;

public class RobotContainer {

  // Subsystems
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final FuelSubsystem fuelSubsystem = new FuelSubsystem();
  private final Shooter shooter = new Shooter();

  
  private final CommandXboxController driverController =
      new CommandXboxController(DRIVER_CONTROLLER_PORT);

  private final CommandXboxController operatorController =
      new CommandXboxController(OPERATOR_CONTROLLER_PORT);

  
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {

    configureBindings();

    autoChooser.setDefaultOption(
        "Default Auto",
        new Auto(driveSubsystem, fuelSubsystem));

    SmartDashboard.putData("Auto Mode", autoChooser);
  }

  private void configureBindings() {

    

    
    operatorController.leftTrigger()
        .whileTrue(new Intake(fuelSubsystem));

    
    operatorController.rightTrigger()
        .onTrue(shooter.shootCommand())
        .onFalse(shooter.idleCommand());

  
    operatorController.rightBumper()
        .whileTrue(new Conveyor(fuelSubsystem));

    

    driveSubsystem.setDefaultCommand(
        new Drive(driveSubsystem, driverController));

    fuelSubsystem.setDefaultCommand(
        fuelSubsystem.run(() -> fuelSubsystem.stop()));
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}