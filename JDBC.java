import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class JDBC {
    public static void main(String[] args) throws SQLException {
        // Connexion JDBC
        Connection maConnection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/erwan", "root", "");

        int id_user = 0;
        boolean cmd = true;

        Scanner sc = new Scanner(System.in);

        while (cmd) {
            System.out.println("Que voulez-vous faire ? ");
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
                case 1: // Ajouter un utilisateur : 
                    
                    System.out.println("Vous avez choisi d'ajouter un nouvel utilisateur...");
                    System.out.println("Veuillez saisir le nom de l'utilisateur :");
                    String nom = sc.nextLine();
                    System.out.println("Veuillez saisir le prénom de l'utilisateur :");
                    String prenom = sc.nextLine();
                    System.out.println("Veuillez saisir le metier de l'utilisateur :");
                    String metier = sc.nextLine();
                    System.out.println("Veuillez saisir l'email de l'utilisateur :");
                    String email = sc.nextLine();
                    System.out.println("Veuillez saisir le mot de passe de l'utilisateur :");
                    String mdp = sc.nextLine();

                    // Requête SQL d'insertion
                    String requeteInsert = "INSERT INTO Utilisateur (nom, prenom, metier, email, mdp, actif) VALUES (?, ?, ?, ?, ?, ?)";
                    PreparedStatement requetePrepareInsert = maConnection.prepareStatement(requeteInsert, PreparedStatement.RETURN_GENERATED_KEYS);

                    requetePrepareInsert.setString(1, nom);
                    requetePrepareInsert.setString(2, prenom);
                    requetePrepareInsert.setString(3, metier);
                    requetePrepareInsert.setString(4, email);
                    requetePrepareInsert.setString(5, mdp);
                    requetePrepareInsert.setBoolean(6, true);

                    requetePrepareInsert.executeUpdate();
                    ResultSet generatedKeys = requetePrepareInsert.getGeneratedKeys();

                    if (generatedKeys.next()) {
                        System.out.println("L'utilisateur a bien été ajouté avec l'id : " + generatedKeys.getInt(1));
                        System.out.println("");
                    } else {
                        System.out.println("L'utilisateur n'a pas été ajouté");
                    }
                    
                    System.out.println("Voulez-vous ajouter un autre utilisateur ? (oui/non)");
                    String reponseAjouter = sc.nextLine();

                    if (reponseAjouter.equalsIgnoreCase("non")) {
                        System.out.println("Au revoir !");
                        break;
                    }else{
                        System.out.println("D'accord alors on continue !");
                        System.out.println("");
                    }

                    break;

                case 2:
                    // Afficher tous les utilisateurs
                    System.out.println("Vous avez choisi d'afficher tous les utilisateurs :");

                    String requeteSelectAllUsers = "SELECT * FROM Utilisateur";
                    PreparedStatement requetePrepareSelectAllUsers = maConnection.prepareStatement(requeteSelectAllUsers);
                    ResultSet resultatAllUsers = requetePrepareSelectAllUsers.executeQuery();

                    while (resultatAllUsers.next()) {
                        System.out.println(resultatAllUsers.getInt("id_user"));
                        System.out.println(resultatAllUsers.getString("nom"));
                        System.out.println(resultatAllUsers.getString("prenom"));
                        System.out.println(resultatAllUsers.getString("metier"));
                        System.out.println(resultatAllUsers.getString("email"));
                        System.out.println(resultatAllUsers.getString("mdp"));
                        System.out.println(resultatAllUsers.getBoolean("actif"));
                        System.out.println("");
                    }

                    // Afficher un utilisateur par l'id
                    while (true) {

                        System.out.println("Vous avez choisi d'afficher un utilisateur");
                        System.out.println("");
                        System.out.println("Veuillez saisir l'id de l'utilisateur que vous voulez afficher");
                        int id = sc.nextInt();
                        sc.nextLine(); 
                        System.out.println("Voici l'utilisateur :");

                        String requeteSelect = "SELECT * FROM Utilisateur WHERE id_user = ?";
                        PreparedStatement requetePrepareSelect = maConnection.prepareStatement(requeteSelect);
                        requetePrepareSelect.setInt(1, id);
                        ResultSet resultat = requetePrepareSelect.executeQuery();

                        if (resultat.next()) {
                            System.out.println(resultat.getInt("id_user"));
                            System.out.println(resultat.getString("nom"));
                            System.out.println(resultat.getString("prenom"));
                            System.out.println(resultat.getString("metier"));
                            System.out.println(resultat.getString("email"));
                            System.out.println(resultat.getString("mdp"));
                            System.out.println(resultat.getBoolean("actif"));
                            System.out.println("");
                        } else {
                            System.out.println("L'utilisateur n'existe pas");
                        }
                        System.out.println("Voulez-vous afficher un autre utilisateur ? (oui/non)");
                        String reponseAfficher = sc.nextLine();

                        if (reponseAfficher.equalsIgnoreCase("oui")) {
                            System.out.println("Au revoir !");
                            break;
                        }
                    }
                    break;

                case 3:
                    // Modifier un utilisateur par l'id
                    System.out.println("Voici tous les utilisateurs :");
                    String requeteSelectAll = "SELECT * FROM Utilisateur";
                    PreparedStatement requetePrepareSelectAll = maConnection.prepareStatement(requeteSelectAll);
                    ResultSet resultatAll = requetePrepareSelectAll.executeQuery();

                    while (resultatAll.next()) {
                        System.out.println(resultatAll.getInt("id_user"));
                        System.out.println(resultatAll.getString("nom"));
                        System.out.println(resultatAll.getString("prenom"));
                        System.out.println(resultatAll.getString("metier"));
                        System.out.println(resultatAll.getString("email"));
                        System.out.println(resultatAll.getString("mdp"));
                        System.out.println(resultatAll.getBoolean("actif"));
                        System.out.println("");
                    }

                    System.out.println("Veuillez saisir l'id de l'utilisateur que vous voulez mettre à jour");
                    id_user = sc.nextInt();
                    sc.nextLine();

                    String requeteUpdate = "UPDATE Utilisateur SET nom = ?, prenom = ?, email = ?, mdp = ?, actif = ? WHERE id_user = ?";
                    PreparedStatement requetePrepareUpdate = maConnection.prepareStatement(requeteUpdate);

                    System.out.println("Veuillez saisir le nouveau nom");
                    requetePrepareUpdate.setString(1, sc.nextLine());
                    System.out.println("Veuillez saisir le nouveau prénom");
                    requetePrepareUpdate.setString(2, sc.nextLine());
                    System.out.println("Veuillez saisir le nouveau metier");
                    requetePrepareUpdate.setString(3, sc.nextLine());
                    System.out.println("Veuillez saisir le nouvel email");
                    requetePrepareUpdate.setString(4, sc.nextLine());
                    System.out.println("Veuillez saisir le nouveau mot de passe");
                    requetePrepareUpdate.setString(5, sc.nextLine());
                    System.out.println("Veuillez saisir la nouvelle valeur d'activation (true/false)");
                    requetePrepareUpdate.setBoolean(6, sc.nextBoolean());
                    requetePrepareUpdate.setInt(7, id_user);

                    requetePrepareUpdate.executeUpdate();

                    break;

                case 4:
                    // Supprimer un utilisateur
                    System.out.println("Voici l'ensemble des utilisateurs :");

                    String requeteSelectAllDelete = "SELECT * FROM Utilisateur";
                    PreparedStatement requetePrepareSelectAllDelete = maConnection.prepareStatement(requeteSelectAllDelete);
                    ResultSet resultatAllDelete = requetePrepareSelectAllDelete.executeQuery();

                    while (resultatAllDelete.next()) {
                        System.out.println(resultatAllDelete.getInt("id_user"));
                        System.out.println(resultatAllDelete.getString("nom"));
                        System.out.println(resultatAllDelete.getString("prenom"));
                        System.out.println(resultatAllDelete.getString("metier"));
                        System.out.println(resultatAllDelete.getString("email"));
                        System.out.println(resultatAllDelete.getString("mdp"));
                        System.out.println(resultatAllDelete.getBoolean("actif"));
                    }

                    System.out.println("Voulez-vous supprimer un utilisateur ? (oui/non)");
                    String reponse = sc.nextLine();

                    if (reponse.equalsIgnoreCase("oui")) {
                        System.out.println("Veuillez saisir l'id de l'utilisateur que vous voulez supprimer");
                        int idSupprimer = sc.nextInt();
                        sc.nextLine();

                        String requeteDelete = "DELETE FROM Utilisateur WHERE id_user = ?";
                        PreparedStatement requetePrepareDelete = maConnection.prepareStatement(requeteDelete);
                        requetePrepareDelete.setInt(1, idSupprimer);

                        requetePrepareDelete.executeUpdate();
                    }

                    break;

                case 5:
                    // Activer / désactiver un utilisateur :
                    System.out.println("Voici l'ensemble des utilisateurs :");

                    String requeteSelectAllUtilisateur = "SELECT * FROM Utilisateur";
                    PreparedStatement requetePrepareSelectAllUtilisateur = maConnection.prepareStatement(requeteSelectAllUtilisateur);
                    ResultSet resultatAllUtilisateur = requetePrepareSelectAllUtilisateur.executeQuery();

                    while (resultatAllUtilisateur.next()) {
                        System.out.println(resultatAllUtilisateur.getInt("id_user"));
                        System.out.println(resultatAllUtilisateur.getString("nom"));
                        System.out.println(resultatAllUtilisateur.getString("prenom"));
                        System.out.println(resultatAllUtilisateur.getString("metier"));
                        System.out.println(resultatAllUtilisateur.getString("email"));
                        System.out.println(resultatAllUtilisateur.getString("mdp"));
                        System.out.println(resultatAllUtilisateur.getBoolean("actif"));
                    }

                    System.out.println("Voulez-vous activer / désactiver un utilisateur ? (oui/non)");
                    String reponseUtilisateur = sc.nextLine();

                    if (reponseUtilisateur.equalsIgnoreCase("non")) {
                        System.out.println("Veuillez saisir l'id de l'utilisateur que vous voulez activer / désactiver");
                        int idUtilisateur = sc.nextInt();
                        sc.nextLine();

                        String requeteUpdateUtilisateur = "UPDATE Utilisateur SET actif = ? WHERE id_user = ?";
                        PreparedStatement requetePrepareUpdateUtilisateur = maConnection.prepareStatement(requeteUpdateUtilisateur);

                        System.out.println("Êtes-vous sûr de vouloir activer / désactiver l'utilisateur ? (true/false)");
                        boolean activation = sc.nextBoolean();
                        System.out.println("L'utilisateur a bien été activé / désactivé");

                        requetePrepareUpdateUtilisateur.setBoolean(1, activation);
                        requetePrepareUpdateUtilisateur.setInt(2, idUtilisateur);

                        requetePrepareUpdateUtilisateur.executeUpdate();

                    } else {
                        break;
                    }

                case 6:
                    cmd = false;
                    break;

                default:
                    System.out.println("Choix non valide, veuillez entrer un chiffre entre 1 et 6 :");
                    System.out.println("Vous pouvez rechoisir :");
                    System.out.println("");
            }
        }
    }
}
