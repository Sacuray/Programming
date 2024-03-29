
package common.data;

import java.io.Serializable;
import java.util.Objects;

/**
 * X-Y coordinates.
 */
public class Coordinates implements Serializable {
    private float x;
    private Float y;

    public Coordinates(float x, Float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return X-coordinate.
     */
    public float getX() {
        return x;
    }

    /**
     * @return Y-coordinate.
     */
    public Float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x, x) == 0 &&
                Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}