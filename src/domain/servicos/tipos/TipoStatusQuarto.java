package domain.servicos.tipos;


public enum TipoStatusQuarto implements Tipo
{
  LIVRE(0, "L", "Livre"),
  RESERVADO(1, "X", "ReservadoOutro"),
  RESERVADO_PRIMEIRODIA(2, "R", "Reservado"), 
  RESERVA_NAO_CONFIRMADA_PRIMEIRODIA(3, "P", "Pre"),
  RESERVA_NAO_CONFIRMADA(4, "X", "PreOutro");
  
  private final Integer value;   
  private final String description;
  private String marcacao;
  
  TipoStatusQuarto(Integer value, String description, String marcacao)
  {   
     this.value = value;
     this.description = description;
	this.marcacao = marcacao;
  }   
  
  public Integer getValue()
  {
     return this.value;
  }
  
  public String getDescription()
  {
    return description;
  }
  
  public String getMarcacao()
  {
    return marcacao;
  }
  
  public static TipoStatusQuarto getType(Integer value)
  {
    for(TipoStatusQuarto type: TipoStatusQuarto.values())
    {
      if (value.equals(type.getValue()))
      {
        return type;
      }
    }
    return null;
  }

  public TipoStatusQuarto[] getValues()
  {
    return TipoStatusQuarto.values();
  }
}
