package frc.robot.commands;

import frc.robot.subsystems.*;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;

public class AprilTagAlignCommand extends Command {

    private final DriveSubsystem drive;
    private final VisionSubsystem vision;

    private final PIDController turnPID =
            new PIDController(0.03, 0, 0.002);

    public AprilTagAlignCommand(DriveSubsystem drive,
                                VisionSubsystem vision) {
        this.drive = drive;
        this.vision = vision;

        turnPID.setTolerance(1.0);
        turnPID.enableContinuousInput(-180, 180);

        addRequirements(drive);
    }

    @Override
    public void initialize() {
        turnPID.reset();
    }

    @Override
    public void execute() {

        if (!vision.hasTarget()) {
            drive.driveArcade(0, 0);
            return;
        }

        double yaw = vision.getTargetYaw();
        double turn = turnPID.calculate(yaw, 0);

        turn = MathUtil.clamp(turn, -0.4, 0.4);

        drive.driveArcade(turn, 0);
    }

    @Override
    public boolean isFinished() {
        return vision.hasTarget() && turnPID.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        drive.driveArcade(0, 0);
    }}
