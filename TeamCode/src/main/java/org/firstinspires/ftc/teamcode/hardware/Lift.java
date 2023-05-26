package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Lift extends MechanismInherit {

    HardwareMap hwMap;
    //position heights
    //TODO: empirically get heights to place cones
    public static final double low = 0;
    public static final double mid = 0;
    public static final double high = 0;
    public static final double rest = 0;

    public static double target = 0;
    public static double prevPos = target;


    //RoadRunner
    public static MotionProfile profile;
    public static int MAX_VEL = 60;
    public static int MAX_ACCEL = 60;


    @Override
    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }
    //TODO: Implement all states in LiftFSM, as well as
    //TODO: setting up the PID system to get to those positions using RoadRunner
    public void MoveToPos(double pos) {
        if (target == pos) return;
        target = pos;
        profile = MotionProfileGenerator.generateSimpleMotionProfile(
                new MotionState(prevPos, 0, 0),
                new MotionState(target, 0, 0),
                MAX_VEL,
                MAX_ACCEL
        );
        prevPos = target;
    }
}
