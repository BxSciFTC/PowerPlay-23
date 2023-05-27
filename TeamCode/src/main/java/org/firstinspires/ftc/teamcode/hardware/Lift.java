package org.firstinspires.ftc.teamcode.hardware;

import com.acmerobotics.roadrunner.control.PIDCoefficients;
import com.acmerobotics.roadrunner.control.PIDFController;
import com.acmerobotics.roadrunner.profile.MotionProfile;
import com.acmerobotics.roadrunner.profile.MotionProfileGenerator;
import com.acmerobotics.roadrunner.profile.MotionState;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Lift extends MechanismInherit {

    HardwareMap hwMap;

    DcMotorEx lift;

    //position heights
    //TODO: empirically get heights to place cones
    public static final double low = 0;
    public static final double mid = 0;
    public static final double high = 0;
    public static final double bottom = 0;

    public static double target = 0;
    public static double prevPos = target;
    public static boolean reachedPos = false;
    public static double tolerance = 30;


    //RoadRunner
    //TODO: figure out PID constants
    public static double kG = -0.2;
    public static double kP = 0.008;
    public static double kI = 0;
    public static double kD = 0;

    public static PIDCoefficients coeff = new PIDCoefficients(kP, kI, kD);
    //                                                                                  accounts for the force of gravity
    public static PIDFController controller = new PIDFController(coeff, 0, 0, 0, (position, velocity) -> kG);
    public static MotionProfile profile;
    ElapsedTime PIDTimer = new ElapsedTime();
    public static int MAX_VEL = 60;
    public static int MAX_ACCEL = 60;


    @Override
    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;
        lift = hwMap.get(DcMotorEx.class, "lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //TODO: check if need to reverse direction
    }
    //TODO: Implement all states in LiftFSM, as well as
    public void setTargetPos(double pos) {
        if (target == pos) return;
        else reachedPos = false;

        target = pos;

        //TODO: Determine if a max jerk last parameter is needed
        profile = MotionProfileGenerator.generateSimpleMotionProfile(
                new MotionState(prevPos, 0, 0),
                new MotionState(target, 0, 0),
                MAX_VEL,
                MAX_ACCEL
        );
        PIDTimer.reset();
        prevPos = target;
    }

    public void PIDUpdate() {
        MotionState state = profile.get(PIDTimer.seconds());
        controller.setTargetPosition(state.getX());
        controller.setTargetVelocity(state.getV());
        controller.setTargetAcceleration(state.getA());
        double power = controller.update(lift.getCurrentPosition());
        lift.setPower(power);
    }
    public void setPower(double power) {
        lift.setPower(power);
    }

    public boolean targetReached() {
        return (Math.abs(lift.getCurrentPosition()-target) < tolerance)
                && prevPos == target
                && lift.getVelocity() < 0.2; //ensure that there is not too much mechanical stress
    }
}
