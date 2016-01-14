/* Copyright (c) 2015 Qualcomm Technologies Inc

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package ftc7729.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * A simple example of a linear op mode that will approach an IR beacon
 */
public class RedMountainAutonomous extends LinearOpMode {

  final static double MOTOR_POWER = 1.0; // Higher values will cause the robot to move faster

  DcMotor motorRight;
  DcMotor motorLeft;
  DcMotor tapeMeasure;

  Servo servo;


  @Override
  public void runOpMode() throws InterruptedException {

    // set up the hardware devices we are going to use
    motorLeft = hardwareMap.dcMotor.get("motor_1");
    motorRight = hardwareMap.dcMotor.get("motor_2");
    tapeMeasure = hardwareMap.dcMotor.get("motor_3");

    servo = hardwareMap.servo.get("servo_1");

    motorLeft.setDirection(DcMotor.Direction.REVERSE);
    tapeMeasure.setDirection(DcMotor.Direction.REVERSE);

    // wait for the start button to be pressed
    waitForStart();
    sleep(10000);

    goStraight(2000);
    turnLeft(300);
    goStraight(450);
    turnLeft(485);
    goStraight(1000);
    //sleep(500);
    //extendTapeMeasure(1000);
    //retractTapeMeasure(500);

  }

  public void turnLeft(int i) throws InterruptedException {
    for (int p = 0; p < i; p++) {
      motorLeft.setPower(-MOTOR_POWER);
      motorRight.setPower(MOTOR_POWER);
      sleep(1);
      telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", MOTOR_POWER));
    }
  }

  public void turnRight(int i) throws InterruptedException {
    for (int p = 0; p < i; p++) {
      motorLeft.setPower(MOTOR_POWER);
      motorRight.setPower(-MOTOR_POWER);
      sleep(1);
      telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", MOTOR_POWER));
    }
  }

  public void goStraight(int i) throws InterruptedException {
    for(int p = 0; p < i; p++) {
      motorRight.setPower(MOTOR_POWER);
      motorLeft.setPower(MOTOR_POWER);
      sleep(1);
      telemetry.addData("right tgt pwr", "right pwr: " + String.format("%.2f", MOTOR_POWER));
    }
  }

  public void extendTapeMeasure(int i) throws InterruptedException {
    servo.setPosition(0.60);
    for (int p = 0; p < i; p++){
      tapeMeasure.setPower(MOTOR_POWER);
      sleep(1);
    }
  }

  public void retractTapeMeasure(int i) throws InterruptedException {
    servo.setPosition(0.80);
    for (int p = 0; p < i; p++){
      tapeMeasure.setPower(MOTOR_POWER);
      sleep(1);
    }
  }

}