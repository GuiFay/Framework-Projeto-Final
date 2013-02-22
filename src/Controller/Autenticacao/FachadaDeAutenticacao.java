/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Autenticacao;

/**
 *
 * @author Guilherme
 */
public class FachadaDeAutenticacao {

    private static Autenticacao _XML, _TXT;

    //criar metodo fabrica para instanciação dos metodos de autenticaçao
    public static Autenticacao obterMetodoAutenticacao(int metodo) {
        Autenticacao autenticação = null;

        switch (metodo) {

            case Autenticacao.XML:
                if (_XML == null) {
                    _XML = new AutenticadorXML();
                }
                autenticação = _XML;
                break;

            case Autenticacao.TXT:
                if (_TXT == null) {
                    _TXT = new AutenticadorTXT();
                }
                autenticação = _TXT;
                break;
        }
        return autenticação;
    }
}
