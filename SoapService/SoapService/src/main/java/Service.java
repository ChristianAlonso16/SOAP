import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.ws.Endpoint;

import java.security.SecureRandom;
import java.util.Random;
@WebService(name = "Service", targetNamespace = "utez")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class Service {
    @WebMethod(operationName = "responseMessage")
    public String responseMessage(@WebParam(name = "message") String message) {
        return "El mensaje recibido fue " + message;
    }
    @WebMethod(operationName = "adivinarNumero")
    public String responseMessage1(@WebParam(name = "message") int numero) {
        Random numeroRandom = new Random();
        int num = numeroRandom.nextInt(15);

        System.out.println(numero);

        if (num == numero) {
            return "Le atinaste!";

        }else {
            return "Intenta de nuevo";
        }
    }
    @WebMethod(operationName = "consonantes")
    public String responseMessage2(@WebParam(name = "message") String frase) {

        return frase.replaceAll("[aeiou]", "");
    }

    @WebMethod(operationName = "rfc")
    public String responseMessage3(@WebParam(name = "nombre") String nombre, @WebParam(name = "apellidoP") String apellidoP,
                                   @WebParam(name = "apellidoM") String apellidoM, @WebParam(name = "fechaNac") String fechaNac) {
        String letraNombre = nombre.substring(0,1);
        String letraApellido1 = apellidoP.substring(0,2);
        String letraApellido2 = apellidoM.substring(0,1);
        String numAño = fechaNac.substring(8,10);
        String numMes = fechaNac.substring(3,5);
        String numDia = fechaNac.substring(0,2);
        String rfcT = letraApellido1+letraApellido2+letraNombre+numAño+numMes+numDia;

        return rfcT.toUpperCase();
    }
    public static void main(String[] args) {
        System.out.println("Initializing service...");
        Endpoint.publish("http://localhost:8080/Service", new Service());
        System.out.println("Waiting requests...");
    }


}
