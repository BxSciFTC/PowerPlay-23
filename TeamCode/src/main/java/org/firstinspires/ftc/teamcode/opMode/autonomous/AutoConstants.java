package org.firstinspires.ftc.teamcode.opMode.autonomous;

import com.acmerobotics.roadrunner.geometry.Pose2d;


/*
* For simplicity, the frame of reference is the robot itself
* The origin starts at the left and bottom most corner relative to the robot
* up is y, right is x
* */
public class AutoConstants {
    //TODO: MEASUREMENTS
    //distance from the center of rotation to the back of the robot, inches
    public static double DistCenterBack = 40;
    public static double width = 20;
    public static double boxSize = 24;

    //TODO: Always calculate and measure
    public static double distEdgeBot = 1.5*(boxSize)-(width/2);

    //y is right to left, x is forward to back
    public static double L_START_X = DistCenterBack;
    public static double L_START_Y = 1.5*boxSize;
    public static double R_START_X = DistCenterBack;
    public static double R_START_Y = 4.5*boxSize;

    //TODO: Find values
    public static double L_PICKUP_X = 37.35;
    public static double L_PICKUP_Y = 12;
    public static double R_PICKUP_X = 37.35;
    public static double R_PICKUP_Y = 12;
    public static double FORWARD_SHIFT = 3.25;
    public static double L_PICKUP_HEADING = Math.toRadians(180);
    public static double R_PICKUP_HEADING = Math.toRadians(0);

    //TODO: Find values
    public static double L_SCORE_X = 37.35;
    public static double L_SCORE_Y = 12;
    public static double R_SCORE_X = 37.35;
    public static double R_SCORE_Y = 12;
    public static double SCORE_HEADING = Math.toRadians(0);

    public static double START_HEADING = Math.toRadians(90);

    public static final Pose2d L_START = new Pose2d(L_START_X, L_START_Y, START_HEADING);
    public static final Pose2d R_START = new Pose2d(R_START_X, R_START_Y, START_HEADING);

    public static final Pose2d L_PICKUP_PRE = new Pose2d(L_PICKUP_X, L_PICKUP_Y, L_PICKUP_HEADING);
    public static final Pose2d R_PICKUP_PRE = new Pose2d(R_PICKUP_X, R_PICKUP_Y, R_PICKUP_HEADING);
    public static final Pose2d L_PICKUP_POST = new Pose2d(L_PICKUP_X, L_PICKUP_Y-FORWARD_SHIFT, L_PICKUP_HEADING);
    public static final Pose2d R_PICKUP_POST = new Pose2d(R_PICKUP_X, R_PICKUP_Y+FORWARD_SHIFT, R_PICKUP_HEADING);
    public static final Pose2d L_SCORE_PRE = new Pose2d(L_SCORE_X, L_SCORE_Y, SCORE_HEADING);
    public static final Pose2d R_SCORE_PRE = new Pose2d(R_SCORE_X, R_SCORE_Y, SCORE_HEADING);
    public static final Pose2d L_SCORE_POST = new Pose2d(L_SCORE_X+FORWARD_SHIFT, L_SCORE_Y, SCORE_HEADING);
    public static final Pose2d R_SCORE_POST = new Pose2d(R_SCORE_X+FORWARD_SHIFT, R_SCORE_Y, SCORE_HEADING);


    // LEFT PARKING VALUES
    public static double LPL_Y = 0.5*boxSize;
    public static double LPM_Y = 1.5*boxSize;
    public static double LPR_Y = 2.5*boxSize;
    public static double LEFT_PARK_HEADING = Math.toRadians(270);
    // RIGHT PARKING VALUES
    public static double RPL_Y = 3.5*boxSize;
    public static double RPM_Y = 4.5*boxSize;
    public static double RPR_Y = 5.5*boxSize;

    public static double PARK_X = 12;
    public static double RIGHT_PARK_HEADING = Math.toRadians(270);

    //parking
    public static final Pose2d L_PARK_LEFT = new Pose2d(PARK_X, LPL_Y, LEFT_PARK_HEADING);
    public static final Pose2d L_PARK_MIDDLE = new Pose2d(PARK_X, LPM_Y, LEFT_PARK_HEADING);
    public static final Pose2d L_PARK_RIGHT = new Pose2d(PARK_X, LPR_Y, LEFT_PARK_HEADING);
    public static final Pose2d R_PARK_LEFT = new Pose2d(PARK_X, RPL_Y, RIGHT_PARK_HEADING);
    public static final Pose2d R_PARK_MIDDLE = new Pose2d(PARK_X, RPM_Y, RIGHT_PARK_HEADING);
    public static final Pose2d R_PARK_RIGHT = new Pose2d(PARK_X, RPR_Y, RIGHT_PARK_HEADING);
}
