package seminar.dataflow.zeroanalysis;

/**
 * This enum represents the possible values of the zero analysis for a variable.
 */
public enum ZeroAbstractValue {

    /**
     * We don't have information about the variable.
     */
    BOTTOM("bottom"),

    /**
     * The variable is positive.
     */
    POSITIVE("positive"),

    /**
     * The variable is zero.
     */
    ZERO("zero"),

    /**
     * The variable is negative.
     */
    NEGATIVE("negative"),

    /**
     * The variable is top (can be any value).
     */
    TOP("top");


    /**
     * The name of the ZeroAbstractValue.
     */
    private final String name;

    @Override
    public String toString() {
        return this.name;
    }

    ZeroAbstractValue(String name) {
        this.name = name;
    }

    /**
     * Returns the result of the addition between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the addition.
     */
    public ZeroAbstractValue add(ZeroAbstractValue another) {
        if (this == BOTTOM || another == BOTTOM){ return BOTTOM; }
        if (this == TOP || another == TOP) { return TOP; }
        if (this == ZERO) { return another; }
        if (this == NEGATIVE && another == NEGATIVE) { return NEGATIVE; }
        if (this == POSITIVE && another == POSITIVE) { return POSITIVE; }
        return TOP;
    }

    /**
     * Returns the result of the division between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the division.
     */
    public ZeroAbstractValue divideBy(ZeroAbstractValue another) {
        if (another == ZERO) { return BOTTOM; }
        if (this == BOTTOM || another == BOTTOM) { return BOTTOM; }
        if (this == ZERO) { return ZERO; }
        return TOP;
    }

    /**
     * Returns the result of the multiplication between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the multiplication.
     */
    public ZeroAbstractValue multiplyBy(ZeroAbstractValue another) {
        if (this == BOTTOM || another == BOTTOM) { return BOTTOM; }
        if (this == ZERO || another == ZERO) { return ZERO; }
        if (this == another) { return POSITIVE; }
        if (this == NEGATIVE && another == POSITIVE || this == POSITIVE && another == NEGATIVE) {
            return POSITIVE;
        }
        return TOP;
    }

    /**
     * Returns the result of the subtraction between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the subtraction.
     */
    public ZeroAbstractValue substract(ZeroAbstractValue another) {
        if (this == BOTTOM || another == BOTTOM) { return BOTTOM; }
        if (this == TOP || another == TOP) { return TOP; }
        if (another == ZERO) { return this; }
        if (this == ZERO) {
            if (another == NEGATIVE) { return POSITIVE; }
            if (another == POSITIVE) { return NEGATIVE; }
        }
        if (this == NEGATIVE && another == POSITIVE) { return NEGATIVE; }
        if (this == POSITIVE && another == NEGATIVE) { return POSITIVE; }
        return TOP;
    }

    /**
     * Returns the result of the merge between this ZeroAbstractValue and another.
     * @param another the other ZeroAbstractValue.
     * @return the result of the merge.
     */
    public ZeroAbstractValue merge(ZeroAbstractValue another) {
        if (this == BOTTOM) return another;
        if (another == BOTTOM) return this;
        if (this == another) return this;
        return TOP;
    }

}
