// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private final SparkMax shooterMotor =
            new SparkMax(Constants.Shooter.MOTOR_ID, MotorType.kBrushless);

    public void shoot() {
        shooterMotor.set(Constants.Shooter.SPEED);
    }

    public void stop() {
        shooterMotor.stopMotor();
    }
}