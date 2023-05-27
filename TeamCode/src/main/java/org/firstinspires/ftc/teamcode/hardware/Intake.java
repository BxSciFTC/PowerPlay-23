package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake extends MechanismInherit {
    HardwareMap hwMap;
    public Servo claw;

    //get the constants for the claw open/closing
    public static double close = 0.04;
    public static double open = 0.25;

    public static double tolerance = 50;
    public boolean isOpen = false;
    public boolean isUp = false;
    public boolean isPlacing = false;

    @Override
    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;
        hwMap.get(Servo.class, "jaw");
    }


    public void open() {
        claw.setPosition(open);
        isOpen = true;
    }

    public void close() {
        claw.setPosition(close);
        isOpen = false;
    }
    public boolean targetReached() {
        return Math.abs(claw.getPosition()-(isOpen ? open : close)) < tolerance;
    }
}
