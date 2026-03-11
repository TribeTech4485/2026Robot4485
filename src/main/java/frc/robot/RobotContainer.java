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

import frc.robot.commands.DoNothing;

import frc.robot.commands.LeftClimbAuto;
import frc.robot.commands.LATCSP;

import frc.robot.commands.CenterClimbAuto;
import frc.robot.commands.CABTSP;

import frc.robot.commands.RightClimbAuto;
import frc.robot.commands.RATCSP;

import frc.robot.commands.Intake;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.FuelSubsystem;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.IntakeRotater;
import frc.robot.subsystems.Climber;

public class RobotContainer {

  // Subsystems
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final FuelSubsystem fuelSubsystem = new FuelSubsystem();
  private final Shooter shooter = new Shooter();
  private final IntakeRotater intakeRotater = new IntakeRotater();
  private final Climber climb = new Climber();
  
  private final CommandXboxController driverController =
      new CommandXboxController(DRIVER_CONTROLLER_PORT);

  private final CommandXboxController operatorController =
      new CommandXboxController(OPERATOR_CONTROLLER_PORT);

  
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();

  public RobotContainer() {

    configureBindings();

    autoChooser.setDefaultOption(
         "DoNothing",
        new DoNothing(driveSubsystem));
        autoChooser.addOption(
        "LeftClimbAuto",
        new LeftClimbAuto(driveSubsystem,fuelSubsystem,shooter));
        autoChooser.addOption(
        "CenterClimbAuto",
        new CenterClimbAuto(driveSubsystem,fuelSubsystem,shooter));
        autoChooser.addOption(
        "RightClimbAuto",
        new RightClimbAuto(driveSubsystem,fuelSubsystem,shooter));
        autoChooser.addOption(
        "CenterAutoBackToStartPosition",
        new CABTSP(driveSubsystem,fuelSubsystem,shooter));
        autoChooser.addOption(
        "RightAutoToCenterStartPosition",
        new RATCSP(driveSubsystem,fuelSubsystem,shooter));
        autoChooser.addOption(
        "leftAutoToCenterStartPosition",
        new LATCSP(driveSubsystem,fuelSubsystem,shooter));

    SmartDashboard.putData("Auto Mode", autoChooser);
  }

  private void configureBindings() {

    

    
    operatorController.leftTrigger()
        .whileTrue(new Intake(fuelSubsystem));

    
    operatorController.rightTrigger()
        .onTrue(shooter.shootCommand())
        .onFalse(shooter.idleCommand());

    operatorController.leftBumper()
        .onTrue(shooter.stopCommand());
  
    operatorController.rightBumper()
        .whileTrue(new Conveyor(fuelSubsystem));

    operatorController.a()
        .whileTrue(climb.climbUp())
        .onFalse(climb.stopClimb());
    operatorController.b()
        .whileTrue(climb.climbDown())
        .onFalse(climb.stopClimb());

    operatorController.x()
        .whileTrue(intakeRotater.rotateIntakeUp())
        .onFalse(intakeRotater.stopIntakeRotation());
    operatorController.y()
        .whileTrue(intakeRotater.rotateIntakeDown())
        .onFalse(intakeRotater.stopIntakeRotation());

    driveSubsystem.setDefaultCommand(
        new Drive(driveSubsystem, driverController));

    fuelSubsystem.setDefaultCommand(
        fuelSubsystem.run(() -> fuelSubsystem.stop()));
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}