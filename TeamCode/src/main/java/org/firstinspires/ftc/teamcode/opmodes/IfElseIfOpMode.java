package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class IfElseIfOpMode extends OpMode {
    double speedfForward = -gamepad1.left_stick_y;

    @Override
    public void init() {


    }

    @Override
    public void loop() {

        if(gamepad1.left_stick_y < -0.5) {
            telemetry.addData("Left stick", "is negative and large");
            speedfForward = speedfForward * 2;
        }
        else if (gamepad1.left_stick_y < 0 ){
            telemetry.addData("Left stick", "is negative and small");
        }
        else if (gamepad1.left_stick_y < 0.5 ){
            telemetry.addData("Left stick", "is positive and small");
        }
        else {
            telemetry.addData("Left stick", " is positive and large");
            speedfForward *= 2;
        }
        telemetry.addData("Left stick y", gamepad1.left_stick_y);
    }
}
