package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants;

public class DriveSubsystem extends SubsystemBase {

    private final SparkMax leftFront =
            new SparkMax(Constants.Drive.LEFT_FRONT_ID, MotorType.kBrushless);

    private final SparkMax leftRear =
            new SparkMax(Constants.Drive.LEFT_REAR_ID, MotorType.kBrushless);

    private final SparkMax rightFront =
            new SparkMax(Constants.Drive.RIGHT_FRONT_ID, MotorType.kBrushless);

    private final SparkMax rightRear =
            new SparkMax(Constants.Drive.RIGHT_REAR_ID, MotorType.kBrushless);

    private final Pigeon2 gyro =
            new Pigeon2(Constants.Drive.PIGEON_ID);

    public DriveSubsystem() {}

    public void arcadeDrive(double forward, double rotation) {

        double left = forward + rotation;
        double right = forward - rotation;

        tankDrive(left, right);
    }

    public void tankDrive(double left, double right) {

        leftFront.set(left);
        leftRear.set(left);

        rightFront.set(-right);
        rightRear.set(-right);
    }

    public void stop() {

        leftFront.stopMotor();
        leftRear.stopMotor();
        rightFront.stopMotor();
        rightRear.stopMotor();
    }

    public void zeroGyro() {
        gyro.reset();
    }

    public double getHeading() {
        return gyro.getYaw().getValueAsDouble();
    }
}