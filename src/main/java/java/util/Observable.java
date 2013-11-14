package java.util;

public class Observable {

	private Vector observers;
	private boolean changed;
	
	public Observable() {
		observers = new Vector();
		changed = false;
	}

	public void addObserver(Observer o) {
		if (!observers.contains(o)) {
			observers.add(o);
		}
	}
	
	public void deleteObserver(Observer o) {
		observers.removeElement(o);
	}
	
	public void deleteObservers() {
		observers.removeAllElements();
	}
	
	public int countObservers() {
		return observers.size();
	}
	
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	public void notifyObservers(Object arg) {
		if (!hasChanged()) {
			return;
		}
		
		for (int i = 0; i < observers.size(); i++) {
			Observer item = (Observer) observers.elementAt(i);
			if (item != null) {
				item.update(this, arg);
			}
		}
		clearChanged();
	}
	
	public void setChanged() {
		changed = true;
	}
	
	public void clearChanged() {
		changed = false;
	}
	
	public boolean hasChanged() {
		return changed;
	}
}
	