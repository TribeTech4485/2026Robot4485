package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DriveForwardAutoCommand extends Command {

    private final DriveSubsystem drive;

    private final long duration;
    private long startTime;

    public DriveForwardAutoCommand(
            DriveSubsystem drive,
            double seconds
    ) {

        this.drive = drive;

        this.duration = (long)(seconds * 1000);

        addRequirements(drive);

    }

    @Override
    public void initialize() {

        startTime = System.currentTimeMillis();

    }

    @Override
    public void execute() {

        drive.tankDrive(0.4, 0.4);

    }

    @Override
    public boolean isFinished() {

        return System.currentTimeMillis() - startTime >= duration;

    }

    @Override
    public void end(boolean interrupted) {

        drive.stop();

    }

}