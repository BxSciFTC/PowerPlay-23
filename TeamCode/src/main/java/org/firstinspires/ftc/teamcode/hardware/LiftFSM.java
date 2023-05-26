package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class LiftFSM extends MechanismInherit {

    HardwareMap hwMap;

    public enum States {
        LOW,
        MID,
        HIGH,
        REST,
    }
    public States state;

    @Override
    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;
        state = States.REST;
    }

    public void start() {
        switch(state) {
            case LOW:
                break;
            case MID:
                break;
            case HIGH:
                break;
            case REST:
                break;
        }
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
        state = state.REST;
    }

}