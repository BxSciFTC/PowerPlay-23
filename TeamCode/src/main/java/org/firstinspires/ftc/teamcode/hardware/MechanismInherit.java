package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;


/*
All mechanisms of the robot should extend this class
    That is, hardware classes which are in the "hardware" folder
It ensures that all such classes use the Hardware Map
 */

public abstract class MechanismInherit {

    /*
    Initializes all the robot's hardware
    Gets and stores references to the robot configuration
        As in, gets and stores the names of each part, and where they are connected on the control/expansion hub
     */
    public abstract void init(HardwareMap hwMap);
}
