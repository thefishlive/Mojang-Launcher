package io.github.thefishlive.minecraft.profiles.exception;

public class LauncherDataException extends RuntimeException {

	private static final long serialVersionUID = -7873435183838154083L;

	public LauncherDataException(String message) {
		super(message);
	}

	public LauncherDataException(Throwable caause) {
		super(caause);
	}

	public LauncherDataException(String message, Throwable cause) {
		super(message, cause);
	}
}
