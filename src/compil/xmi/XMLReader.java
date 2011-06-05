package compil.xmi;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLReader {

    private static ArrayList<Association> myAssociationVector = new ArrayList<Association>();
    private static EnumClass myEnum;
    private static ArrayList<Classe> myClasses = new ArrayList<Classe>();

    public void startParser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Informe o caminho do XML: ");
        String path = sc.nextLine();
        if(path.equals(""))
            parserXMI("build/classes/compil/xmi/arquivo/profe.xml");
        else
            parserXMI(path);
        makeAssociations();
    }

    public static ArrayList<Association> getMyAssociationVector() {
        return myAssociationVector;
    }

    public static ArrayList<Classe> getMyClasses() {
        return myClasses;
    }

    public static void makeAssociations() {
        //Varrer todas as associacoes
        for (int i = 0; i < myClasses.size(); i++) {
            for (int j = 0; j < myClasses.get(i).getMyAtt().size(); j++) {
                for (int k = 0; k < myAssociationVector.size(); k++) {

                    if (myClasses.get(i).getMyAtt().get(j).getNome().equals("")) {
                        if (myAssociationVector.get(k).getId().equals(myClasses.get(i).getMyAtt().get(j).getAssociacao().trim())) {
                            if (myClasses.get(i).getMyAtt().get(j).getId().equals(myAssociationVector.get(k).getIdAssociationBegin().trim())) {
                                if (!myClasses.get(i).getMyAtt().get(j).getNome().equals("")) {
                                    myClasses.get(i).getMyAtt().get(j).setNome(findByAssociationEnd(myAssociationVector.get(k).getIdAssociationEnd().trim()));
                                } else if (myClasses.get(i).getMyAtt().get(j).getNome().equals("")) {
                                    myClasses.get(i).getMyAtt().get(j).setNome(findByClassesAssociated(myClasses.get(i).getMyAtt().get(j).getTipo()));
                                }
                            } else if (myClasses.get(i).getMyAtt().get(j).getId().equalsIgnoreCase(myAssociationVector.get(k).getIdAssociationEnd().trim())) {
                                myClasses.get(i).getMyAtt().get(j).setNome(findByClassesAssociated(myClasses.get(i).getMyAtt().get(j).getTipo()));
                            }
                        } else {
                            if (myClasses.get(i).getMyAtt().get(j).getNome().equals("") && myClasses.get(i).getId().equalsIgnoreCase("_fx7WImPXEd-bkL5iYhiD_Q")) {
                                myClasses.get(i).getMyAtt().get(j).setNome(findByClassesAssociated(myClasses.get(i).getMyAtt().get(j).getTipo()));
                            }
                        }
                    }

                }
            }
        }
    }

    public static String findByAssociationEnd(String associationEnd) {
        for (int i = 0; i < myClasses.size(); i++) {
            for (int j = 0; j < myClasses.get(i).getMyAtt().size(); j++) {
                if (myClasses.get(i).getMyAtt().get(j).getId().equals(associationEnd)) {
                    return myClasses.get(i).getMyAtt().get(j).getNome();
                }
            }
        }
        return "";
    }

    public static String findByClassesAssociated(String typeOfAtribute) {
        for (int i = 0; i < myClasses.size(); i++) {
            if (myClasses.get(i).getId().equals(typeOfAtribute)) {
                return myClasses.get(i).getNome().toLowerCase().concat("*");
            }
        }
        return "";
    }

    public static void printClasses() {
        for (int i = 0; i < myClasses.size(); i++) {
            System.out.println(myClasses.get(i).toString());
        }
    }

    public void parserXMI(String filePath) {
        try {
            // Utilizacao do DOM
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();

            // Para cada "tag" ownedMember
            NodeList nodeLst = doc.getElementsByTagName("ownedMember");
            int position = 0;
            for (int s = 0; s < nodeLst.getLength(); s++) {

                // Para cada nodo que obteve de ownedMember
                Element fstNode = (Element) nodeLst.item(s);


                if (!fstNode.getAttribute("xmi:type").equalsIgnoreCase(
                        "uml:Package")) {
                    String type = fstNode.getAttribute("xmi:type");
                    String id = fstNode.getAttribute("xmi:id");
                    String name = fstNode.getAttribute("name");

                    // Pega cada tag interna ao ownedMember
                    if (type.equalsIgnoreCase("uml:Class")) {
                        // Cria nova classe
                        myClasses.add(position, new Classe(name, id, type));

                        NodeList children = fstNode.getElementsByTagName("ownedAttribute");
                        NodeList children2 = fstNode.getElementsByTagName("ownedOperation");

                        if (children != null) {
                            for (int i = 0; i < children.getLength(); i++) {
                                // Pega cada tag interna do tipo ownedAttribute
                                Element child = (Element) children.item(i);

                                String idAtt = child.getAttribute("xmi:id");
                                String nameAtt = child.getAttribute("name");
                                String typeAtt = child.getAttribute("type");
                                String assAtt = child.getAttribute("association");
                                // Se tiver atributos, coloca os atributos na
                                // lista de atributos da classe
                                myClasses.get(position).addAtributo(
                                        new Atributo(nameAtt, idAtt, assAtt,
                                        typeAtt));
                            }
                        }
                        if (children2 != null) {
                            for (int i = 0; i < children2.getLength(); i++) {

                                // Pega cada tag interna do tipo ownedOperation
                                Element child2 = (Element) children2.item(i);

                                if (child2 != null) {
                                    String operId = child2.getAttribute("xmi:id");
                                    String operName = child2.getAttribute("name");
                                    String operType = child2.getAttribute("type");

                                    // Faz uma lista das tags interna de uma tag
                                    // interna (ownedOperation e internamente
                                    // ownedParameter)
                                    NodeList childFromChild2_first = child2.getElementsByTagName("ownedParameter");
                                    // Faz uma lista das tags interna e uma tag
                                    // interna (ownedOperation e internamente
                                    // returnResult)
                                    NodeList childFromChild2_second = child2.getElementsByTagName("returnResult");

                                    if (childFromChild2_first != null) {
                                        for (int j = 0; j < childFromChild2_first.getLength(); j++) {

                                            // Pega cada tag interna do tipo
                                            // ownedOperation, ownedParameter
                                            Element childFromChild2_1 = (Element) childFromChild2_first.item(j);

                                            String idOperAtt = childFromChild2_1.getAttribute("xmi:id");
                                            String idOperName = childFromChild2_1.getAttribute("name");
                                            String idOperType = childFromChild2_1.getAttribute("type");
                                            String idOperDirection = childFromChild2_1.getAttribute("direction");

                                            myClasses.get(position).addOperation(
                                                    new Operacao(
                                                    operName,
                                                    operId,
                                                    operType,
                                                    idOperAtt,
                                                    idOperName,
                                                    idOperType,
                                                    idOperDirection));
                                        }
                                    } else if (childFromChild2_second != null) {
                                        for (int j = 0; j < childFromChild2_second.getLength(); j++) {

                                            // Pega cada tag interna do tipo
                                            // ownedOperation, returnResult
                                            Element childFromChild2_1 = (Element) childFromChild2_second.item(j);

                                            String idResultID = childFromChild2_1.getAttribute("xmi:id");
                                            String idResultType = childFromChild2_1.getAttribute("type");
                                            String idResultDirection = childFromChild2_1.getAttribute("direction");

                                            myClasses.get(position).addOperation(
                                                    new Operacao(
                                                    operId,
                                                    operName,
                                                    operType,
                                                    idResultID,
                                                    idResultType,
                                                    idResultDirection));
                                        }
                                    }
                                }

                            }
                        }
                        position++;
                    } else if (type.equalsIgnoreCase("uml:Enumeration")) {
                        myEnum = new EnumClass(name, id, type);
                        NodeList children = fstNode.getElementsByTagName("ownedLiteral");

                        if (children != null) {
                            for (int i = 0; i < children.getLength(); i++) {
                                Element child = (Element) children.item(i);

                                String idEnum = child.getAttribute("xmi:id");
                                String nameEnum = child.getAttribute("name");
                                Literal myLiteral = new Literal(idEnum,
                                        nameEnum);
                                myEnum.addLiteral(myLiteral);
                            }
                        }
                    } else if (type.equalsIgnoreCase("uml:AssociationClass")) {
                        myClasses.add(position, new Classe(name, id, type));
                        NodeList children = fstNode.getElementsByTagName("ownedAttribute");
                        if (children != null) {
                            for (int i = 0; i < children.getLength(); i++) {
                                // Pega cada tag interna do tipo ownedAttribute
                                Element child = (Element) children.item(i);

                                String idAtt = child.getAttribute("xmi:id");
                                String nameAtt = child.getAttribute("name");
                                String typeAtt = child.getAttribute("type");
                                String assAtt = child.getAttribute("association");
                                // Se tiver atributos, coloca os atributos na
                                // lista de atributos da classe
                                int posicaoAntiga = position - 1;
                                myClasses.get(posicaoAntiga).addAtributo(
                                        new Atributo(nameAtt, idAtt, assAtt,
                                        typeAtt));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
