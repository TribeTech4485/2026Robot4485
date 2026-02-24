package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ConveyorSubsystem extends SubsystemBase {

    private final SparkMax motor =
            new SparkMax(Constants.Conveyor.MOTOR_ID, MotorType.kBrushless);

    public void runForward() {
        motor.set(Constants.Conveyor.SPEED);
    }

    public void stop() {
        motor.stopMotor();
    }
}