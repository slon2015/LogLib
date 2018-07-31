package Events;

@FunctionalInterface
public interface MessageAvailableEventListener {
	public void MessageAvailable(MessageAvailableEvent e);
}
