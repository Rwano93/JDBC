import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class jdbcex {
    public static void main(String[] args) throws SQLException {
        Connection maConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/erwan", "root", "");

        int id_user = 0;
        boolean cmd = true;

        Scanner sc = new Scanner(System.in);
        while (cmd) {
            System.out.println("Bienvenue dans l'application de gestion des utilisateurs !");
            System.out.println("1 - S'inscrire");
            System.out.println("2 - Se connecter");

            int choixInitial = sc.nextInt();
            sc.nextLine();

            switch (choixInitial) {
                case 1:
                    // User Registration
                    System.out.println("Vous avez choisi de vous inscrire...");
                    System.out.println("Veuillez saisir votre email :");
                    String userEmail = sc.nextLine();
                    System.out.println("Veuillez saisir votre mot de passe :");
                    String userMdp = sc.nextLine();

                    // Perform user registration logic here...
                    // For example, insert the user into the database
                    registerUser(userEmail, userMdp, maConnection);

                    System.out.println("Inscription réussie !");
                    break;

                case 2:
                    // User Login
                    System.out.println("Vous avez choisi de vous connecter...");
                    System.out.println("Veuillez saisir votre email :");
                    String email = sc.nextLine();
                    System.out.println("Veuillez saisir votre mot de passe :");
                    String mdp = sc.nextLine();

                    if (email.equals("admin") && mdp.equals("admin")) {
                        System.out.println("Vous êtes connecté en tant qu'administrateur");
                        adminMenu(sc, maConnection);
                    } else {
                        System.out.println("Vous êtes connecté en tant qu'utilisateur");
                        userMenu(sc, maConnection);
                    }
                    break;

                default:
                    System.out.println("Choix non valide, veuillez entrer 1 ou 2.");
                    break;
            }

            System.out.println("Voulez-vous quitter ? (oui/non)");
            String quitter = sc.nextLine();
            if (quitter.equalsIgnoreCase("oui")) {
                cmd = false;
            }
        }

        // Close the resources
        sc.close();
        maConnection.close();
    }

    private static void registerUser(String email, String mdp, Connection maConnection) throws SQLException {
        String requeteInsert = "INSERT INTO Utilisateur (email, mdp, actif) VALUES (?, ?, ?)";
        PreparedStatement requetePrepareInsert = maConnection.prepareStatement(requeteInsert);

        requetePrepareInsert.setString(1, email);
        requetePrepareInsert.setString(2, mdp);
        requetePrepareInsert.setBoolean(3, true);

        requetePrepareInsert.executeUpdate();
    }

    private static void adminMenu(Scanner sc, Connection maConnection) throws SQLException {
        int id_user = 0;
        boolean cmd = true;

        while (cmd) {
            System.out.println("Que voulez-vous faire ? ");
            System.out.println("Menu Principal : ");
            System.out.println("");
            System.out.println("1 - Ajouter un utilisateur :");
            System.out.println("2 - Afficher un utilisateur :");
            System.out.println("3 - Mettre à jour les infos d'un utilisateur :");
            System.out.println("4 - Supprimer un utilisateur :");
            System.out.println("5 - Activer / désactiver un utilisateur :");
            System.out.println("6 - Quitter :");

            int choix = sc.nextInt();
            sc.nextLine();
            System.out.println("");

            switch (choix) {
                // ... (rest of your admin menu logic)
            }
        }
    }

    private static void userMenu(Scanner sc, Connection maConnection) throws SQLException {
        int id_user = 0;
        boolean cmd = true;

        while (cmd) {
            System.out.println("Que voulez-vous faire ? ");
            System.out.println("Menu Principal : ");
            System.out.println("");
            System.out.println("1 - Ajouter un utilisateur :");
            System.out.println("2 - Afficher un utilisateur :");
            System.out.println("3 - Mettre à jour les infos d'un utilisateur :");
            System.out.println("4 - Supprimer un utilisateur :");
            System.out.println("5 - Activer / désactiver un utilisateur :");
            System.out.println("6 - Quitter :");

            int choix = sc.nextInt();
            sc.nextLine();
            System.out.println("");

            switch (choix) {
                // ... (rest of your user menu logic)
            }
        }
    }
}
