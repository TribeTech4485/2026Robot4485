// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    // Motor controller IDs for drivetrain motors
    public static final int LEFT_LEADER_ID = 1;
    public static final int LEFT_FOLLOWER_ID = 2;
    public static final int RIGHT_LEADER_ID = 3;
    public static final int RIGHT_FOLLOWER_ID = 4;

    // Current limit for drivetrain motors. Typical is 40-60 for NEO motors and
    // 20-40 for NEO 550 motors. Set to 40 to limit power draw for now.
    public static final int DRIVE_MOTOR_CURRENT_LIMIT = 35;
  }

  public static final class FuelConstants {
    // Motor controller IDs for Fuel Mechanism motors
    public static final int SHOOTER_MOTOR_ID = 5;
    public static final int INTAKE_MOTOR_ID = 7;

    public static final int FEEDER_MOTOR_ID = 6;
    public static final int CONVEY_MOTOR_ID = 8;

    // Current limit and nominal voltage for fuel mechanism motors.
    public static final int FEEDER_MOTOR_CURRENT_LIMIT = 40;
    public static final int LAUNCHER_MOTOR_CURRENT_LIMIT = 40;
    public static final int INTAKE_MOTOR_CURRENT_LIMIT = 40;
    public static final int CONVEYOR_MOTOR_CURRENT_LIMIT = 40;
    // Voltage values for various fuel operations. These values may need to be tuned
    // based on exact robot construction.
    // See the Software Guide for tuning information
    public static final double FEEDER_VOLTAGE = 12;
    public static final double INTAKING_VOLTAGE = 10;
    public static final double LAUNCHING_VOLTAGE = 9;
    public static final double CONVEYOR_VOLTAGE = 10.6;

    public static final double SPIN_UP_FEEDER_VOLTAGE = -6;
    public static final double SPIN_UP_SECONDS = 1;
  }

  public static final class OperatorConstants {
    // Port constants for driver and operator controllers. These should match the
    // values in the Joystick tab of the Driver Station software
    public static final int DRIVER_CONTROLLER_PORT = 0;
    public static final int OPERATOR_CONTROLLER_PORT = 1;

    // This value is multiplied by the joystick value when rotating the robot to
    // help avoid turning too fast and beign difficult to control
    public static final double DRIVE_SCALING = .7;
    public static final double ROTATION_SCALING = .8;
  }

  public static final class ClimberConstants {
    public static final int LEFT_CLIMBER_ID = 10;
    public static final int RIGHT_CLIMBER_ID = 11;
    public static final int CLIMBER_VOLTAGE = 40;
  }

  public static final class IntakeConstants {
    public static final int INTAKE_ROTATER_ID = 9;
    public static final int INTAKE_ROTATER_CURRENT_LIMIT = 40;
    public static final double positionConversionFactor = 1 / 17d * 2;
    public static final double kP = 10; // 10
    public static final double kI = 0;
    public static final double kD = 0;
    public static final double kS = 0;
    public static final double kV = 0;
    public static final double kA = 0;
    public static final double kG = 0;
    public static final double MAXVELOCITY = 6;
    public static final double MAXACCELERATION = 10;
    public static final double MAX_UPPER = Math.PI / 2 + 0.2;
    public static final double STARTING_POS = Math.PI / 2;
    public static final double MAX_LOWER = -0.1;
    // 14.808897018432617
  }
}
