package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp

public class FirstTeleOp extends OpMode {

    public void init(){
        String myName = "Mohammad";

        int teamNumber = 26265;

        telemetry.addData("Hello Driver, ",myName);
        telemetry.addData("team",teamNumber);
    }

    public void loop(){

    }
}
