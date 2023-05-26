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

    //TODO: fill states
    public enum States {

    }

    public States state;

    @Override
    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;
    }

}
