package eu.birsan.calculatorcarburant;
/*
  Are update la gradle 7.0.4.
  Toate dependintele din build.gradle au fost actualizate.
  Ads functioneaza okay cu ajutorul unor modificari la build.gradle
  Codul a fost modificat automat la sugestiile propuse de Android Studio. A mutat variabila private AdView mAdView,
  a schimbat codul la ads, a eliminat din imports, etc.
*/

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    //definesc metoda pentru onclick la buton
    public void apasaButon(View view) {
        //definesc variabilele pentru casutele unde se introduc datele de catre utilizator
        EditText casutaConsum = findViewById(R.id.editTextConsum);
        EditText casutaDistanta = findViewById(R.id.editTextDistanta);
        EditText casutaPret = findViewById(R.id.editTextPretCarburant);
        EditText casutaPersoane = findViewById(R.id.editTextNrPasageri);
        //obtin datele de tip String de la variabilele create mai sus
        String casutaConsumString = casutaConsum.getText().toString();
        String casutaDistantaString = casutaDistanta.getText().toString();
        String casutaPretString = casutaPret.getText().toString();
        String casutaPersoaneString = casutaPersoane.getText().toString();
        //folosesc if pentru a verifica daca sunt introduse date in stringurile de mai sus
        if (casutaConsumString.matches("") || casutaDistantaString.matches("") || casutaPretString.matches("") || casutaPersoaneString.matches("")) {
            String mesajAvertizare = getString(R.string.mesaj_alerta_toast); //string folosit pentru traducere, incorporta in functia toast de mai jos
            Toast.makeText(this, mesajAvertizare, Toast.LENGTH_SHORT).show();
            return;
        }
        //transform variabilele String de mai sus in double pentru a le putea aduna sau imparti
        double casutaConsumInt = Double.parseDouble(casutaConsumString);
        double casutaDistantaInt = Double.parseDouble(casutaDistantaString);
        double casutaPretInt = Double.parseDouble(casutaPretString);
        double casutaPersoaneInt = Double.parseDouble(casutaPersoaneString);
        //se fac calculele matematice cu variabile de tip double
        double litriCarburant = (casutaConsumInt * casutaDistantaInt) / 100;
        double pretCarburant = litriCarburant * casutaPretInt;
        double pretPerPersoana = pretCarburant / casutaPersoaneInt;
        //String pentru traduceri:
        String veiConsuma = getString(R.string.veti_consuma);
        String litriCarburantMesajTradus = getString(R.string.litri_carburant);
        String aceastaCalatorie = getString(R.string.aceasta_calatorie);
        String dePersoana = getString(R.string.lei_persoana);
        //metoda pentru a afla moneda utilizatorului:
        //se afiseaza rezultatul printr-o metoda. De asemenea am folosit String.format("%.2f", variabilaDouble) pentru a rotunji la 2 zecimale
        String rezultat = veiConsuma + " " + litriCarburant + " " + litriCarburantMesajTradus + "\n" + aceastaCalatorie + " " + String.format("%.2f", pretPerPersoana) + " " + dePersoana;
        afisareRezultat(rezultat);

    }

    //definesc metoda care afiseaza rezultatul in casuta de rezultat
    public void afisareRezultat(String rez) {
        TextView casutaAfisareRezultat = findViewById(R.id.textViewAfisareRezultat);
        casutaAfisareRezultat.setText(rez);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Cod Ads
        MobileAds.initialize(this, initializationStatus -> {
        });

        // Cod AdMob
        AdView mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}