package controllers.logins.admin;

import controllers.logins.admin.gerenciar.GerenciarAlunosController;
import controllers.logins.admin.gerenciar.GerenciarProfessoresController;
import extras.Util;
import java.util.Scanner;
import models.Database;

public class AdministradorController {
	// Metodo de login ADM
	public static void loginAdmin(Scanner scanner, Database database, String administrador, String senhaAdministrador) {
		String userSession = "";
		while (true) {
			String user = null;
			String senha = null;
			Util.limparTela();
			if (userSession.isEmpty()) {
				System.out.print("Informe o usuário para login:\t");
				user = scanner.nextLine();
				System.out.print("Informe a senha para login:\t");
				senha = scanner.nextLine();
			}

			if (!userSession.equals("admin") && (!administrador.equals(user) || !senhaAdministrador.equals(senha))) {
				Util.limparTela();
				System.out.println("Usuário ou senha incorretos!");
				System.out.println("Deseja tentar novamente?");
				int prosseguir = Util.optionPainel(scanner, new String[] { "0  Não (default)", " 1  Sim" });
				switch (prosseguir) {
					case 1:
						continue;
					case 0:
					default:
						System.out.println("Retornando a página inicial");
						return;
				}
			} else {
				userSession = "admin";
			}
			Util.limparTela();
			int option = Util.optionPainel(scanner, new String[] {
					/*
					 * Opções de ações para o ADM 1 - Vai para gerenciamento de Aluno
					 * (GerenciarAlunoController) 2 - Vai para gerenciamento de Prof
					 * (GerenciarProfessorController) 3 - Sair
					 */
					" 1  Gerenciar alunos", " 2  Gerenciar professores", " 3  Sair da conta" });
			switch (option) {
				case 1:
					GerenciarAlunosController.gerenciarAlunos(scanner, database);
					break;
				case 2:
					// Programa.gerenciarProfessores();
					GerenciarProfessoresController.gerenciarProfessores(scanner, database);
					break;
				case 3:
					System.out.println("Voltando a página inicial");
					userSession = "";
					return;
				default:
					System.out.println("\nDigite uma opção válida!\n");
					break;
			}
		}
	}
}
