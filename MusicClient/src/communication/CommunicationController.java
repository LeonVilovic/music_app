/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import Domain.Izvodjac;
import Domain.Kompozicija;
import Domain.Korisnik;
import Domain.Kupovina;
import Domain.Partitura;
import Domain.ZvucniZapis;
import Domain.OpstiDomenskiObjekat;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.Operation;

/**
 *
 * @author Leon
 */
public class CommunicationController {

    private Socket socket;
    private Korisnik loggedUser = null;
    private Object SavedData = null;

    public Object getSavedData() {
        return SavedData;
    }

    public void setSavedData(Object SavedData) {
        this.SavedData = SavedData;
    }
    private static CommunicationController instance;

    private CommunicationController() throws IOException {
        socket = new Socket("localhost", 9000);
    }

    /**
     *
     * @return @throws IOException
     */
    public static CommunicationController getInstance() throws IOException {
        if (instance == null) {
            instance = new CommunicationController();
        }
        return instance;
    }

    public Korisnik getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(Korisnik loggedUser) {
        this.loggedUser = loggedUser;
    }

    private void sendRequest(RequestObject request) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(request);
        out.flush();
    }

    private ResponseObject receiveResponse() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ResponseObject response = (ResponseObject) in.readObject();
        return response;
    }

    public Korisnik logIn(String username, String password) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_LOGIN);
        Korisnik korisnik = new Korisnik();
        korisnik.setUsername(username);
        korisnik.setPassword(password);
        request.setData(korisnik);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }

        return loggedUser = (Korisnik) response.getData();
    }
        public Korisnik getKorisnik(Korisnik korisnik) throws Exception {
         RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_GET_KORISNIK);
        request.setData(korisnik);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
        return (Korisnik) response.getData();
    }

    public void saveKorisnik(Korisnik korisnik) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_SAVE_KORISNIK);
        request.setData(korisnik);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void deleteKorisnik(Korisnik korisnik) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_DELETE_KORISNIK);
        request.setData(korisnik);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void updateKorisnik(Korisnik korisnik) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_UPDATE_KORISNIK);
        request.setData(korisnik);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void updateKompozicija(Kompozicija kompozicija) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_UPDATE_KOMPOZICIJA);
        request.setData(kompozicija);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Korisnik> searchKorisnik(Korisnik korisnik, String kriterijumPretrage) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_SEARCH_KORISNIK);
        Map<String, Object> data = new HashMap<>();
        data.put("korisnik", korisnik);
        data.put("kriterijumPretrage", kriterijumPretrage);
        request.setData(data);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
        return (List<Korisnik>) response.getData();
    }

    public List<OpstiDomenskiObjekat> searchKompozicija(Kompozicija kompozicija, String kriterijumPretrage) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_SEARCH_KOMPOZICIJA);
        Map<String, Object> data = new HashMap<>();
        data.put("kompozicija", kompozicija);
        data.put("kriterijumPretrage", kriterijumPretrage);
        request.setData(data);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
        return (List<OpstiDomenskiObjekat>) response.getData();
    }

    public void saveKompozicija(Kompozicija kompozicija, List<Partitura> partiture, List<ZvucniZapis> zvucniZapisi, List<Izvodjac> izvodjaci) throws Exception {
        RequestObject request = new RequestObject();
        Map<String, Object> data = new HashMap<>();
        data.put("kompozicija", kompozicija);
        data.put("partiture", partiture);
        data.put("zvucniZapisi", zvucniZapisi);
        data.put("izvodjaci", izvodjaci);
        request.setData(data);
        request.setOperation(Operation.OPERATION_SAVE_KOMPOZICIJA);

        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void deleteKompozicija(Kompozicija kompozicija) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_DELETE_KOMPOZICIJA);
        request.setData(kompozicija);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }
    
        public void kupiKompoziciju(Kupovina kupovina) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_BUY_KOMPOZICIJA);
        request.setData(kupovina);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
    }
    
    public File getAudioFile(ZvucniZapis zvucniZapis) throws Exception {
        RequestObject request = new RequestObject();
        request.setOperation(Operation.OPERATION_GET_AUDIO_ZVUCNIZAPIS);
        request.setData(zvucniZapis);
        sendRequest(request);
        ResponseObject response = receiveResponse();
        if (response.getException() != null) {
            throw response.getException();
        }
       return (File) response.getData();
    }
    
    
}
