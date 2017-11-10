package logic.ants;

public class AntFoodSystem {

	private double maxAmmount,currentAmmount,hungerDecrease,minAfterShare;
	//TODO add eating food / sharing food speed;

	public AntFoodSystem(double maxAmmount, double currentAmmount, double hungerDecrease, double minAfterShare) {
		super();
		this.maxAmmount = maxAmmount;
		this.currentAmmount = currentAmmount;
		this.hungerDecrease = hungerDecrease;
		this.minAfterShare = minAfterShare;
	}
	
	public double Eat(double ammount)
	{
		double avaible = maxAmmount - currentAmmount;
		if(avaible>ammount)
		{
			currentAmmount += ammount;
			return ammount;
		}
		else
		{
			currentAmmount = maxAmmount;
			return avaible;
		}
	}
	
	public double getMaxAmmount() {
		return maxAmmount;
	}

	public double getCurrentAmmount() {
		return currentAmmount;
	}

	public void ShareFood(AntFoodSystem other, double ammount)
	{
		if(other==this)
		{
			return;
		}
		if(currentAmmount -ammount < minAfterShare)
		{
			ammount = currentAmmount - minAfterShare;
		}
		double reallyGiven = other.Eat(ammount);
		currentAmmount -= reallyGiven;
	}

	public void Routine()
	{
		currentAmmount -= hungerDecrease;
	}
}
