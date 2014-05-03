package io.github.thefishlive.minecraft.profiles.exception;

public class ProfileFileNotFoundException extends LauncherDataException {

	private static final long serialVersionUID = -7406243941208131678L;

	public ProfileFileNotFoundException() {
		super("The minecraft launcher profiles file could not be found, make sure you have run the installer at least once");
	}

}
