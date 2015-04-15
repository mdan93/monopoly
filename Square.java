package monopoly;

/**
 * Created by fjricci on 4/7/2015.
 */
public interface Square {
    int position();

    String name();

    boolean isOwnable();

    boolean isOwned();

    boolean isMortgaged();

    int cost();

    void purchase(Player player);

    int rent(int val);

    int mortgageCost();

    int mortgage();

    Player owner();
}
