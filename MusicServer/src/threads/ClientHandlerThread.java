/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import Domain.Kompozicija;
import Domain.Korisnik;
import Domain.Kupovina;
import Domain.ZvucniZapis;
import controller.Controller;
import Domain.OpstiDomenskiObjekat;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import transfer.RequestObject;
import transfer.ResponseObject;
import util.Operation;

/**
 *
 * @author student1
 */
public class ClientHandlerThread extends Thread {

    private Socket socket;
    private Korisnik loggedUser;

    public ClientHandlerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            try {
                RequestObject request = receiveRequest();
                ResponseObject response = null;
                switch (request.getOperation()) {
                    case Operation.OPERATION_LOGIN:
                        response = logIn(request);
                        break;
                    case Operation.OPERATION_SAVE_KORISNIK:
                        response = saveKorisnik(request);
                        break;
                    case Operation.OPERATION_SEARCH_KORISNIK:
                        response = searchKorisnik(request);
                        break;
                    case Operation.OPERATION_DELETE_KORISNIK:
                        response = deleteKorisnik(request);
                        break;
                    case Operation.OPERATION_UPDATE_KORISNIK:
                        response = updateKorisnik(request);
                        break;
                        case Operation.OPERATION_GET_KORISNIK:
                        response = getKorisnik(request);
                        break;
                    case Operation.OPERATION_SAVE_KOMPOZICIJA:
                        response = saveKompozicija(request);
                        break;
                    case Operation.OPERATION_SEARCH_KOMPOZICIJA:
                        response = searchKompozicija(request);
                        break;
                    case Operation.OPERATION_DELETE_KOMPOZICIJA:
                        response = deleteKompozicjia(request);
                        break;
                    case Operation.OPERATION_UPDATE_KOMPOZICIJA:
                        response = updateKompozicija(request);
                        break;
                    case Operation.OPERATION_GET_AUDIO_ZVUCNIZAPIS:
                        response = getAudioFile(request);
                        break;
                    case Operation.OPERATION_BUY_KOMPOZICIJA:
                        response = buyKompozicija(request);
                        break;
                }
                sendResponse(response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private ResponseObject logIn(RequestObject request) {
        Korisnik korisnik = (Korisnik) request.getData();
        ResponseObject response = new ResponseObject();
        try {
            Korisnik user = Controller.getInstance().logIn(korisnik);
            loggedUser = user;
            response.setData(user);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }
    
        private ResponseObject getKorisnik(RequestObject request) {
        Korisnik korisnik = (Korisnik) request.getData();
        ResponseObject response = new ResponseObject();
        try {
            Korisnik korisnik2 = Controller.getInstance().getKorisnik(korisnik);
            loggedUser = korisnik2;
            response.setData(korisnik2);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject saveKorisnik(RequestObject request) {
        Korisnik korisnik = (Korisnik) request.getData();
        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().saveKorisnik(korisnik);
            //   response.setData(korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;

    }

    private ResponseObject saveKompozicija(RequestObject request) {
        Map<String, Object> data = (Map<String, Object>) request.getData();
        Kompozicija kompozicija = (Kompozicija) data.get("kompozicija");
        List<OpstiDomenskiObjekat> zvucniZapisi = (List<OpstiDomenskiObjekat>) data.get("zvucniZapisi");
        List<OpstiDomenskiObjekat> izvodjaci = (List<OpstiDomenskiObjekat>) data.get("izvodjaci");
        List<OpstiDomenskiObjekat> partiture = (List<OpstiDomenskiObjekat>) data.get("partiture");

        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().saveKompozicija(kompozicija, zvucniZapisi, izvodjaci, partiture);

        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;

    }

    private ResponseObject searchKorisnik(RequestObject request) {
        Map<String, Object> data = (Map<String, Object>) request.getData();
        Korisnik korisnik = (Korisnik) data.get("korisnik");
        String kriterijumPretrage = (String) data.get("kriterijumPretrage");

        ResponseObject response = new ResponseObject();
        try {
            List<Korisnik> listaKorisnika = Controller.getInstance().searchKorisnik(korisnik, kriterijumPretrage);
            if (listaKorisnika.isEmpty()) {
                throw new Exception("Sitem ne moze da nadje korisnike po zadatoj vrednosti");
            }
            response.setData(listaKorisnika);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject searchKompozicija(RequestObject request) {
        Map<String, Object> data = (Map<String, Object>) request.getData();
        Kompozicija kompozicija = (Kompozicija) data.get("kompozicija");
        String kriterijumPretrage = (String) data.get("kriterijumPretrage");

        ResponseObject response = new ResponseObject();
        try {
            List<OpstiDomenskiObjekat> listaKompozicija = Controller.getInstance().searchKompozicija(kompozicija, kriterijumPretrage);
            if (listaKompozicija.isEmpty()) {
                throw new Exception("Sitem ne moze da nadje kompozicije po zadatoj vrednosti");
            }
            response.setData(listaKompozicija);

        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject deleteKorisnik(RequestObject request) {
        Korisnik korisnik = (Korisnik) request.getData();

        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().deleteKorisnik(korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject deleteKompozicjia(RequestObject request) {
        Kompozicija komppozicija = (Kompozicija) request.getData();

        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().deleteKompozicija(komppozicija);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject updateKorisnik(RequestObject request) {
        Korisnik korisnik = (Korisnik) request.getData();

        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().updateKorisnik(korisnik);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject updateKompozicija(RequestObject request) {
        Kompozicija kompozicija = (Kompozicija) request.getData();

        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().updateKompozicija(kompozicija);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject getAudioFile(RequestObject request) {
        ZvucniZapis zvucniZapis = (ZvucniZapis) request.getData();
        ResponseObject response = new ResponseObject();
        try {

            response.setData(Controller.getInstance().getFileZvucniZapis(zvucniZapis));
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;
    }

    private ResponseObject buyKompozicija(RequestObject request) {
        Kupovina kupovina = (Kupovina) request.getData();
        ResponseObject response = new ResponseObject();
        try {
            Controller.getInstance().kupiKompoziciju(kupovina);
        } catch (Exception e) {
            e.printStackTrace();
            response.setException(e);
        }
        return response;

    }

    private RequestObject receiveRequest() throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        return (RequestObject) in.readObject();
    }

    private void sendResponse(ResponseObject response) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(response);
        out.flush();
    }

    public void stopClientHandler() throws IOException {
        socket.close();
    }

    public Korisnik getLoggedUser() {
        return loggedUser;
    }

}
