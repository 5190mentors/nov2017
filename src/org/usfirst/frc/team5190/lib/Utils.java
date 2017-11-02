package org.usfirst.frc.team5190.lib;

public class Utils {
	public static double ticksToInches(int encPosition, int driveTicksPerRotation, double wheelRadius) {
		double rotations = (double) encPosition / (double) driveTicksPerRotation;
		double distancePerRotation = wheelRadius * 2 * Math.PI;
		return rotations * distancePerRotation;
	}
	
	public static double feetPerSecondToRPM(double feetPerSecond, double wheelRadius) {
		return (feetPerSecond * 12.0) / (wheelRadius * Math.PI * 2.0) * 60.0;
	}
}
