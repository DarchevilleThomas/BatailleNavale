package fr.univartois.butinfo.ihm;

import java.util.Objects;

public class BoatPart {
	// Attributs
	private Boat boat;
	private int position;
	private boolean tirAlready;
	
	/// Constructeurs
	public BoatPart(Boat boat, int position) {
		this.boat = boat;
		this.position = position;
		this.tirAlready = false;
	}
	
	
	// Méthodes
	public void shootBoatPart() {
		boat.shootBoat(position);
	}
	
	public boolean getTirAlready() {
		return tirAlready;
	}


	public void setTirAlready(boolean tirAlready) {
		this.tirAlready = tirAlready;
	}


	public boolean partBoatCoule() {
		 return boat.boatCoule();
	}

	public BoatType typeDuBoat() {
		return boat.getBoatType();
	}


	// ToString
	@Override
	public String toString() {
		return  boat + "position = " + position ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(boat, position);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BoatPart other = (BoatPart) obj;
		return Objects.equals(boat.getBoatType(), other.boat.getBoatType()) && position == other.position;
	}
	
	
	
}
