package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {

    private boolean hasTarget = true;
    private double yaw = 0;

    public boolean hasTarget() {
        return hasTarget;
    }

    public double getTargetYaw() {
        return yaw;
    }

    public void update(boolean hasTarget, double yaw) {
        this.hasTarget = hasTarget;
        this.yaw = yaw;
    }
}