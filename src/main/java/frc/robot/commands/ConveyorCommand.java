// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


package frc.robot.commands;

import frc.robot.subsystems.ConveyorSubsystem;

import edu.wpi.first.wpilibj2.command.Command;

public class ConveyorCommand extends Command {

    private final ConveyorSubsystem conveyor;

    public ConveyorCommand(ConveyorSubsystem conveyor) {

        this.conveyor = conveyor;

        addRequirements(conveyor);

    }

    @Override
    public void execute() {

        conveyor.runForward();

    }

    @Override
    public void end(boolean interrupted) {

        conveyor.stop();

    }

}