/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import Domain.Izvodjac;
import Domain.Kompozicija;
import Domain.Korisnik;
import Domain.Kupovina;
import Domain.Partitura;
import Domain.ZvucniZapis;
import Domain.OpstiDomenskiObjekat;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leon
 */
public class DatabaseBroker {

    Connection connection;

    public DatabaseBroker() {
    }

    public void connect() throws Exception {
        try {
            FileInputStream in = new FileInputStream("src\\dbsettings.properties");
            Properties props = new Properties();
            props.load(in);
            String driver = props.getProperty("driver");
            String url = props.getProperty("url");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("Database Broker: succesfull connection!");
            in.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Database Broker: connection error!\n" + ex.getMessage());
        }
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new Exception("Greska prilikom raskidanja konekcije: " + ex.getMessage());
            }
        }
    }

    public void commit() throws Exception {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException ex) {
                throw new Exception("Greska prilikom potvrdjivanja transakcije: " + ex.getMessage());
            }
        }
    }

    public void rollback() throws Exception {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new Exception("Greska prilikom ponistavanja transakcije: " + ex.getMessage());
            }
        }
    }

    //  Primarni kljuc u opstem domenskom objektu mora biti tipa long
    public OpstiDomenskiObjekat insert(OpstiDomenskiObjekat o) throws SQLException {
        try {
            String upit = "INSERT INTO " + o.dajNazivTabele() + " (" + o.dajNaziveAtributa() + ") VALUES (" + o.dajVrednostiAtributa() + ")";
            System.out.println(upit);
            Statement statement = connection.createStatement();

            statement.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
            if (o.isAutoincrement()) {
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    o.setId(id);
                }
                rs.close();
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return o;
    }

    public OpstiDomenskiObjekat delete(OpstiDomenskiObjekat o) throws SQLException {
        String upit = "DELETE FROM " + o.dajNazivTabele() + " WHERE (" + o.dajNazivPrimarnogKljuca() + " = " + o.getID() + ")";
        System.out.println(upit);
        Statement statement = connection.createStatement();

        statement.executeUpdate(upit);

        return o;
    }

    public OpstiDomenskiObjekat update(OpstiDomenskiObjekat o) throws SQLException {
        String upit = "UPDATE " + o.dajNazivTabele() + " SET " + o.postaviVrednostiAtributa() + " WHERE (" + o.dajNazivPrimarnogKljuca() + "=" + o.getID() + ")";
        System.out.println(upit);
        Statement statement = connection.createStatement();

        statement.executeUpdate(upit);

        return o;
    }

    public List<OpstiDomenskiObjekat> classConverter(ResultSet rs) throws SQLException {
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        ResultSetMetaData meta = rs.getMetaData();
        if ("KorisnikID".equals(meta.getColumnName(1)) && "Ime".equals(meta.getColumnName(2)) && "Prezime".equals(meta.getColumnName(3)) && "ClanOd".equals(meta.getColumnName(4)) && "Username".equals(meta.getColumnName(5))) {
            while (rs.next()) {
                Date d = rs.getDate(4);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(d);
                Korisnik k = new Korisnik(rs.getLong(1), rs.getString(2), rs.getString(3), gregorianCalendar, rs.getString(5), rs.getString(6), rs.getString(7));
                lista.add(k);
            }
        }
        if ("IzvodjacID".equals(meta.getColumnName(1)) && "Naziv".equals(meta.getColumnName(2))) {
            while (rs.next()) {
                Izvodjac i = new Izvodjac(rs.getLong(1), rs.getString(2));
                lista.add(i);
            }
        }
        if ("ZvucniZapisID".equals(meta.getColumnName(1)) && "Duzina".equals(meta.getColumnName(2)) && "PutDoFajla".equals(meta.getColumnName(3)) && "Format".equals(meta.getColumnName(4))) {
            while (rs.next()) {
                Izvodjac iz = (Izvodjac) getODOUsingPK(new Izvodjac(), rs.getLong(8));
                Kompozicija k = (Kompozicija) getODOUsingPK(new Kompozicija(), rs.getLong(7));
                ZvucniZapis z = new ZvucniZapis(rs.getLong(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getString(6), k, iz);
                lista.add(z);
            }
        }
        if ("PartituraID".equals(meta.getColumnName(1)) && "PutDoFajla".equals(meta.getColumnName(2)) && "Format".equals(meta.getColumnName(3))) {
            while (rs.next()) {
                Kompozicija k = (Kompozicija) getODOUsingPK(new Kompozicija(), rs.getLong(6));
                Partitura p = new Partitura(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getString(5), k);
                lista.add(p);
            }
        }

        if ("KompozicijaID".equals(meta.getColumnName(1)) && "Naziv".equals(meta.getColumnName(2)) && "UBaziOd".equals(meta.getColumnName(3)) && "KorisnikID".equals(meta.getColumnName(4))) {
            while (rs.next()) {
                Korisnik k = (Korisnik) getODOUsingPK(new Korisnik(), rs.getLong(4));
                Date d = rs.getDate(3);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(d);
                Kompozicija komp = new Kompozicija(rs.getLong(1), rs.getString(2), gregorianCalendar, k);
                lista.add(komp);
            }
        }
        if ("KorisnikID".equals(meta.getColumnName(1)) && "KompozicijaID".equals(meta.getColumnName(2)) && "DatumVremeKupovine".equals(meta.getColumnName(3)) && "Cena".equals(meta.getColumnName(4)) && "Valuta".equals(meta.getColumnName(5)) && "Popust".equals(meta.getColumnName(6))) {
            while (rs.next()) {
                Korisnik k = (Korisnik) getODOUsingPK(new Korisnik(), rs.getLong(1));
                Kompozicija komp = (Kompozicija) getODOUsingPK(new Kompozicija(), rs.getLong(2));
                Date d = rs.getDate(3);
                GregorianCalendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(d);
                Kupovina kup = new Kupovina(k, komp, gregorianCalendar, rs.getDouble(4), rs.getString(5), rs.getDouble(6));
                lista.add(kup);
            }
        }
        return lista;
    }

    public OpstiDomenskiObjekat getODOUsingPK(OpstiDomenskiObjekat o, Long primaryKey) throws SQLException {
        String upit = "SELECT * FROM " + o.dajNazivTabele() + " WHERE (" + o.dajNazivPrimarnogKljuca() + "= " + primaryKey + ")";
        System.out.println(upit);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        List<OpstiDomenskiObjekat> lista;
        lista = classConverter(rs);
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public OpstiDomenskiObjekat getODOUsingComplexkey(OpstiDomenskiObjekat o, List<Long> complexKey) throws SQLException {

        String upit = "SELECT * FROM " + o.dajNazivTabele() + " WHERE";
        for (int i = 0; i < o.dajKompleksniKljuc().size(); i++) {
            upit = upit + " (" + o.dajKompleksniKljuc().get(i) + "= " + complexKey.get(i) + ")";
            if (i + 1 < o.dajKompleksniKljuc().size()) {
                upit = upit + " AND";
            }
        }
        System.out.println(upit);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        List<OpstiDomenskiObjekat> lista;
        lista = classConverter(rs);
        if (lista.isEmpty()) {
            return null;
        } else {
            return lista.get(0);
        }
    }

    public List<OpstiDomenskiObjekat> search(OpstiDomenskiObjekat o, String kriterijumPretrage) throws SQLException {
        List<String> listaAtributaZaPretragu = o.dajAributeZaPretragu();
        String upit = "SELECT * FROM " + o.dajNazivTabele() + " WHERE";
        for (int i = 0; i < listaAtributaZaPretragu.size(); i++) {
            upit = upit + " (" + listaAtributaZaPretragu.get(i) + " LIKE '" + kriterijumPretrage + "%')";
            if (i + 1 < listaAtributaZaPretragu.size()) {
                upit = upit + " OR";
            }
        }
        System.out.println(upit);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        return classConverter(rs);
    }

    /**
     * opsti domenski objekti se vracaju sledecin redosledom: Kompozicija,
     * Partitura, ZvucniZapis, Izvodjac, Korisnik
     */
    public List<OpstiDomenskiObjekat> searchKompozicijaComplex(OpstiDomenskiObjekat o, String kriterijumPretrage) throws SQLException {
        String upit = "SELECT * FROM kompozicija k LEFT JOIN partitura p ON k.KompozicijaID = p.KompozicijaID LEFT JOIN zvucni_zapis z ON k.KompozicijaID = z.KompozicijaID LEFT JOIN korisnik ko ON ko.KorisnikID = k.KorisnikID WHERE (k.Naziv like '" + kriterijumPretrage + "%');";
        System.out.println(upit);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(upit);
        List<OpstiDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            Date d = rs.getDate(22);
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(d);
            Korisnik k = new Korisnik(rs.getLong(19), rs.getString(20), rs.getString(21), gregorianCalendar, rs.getString(23), rs.getString(24), rs.getString(25));
            d = rs.getDate(3);
            gregorianCalendar.setTime(d);
            Kompozicija kom = new Kompozicija(rs.getLong(1), rs.getString(2), gregorianCalendar, k);
            Partitura p;
            if (rs.getLong(5) != 0) {
                p = new Partitura(rs.getLong(5), rs.getString(6), rs.getString(7), rs.getDouble(8), rs.getString(9), kom);
            } else {
                p = new Partitura();
            }
            Izvodjac iz;
            if (rs.getLong(18) != 0) {
                iz = (Izvodjac) getODOUsingPK(new Izvodjac(), rs.getLong(18));
            } else {
                iz = new Izvodjac();
            }
            ZvucniZapis z;
            if (rs.getLong(11) != 0) {
                z = new ZvucniZapis(rs.getLong(11), rs.getInt(12), rs.getString(13), rs.getString(14), rs.getDouble(15), rs.getString(16), kom, iz);
            } else {
                z = new ZvucniZapis();
            }
            lista.add(kom);
            lista.add(p);
            lista.add(z);
            lista.add(iz);
            lista.add(k);
        }
        return lista;
    }

    public OpstiDomenskiObjekat logIn(OpstiDomenskiObjekat o) throws Exception {
        Korisnik korisnikZaLogovanje = (Korisnik) o;
        String username = korisnikZaLogovanje.getUsername();
        String password = korisnikZaLogovanje.getPassword();
        Korisnik k = new Korisnik();
        List<OpstiDomenskiObjekat> users = search(k, "");
        for (OpstiDomenskiObjekat user : users) {
            Korisnik korisnik = (Korisnik) user;
            if (korisnik.getUsername().equalsIgnoreCase(username)) {
                if (korisnik.getPassword().equals(password)) {
                    return korisnik;

                } else {
                    throw new Exception("Lozinka nije odgovarajuća!");
                }
            }
        }
        throw new Exception("Korisnik nije registrovan!");
    }
}
