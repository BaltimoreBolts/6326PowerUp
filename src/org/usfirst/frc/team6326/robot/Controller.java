package org.usfirst.frc.team6326.robot;

public class Controller {
	public class XBOX {
		public static final int A = 1;
		public static final int B = 2;
		public static final int X = 3;
		public static final int Y = 4;
		public static final int BACK = 7;
		public static final int START = 8;
		
		public class BUMPER {
			public static final int LEFT = 5;
			public static final int RIGHT = 6;
		}
		
		// handy boolean conversion
		// if (controller.getPOV() == Controller.DPAD.UP) == true
		public class DPAD {
			public static final int UP = 0;
			public static final int UP_RIGHT = 45;
			public static final int RIGHT = 12;
			public static final int DOWN_RIGHT = 135;
			public static final int DOWN = 180;
			public static final int DOWN_LEFT = 225;
			public static final int LEFT = 270;
			public static final int UP_LEFT = 315;
		}
		
		public class TRIGGER {
			public static final int LEFT = 2;
			public static final int RIGHT = 3;
		}
		
		public class STICK {
			public class LEFT {
				public static final int X = 0;
				public static final int Y = 1;
			}
			
			public class RIGHT {
				public static final int X = 4;
				public static final int Y = 5;
			}
		}
	}
}
