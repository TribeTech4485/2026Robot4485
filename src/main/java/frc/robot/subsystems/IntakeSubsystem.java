package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class IntakeSubsystem extends SubsystemBase {

    private final SparkMax intakeMotor =
            new SparkMax(3, MotorType.kBrushless);

    public void run(double speed) {

        intakeMotor.set(speed);

    }

    public void stop() {

        intakeMotor.set(0);

    }

}