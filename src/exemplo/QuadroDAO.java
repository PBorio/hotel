package exemplo;



public class QuadroDAO {
	
//	protected final PersistenceGeneric persistence;
//	private EntityManager entityManager;

//	public QuadroDAO(EntityManager entityManager) {
//		this.persistence = new PersistenceGeneric(entityManager);
//		this.entityManager = entityManager;
//	}
//	
//	public List<PostIt> buscarChamadosDeEntrada(){
//			StringBuilder sql = new StringBuilder();
//			sql.append("SELECT C.CODCHAMADO, ");
//			sql.append("       c.DESCSOLICITACAO, "); 
//			sql.append("       S.SIGLASISTEMA, ");
//			sql.append("       A.STATUS, ");
//			sql.append("       A.CODANDAMENTO, ");
//			sql.append("		   C.TIPO, ");
//			sql.append("       usuario.NOME as nomeUsuario, ");
//			sql.append("       p.NOME ");
//			sql.append("   FROM CHAMADO C ");
//			sql.append("    join SISTEMA s on s.IDSISTEMA = c.IDSISTEMA ");
//			sql.append("    join PESSOA usuario on usuario.CODPESSOA = c.CODUSUARIOSOLICITANTE ");
//			sql.append("    left join ANDAMENTOCHAMADO a on a.CODCHAMADO = c.CODCHAMADO ");
//			sql.append("    LEFT JOIN PESSOA P on p.CODPESSOA = A.CODPESSOADIRECIONADO ");
//			sql.append("  WHERE NOT EXISTS (SELECT A2.IDANDAMENTOCHAMADO ");
//			sql.append("                     FROM ANDAMENTOCHAMADO A2  ");
//			sql.append("                     WHERE A2.CODCHAMADO = C.CODCHAMADO) ");
//			sql.append(" AND C.DATAABERTURA > '01/01/2012' ");
//			sql.append(" AND s.IDSISTEMA in (130,119,126,129,125,122,132,124,113,120) ");
//			sql.append(" ORDER BY C.CODCHAMADO DESC ");
//			
//			return carregarPostIts(sql);
//	}
//	
//	public List<PostIt> buscarChamadosNoSuporte(){
//			StringBuilder sql = new StringBuilder();
//			sql.append(" SELECT C.CODCHAMADO, ");
//			sql.append("       c.DESCSOLICITACAO, "); 
//			sql.append("       S.SIGLASISTEMA,");
//			sql.append("       A.STATUS, ");
//			sql.append("       A.CODANDAMENTO, ");
//			sql.append("		   C.TIPO, ");
//			sql.append("       usuario.NOME as nomeUsuario, ");
//			sql.append("       p.NOME ");
//			sql.append(" from ANDAMENTOCHAMADO a ");
//			sql.append(" join CHAMADO c on c.CODCHAMADO = a.CODCHAMADO ");
//			sql.append(" join SISTEMA s on s.IDSISTEMA = c.IDSISTEMA ");
//			sql.append(" join PESSOA usuario on usuario.CODPESSOA = c.CODUSUARIOSOLICITANTE ");
//			sql.append(" LEFT JOIN PESSOA P on p.CODPESSOA = A.CODPESSOADIRECIONADO ");
//			sql.append(" where s.IDSISTEMA in (130,119,126,129,125,122,132,124,113,120) ");
//			sql.append(" AND A.STATUS BETWEEN 2 AND 3  ");
//			sql.append(" AND C.DATAABERTURA > '01/01/2012'  ");
//			sql.append(" and a.CODANDAMENTO = (SELECT max(a2.CODANDAMENTO)  ");
//			sql.append("                         from ANDAMENTOCHAMADO a2 ");
//			sql.append("                         where a2.codchamado = c.CODCHAMADO  ");
//			sql.append("                         and a2.CODPESSOADIRECIONADO is not null) ");
//			sql.append(" and not EXISTS ( ");
//			sql.append("    SELECT 1  ");
//			sql.append("      from ANDAMENTOCHAMADO a3 ");
//			sql.append("      where a3.CODCHAMADO = c.CODCHAMADO ");
//			sql.append("      and a3.CODPESSOATECNICO in( ");
//			sql.append("            1316, /*MARCELO ROCHETTO VELLAME*/ ");
//			sql.append("            1320, /*PAULO ROBERTO BORIO JUNIOR*/ ");
//			sql.append("            1308, /*RODRIGO FERNANDES BIJEGA*/ ");
//			sql.append("            1111, /*CARLOS EUGÊNIO RODRIGUES JUNIOR*/ ");
//			sql.append("            1318, /*CARLOS EDUARDO SILVA MOREIRA*/ ");
//			sql.append("            1119, /*RODRIGO DA SILVEIRA DE SOUZA*/ ");
//			sql.append("            1302, /*RAFAEL HAYASHI*/ ");
//			sql.append("            1309, /*FRANCISCO CARDOSO NUSSBAUM*/ ");
//			sql.append("            1121,  /*EDUARDO RIBEIRO FERREIRA DA SILVA*/ ");
//			sql.append("            1332  /*RODRIGO LOPES*/ ");
//			sql.append("      ) ");
//			sql.append(" ) ");
//			sql.append(" and a.CODPESSOADIRECIONADO in( ");
//			sql.append("    1313,	/*ALLAN KOROVISKI*/ ");
//			sql.append("    1102,	/*TALITA LORO CRUZARA*/  ");
//			sql.append("    1010,	/*DEBORA JULIANA SEGANTINE*/ ");
//			sql.append("    1085,	/*ANGELA MARIA NOGUEIRA*/  ");
//			sql.append("    1094,	/*FABIO MIGUEL SECCON LOURENÇO*/ ");
//			sql.append("    1322,	/*NEUSA OLIVEIRA JESUS*/ ");
//			sql.append("    1089,	/*KELEN ROBERTA ALVES PEREIRA*/ ");
//			sql.append(" 	1075,  /*SILVIA LUZIA BAIAK*/ ");
//			sql.append(" 	1324,	/*LUCÉLIA GUSE ALVES*/ ");
//			sql.append(" 	1330,	/*HIAGO BIJEGA*/ ");
//			sql.append(" 	1334,	/*DENISE VIEIRA PINTO JOST*/ ");
//			sql.append(" 	1328,	/*JOSIANE CARRAO*/ ");
//			sql.append("    3053 ");
//			sql.append(" ) ");
//			sql.append(" and not EXISTS( ");
//			sql.append("    SELECT 1 from ANDAMENTOCHAMADO a4 ");
//			sql.append("     where ( a4.STATUS < 2 ");
//			sql.append("     or      a4.STATUS > 3 ) ");
//			sql.append("     and a4.CODCHAMADO = c.CODCHAMADO ");
//			sql.append("  ) ");
//			sql.append(" ORDER BY C.CODCHAMADO DESC ");
//			
//			return carregarPostIts(sql);
//	}
//	
//	public List<PostIt> buscarChamadosEmPriorizacao(){
//			
//			StringBuilder sql = new StringBuilder();
//			sql.append(" SELECT C.CODCHAMADO, ");
//			sql.append("        c.DESCSOLICITACAO, ");
//			sql.append("        S.SIGLASISTEMA, ");
//			sql.append("        A.STATUS, ");
//			sql.append("        A.CODANDAMENTO, ");
//			sql.append("		    C.TIPO, ");
//			sql.append("       usuario.NOME as nomeUsuario, ");
//			sql.append("        p.NOME ");
//			sql.append(" from ANDAMENTOCHAMADO a  ");
//			sql.append("  join CHAMADO c on c.CODCHAMADO = a.CODCHAMADO ");
//			sql.append("  join SISTEMA s on s.IDSISTEMA = c.IDSISTEMA ");
//			sql.append("  join PESSOA usuario on usuario.CODPESSOA = c.CODUSUARIOSOLICITANTE ");
//			sql.append("  LEFT JOIN PESSOA P on p.CODPESSOA = a.CODPESSOADIRECIONADO ");
//			sql.append("  where s.IDSISTEMA in (130,119,126,129,125,122,132,124,113,120) ");
//			sql.append("  AND A.STATUS BETWEEN 2 AND 3 ");
//			sql.append("  AND C.DATAABERTURA > '01/01/2012'  ");
//			sql.append("  and a.CODANDAMENTO = (SELECT max(a2.CODANDAMENTO)  ");
//			sql.append("                          from ANDAMENTOCHAMADO a2 ");
//			sql.append("                          where a2.codchamado = c.CODCHAMADO ");
//			sql.append("                          and a2.CODPESSOADIRECIONADO is not null) ");
//			sql.append("  and a.CODPESSOADIRECIONADO in( ");
//			sql.append("         1059, /*MARCELO MACHADO DE SOUZA*/ ");
//			sql.append("         1095,  /*KATIANE CURKAREVICZ RICKLI*/ ");
//			sql.append("         1205,  /*ADRIANA MACEDO ALANO*/ ");
//			sql.append("         1053,  /*ADNAN*/ ");
//			sql.append("         5053   /*DESENVOLVIMENTO*/ ");
//			sql.append("  ) ");
//			sql.append("  and not EXISTS( ");
//			sql.append("     SELECT 1 from ANDAMENTOCHAMADO a3 ");
//			sql.append("      where ( a3.STATUS < 2");
//			sql.append("      or    a3.STATUS > 3 )  ");
//			sql.append("      and a3.CODCHAMADO = c.CODCHAMADO ");
//			sql.append("   ) ");
//			sql.append(" ORDER BY C.CODCHAMADO DESC ");
//			
//			return carregarPostIts(sql);
//	}
//
//	public List<PostIt> buscarChamadosEmDesenvolvimento(){
//			
//			StringBuilder sql = new StringBuilder();
//			sql.append(" SELECT C.CODCHAMADO, ");
//			sql.append("        c.DESCSOLICITACAO, ");
//			sql.append("        S.SIGLASISTEMA, ");
//			sql.append("        A.STATUS, ");
//			sql.append("        A.CODANDAMENTO, ");
//			sql.append("		    C.TIPO, ");
//			sql.append("       usuario.NOME as nomeUsuario, ");
//			sql.append("        p.NOME ");
//			sql.append(" from ANDAMENTOCHAMADO a "); 
//			sql.append("  join CHAMADO c on c.CODCHAMADO = a.CODCHAMADO ");
//			sql.append("  join SISTEMA s on s.IDSISTEMA = c.IDSISTEMA ");
//			sql.append("  join PESSOA usuario on usuario.CODPESSOA = c.CODUSUARIOSOLICITANTE ");
//			sql.append("  LEFT JOIN PESSOA P on p.CODPESSOA = a.CODPESSOADIRECIONADO ");
//			sql.append("  where s.IDSISTEMA in (130,119,126,129,125,122,132,124,113,120) ");
//			sql.append("  AND C.DATAABERTURA > '01/01/2012' ");
//			sql.append("  and a.CODANDAMENTO = (SELECT max(a2.CODANDAMENTO) "); 
//			sql.append("                          from ANDAMENTOCHAMADO a2 ");
//			sql.append("                          where a2.codchamado = c.CODCHAMADO ");
//			sql.append("                          and a2.CODPESSOADIRECIONADO is not null) ");
//			sql.append("  and a.CODPESSOADIRECIONADO in( ");
//			sql.append("     1316, /*MARCELO ROCHETTO VELLAME*/ ");
//			sql.append("     1320, /*PAULO ROBERTO BORIO JUNIOR*/ ");
//			sql.append("     1308, /*RODRIGO FERNANDES BIJEGA*/ ");
//			sql.append("     1111, /*CARLOS EUGÊNIO RODRIGUES JUNIOR*/ ");
//			sql.append("     1318, /*CARLOS EDUARDO SILVA MOREIRA*/ ");
//			sql.append("     1119, /*RODRIGO DA SILVEIRA DE SOUZA*/ ");
//			sql.append("     1302, /*RAFAEL HAYASHI*/ ");
//			sql.append("     1309, /*FRANCISCO CARDOSO NUSSBAUM*/ ");
//			sql.append("     1121,  /*EDUARDO RIBEIRO FERREIRA DA SILVA*/ ");
//			sql.append("     1332  /*RODRIGO LOPES*/ ");
//			sql.append("  ) ");
//			sql.append("  and not EXISTS( ");
//			sql.append("     SELECT 1 from ANDAMENTOCHAMADO a3 ");
//			sql.append("      where ( a3.STATUS < 2 ");
//			sql.append("      or    a3.STATUS > 3 ) ");
//			sql.append("      and a3.CODCHAMADO = c.CODCHAMADO ");
//			sql.append("   ) ");
//			sql.append(" ORDER BY C.CODCHAMADO DESC ");
//			
//			return carregarPostIts(sql);
//			
//	}
//	
//	public List<PostIt> buscarChamadosEmTeste(){
//			
//			StringBuilder sql = new StringBuilder();
//			sql.append(" SELECT C.CODCHAMADO, ");
//			sql.append("        c.DESCSOLICITACAO, ");
//			sql.append("        S.SIGLASISTEMA, ");
//			sql.append("        A.STATUS, ");
//			sql.append("        A.CODANDAMENTO, ");
//			sql.append("		    C.TIPO, ");
//			sql.append("       usuario.NOME as nomeUsuario, ");
//			sql.append("        p.NOME ");
//			sql.append(" from ANDAMENTOCHAMADO a "); 
//			sql.append("  join CHAMADO c on c.CODCHAMADO = a.CODCHAMADO ");
//			sql.append("  join SISTEMA s on s.IDSISTEMA = c.IDSISTEMA ");
//			sql.append("  join PESSOA usuario on usuario.CODPESSOA = c.CODUSUARIOSOLICITANTE ");
//			sql.append("  LEFT JOIN PESSOA P on p.CODPESSOA = a.CODPESSOADIRECIONADO ");
//			sql.append("  where s.IDSISTEMA in (130,119,126,129,125,122,132,124,113,120) ");
//			sql.append("  AND C.DATAABERTURA > '01/01/2012' ");
//			sql.append("  and a.CODANDAMENTO = (SELECT max(a2.CODANDAMENTO) "); 
//			sql.append("                          from ANDAMENTOCHAMADO a2 ");
//			sql.append("                          where a2.codchamado = c.CODCHAMADO ");
//			sql.append("                          and a2.CODPESSOADIRECIONADO is not null ");
//			sql.append("                          ) ");
//			sql.append("  and EXISTS ( ");
//			sql.append("     SELECT 1  ");
//			sql.append("       from ANDAMENTOCHAMADO a3 ");
//			sql.append("       where a3.CODCHAMADO = c.CODCHAMADO ");
//			sql.append("       and a3.CODPESSOATECNICO in( ");
//			sql.append("             1316, /*MARCELO ROCHETTO VELLAME*/ ");
//			sql.append("             1320, /*PAULO ROBERTO BORIO JUNIOR*/ ");
//			sql.append("             1308, /*RODRIGO FERNANDES BIJEGA*/ ");
//			sql.append("             1111, /*CARLOS EUGÊNIO RODRIGUES JUNIOR*/ ");
//			sql.append("             1318, /*CARLOS EDUARDO SILVA MOREIRA*/ ");
//			sql.append("             1119, /*RODRIGO DA SILVEIRA DE SOUZA*/ ");
//			sql.append("             1302, /*RAFAEL HAYASHI*/ ");
//			sql.append("             1309, /*FRANCISCO CARDOSO NUSSBAUM*/ ");
//			sql.append("             1121, /*EDUARDO RIBEIRO FERREIRA DA SILVA*/ ");
//			sql.append("     		 1332  /*RODRIGO LOPES*/ ");
//			sql.append("       ) ");
//			sql.append("  ) ");
//			sql.append("  and a.CODPESSOADIRECIONADO in( ");
//			sql.append("         1313,	/*ALLAN KOROVISKI*/ ");
//			sql.append("         1102,	/*TALITA LORO CRUZARA*/ "); 
//			sql.append("         1010,	/*DEBORA JULIANA SEGANTINE*/ ");
//			sql.append("         1085,	/*ANGELA MARIA NOGUEIRA*/ ");
//			sql.append("         1094,	/*FABIO MIGUEL SECCON LOURENÇO*/ ");
//			sql.append("         1322,	/*NEUSA OLIVEIRA JESUS*/ ");
//			sql.append("         1089,  /*KELEN ROBERTA ALVES PEREIRA*/ ");
//			sql.append(" 		 1075,  /*SILVIA LUZIA BAIAK*/ ");
//			sql.append(" 		 1324,	/*LUCÉLIA GUSE ALVES*/ ");
//			sql.append(" 	     1330,	/*HIAGO BIJEGA*/ ");
//			sql.append(" 	     1334,	/*DENISE VIEIRA PINTO JOST*/ ");
//			sql.append(" 	     1328,	/*JOSIANE CARRAO*/ ");
//			sql.append("         3053 ");
//			sql.append("     ) ");
//			sql.append("   and not EXISTS( ");
//			sql.append("     SELECT 1 from ANDAMENTOCHAMADO a4 ");
//			sql.append("      where ( a4.STATUS < 2 ");
//			sql.append("      or    a4.STATUS > 3 ) ");
//			sql.append("      and a4.CODCHAMADO = c.CODCHAMADO "); 
//			sql.append("   ) ");
//			sql.append(" ORDER BY C.CODCHAMADO DESC ");
//
//			return carregarPostIts(sql);
//	}
//	
//	public List<PostIt> buscarChamadosResolvidos(){
//			
//			StringBuilder sql = new StringBuilder();
//			sql.append(" SELECT C.CODCHAMADO, ");
//			sql.append("        C.DESCSOLICITACAO, ");
//			sql.append("        S.SIGLASISTEMA, ");
//			sql.append("        A.STATUS, ");
//			sql.append("        A.CODANDAMENTO, ");
//			sql.append("		    C.TIPO, ");
//			sql.append("       usuario.NOME as nomeUsuario, ");
//			sql.append("        p.NOME ");
//			sql.append(" from ANDAMENTOCHAMADO a "); 
//			sql.append("  join CHAMADO c on c.CODCHAMADO = a.CODCHAMADO ");
//			sql.append("  join SISTEMA s on s.IDSISTEMA = c.IDSISTEMA ");
//			sql.append("  join PESSOA usuario on usuario.CODPESSOA = c.CODUSUARIOSOLICITANTE ");
//			sql.append("  LEFT JOIN PESSOA P on p.CODPESSOA = a.CODPESSOATECNICO ");
//			sql.append("  where s.IDSISTEMA in (130,119,126,129,125,122,132,124,113,120) ");
//			sql.append("  AND A.STATUS = 4 ");
//			sql.append("  AND C.DATAABERTURA > '01/01/2012' ");
//			sql.append("  and a.CODANDAMENTO = (SELECT max(a2.CODANDAMENTO) "); 
//			sql.append("                          from ANDAMENTOCHAMADO a2");
//			sql.append("                          where a2.codchamado = c.CODCHAMADO) ");
//			sql.append(" ORDER BY C.CODCHAMADO DESC ");
//			
//			return carregarPostIts(sql);
//	}
//	
//	private List<PostIt> carregarPostIts(StringBuilder sql) {
//		ConnectionFactory connectionFactory = new ConnectionFactory(entityManager);
//		List<PostIt> lista = new ArrayList<PostIt>();
//		try{
//			Connection conn = connectionFactory.criarConnection();
//			PreparedStatement stmt = conn.prepareStatement(sql.toString());
//			ResultSet rs = stmt.executeQuery();
//			while (rs.next()){
//				PostIt post = new PostIt();
//				post.setNumeroChamado(rs.getInt("CODCHAMADO"));
//				post.setDescricao(rs.getString("DESCSOLICITACAO"));
//				post.setSiglaSistema(rs.getString("SIGLASISTEMA"));
//				post.setNome(rs.getString("NOME"));
//				post.setCodTipo(rs.getInt("TIPO"));
//				post.setSolicitante(rs.getString("nomeUsuario"));
//				
//				lista.add(post);
//			}
//			return lista;
//		}
//		catch (Exception e) {
//			throw new RuntimeException(e.getMessage(), e);
//		}
//		finally{
//			connectionFactory.closeConnection();
//		}
//	}


}

