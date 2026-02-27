package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {

    public boolean hasTarget() {
        return false;
    }

    public double getTargetYaw() {
        return 0.0;
    }
}