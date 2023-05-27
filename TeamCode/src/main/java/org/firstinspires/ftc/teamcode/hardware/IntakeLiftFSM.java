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
        IDLE,
        PICK_BOTTOM,
        LOW,
        MED,
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

}
