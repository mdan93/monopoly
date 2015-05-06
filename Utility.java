package monopoly;

public class Utility implements Square {
	//rent multiplier, given number of utilities owned by a player
	private final int ONE = 4;
	private final int TWO = 10;
	private final int COST = 150; //cost to purchase utility

	private Dice dice;

	private Player.PlayerType ownerType; //stores utility owner name enum
	private Player owner;     //stores utility owner
	private boolean owned;  //is utility owned?

	private int numOwned; //number of utilities owned by a player
	private boolean mortgaged; //is property mortgaged?

	private String name;
	private int pos;

	private Utility other;

	//utility constructor
	public Utility(String name, int pos) {
		numOwned = 0;
		mortgaged = false;
		this.name = name;
		this.pos = pos;
		this.dice = new InputDice(new Input());
	}

	public void setOther(Utility other) {
		this.other = other;
	}

	public int increasedRent() {
		return 10 * dice.roll().val;
	}

	public int position() {
		return pos;
	}

	public String name() {
		return name;
	}

	public boolean isOwnable() {
		return true;
	}

	//update status of property to owned
	public void purchase(Player player) {
		owned = true;
		owner = player;
		ownerType = player.getPlayer();
		numOwned = 1;
		player.properties().stream().filter(s -> s instanceof Utility).forEach(s -> numOwned++);
	}

	//return rent on utility, given a roll
	public int rent(int roll) {
		if (roll == 0)
			roll = dice.roll().val;

		if (owner.equals(other.owner()))
			return ONE * roll;

		return TWO * roll;
	}

	//return total utilities owned by player owning this utility
	public boolean isOwned() {
		return owned;
	}

	//return owner name enum
	public Player.PlayerType ownerType() {
		return ownerType;
	}

	//return player object of owner
	public Player owner() {
		return owner;
	}

	//return cost to purchase utility
	public int cost() {
		return COST;
	}

	//mortgage property
	public int mortgage() {
		if (mortgaged) {
			mortgaged = false;
			return (int) Math.round((COST / 2) * 1.1);
		} else {
			mortgaged = true;
			return COST / 2;
		}
	}

	public boolean isMortgaged() {
		return mortgaged;
	}

	public int mortgageCost() {
		return COST / 2;
	}

	public String toString() {
		return name;
	}
}