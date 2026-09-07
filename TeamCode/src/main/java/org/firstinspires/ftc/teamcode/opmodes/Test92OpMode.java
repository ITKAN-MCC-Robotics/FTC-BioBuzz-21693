package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class Test92OpMode extends OpMode {
    Boolean movement = Boolean.FALSE;

    @Override
    public void init() {

    }

    @Override
    public void loop() {
        if (gamepad1.a){
            movement = Boolean.TRUE;
        } else if (gamepad1.b){
            movement = Boolean.FALSE;
        }
        else if (gamepad1.x) {
            telemetry.addData("Good Job","JK");
        }

        telemetry.addData("Movement :",movement);


    }
}
