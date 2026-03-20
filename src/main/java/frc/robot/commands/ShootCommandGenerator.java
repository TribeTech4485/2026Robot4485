// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Shooter;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class ShootCommandGenerator {
  /** Creates a new ShootCommand. */
  Shooter shooter;
  Conveyor convey;

  public ShootCommandGenerator(Shooter shooter, Conveyor convey) {
    this.shooter = shooter;
    this.convey = convey;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  public Command shootCommand() {
    return new SequentialCommandGroup(
        shooter.shootCommand(),
        shooter.waitTillSpeed(),
        convey.ConveyorForword(),
        new WaitUntilCommand(() -> false))
        .finallyDo(() -> { // When interrupted
          shooter.idleCommand().execute();
          convey.stop().execute();
        });
  }
}
