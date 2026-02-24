// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

public final class Constants {

    public static final class Drive {

        public static final int LEFT_FRONT_ID = 1;
        public static final int LEFT_REAR_ID = 2;
        public static final int RIGHT_FRONT_ID = 3;
        public static final int RIGHT_REAR_ID = 4;

        public static final int PIGEON_ID = 0;

        public static final double MAX_SPEED = 0.8;
        public static final double MAX_ROTATION = 0.6;
    }

    public static final class Intake {

        public static final int MOTOR_ID = 5;
        public static final double SPEED = 0.7;
    }

    public static final class Conveyor {

        public static final int MOTOR_ID = 6;
        public static final double SPEED = 0.7;
    }

    public static final class Shooter {

        public static final int MOTOR_ID = 7;
        public static final double SPEED = 1.0;
    }
}