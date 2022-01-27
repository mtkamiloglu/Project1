import java.util.Comparator;

public class HouseComparator implements Comparator<House> {

	@Override
	public int compare(House h1, House h2) {
		return h1.compareTo(h2);
	}
}
