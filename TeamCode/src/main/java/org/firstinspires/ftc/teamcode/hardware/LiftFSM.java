package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class LiftFSM extends MechanismInherit {

    HardwareMap hwMap;
    Lift lift = new Lift();

    public enum States {
        LOW,
        MID,
        HIGH,
        BOTTOM,
    }
    public States state;

    @Override
    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;
        lift.init(hwMap);
        state = States.BOTTOM;
    }

    public void run() {
        switch(state) {
            case LOW:
                lift.setTargetPos(Lift.low);
                break;
            case MID:
                lift.setTargetPos(Lift.mid);
                break;
            case HIGH:
                lift.setTargetPos(Lift.high);
                break;
            case BOTTOM:
                lift.setTargetPos(Lift.bottom);
                break;
        }
        if (!lift.targetReached())
            lift.PIDUpdate();
        else
            lift.setPower(0);
    }

    public void high() {
        state = state.HIGH;
    }
    public void mid() {
        state = state.MID;
    }
    public void low() {
        state = state.LOW;
    }
    public void rest() {
        state = state.BOTTOM;
    }

    public boolean targetReached() {
        return lift.targetReached();
    }
}