/**
 * Created by Shiang Jin, Chin
 */
package instameal.model;

import java.math.BigDecimal;

public class Ingredients{
	protected String ingredientName;
	protected String aliasName;
	protected BigDecimal fatPerG;
	protected BigDecimal sugarPerG;
	protected BigDecimal sodiumPerG;
	protected BigDecimal proteinPerG;
	protected BigDecimal saturatedFatPerG;
	
	public Ingredients(String ingredientName, String aliasName, BigDecimal fatPerG, BigDecimal sugarPerG,
			BigDecimal sodiumPerG, BigDecimal proteinPerG, BigDecimal saturatedFatPerG) {
		super();
		this.ingredientName = ingredientName;
		this.aliasName = aliasName;
		this.fatPerG = fatPerG;
		this.sugarPerG = sugarPerG;
		this.sodiumPerG = sodiumPerG;
		this.proteinPerG = proteinPerG;
		this.saturatedFatPerG = saturatedFatPerG;
	}
	
	public Ingredients(String ingredientName, String aliasName) {
		super();
		this.ingredientName = ingredientName;
		this.aliasName = aliasName;
		this.fatPerG = BigDecimal.ZERO;
		this.sugarPerG = BigDecimal.ZERO;
		this.sodiumPerG = BigDecimal.ZERO;
		this.proteinPerG = BigDecimal.ZERO;
		this.saturatedFatPerG = BigDecimal.ZERO;
	}
	
	public Ingredients(String ingredientName) {
		this.ingredientName = ingredientName;
		this.aliasName = ingredientName;
		this.fatPerG = BigDecimal.ZERO;
		this.sugarPerG = BigDecimal.ZERO;
		this.sodiumPerG = BigDecimal.ZERO;
		this.proteinPerG = BigDecimal.ZERO;
		this.saturatedFatPerG = BigDecimal.ZERO;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public BigDecimal getFatPerG() {
		return fatPerG;
	}

	public void setFatPerG(BigDecimal fatPerG) {
		this.fatPerG = fatPerG;
	}

	public BigDecimal getSugarPerG() {
		return sugarPerG;
	}

	public void setSugarPerG(BigDecimal sugarPerG) {
		this.sugarPerG = sugarPerG;
	}

	public BigDecimal getSodiumPerG() {
		return sodiumPerG;
	}

	public void setSodiumPerG(BigDecimal sodiumPerG) {
		this.sodiumPerG = sodiumPerG;
	}

	public BigDecimal getProteinPerG() {
		return proteinPerG;
	}

	public void setProteinPerG(BigDecimal proteinPerG) {
		this.proteinPerG = proteinPerG;
	}

	public BigDecimal getSaturatedFatPerG() {
		return saturatedFatPerG;
	}

	public void setSaturatedFatPerG(BigDecimal saturatedFatPerG) {
		this.saturatedFatPerG = saturatedFatPerG;
	}

	@Override
	public String toString() {
		return "Ingredients [ingredientName=" + ingredientName + ", aliasName=" + aliasName + ", fatPerG=" + fatPerG
				+ ", sugarPerG=" + sugarPerG + ", sodiumPerG=" + sodiumPerG + ", proteinPerG=" + proteinPerG
				+ ", saturatedFatPerG=" + saturatedFatPerG + "]";
	}
	
	
	
	
	
}