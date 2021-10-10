package pacote;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.StringJoiner;

public class AplicacaoColegio {

	public static void main(String[] args) throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/colegio?" + "user=root");

			Statement stmt = null;
			ResultSet rs = null;
			String codSQL = "SELECT SA.codpredio, SA.numsala, HOR.diasem, HOR.horainicio, HOR.numhoras "
					+ "FROM SALA SA JOIN HORARIO HOR " + "ON SA.NUMSALA = HOR.numsala AND SA.codpredio = HOR.codpredio "
					+ "ORDER BY SA.codpredio, SA.numsala, HOR.diasem, HOR.horainicio";
			stmt = conn.createStatement();
			if (!stmt.execute(codSQL)) {
				return;
			}
			rs = stmt.getResultSet();

			ArrayList<Horario> horarios = null;
			ArrayList<Sala> salas = new ArrayList<Sala>();
			int nSala = -1;
			int nPredio = -1;
			Sala salaAdd = null;
			while (rs.next()) {

				if ((int) rs.getObject(1) != nPredio || (int) rs.getObject(2) != nSala) {
					salaAdd = new Sala();
					salas.add(salaAdd);
					horarios = new ArrayList<Horario>();
					salaAdd.setHorarios(horarios);
					nPredio = (int) rs.getObject(1);
					nSala = (int) rs.getObject(2);
				}

				Horario horarioAdd = new Horario();
				salaAdd.setCodPredio((int) rs.getObject(1));
				salaAdd.setNumSala((int) rs.getObject(2));
				horarioAdd.setDiaSemana((String) rs.getObject(3));
				horarioAdd.setHoraInicio((int) rs.getObject(4));
				horarioAdd.setNumHoras((int) rs.getObject(5));
				horarioAdd.setHoraFim(horarioAdd.getHoraInicio() + horarioAdd.getNumHoras());
				horarios.add(horarioAdd);

				// System.out.println(" "+ horarioAdd.getCodPredio()+" " +
				// horarioAdd.getNumSala()+" " +
				// horarioAdd.getDiaSemana()+" " + horarioAdd.getHoraInicio()+" " +
				// horarioAdd.getNumHoras()
				// +" "+ horarioAdd.getHoraFim());
			}
			String[] diaSemana = new String[6];
			diaSemana[0] = "segunda";
			diaSemana[1] = "terca";
			diaSemana[2] = "quarta";
			diaSemana[3] = "quinta";
			diaSemana[4] = "sexta";
			diaSemana[5] = "sabado";
			String ocupacao = null;
			for (Sala sal : salas) {
				System.out.println(" Numero da sala " + sal.getNumSala() + ":");
				ArrayList<Horario> agenda = sal.getHorarios();
				for (int i = 0; i < 6; i++) {
					System.out.println(diaSemana[i]);
					for (int j = 6; j < 23; j++) {
						ocupacao = "livre";
						for (Horario hor : agenda) {
							//testar dia da semana, se o "j" ta dentro do horario de inicio e fim
							
							if (diaSemana[i].equals(hor.getDiaSemana()) && j >= hor.getHoraInicio() && j<= hor.getHoraFim()) {
								ocupacao = "ocupado";
							}
						}
						System.out.println("\t" + j + "h \t"+ocupacao);

					}
				}

			}

		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}

	}

}
