package org.firstinspires.ftc.teamcode.hardware;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeFSM extends MechanismInherit {
    HardwareMap hwMap;
    Intake intake;
    public enum States {
        OPEN,
        CLOSE,
    }
    public States state;

    @Override
    public void init(HardwareMap hwMap) {
        this.hwMap = hwMap;
        intake.init(hwMap);
        state = States.OPEN;
    }
    public void loop() {
        switch(state) {
            case OPEN:
                intake.open();
                break;
            case CLOSE:
                intake.close();
                break;
        }
    }

    public void open() {state = States.OPEN;}
    public void close() {state = States.CLOSE;}

    public boolean targetReached() {
        return intake.targetReached();
    }
}
