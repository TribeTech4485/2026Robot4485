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
import frc.robot.commands.IntakeCommand;

import frc.robot.commands.DoNothing;

import frc.robot.commands.LeftClimbAuto;
import frc.robot.commands.LATCSP;

import frc.robot.commands.CenterClimbAuto;
import frc.robot.commands.CABTSP;

import frc.robot.commands.RightClimbAuto;
import frc.robot.commands.ShootCommandGenerator;
import frc.robot.commands.RATCSP;
import frc.robot.commands.RATPPAS;
import frc.robot.commands.RAAS;
import frc.robot.commands.LAAS;
import frc.robot.commands.CABAS;
import frc.robot.commands.RAASD;
import frc.robot.commands.LAASD;
import frc.robot.commands.CABASD;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.IntakeRotater;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyor;

public class RobotContainer {

        // Subsystems
        public final DriveSubsystem driveSubsystem = new DriveSubsystem();
        public final Intake fuelSubsystem = new Intake();
        public final Shooter shooter = new Shooter();
        public final IntakeRotater intakeRotater = new IntakeRotater();
        public final Climber climb = new Climber();
        public final Conveyor convey = new Conveyor();

        private final CommandXboxController driverController = new CommandXboxController(DRIVER_CONTROLLER_PORT);

        private final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);

        private final SendableChooser<Command> autoChooser = new SendableChooser<>();

        public RobotContainer() {

                configureBindings();

                autoChooser.setDefaultOption(
                                "don't Move And Shoot",
                                new DoNothing(driveSubsystem, fuelSubsystem, shooter, convey));
                /*
                 * autoChooser.addOption(
                 * "Left Climb Auto",
                 * new LeftClimbAuto(driveSubsystem, fuelSubsystem, shooter, convey));
                 * autoChooser.addOption(
                 * "Center Climb Auto",
                 * new CenterClimbAuto(driveSubsystem, fuelSubsystem, shooter, convey));
                 * autoChooser.addOption(
                 * "Right Climb Auto",
                 * new RightClimbAuto(driveSubsystem, fuelSubsystem, shooter, convey));
                 */
                autoChooser.addOption(
                                "Center Auto Back To Start Position",
                                new CABTSP(driveSubsystem, fuelSubsystem, shooter, convey));
                autoChooser.addOption(
                                "Right Auto To Center Start Position",
                                new RATCSP(driveSubsystem, fuelSubsystem, shooter, convey, intakeRotater));
                autoChooser.addOption(
                                "Left Auto To Center Start Position",
                                new LATCSP(driveSubsystem, fuelSubsystem, shooter, convey, intakeRotater));
                /*
                 * autoChooser.addOption(
                 * "Right Auto To Player Pickup And Shoot",
                 * new RATPPAS(driveSubsystem, fuelSubsystem, shooter, convey));
                 */
                autoChooser.addOption(
                                "Center Auto Back And Stop Intake Up",
                                new CABAS(driveSubsystem, fuelSubsystem, shooter, convey));
                autoChooser.addOption(
                                "Right Auto And Stop Intake Up",
                                new RAAS(driveSubsystem, fuelSubsystem, shooter, convey, intakeRotater));
                autoChooser.addOption(
                                "Left Auto And Stop Intake Up",
                                new LAAS(driveSubsystem, fuelSubsystem, shooter, convey, intakeRotater));
                autoChooser.addOption(
                                "Center Auto Back And Stop Intake Down",
                                new CABASD(driveSubsystem, fuelSubsystem, shooter, convey,intakeRotater));
                autoChooser.addOption(
                                "Right Auto And Stop Intake Down",
                                new RAASD(driveSubsystem, fuelSubsystem, shooter, convey, intakeRotater));
                autoChooser.addOption(
                                "Left Auto And Stop Intake Down",
                                new LAASD(driveSubsystem, fuelSubsystem, shooter, convey, intakeRotater));

                SmartDashboard.putData("Auto Mode", autoChooser);
        }

        private void configureBindings() {
                ShootCommandGenerator SCG = new ShootCommandGenerator(shooter, convey);
                /*
                 * operatorController.leftTrigger()
                 * .whileTrue(new IntakeCommand(fuelSubsystem));
                 * 
                 * operatorController.rightTrigger()
                 * .onTrue(shooter.shootCommand())
                 * .onFalse(shooter.idleCommand());
                 * 
                 * operatorController.leftBumper()
                 * .onTrue(shooter.stopCommand());
                 * 
                 * operatorController.rightBumper()
                 * .whileTrue(convey.ConveyorForword())
                 * .whileFalse(convey.stop());
                 * operatorController.povUp()
                 * .whileTrue(convey.ConveyorBackword())
                 * .whileFalse(convey.stop());
                 * 
                 * operatorController.a()
                 * .whileTrue(climb.climbUp())
                 * .onFalse(climb.stopClimb());
                 * operatorController.b()
                 * .whileTrue(climb.climbDown())
                 * .onFalse(climb.stopClimb());
                 * 
                 * operatorController.x()
                 * .whileTrue(intakeRotater.rotateIntakeUp());
                 * 
                 * operatorController.y()
                 * .whileTrue(intakeRotater.rotateIntakeDown());
                 */
                driveSubsystem.setDefaultCommand(
                                new Drive(driveSubsystem, driverController));

                fuelSubsystem.setDefaultCommand(
                                fuelSubsystem.run(() -> fuelSubsystem.stop()));

                operatorController.leftTrigger()
                                .toggleOnTrue(new IntakeCommand(fuelSubsystem));

                operatorController.rightTrigger()
                                .whileTrue(SCG.shootCommand())
                                .whileFalse(shooter.idleCommand());
                operatorController.rightBumper()
                                .whileTrue(convey.ConveyorBackword());
                operatorController.povUp()
                                .whileTrue(climb.climbUp());
                operatorController.povDown()
                                .whileTrue(climb.climbDown());
                operatorController.y()
                                .whileTrue(intakeRotater.rotateIntakeUp());
                operatorController.x()
                                .whileTrue(intakeRotater.rotateIntakeDown());
                operatorController.leftStick().or(operatorController.rightStick().or(operatorController.leftBumper()))
                                .onTrue(shooter.stopCommand());
                operatorController.a()
                                .whileTrue(shooter.shootCommand());
                operatorController.b()
                                .whileTrue(shooter.idleCommand());
        }

        public Command getAutonomousCommand() {
                return autoChooser.getSelected();
        }
}