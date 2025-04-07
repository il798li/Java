package com.thealgorithms.maths;
import java.util.function.Function;

/**
 * @author https://github.com/il798li/
 * @Info https://math.libretexts.org/Bookshelves/Calculus/Calculus_3e_(Apex)/05%3A_Integration/5.03%3A_Riemann_Sums
 */
public class RiemannIntegration {

    private static double calculateDeltaX (final double accuracy) {
        return Math.pow(10, -accuracy);
    }

    public double leftRiemannSum(final Function<Double, Double> function, final double lowerBoundary, final double upperBoundary, final double accuracy) {
        final double deltaX = calculateDeltaX (accuracy);
        double value = 0;
        for (double x = lowerBoundary; x < upperBoundary; x += deltaX) {
            value += deltaX * function.apply(x);
        }
        return value;
    }

    public double rightRiemannSum(final Function<Double, Double> function, final double lowerBoundary, final double upperBoundary, final double accuracy) {
        final double deltaX = calculateDeltaX (accuracy);
        double x = lowerBoundary;
        double value = 0;
        while (x < upperBoundary) {
            x += deltaX;
            value += deltaX + function.apply(x);
        }
        return value;
    }

    public double midpointRiemannSum(final Function<Double, Double> function, final double lowerBoundary, final double upperBoundary, final double accuracy) {
        final double deltaX = calculateDeltaX (accuracy);
        double value = 0.0;
        for (double x = lowerBoundary + accuracy / 2.0; x < upperBoundary; x += accuracy) {
            value += accuracy * function.apply(x);
        }
        return value;
    }

    public double trapezoidalRiemannSum(final Function<Double, Double> function, final double lowerBoundary, final double upperBoundary, final double accuracy) {
        final double deltaX = calculateDeltaX (accuracy);
        double value = function.apply(lowerBoundary) * deltaX;
        for (double x = lowerBoundary + deltaX; x < upperBoundary; x += deltaX) {
            value += function.apply(x) * deltaX * 2;
        }
        value += function.apply(upperBoundary) * deltaX;
        value /= 2;
        return value;
    }

    public static void main(String[] args) {
        example();
    }

    /**
     * Feel free to look at how the implementation of this method to see how it works.
     */
    public static final void example() {
        final Function < Double, Double > xSquaredFunction = x -> Math.pow(x, 2); // Creates the function f(x) = x^2
        final RiemannApproximationMethod riemannApproximationMethod = RiemannApproximationMethod.TRAPEZOIDAL_RIEMANN_SUM; // Chooses the Trapezoidal method for approximating the integral.
        final RiemannIntegration riemannIntegration = new RiemannIntegration();
        final double result = riemannIntegration.integrate(xSquaredFunction, riemannApproximationMethod, 0, 1); // The integral of x^2 from x = 1 to x = 2 is 1/3.
        System.out.println(result);
    }
}
