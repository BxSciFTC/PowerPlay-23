package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

/*
*
* This is the master class that encapsulates both the
* intake and the lift to coordinate movement and timing
*
*
* */
public class IntakeLiftFSM extends MechanismInherit {
    HardwareMap hwMap;
    IntakeFSM intake = new IntakeFSM();
    LiftFSM lift = new LiftFSM();

    //TODO: fill states
    public enum States {
        BOTTOM_OPEN,
        BOTTOM_CLOSE,
        LOW,
        MID,
        HIGH,
        SCORE,
    }

    public States state;

    @Override
    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;
        intake.init(hwMap);
        lift.init(hwMap);
    }

    public void loop() {
        switch (state) {
            case BOTTOM_OPEN:
                lift.bottom();
                intake.close();
                break;
            case BOTTOM_CLOSE:
                lift.bottom();
                intake.open();
                break;
            case LOW:
                lift.low();
                break;
            case MID:
                lift.mid();
                break;
            case HIGH:
                lift.high();
                break;
            case SCORE:
                intake.open();
                break;
        }
    }
    public void pickUpOpen() {state = States.BOTTOM_OPEN;}
    public void pickUpClose() {state = States.BOTTOM_CLOSE;}
    public void low() {state = States.LOW;}
    public void mid() {state = States.MID;}
    public void high() {state = States.HIGH;}
    public void score() {state = States.SCORE;}
}
