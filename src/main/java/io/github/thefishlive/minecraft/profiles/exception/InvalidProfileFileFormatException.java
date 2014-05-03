package io.github.thefishlive.minecraft.profiles.exception;

public class InvalidProfileFileFormatException extends LauncherDataException {

	private static final long serialVersionUID = -1239490874081255476L;

	public InvalidProfileFileFormatException(String message) {
		super(message);
	}
	
	public InvalidProfileFileFormatException(Throwable caause) {
		super(caause);
	}

}
