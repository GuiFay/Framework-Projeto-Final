package Controller;

import Model.Usuario;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DaoUsuarioTXT {

    public static void SerializarUsuario(ArrayList<Usuario> aluno) {

        FileOutputStream fos;
        try {
            fos = new FileOutputStream("Cadastro2.txt");
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(fos);
            oos.writeObject(aluno);
            oos.flush();
            oos.close();

        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }

    public static void incluirUsuario(Usuario user) {
        ArrayList array = null;

        try {
            array = DeserializarUsuario();

        } catch (Exception ex) {
            array = new ArrayList();
        }
        array.add(user);
        SerializarUsuario(array);

    }

    public static void deletarUsuario(String login) {
        ArrayList<Usuario> array = null;
        try {
            array = (ArrayList<Usuario>) DeserializarUsuario();
            for (Usuario u : array) {
                if (u.login.equals(login)) {
                    array.remove(u);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DaoUsuarioTXT.class.getName()).log(Level.SEVERE, null, ex);
        }
        SerializarUsuario(array);
    }

    public static void alterarUsuario(String login, Usuario user) {
        ArrayList<Usuario> array = null;
        try {
            array = (ArrayList<Usuario>) DeserializarUsuario();
            for (Usuario u : array) {
                if (u.login.equals(login)) {
                    array.remove(u);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DaoUsuarioTXT.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*  try {
         array = DeserializarUsuario();

         } catch (Exception ex) {
         array = new ArrayList();
         }
         */
        array.add(user);
        SerializarUsuario(array);

    }

    //MUDAR METODO DE LUGAR
    public static int autenticarUsuarioTxT(String login, String senha) {
        ArrayList<Usuario> array = null;
        try {
            array = (ArrayList<Usuario>) DeserializarUsuario();
            for (Usuario u : array) {
                if (u.login.equals(login) && u.senha.equals(senha)) {
                    return 1;
                }
            }
            JOptionPane.showMessageDialog(null,
                    "Usuário Autenticado com sucesso!", "Autenticado!",
                    JOptionPane.INFORMATION_MESSAGE, null);
        } catch (Exception ex) {
            Logger.getLogger(DaoUsuarioTXT.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
                    "Não foi possível Autenticar o Usuário!", "ATENÇÃO!!",
                    JOptionPane.ERROR_MESSAGE);
        }
        return 0;
    }

    public static ArrayList DeserializarUsuario() throws Exception {
        FileInputStream fis = new FileInputStream("Cadastro2.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList aluno = (ArrayList) ois.readObject();
        ois.close();
        fis.close();
        return aluno;
    }
}