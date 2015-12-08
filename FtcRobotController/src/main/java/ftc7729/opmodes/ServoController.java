/* Copyright (c) 2014 Qualcomm Technologies Inc

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

import com.qualcomm.ftccommon.DbgLog;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * TeleOp Mode
 * <p>
 * Enables control of the robot via the gamepad
 */
public class ServoController extends OpMode {
	/**
	 * Manage the aspects of the left hand servo.
	 */
	private Servo v_servo_left_hand;

	//--------------------------------------------------------------------------
	//
	// v_servo_right_hand
	//
	/**
	 * Manage the aspects of the right hand servo.
	 */
	private Servo v_servo_right_hand;
	private boolean v_warning_generated = false;
	private String v_warning_message;

	void m_warning_message (String p_exception_message)

	{
		if (v_warning_generated)
		{
			v_warning_message += ", ";
		}
		v_warning_generated = true;
		v_warning_message += p_exception_message;

	}

	/**
	 * Constructor
	 */
	public ServoController() {

	}

	/*
	 * Code to run when the op mode is first enabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#start()
	 */
	@Override
	public void init() {
		/*
		 * Use the hardwareMap to get the dc motors and servos by name. Note
		 * that the names of the devices must match the names used when you
		 * configured your robot and created the configuration file.
		 */

		/*
		 * For the demo Tetrix K9 bot we assume the following,
		 *   There are two motors "motor_1" and "motor_2"
		 *   "motor_1" is on the right side of the bot.
		 *   "motor_2" is on the left side of the bot.
		 *
		 * We also assume that there are two servos "servo_1" and "servo_6"
		 *    "servo_1" controls the arm joint of the manipulator.
		 *    "servo_6" controls the claw joint of the manipulator.
		 */
		double l_hand_position = 0.5;

		try
		{
			v_servo_left_hand = hardwareMap.servo.get ("left_hand");
			v_servo_left_hand.setPosition (l_hand_position);
		}
		catch (Exception p_exeception)
		{
			m_warning_message ("left_hand");
			DbgLog.msg(p_exeception.getLocalizedMessage());

			v_servo_left_hand = null;
		}

		try
		{
			v_servo_right_hand = hardwareMap.servo.get ("right_hand");
			v_servo_right_hand.setPosition (l_hand_position);
		}
		catch (Exception p_exeception)
		{
			m_warning_message ("right_hand");
			DbgLog.msg (p_exeception.getLocalizedMessage ());

			v_servo_right_hand = null;
		}
	}

	/*
	 * This method will be called repeatedly in a loop
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#run()
	 */
	@Override
	public void loop() {
		if (gamepad2.x)
		{
			v_servo_left_hand.setPosition (Servo.MAX_POSITION);
		}
		else if (gamepad2.b)
		{
			v_servo_left_hand.setPosition (Servo.MIN_POSITION);
		}
		telemetry.addData("Text", "*** Robot Data***");
 		//telemetry.addData("left tgt pwr",  "left  pwr: " + String.format("%.2f", left));
	}

	/*
	 * Code to run when the op mode is first disabled goes here
	 *
	 * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
	 */
	@Override
	public void stop() {

	}
}
