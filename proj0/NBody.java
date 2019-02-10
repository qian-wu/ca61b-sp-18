public class NBody{
	public static double readRadius(String path) {
		In in = new In(path);
		int planetCount = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Planet[] readPlanets(String path) {
		In in = new In(path);
		int planetCount = in.readInt();
		double radius = in.readDouble();
		Planet[] planets = new Planet[planetCount];
		for(int i = 0; i < planetCount; i ++) {
			double xP = in.readDouble();
			double yP = in.readDouble();
			double xV = in.readDouble();
			double yV = in.readDouble();
			double m = in.readDouble();
			String img = in.readString();

			Planet p = new Planet(xP, yP, xV, yV, m, img);
			planets[i] = p;
		}
		return planets;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		Planet[] planets = readPlanets(filename); // read config file to create planets array

		double radius = readRadius(filename);
		StdDraw.setXscale(-1 * radius, radius);
		StdDraw.setYscale(-1 * radius, radius);
		StdDraw.enableDoubleBuffering();
		

		double time = 0;
		double[] netForceX = new double[planets.length];
		double[] netForceY = new double[planets.length];

		// StdAudio.play("audio/2001.mid");

		while(time < T) {
			for(int i = 0; i < planets.length; i ++ ) {
				netForceX[i] = planets[i].calcNetForceExertedByX(planets);
				netForceY[i] = planets[i].calcNetForceExertedByY(planets);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");
			for(int j = 0; j < planets.length; j ++ ) {
				Planet p = planets[j];
				p.update(dt,netForceX[j], netForceY[j]);
				StdDraw.picture(p.xxPos, p.yyPos, "images/" + p.imgFileName);
			}
			
			time = time + dt;
			StdDraw.show();
			StdDraw.pause(10);
		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
		                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}


	}
}