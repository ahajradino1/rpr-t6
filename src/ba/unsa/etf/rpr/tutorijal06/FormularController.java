package ba.unsa.etf.rpr.tutorijal06;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import java.time.YearMonth;


import static java.time.LocalDate.now;

public class FormularController {

    public TextField imeField, prezimeField, indeksField, emailField, telefonField, adresaField, jmbgField;
    public ComboBox<String> mjestoRodjenja;
    public DatePicker datum;
    public ChoiceBox<String> odjsek, godina, ciklus, status, borackaKateg;
    private boolean imeValidno, prezimeValidno, indeksValidan, jmbgValidan, datumValidan, brojValidan, emailValidan;

    public boolean formularValidan() {
        return imeValidno && prezimeValidno && indeksValidan && jmbgValidan && datumValidan && brojValidan && emailValidan;
    }

    private boolean validnoImePrezime(String s) {
        for(int i = 0; i < s.length(); i++)
            if(Character.toLowerCase(s.charAt(i)) < 'a' || Character.toLowerCase(s.charAt(i)) > 'z')
                return false;
        return s.length() <= 20 && !s.isEmpty();
    }

    private boolean isIndeksValidan(String s) {
        if(s.length() != 5) return false;
        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) < '0' || s.charAt(i) > '9') return false;
        return !s.trim().isEmpty();
    }
    private boolean isJmbgValidan(String s) {
        if(s.length() != 13) return false;
        int dd = Integer.parseInt( s.substring(0,2) );
        int mm = Integer.parseInt( s.substring(2,4) );
        int ggg = Integer.parseInt( s.substring(4,7) );

        if( dd < 1 || dd > YearMonth.of(ggg, mm).lengthOfMonth() )
            return false;

        if(mm>12 || mm<0)
            return false;

        if(ggg>18 && ggg<0)
            return false;
        int k = Integer.parseInt(s.substring(12, 13));
        int l = 11 - ((7*(Integer.parseInt(jmbgField.getText(0,1)) + Integer.parseInt(jmbgField.getText(6,7))) +
                6*(Integer.parseInt(jmbgField.getText(1,2)) + Integer.parseInt(jmbgField.getText(7,8))) +
                5*(Integer.parseInt(jmbgField.getText(2,3)) + Integer.parseInt(jmbgField.getText(8,9))) +
                4*(Integer.parseInt(jmbgField.getText(3,4)) + Integer.parseInt(jmbgField.getText(9,10))) +
                3*(Integer.parseInt(jmbgField.getText(4,5)) + Integer.parseInt(jmbgField.getText(10,11))) +
                2*(Integer.parseInt(jmbgField.getText(5,6)) + Integer.parseInt(jmbgField.getText(11,12)))) % 11);
        if(l>=1 && l<=9 && l!=k)
            return false;
        if(l>9 && k!=0)
            return false;
        return true;
    }

    public boolean isDatumValidan(DatePicker d) {
        return d.getValue().isAfter(now());
    }

    public boolean isBrojValidan(String s) {
        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) < '0' || s.charAt(i) > '9') return false; //+
        return true;
    }

    public boolean isEmailValidan(String s) {
        if(s.contains("@") && (s.indexOf("@") == 0 || s.indexOf("@") == s.length()-1)) return false;
        int brojac = 0;
        for(int i = 0; i < s.length(); i++)
            if(s.charAt(i) == '@') brojac++;
        return brojac > 0;
    }

    @FXML
    public void initialize() {
        imeValidno = false;
        prezimeValidno = false;
        indeksValidan = false;
        datumValidan = false;
        emailValidan = false;
        brojValidan = false;
        jmbgValidan = false;
        imeField.getStyleClass().add("poljeNijeIspravno");
        imeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(validnoImePrezime(t1)) {
                    imeField.getStyleClass().removeAll("poljeNijeIspravno");
                    imeField.getStyleClass().add("poljeIspravno");
                    imeValidno = true;
                } else {
                    imeField.getStyleClass().removeAll("poljeIspravno");
                    imeField.getStyleClass().add("poljeNijeIspravno");
                    imeValidno = false;
                }
            }
        });
        prezimeField.getStyleClass().add("poljeNijeIspravno");
        prezimeField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(validnoImePrezime(t1)) {
                    prezimeField.getStyleClass().removeAll("poljeNijeIspravno");
                    prezimeField.getStyleClass().add("poljeIspravno");
                    prezimeValidno = true;
                } else {
                    prezimeField.getStyleClass().removeAll("poljeIspravno");
                    prezimeField.getStyleClass().add("poljeNijeIspravno");
                    prezimeValidno = false;
                }
            }
        });
        indeksField.getStyleClass().add("poljeNijeIspravno");
        indeksField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(isIndeksValidan(t1)) {
                    indeksField.getStyleClass().removeAll("poljeNijeIspravno");
                    indeksField.getStyleClass().add("poljeIspravno");
                    indeksValidan = true;
                } else {
                    indeksField.getStyleClass().removeAll("poljeIspravno");
                    indeksField.getStyleClass().add("poljeNijeIspravno");
                    indeksValidan = false;
                }
            }
        });
        emailField.getStyleClass().add("poljeNijeIspravno");
        emailField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(isEmailValidan(t1)) {
                    emailField.getStyleClass().removeAll("poljeNijeIspravno");
                    emailField.getStyleClass().add("poljeIspravno");
                    emailValidan = true;
                } else {
                    emailField.getStyleClass().removeAll("poljeIspravno");
                    emailField.getStyleClass().add("poljeNijeIspravno");
                    emailValidan = false;
                }
            }
        });
        telefonField.getStyleClass().add("poljeNijeIspravno");
        telefonField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(isBrojValidan(t1)) {
                    telefonField.getStyleClass().removeAll("poljeNijeIspravno");
                    telefonField.getStyleClass().add("poljeIspravno");
                    brojValidan = true;
                } else {
                    telefonField.getStyleClass().removeAll("poljeIspravno");
                    telefonField.getStyleClass().add("poljeNijeIspravno");
                    brojValidan = false;
                }
            }
        });
        jmbgField.getStyleClass().add("poljeNijeIspravno");
        jmbgField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                if(isJmbgValidan(t1)) {
                    jmbgField.getStyleClass().removeAll("poljeNijeIspravno");
                    jmbgField.getStyleClass().add("poljeIspravno");
                    jmbgValidan = true;
                } else {
                    jmbgField.getStyleClass().removeAll("poljeIspravno");
                    jmbgField.getStyleClass().add("poljeNijeIspravno");
                    jmbgValidan = false;
                }
            }
        });
    }
    public void ispisi() {
        System.out.println("Ime i prezime:" + imeField.getText() + prezimeField.getText());
        System.out.println("Broj indeksa:" + indeksField.getText());
        System.out.println("Jmbg:" + jmbgField.getText());
        System.out.println("Datum rodjenja:" + datum.getValue());
        System.out.println("Mjesto rodjenja:" + mjestoRodjenja.getSelectionModel().getSelectedItem());
        System.out.println("Adresa:" + adresaField.getText());
        System.out.println("Kontakt telefon:" + telefonField.getText());
        System.out.println("Email adresa:" + emailField.getText());
        System.out.println("Odsjek:" + odjsek.getSelectionModel().getSelectedItem());
        System.out.println("Ciklus:" + ciklus.getSelectionModel().getSelectedItem());
        System.out.println("Status:" + status.getSelectionModel().getSelectedItem());
        System.out.println("Pripadnost borackoj kategoriji:" + borackaKateg.getSelectionModel().getSelectedItem());
    }
    public void potvrdiButton(ActionEvent actionEvent) {
        ispisi();
    }
}
