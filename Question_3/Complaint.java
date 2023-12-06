package Question_3;

public class Complaint {
	private String title;
	private Severity severity;

	public Complaint(String title, Severity severity) {
		if (title != null && severity != null) {
			this.title = title;
			this.severity = severity;
		} else {
			throw new NullPointerException();
		}
	}

	public String getTitle() {
		return title;
	}

	public Severity getSeverity() {
		return severity;
	}
}
