package domain.servicos.tipos;


public enum TipoStatusQuarto implements Tipo
{
  LIVRE(0, "L"),
  RESERVADO(1, "X"),
  RESERVADO_PRIMEIRODIA(2,"R"), 
  RESERVA_NAO_CONFIRMADA(3, "Lb");   
  
  private final Integer value;   
  private final String description;
  
  TipoStatusQuarto(Integer value, String description)
  {   
     this.value = value;
     this.description = description;
  }   
  
  public Integer getValue()
  {
     return this.value;
  }
  
  public String getDescription()
  {
    return description;
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
