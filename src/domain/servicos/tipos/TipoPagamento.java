package domain.servicos.tipos;

public enum TipoPagamento implements Tipo {
	  CARTAO(1, "Cartão"),
	  DEPOSITO(2, "Depósito"),
	  DINHEIRO(3, "Dinheiro"); 
	  
	  private final Integer value;   
	  private final String description;
	  
	  TipoPagamento(Integer value, String description)
	  {   
	     this.value = value;
	     this.description = description;
	  }   
	  
	  public static TipoPagamento getType(Integer value)
	  {
	    for(TipoPagamento type: TipoPagamento.values())
	    {
	      if (value.equals(type.getValue()))
	      {
	        return type;
	      }
	    }
	    return null;
	  }

	  public TipoPagamento[] getValues()
	  {
	    return TipoPagamento.values();
	  }

	public String getDescription() {
		return this.description;
	}

	public Integer getValue() {
		return this.value;
	}

}
