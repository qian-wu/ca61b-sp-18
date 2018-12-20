public class NBody{
	public static double readRadius(String path) {
		In in = new In(path);
		int planetCount = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
}