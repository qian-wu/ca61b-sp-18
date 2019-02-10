import java.math.*;

public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static double G = 6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double xDist = this.xxPos - p.xxPos;
		double yDist = this.yyPos - p.yyPos;
		return Math.pow(Math.pow(xDist, 2) + Math.pow(yDist, 2), 0.5);
	}

	public double calcForceExertedBy(Planet p) {
		double dist = this.calcDistance(p);
		return G * this.mass * p.mass / Math.pow(dist, 2);
	}

	public double calcForceExertedByX(Planet p) {
		double distX = p.xxPos - this.xxPos;
		return calcForceExertedBy(p) * distX / calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		double distY = p.yyPos - this.yyPos;
		return calcForceExertedBy(p) * distY / calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double netForceX = 0;
		for(int i = 0; i < planets.length; i ++) {
			if(this.equals(planets[i])) {
				continue;
			}
			netForceX = netForceX + calcForceExertedByX(planets[i]);
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		double netForceY = 0;
		for(int i = 0; i < planets.length; i ++) {
			if(this.equals(planets[i])) {
				continue;
			}
			netForceY = netForceY + calcForceExertedByY(planets[i]);
		}
		return netForceY;
	}

	public void update(double time, double forceX, double forceY) {
		double accX = forceX / this.mass;
		double accY = forceY / this.mass;

		this.xxVel = this.xxVel + time * accX;
		this.yyVel = this.yyVel + time * accY;

		this.xxPos = this.xxPos + time * this.xxVel;
		this.yyPos = this.yyPos + time * this.yyVel;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, imgFileName);
	}

	/** use for test
	public static void main(String[] args) {
		Planet samh = new Planet(1, 0, 0, 0, 10, "samh");
		Planet aegir = new Planet(3, 3, 0, 0, 5, "aegir");
		Planet rocinante = new Planet(5, -3, 0, 0, 50, "rocinante");

		Planet[] allPlanets = {samh, rocinante, aegir};
		double netForceX = samh.calcNetForceExertedByX(allPlanets);
		System.out.println(netForceX);
		double netForceY = samh.calcNetForceExertedByY(allPlanets);
		System.out.println(netForceY);
	}
	*/
}














