package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.VisionSubsystem;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;

public class AprilTagAlignCommand extends Command {

    private final DriveSubsystem drive;
    private final VisionSubsystem vision;

    private final PIDController turnPID =
        new PIDController(0.02, 0, 0);

    public AprilTagAlignCommand(
        DriveSubsystem drive,
        VisionSubsystem vision
    ) {

        this.drive = drive;
        this.vision = vision;

        addRequirements(drive);
    }

    @Override
    public void execute() {

        if (!vision.hasTarget()) {
            drive.stop();
            return;
        }

        double yaw = vision.getTargetYaw();
        double turn = turnPID.calculate(yaw, 0);

        drive.arcadeDrive(0, turn);
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
    }
}