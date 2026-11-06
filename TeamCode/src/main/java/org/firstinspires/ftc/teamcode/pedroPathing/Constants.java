package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("front_left");
        c.frontRightName.set("front_right");
        c.backLeftName.set("back_left");
        c.backRightName.set("back_right");
        c.frontLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
    });
    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-1.9892757145438609);
        c.yPodOffset.set(-0.3880194040733998);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });
    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.3785133616164248);
                Controller secondaryTranslationalForward = Controller.proportional(0.1398505730539732);
                Controller primaryTranslationalLateral = Controller.proportional(5.815149153433483);
                Controller secondaryTranslationalLateral = Controller.proportional(2.1485422285465505);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.013791281765223956));
                c.brake.set(Controller.proportionalFeedforward(0.011722589500440363));

                c.headingFeedback.set(Controller.proportional(6.130265172336954));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.06290861488439232, 0.00700813249588057));

                c.linearBrakeCoefficients.set(Matrix.diag(0.07196063950962062, 0.0426676539248652));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0014329800746813257, 0.0015999116221670363));

                c.maxAchievableForwardVelocity.set(51.50965955417716);
                c.maxAchievableStrafeVelocity.set(31.721375771150825);
                c.naturalForwardDeceleration.set(56.97885327073571);
                c.naturalStrafeDeceleration.set(103.78536311267987);
            }
    );
}