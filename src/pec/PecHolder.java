package pec;
/** *
 * This is the place where the pec is stored
 */
/** Interface that holds the {@link PEC} so that it can be referenced where needed and without most classes can be unaware
 * of its existence */
public interface PecHolder {
    PEC pec= new PEC();
}
